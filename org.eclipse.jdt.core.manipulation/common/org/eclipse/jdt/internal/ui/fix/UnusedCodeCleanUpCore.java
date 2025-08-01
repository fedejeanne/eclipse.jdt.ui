/*******************************************************************************
 * Copyright (c) 2019, 2024 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Red Hat Inc. - copied and modified f
 *******************************************************************************/
package org.eclipse.jdt.internal.ui.fix;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.SimpleName;

import org.eclipse.jdt.internal.corext.fix.CleanUpConstants;
import org.eclipse.jdt.internal.corext.fix.RenameUnusedVariableFixCore;
import org.eclipse.jdt.internal.corext.fix.UnusedCodeFixCore;

import org.eclipse.jdt.ui.cleanup.CleanUpRequirements;
import org.eclipse.jdt.ui.cleanup.ICleanUpFix;
import org.eclipse.jdt.ui.text.java.IProblemLocation;

import org.eclipse.jdt.internal.ui.text.correction.ProblemLocation;

/**
 * Create fixes which can remove unused code
 * see org.eclipse.jdt.internal.corext.fix.UnusedCodeFix
 */
public class UnusedCodeCleanUpCore extends AbstractMultiFix {

	public UnusedCodeCleanUpCore(Map<String, String> options) {
		super(options);
	}

	public UnusedCodeCleanUpCore() {
		super();
	}

	@Override
	public CleanUpRequirements getRequirements() {
		boolean requireAST= requireAST();
		Map<String, String> requiredOptions= requireAST ? getRequiredOptions() : null;
		return new CleanUpRequirements(requireAST, requireAST, false, requiredOptions);
	}

	private boolean requireAST() {
		boolean removeUnuseMembers= isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_MEMBERS);

