/*******************************************************************************
 * Copyright (c) 2000, 2016 IBM Corporation and others.
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
 *******************************************************************************/
package org.eclipse.jdt.internal.corext.codemanipulation;

import java.util.Comparator;
import java.util.List;

import java.text.Collator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;

import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.AnnotationTypeMemberDeclaration;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.util.CompilationUnitSorter;

import org.eclipse.jdt.internal.core.manipulation.MembersOrderPreferenceCacheCommon;
import org.eclipse.jdt.internal.corext.dom.ASTNodes;
import org.eclipse.jdt.internal.corext.util.JdtFlags;

import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.corext.dom.IASTSharedValues;
import org.eclipse.jdt.internal.ui.preferences.MembersOrderPreferenceCache;

/**
 * Orders members in a compilation unit. A working copy must be passed.
 */
public class SortMembersOperation implements IWorkspaceRunnable {


	/**
	 * Default comparator for body declarations.
	 */
	public static class DefaultJavaElementComparator implements Comparator<BodyDeclaration> {

		private final Collator fCollator;
		private final MembersOrderPreferenceCache fMemberOrderCache;
		private final boolean fDoNotSortFields;

		public DefaultJavaElementComparator(boolean doNotSortFields) {
			fDoNotSortFields= doNotSortFields;
			fCollator= Collator.getInstance();
			fMemberOrderCache= JavaPlugin.getDefault().getMemberOrderPreferenceCache();
		}

		private int category(BodyDeclaration bodyDeclaration) {
			switch (bodyDeclaration.getNodeType()) {
				case ASTNode.METHOD_DECLARATION:
					{
						MethodDeclaration method= (MethodDeclaration) bodyDeclaration;
						if (method.isConstructor()) {
							return MembersOrderPreferenceCacheCommon.CONSTRUCTORS_INDEX;
						}
						int flags= method.getModifiers();
						if (Modifier.isStatic(flags))
							return MembersOrderPreferenceCacheCommon.STATIC_METHODS_INDEX;
						else
							return MembersOrderPreferenceCacheCommon.METHOD_INDEX;
					}
				case ASTNode.FIELD_DECLARATION :
					{
						if (JdtFlags.isStatic(bodyDeclaration))
							return MembersOrderPreferenceCacheCommon.STATIC_FIELDS_INDEX;
						else
							return MembersOrderPreferenceCacheCommon.FIELDS_INDEX;
					}
				case ASTNode.INITIALIZER :
					{
						int flags= bodyDeclaration.getModifiers();
						if (Modifier.isStatic(flags))
							return MembersOrderPreferenceCacheCommon.STATIC_INIT_INDEX;
						else
							return MembersOrderPreferenceCacheCommon.INIT_INDEX;
					}
				case ASTNode.TYPE_DECLARATION :
				case ASTNode.ENUM_DECLARATION :
				case ASTNode.ANNOTATION_TYPE_DECLARATION :
					return MembersOrderPreferenceCacheCommon.TYPE_INDEX;
				case ASTNode.ENUM_CONSTANT_DECLARATION :
					return MembersOrderPreferenceCacheCommon.ENUM_CONSTANTS_INDEX;
				case ASTNode.ANNOTATION_TYPE_MEMBER_DECLARATION:
					return MembersOrderPreferenceCacheCommon.METHOD_INDEX; // reusing the method index

			}
			return 0; // should never happen
		}

		private int getCategoryIndex(int category) {
			return fMemberOrderCache.getCategoryIndex(category);
		}

