/*
 * (c) Copyright IBM Corp. 2000, 2001.
 * All Rights Reserved.
 */
package org.eclipse.jdt.internal.corext.refactoring.base;

import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IImportDeclaration;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.ISourceRange;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;

import org.eclipse.jdt.internal.corext.dom.Binding2JavaModel;
import org.eclipse.jdt.internal.corext.dom.Selection;
import org.eclipse.jdt.internal.corext.refactoring.SourceRange;
import org.eclipse.jdt.internal.corext.refactoring.base.RefactoringStatusEntry.Context;

/**
 * A java element context can be used to annotate a </code>RefactoringStatusEntry<code> with
 * detailed information about an error detected in an <code>IJavaElement</code>
 */
public abstract class JavaSourceContext extends Context {

	private static class MemberSourceContext extends JavaSourceContext {
		private IMember fMember;
		private MemberSourceContext(IMember member) {
			fMember= member;
		}
		public boolean isBinary() {
			return fMember.isBinary();
		}
		public ICompilationUnit getCompilationUnit() {
			return fMember.getCompilationUnit();
		}
		public IClassFile getClassFile() {
			return fMember.getClassFile();
		}
		public ISourceRange getSourceRange() {
			try {
				return fMember.getSourceRange();
			} catch (JavaModelException e) {
				return new SourceRange(0,0);
			}
		}
	}
	
	private static class ImportDeclarationSourceContext extends JavaSourceContext {
		private IImportDeclaration fImportDeclartion;
		private ImportDeclarationSourceContext(IImportDeclaration declaration) {
			fImportDeclartion= declaration;
		}
		public boolean isBinary() {
			return false;
		}
		public ICompilationUnit getCompilationUnit() {
			return (ICompilationUnit)fImportDeclartion.getParent().getParent();
		}
		public IClassFile getClassFile() {
			return null;
		}
		public ISourceRange getSourceRange() {
			try {
				return fImportDeclartion.getSourceRange();
			} catch (JavaModelException e) {
				return new SourceRange(0,0);
			}
		}
	}
	
	private static class CompilationUnitSourceContext extends JavaSourceContext {
		private ICompilationUnit fCUnit;
		private ISourceRange fSourceRange;
		private CompilationUnitSourceContext(ICompilationUnit cunit, ISourceRange range) {
			fCUnit= cunit;
			fSourceRange= range;
			if (fSourceRange == null)
				fSourceRange= new SourceRange(0,0);
		}
		public boolean isBinary() {
			return false;
		}
		public ICompilationUnit getCompilationUnit() {
			return fCUnit;
		}
		public IClassFile getClassFile() {
			return null;
		}
		public ISourceRange getSourceRange() {
			return fSourceRange;
		}
	}

	/**
	 * Creates an status entry context for the given member
	 * 
	 * @param member the java member for which the context is supposed to be created
	 * @return the status entry context or <code>Context.NULL_CONTEXT</code> if the
	 * 	context cannot be created
	 */
	public static Context create(IMember member) {
		if (member == null || !member.exists())
			return NULL_CONTEXT;
		return new MemberSourceContext(member);
	}
	
	/**
	 * Creates an status entry context for the given import declaration
	 * 
	 * @param declaration the import declaration for which the context is supposed to be created
	 * @return the status entry context or <code>Context.NULL_CONTEXT</code> if the
	 * 	context cannot be created
	 */
	public static Context create(IImportDeclaration declaration) {
		if (declaration == null || !declaration.exists())
			return NULL_CONTEXT;
		return new ImportDeclarationSourceContext(declaration);
	}
	
	/**
	 * Creates an status entry context for the given method binding
	 * 
	 * @param method the method binding for which the context is supposed to be created
	 * @param scope the Java project that is used to convert the method binding into a
	 * 	<code>IMethod</code>
	 * @return the status entry context or <code>Context.NULL_CONTEXT</code> if the
	 * 	context cannot be created
	 */
	public static Context create(IMethodBinding method, IJavaProject scope) {
		ITypeBinding declaringClass= method.getDeclaringClass();
		IMethod mr= null;
		try {
			IType resource= Binding2JavaModel.find(declaringClass, scope);
			if (resource != null)
				mr= org.eclipse.jdt.internal.corext.dom.Binding2JavaModel.find(method, resource);
		} catch (JavaModelException e) {
		}
		return create(mr);
	}

	/**
	 * Creates an status entry context for the given compilation unit.
	 * 
	 * @param cunit the compilation unit containing the error
	 * @return the status entry context or <code>Context.NULL_CONTEXT</code> if the
	 * 	context cannot be created
	 */
	public static Context create(ICompilationUnit cunit) {
		return create(cunit, (ISourceRange)null);
	}

	/**
	 * Creates an status entry context for the given compilation unit and source range.
	 * 
	 * @param cunit the compilation unit containing the error
	 * @param range the source range that has caused the error or <code>null</code>
	 *  	if the source range is unknown.
	 * @return the status entry context or <code>Context.NULL_CONTEXT</code> if the
	 * 	context cannot be created
	 */
	public static Context create(ICompilationUnit cunit, ISourceRange range) {
		if (cunit == null)
			return NULL_CONTEXT;
		return new CompilationUnitSourceContext(cunit, range);
	}

	/**
	 * Creates an status entry context for the given compilation unit and AST node.
	 * 
	 * @param cunit the compilation unit containing the error
	 * @param astNode an astNode denoting the source range that has caused the error
	 * 
	 * @return the status entry context or <code>Context.NULL_CONTEXT</code> if the
	 * 	context cannot be created
	 */
	public static Context create(ICompilationUnit cunit, ASTNode node) {
		ISourceRange range= null;
		if (node != null)
			range= new SourceRange(node.getStartPosition(), node.getLength());
		return create(cunit, range);
	}

	/**
	 * Creates an status entry context for the given compilation unit and selection.
	 * 
	 * @param cunit the compilation unit containing the error
	 * @param selection a selection denoting the source range that has caused the error
	 * 
	 * @return the status entry context or <code>Context.NULL_CONTEXT</code> if the
	 * 	context cannot be created
	 */
	public static Context create(ICompilationUnit cunit, Selection selection) {
		ISourceRange range= null;
		if (selection != null)
			range= new SourceRange(selection.getOffset(), selection.getLength());
		return create(cunit, range);
	}

	/**
	 * Returns whether this context is for a class file.
	 *
	 * @return <code>true</code> if from a class file, and <code>false</code> if
	 *   from a compilation unit
	 */
	public abstract boolean isBinary();
	
	/**
	 * Returns the compilation unit this context is working on. Returns <code>null</code>
	 * if the context is a binary context.
	 * 
	 * @return the compilation unit
	 */
	public abstract ICompilationUnit getCompilationUnit();
	
	/**
	 * Returns the class file this context is working on. Returns <code>null</code>
	 * if the context is not a binary context.
	 * 
	 * @return the class file
	 */
	public abstract IClassFile getClassFile();
	
	/**
	 * Returns the source range associated with this element.
	 *
	 * @return the source range
	 */
	public abstract ISourceRange getSourceRange();
}