		return removeUnuseMembers && isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_METHODS) ||
				removeUnuseMembers && isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_CONSTRUCTORS) ||
				removeUnuseMembers && isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_FELDS) ||
				removeUnuseMembers && isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_TYPES) ||
				isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_LOCAL_VARIABLES) ||
				isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_IMPORTS) && !isEnabled(CleanUpConstants.ORGANIZE_IMPORTS) ||
				isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_METHOD_PARAMETERS);
	}

	@Override
	public ICleanUpFix createFix(CompilationUnit compilationUnit) throws CoreException {
		boolean removeUnuseMembers= isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_MEMBERS);

		return UnusedCodeFixCore.createCleanUp(compilationUnit,
				removeUnuseMembers && isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_METHODS),
				removeUnuseMembers && isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_CONSTRUCTORS),
				removeUnuseMembers && isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_FELDS),
				removeUnuseMembers && isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_TYPES),
				isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_LOCAL_VARIABLES),
				isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_IMPORTS) && !isEnabled(CleanUpConstants.ORGANIZE_IMPORTS),
				false,
				isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_METHOD_PARAMETERS));
	}

	@Override
	public ICleanUpFix createFix(CompilationUnit compilationUnit, IProblemLocation[] problems) throws CoreException {
		boolean removeMembers= isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_MEMBERS);

		return UnusedCodeFixCore.createCleanUp(compilationUnit, problems,
				removeMembers && isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_METHODS),
				removeMembers && isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_CONSTRUCTORS),
				removeMembers && isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_FELDS),
				removeMembers && isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_TYPES),
				isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_LOCAL_VARIABLES),
				isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_IMPORTS) && !isEnabled(CleanUpConstants.ORGANIZE_IMPORTS),
				false,
				isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_METHOD_PARAMETERS));
	}

	public Map<String, String> getRequiredOptions() {
		Map<String, String> result= new Hashtable<>();

		if (isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_IMPORTS) && !isEnabled(CleanUpConstants.ORGANIZE_IMPORTS))
			result.put(JavaCore.COMPILER_PB_UNUSED_IMPORT, JavaCore.WARNING);

		boolean removeMembers= isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_MEMBERS);
		if (removeMembers && (
				isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_METHODS) ||
				isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_CONSTRUCTORS) ||
				isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_FELDS) ||
				isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_TYPES)))
			result.put(JavaCore.COMPILER_PB_UNUSED_PRIVATE_MEMBER, JavaCore.WARNING);

		if (isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_LOCAL_VARIABLES)) {
			result.put(JavaCore.COMPILER_PB_UNUSED_LOCAL, JavaCore.WARNING);
		}

		if (isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_METHOD_PARAMETERS)) {
			result.put(JavaCore.COMPILER_PB_UNUSED_PARAMETER, JavaCore.WARNING);
		}

		return result;
	}

	@Override
	public String[] getStepDescriptions() {
		List<String> result= new ArrayList<>();
		if (isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_IMPORTS))
			result.add(MultiFixMessages.UnusedCodeMultiFix_RemoveUnusedImport_description);
		if (isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_MEMBERS) && isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_METHODS))
			result.add(MultiFixMessages.UnusedCodeMultiFix_RemoveUnusedMethod_description);
		if (isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_MEMBERS) && isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_CONSTRUCTORS))
			result.add(MultiFixMessages.UnusedCodeMultiFix_RemoveUnusedConstructor_description);
		if (isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_MEMBERS) && isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_TYPES))
			result.add(MultiFixMessages.UnusedCodeMultiFix_RemoveUnusedType_description);
		if (isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_MEMBERS) && isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_FELDS))
			result.add(MultiFixMessages.UnusedCodeMultiFix_RemoveUnusedField_description);
		if (isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_LOCAL_VARIABLES))
			result.add(MultiFixMessages.UnusedCodeMultiFix_RemoveUnusedVariable_description);
		if (isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_METHOD_PARAMETERS))
			result.add(MultiFixMessages.UnusedCodeMultiFix_RemoveUnusedParameter_description);
			return result.toArray(new String[0]);
	}

	@Override
	public String getPreview() {
		StringBuilder buf= new StringBuilder();

		if (!isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_IMPORTS)) {
			buf.append("import pack.Bar;\n"); //$NON-NLS-1$
		}
		buf.append("class Example {\n"); //$NON-NLS-1$
		if (!isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_MEMBERS) || !isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_TYPES)) {
			buf.append("    private class Sub {}\n"); //$NON-NLS-1$
		}
		buf.append("    public Example(boolean b) {}\n"); //$NON-NLS-1$
		if (!isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_MEMBERS) || !isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_CONSTRUCTORS)) {
			buf.append("    private Example() {}\n"); //$NON-NLS-1$
		}
		if (!isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_MEMBERS) || !isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_FELDS)) {
			buf.append("    private int fField;\n"); //$NON-NLS-1$
		}
		if (!isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_MEMBERS) || !isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_METHODS)) {
			buf.append("    private void foo() {}\n"); //$NON-NLS-1$
		}
		buf.append("    public void bar() {\n"); //$NON-NLS-1$
		if (!isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_LOCAL_VARIABLES)) {
			buf.append("        int i= 10;\n"); //$NON-NLS-1$
		}
		buf.append("    }\n"); //$NON-NLS-1$


		if (isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_METHOD_PARAMETERS)) {
			String code= """
				    public void zoz() {
				        zozo();
				    }

				    private void zozo() {
				        System.out.println("");
				    };
				"""; //$NON-NLS-1$

			buf.append(code);
		} else {
			String code= """
				    public void zoz() {
				        zozo(34);
				    }

				    private void zozo(int k) {
				        System.out.println("");
				    };
				"""; //$NON-NLS-1$
			buf.append(code);
		}

		buf.append("}\n"); //$NON-NLS-1$

		if (isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_IMPORTS)) {
			buf.append("\n"); //$NON-NLS-1$
		}

		if (isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_MEMBERS)) {
			if (isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_TYPES)) {
				buf.append("\n"); //$NON-NLS-1$
			}

			if (isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_CONSTRUCTORS)) {
				buf.append("\n"); //$NON-NLS-1$
			}

			if (isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_FELDS)) {
				buf.append("\n"); //$NON-NLS-1$
			}

			if (isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_METHODS)) {
				buf.append("\n"); //$NON-NLS-1$
			}
		}

		if (isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_LOCAL_VARIABLES)) {
			buf.append("\n"); //$NON-NLS-1$
		}

		return buf.toString();
	}

	@Override
	public boolean canFix(ICompilationUnit compilationUnit, IProblemLocation problem) {
		if (UnusedCodeFixCore.isUnusedImport(problem))
			return isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_IMPORTS);

		if (UnusedCodeFixCore.isUnusedMember(problem))
			return isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_MEMBERS) && isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_METHODS) ||
				isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_MEMBERS) && isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_CONSTRUCTORS) ||
				isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_MEMBERS) && isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_TYPES) ||
				isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_MEMBERS) && isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_FELDS) ||
				isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_LOCAL_VARIABLES);

		if (UnusedCodeFixCore.isUnusedParameter(problem)) {
			return isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_METHOD_PARAMETERS);
		}

		return false;
	}

	@Override
	public int computeNumberOfFixes(CompilationUnit compilationUnit) {
		int result= 0;
		IProblem[] problems= compilationUnit.getProblems();
		if (isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_IMPORTS) && !isEnabled(CleanUpConstants.ORGANIZE_IMPORTS)) {
			for (IProblem problem : problems) {
				int id= problem.getID();
				if (id == IProblem.UnusedImport || id == IProblem.DuplicateImport || id == IProblem.ConflictingImport ||
					    id == IProblem.CannotImportPackage || id == IProblem.ImportNotFound)
					result++;
			}
		}
		if (isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_MEMBERS) && isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_METHODS))
			result+= getNumberOfProblems(problems, IProblem.UnusedPrivateMethod);
		if (isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_MEMBERS) && isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_CONSTRUCTORS))
			result+= getNumberOfProblems(problems, IProblem.UnusedPrivateConstructor);
		if (isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_MEMBERS) && isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_TYPES))
			result+= getNumberOfProblems(problems, IProblem.UnusedPrivateType);
		if (isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_MEMBERS) && isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_PRIVATE_FELDS))
			result+= getNumberOfProblems(problems, IProblem.UnusedPrivateField);
		if (isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_LOCAL_VARIABLES)) {
			for (IProblem problem : problems) {
				int id= problem.getID();
				if (id == IProblem.LocalVariableIsNeverUsed) {
					ProblemLocation location= new ProblemLocation(problem);
					SimpleName name= UnusedCodeFixCore.getUnusedName(compilationUnit, location);
					if (!RenameUnusedVariableFixCore.canRenameToUnnamedVariable(compilationUnit, name)) {
						result++;
					}
				}
			}
		}
		if (isEnabled(CleanUpConstants.REMOVE_UNUSED_CODE_METHOD_PARAMETERS))
			result+= getNumberOfProblems(problems, IProblem.ArgumentIsNeverUsed);
		return result;
	}
}