		/**
		 * This comparator follows the contract defined in CompilationUnitSorter.sort.
		 * @see Comparator#compare(java.lang.Object, java.lang.Object)
		 * @see CompilationUnitSorter#sort(int, org.eclipse.jdt.core.ICompilationUnit, int[], java.util.Comparator, int, org.eclipse.core.runtime.IProgressMonitor)
		 */
		@Override
		public int compare(BodyDeclaration bodyDeclaration1, BodyDeclaration bodyDeclaration2) {
			boolean preserved1= fDoNotSortFields && isSortPreserved(bodyDeclaration1);
			boolean preserved2= fDoNotSortFields && isSortPreserved(bodyDeclaration2);

			// Bug 407759: need to use a common category for all isSortPreserved members that are to be sorted in the same group:
			int cat1= category(bodyDeclaration1);
			if (preserved1) {
				cat1= sortPreservedCategory(cat1);
			}
			int cat2= category(bodyDeclaration2);
			if (preserved2) {
				cat2= sortPreservedCategory(cat2);
			}

			if (cat1 != cat2) {
				return getCategoryIndex(cat1) - getCategoryIndex(cat2);
			}

			// cat1 == cat2 implies preserved1 == preserved2

			if (preserved1) {
				return preserveRelativeOrder(bodyDeclaration1, bodyDeclaration2);
			}

			if (fMemberOrderCache.isSortByVisibility()) {
				int flags1= JdtFlags.getVisibilityCode(bodyDeclaration1);
				int flags2= JdtFlags.getVisibilityCode(bodyDeclaration2);
				int vis= fMemberOrderCache.getVisibilityIndex(flags1) - fMemberOrderCache.getVisibilityIndex(flags2);
				if (vis != 0) {
					return vis;
				}
			}

			switch (bodyDeclaration1.getNodeType()) {
				case ASTNode.METHOD_DECLARATION :
					{
						MethodDeclaration method1= (MethodDeclaration) bodyDeclaration1;
						MethodDeclaration method2= (MethodDeclaration) bodyDeclaration2;

						if (fMemberOrderCache.isSortByVisibility()) {
							int vis= fMemberOrderCache.getVisibilityIndex(method1.getModifiers()) - fMemberOrderCache.getVisibilityIndex(method2.getModifiers());
							if (vis != 0) {
								return vis;
							}
						}

						String name1= method1.getName().getIdentifier();
						String name2= method2.getName().getIdentifier();

						// method declarations (constructors) are sorted by name
						int cmp= this.fCollator.compare(name1, name2);
						if (cmp != 0) {
							return cmp;
						}

						// if names equal, sort by parameter types
						List<SingleVariableDeclaration> parameters1= method1.parameters();
						List<SingleVariableDeclaration> parameters2= method2.parameters();
						int length1= parameters1.size();
						int length2= parameters2.size();

						int len= Math.min(length1, length2);
						for (int i= 0; i < len; i++) {
							SingleVariableDeclaration param1= parameters1.get(i);
							SingleVariableDeclaration param2= parameters2.get(i);
							cmp= this.fCollator.compare(buildSignature(param1.getType()), buildSignature(param2.getType()));
							if (cmp != 0) {
								return cmp;
							}
						}
						if (length1 != length2) {
							return length1 - length2;
						}
						return preserveRelativeOrder(bodyDeclaration1, bodyDeclaration2);
					}
				case ASTNode.FIELD_DECLARATION :
					{
						FieldDeclaration field1= (FieldDeclaration) bodyDeclaration1;
						FieldDeclaration field2= (FieldDeclaration) bodyDeclaration2;

						String name1= ((VariableDeclarationFragment) field1.fragments().get(0)).getName().getIdentifier();
						String name2= ((VariableDeclarationFragment) field2.fragments().get(0)).getName().getIdentifier();

						// field declarations are sorted by name
						return compareNames(bodyDeclaration1, bodyDeclaration2, name1, name2);
					}
				case ASTNode.INITIALIZER :
					{
						// preserve relative order
						return preserveRelativeOrder(bodyDeclaration1, bodyDeclaration2);
					}
				case ASTNode.TYPE_DECLARATION :
				case ASTNode.ENUM_DECLARATION :
				case ASTNode.ANNOTATION_TYPE_DECLARATION :
					{
						AbstractTypeDeclaration type1= (AbstractTypeDeclaration) bodyDeclaration1;
						AbstractTypeDeclaration type2= (AbstractTypeDeclaration) bodyDeclaration2;

						String name1= type1.getName().getIdentifier();
						String name2= type2.getName().getIdentifier();

						// typedeclarations are sorted by name
						return compareNames(bodyDeclaration1, bodyDeclaration2, name1, name2);
					}
				case ASTNode.ENUM_CONSTANT_DECLARATION :
					{
						EnumConstantDeclaration decl1= (EnumConstantDeclaration) bodyDeclaration1;
						EnumConstantDeclaration decl2= (EnumConstantDeclaration) bodyDeclaration2;

						String name1= decl1.getName().getIdentifier();
						String name2= decl2.getName().getIdentifier();

						// enum constants declarations are sorted by name
						return compareNames(bodyDeclaration1, bodyDeclaration2, name1, name2);
					}
				case ASTNode.ANNOTATION_TYPE_MEMBER_DECLARATION :
					{
						AnnotationTypeMemberDeclaration decl1= (AnnotationTypeMemberDeclaration) bodyDeclaration1;
						AnnotationTypeMemberDeclaration decl2= (AnnotationTypeMemberDeclaration) bodyDeclaration2;

						String name1= decl1.getName().getIdentifier();
						String name2= decl2.getName().getIdentifier();

						// enum constants declarations are sorted by name
						return compareNames(bodyDeclaration1, bodyDeclaration2, name1, name2);
					}
			}
			return 0;
		}

