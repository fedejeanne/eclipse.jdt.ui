/*******************************************************************************
 * Copyright (c) 2021, 2025 Fabrice TIERCELIN and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Fabrice TIERCELIN - initial API and implementation
 *******************************************************************************/
package org.eclipse.jdt.internal.corext.fix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.CoreException;

import org.eclipse.text.edits.TextEditGroup;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ThisExpression;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.TargetSourceRangeComputer;

import org.eclipse.jdt.internal.corext.dom.ASTNodes;
import org.eclipse.jdt.internal.corext.dom.OrderedInfixExpression;
import org.eclipse.jdt.internal.corext.refactoring.structure.CompilationUnitRewrite;

import org.eclipse.jdt.ui.cleanup.ICleanUpFix;

import org.eclipse.jdt.internal.ui.fix.MultiFixMessages;

public class StandardComparisonFixCore extends CompilationUnitRewriteOperationsFixCore {
	public static final class StandardComparisonFinder extends ASTVisitor {
		private List<StandardComparisonFixOperation> fResult;

		public StandardComparisonFinder(List<StandardComparisonFixOperation> ops) {
			fResult= ops;
		}

		@Override
		public boolean visit(final InfixExpression visited) {
			OrderedInfixExpression<MethodInvocation, Expression> orderedCondition= ASTNodes.orderedInfix(visited, MethodInvocation.class, Expression.class);

			if (orderedCondition != null
					&& Arrays.asList(InfixExpression.Operator.EQUALS, InfixExpression.Operator.NOT_EQUALS).contains(orderedCondition.getOperator())) {
				MethodInvocation comparisonMI= orderedCondition.getFirstOperand();
				if (comparisonMI != null) {
					IMethodBinding comparisonMethodBinding= comparisonMI.resolveMethodBinding();
					if (comparisonMethodBinding != null) {
						IMethodBinding methodDeclaration= comparisonMethodBinding.getMethodDeclaration();
						ITypeBinding declaringClass= methodDeclaration.getDeclaringClass();
						if (declaringClass != null) {
							boolean knownComparison= false;
							if (comparisonMethodBinding.getName().equals("compareTo") && isFromClass(declaringClass, "java.lang.Comparable") //$NON-NLS-1$ //$NON-NLS-2$
									|| comparisonMethodBinding.getName().equals("compareToIgnoreCase") && isFromClass(declaringClass, "java.lang.String") //$NON-NLS-1$ //$NON-NLS-2$
									|| comparisonMethodBinding.getName().equals("compare") && isFromClass(declaringClass, "java.util.Comparator")) { //$NON-NLS-1$ //$NON-NLS-2$
								knownComparison= true;
							}
							if (knownComparison) {
								Long literalValue= ASTNodes.getIntegerLiteral(orderedCondition.getSecondOperand());

								if (literalValue != null
										&& literalValue.compareTo(0L) != 0
										&& comparisonMI.getExpression() != null
										&& !ASTNodes.is(comparisonMI.getExpression(), ThisExpression.class)) {
									if (literalValue.compareTo(0L) < 0) {
										if (InfixExpression.Operator.EQUALS.equals(orderedCondition.getOperator())) {
											fResult.add(new StandardComparisonFixOperation(visited, comparisonMI, InfixExpression.Operator.LESS));
										} else {
											fResult.add(new StandardComparisonFixOperation(visited, comparisonMI, InfixExpression.Operator.GREATER_EQUALS));
										}
									} else if (InfixExpression.Operator.EQUALS.equals(orderedCondition.getOperator())) {
										fResult.add(new StandardComparisonFixOperation(visited, comparisonMI, InfixExpression.Operator.GREATER));
									} else {
										fResult.add(new StandardComparisonFixOperation(visited, comparisonMI, InfixExpression.Operator.LESS_EQUALS));
									}

									return false;
								}
							}
						}
					}
				}
			}

			return true;
		}

		private boolean isFromClass(ITypeBinding declaringClass, String name) {
			if (declaringClass == null) {
				return false;
			}
			if (declaringClass.getErasure().getQualifiedName().equals(name)) {
				return true;
			}
			ITypeBinding[] interfaces= declaringClass.getInterfaces();
			for (ITypeBinding anInterface : interfaces) {
				if (isFromClass(anInterface, name)) {
					return true;
				}
			}
			ITypeBinding superClass= declaringClass.getSuperclass();
			return isFromClass(superClass, name);
		}
	}

	public static class StandardComparisonFixOperation extends CompilationUnitRewriteOperation {
		private final InfixExpression visited;
		private final MethodInvocation comparisonMethod;
		private final InfixExpression.Operator operator;

		public StandardComparisonFixOperation(final InfixExpression visited, final MethodInvocation comparisonMethod, final InfixExpression.Operator operator) {
			this.visited= visited;
			this.comparisonMethod= comparisonMethod;
			this.operator= operator;
		}

		@Override
		public void rewriteAST(final CompilationUnitRewrite cuRewrite, final LinkedProposalModelCore linkedModel) throws CoreException {
			ASTRewrite rewrite= cuRewrite.getASTRewrite();
			AST ast= cuRewrite.getRoot().getAST();
			TextEditGroup group= createTextEditGroup(MultiFixMessages.StandardComparisonCleanUp_description, cuRewrite);
			rewrite.setTargetSourceRangeComputer(new TargetSourceRangeComputer() {
				@Override
				public SourceRange computeSourceRange(final ASTNode nodeWithComment) {
					if (Boolean.TRUE.equals(nodeWithComment.getProperty(ASTNodes.UNTOUCH_COMMENT))) {
						return new SourceRange(nodeWithComment.getStartPosition(), nodeWithComment.getLength());
					}

					return super.computeSourceRange(nodeWithComment);
				}
			});

			InfixExpression newInfixExpression= ast.newInfixExpression();
			newInfixExpression.setLeftOperand(ASTNodes.createMoveTarget(rewrite, comparisonMethod));
			newInfixExpression.setOperator(operator);
			newInfixExpression.setRightOperand(ast.newNumberLiteral("0")); //$NON-NLS-1$

			ASTNodes.replaceButKeepComment(rewrite, visited, newInfixExpression, group);
		}
	}


	public static ICleanUpFix createCleanUp(final CompilationUnit compilationUnit) {
		List<StandardComparisonFixOperation> operations= new ArrayList<>();
		StandardComparisonFinder finder= new StandardComparisonFinder(operations);
		compilationUnit.accept(finder);

		if (operations.isEmpty()) {
			return null;
		}

		CompilationUnitRewriteOperationsFixCore.CompilationUnitRewriteOperation[] ops= operations.toArray(new CompilationUnitRewriteOperationsFixCore.CompilationUnitRewriteOperation[0]);
		return new StandardComparisonFixCore(FixMessages.StandardComparisonFix_compare_to_zero, compilationUnit, ops);
	}

	protected StandardComparisonFixCore(final String name, final CompilationUnit compilationUnit, final CompilationUnitRewriteOperationsFixCore.CompilationUnitRewriteOperation[] fixRewriteOperations) {
		super(name, compilationUnit, fixRewriteOperations);
	}
}