		private static int sortPreservedCategory(int category) {
			switch (category) {
				case MembersOrderPreferenceCacheCommon.STATIC_FIELDS_INDEX:
				case MembersOrderPreferenceCacheCommon.STATIC_INIT_INDEX:
					return MembersOrderPreferenceCacheCommon.STATIC_FIELDS_INDEX;
				case MembersOrderPreferenceCacheCommon.FIELDS_INDEX:
				case MembersOrderPreferenceCacheCommon.INIT_INDEX:
					return MembersOrderPreferenceCacheCommon.FIELDS_INDEX;
				default:
					return category;
			}
		}

		private boolean isSortPreserved(BodyDeclaration bodyDeclaration) {
			switch (bodyDeclaration.getNodeType()) {
				case ASTNode.FIELD_DECLARATION:
				case ASTNode.ENUM_CONSTANT_DECLARATION:
				case ASTNode.INITIALIZER:
					return true;
				default:
					return false;
			}
		}

		private int preserveRelativeOrder(BodyDeclaration bodyDeclaration1, BodyDeclaration bodyDeclaration2) {
			int value1= ((Integer) bodyDeclaration1.getProperty(CompilationUnitSorter.RELATIVE_ORDER));
			int value2= ((Integer) bodyDeclaration2.getProperty(CompilationUnitSorter.RELATIVE_ORDER));
			return value1 - value2;
		}

		private int compareNames(BodyDeclaration bodyDeclaration1, BodyDeclaration bodyDeclaration2, String name1, String name2) {
			int cmp= this.fCollator.compare(name1, name2);
			if (cmp != 0) {
				return cmp;
			}
			return preserveRelativeOrder(bodyDeclaration1, bodyDeclaration2);
		}

		private String buildSignature(Type type) {
			return ASTNodes.asString(type);
		}
	}


	private ICompilationUnit fCompilationUnit;
	private int[] fPositions;
	private final boolean fDoNotSortFields;

	/**
	 * Creates the operation.
	 * @param cu The working copy of a compilation unit.
	 * @param positions Positions to track or <code>null</code> if no positions
	 * should be tracked.
	 * @param doNotSortFields no fields and enum constants are sorted if true
	 */
	public SortMembersOperation(ICompilationUnit cu, int[] positions, boolean doNotSortFields) {
		fCompilationUnit= cu;
		fPositions= positions;
		fDoNotSortFields= doNotSortFields;
	}


	/**
	 * Runs the operation.
	 * @param monitor a monitor to use to report progress
	 * @throws CoreException if the compilation unit could not be
	 * sorted. Reasons include:
	 * <ul>
	 * <li> The given compilation unit does not exist (ELEMENT_DOES_NOT_EXIST)</li>
	 * <li> The given compilation unit is not a working copy (INVALID_ELEMENT_TYPES)</li>
	 * <li> A <code>CoreException</code> occurred while accessing the underlying
	 * resource
	 * </ul>
	 */
	@Override
	public void run(IProgressMonitor monitor) throws CoreException {
		CompilationUnitSorter.sort(IASTSharedValues.SHARED_AST_LEVEL, fCompilationUnit, fPositions, new DefaultJavaElementComparator(fDoNotSortFields), 0, monitor);
	}

	/**
	 * @return Returns the scheduling rule for this operation
	 */
	public ISchedulingRule getScheduleRule() {
		return ResourcesPlugin.getWorkspace().getRoot();
	}

}
