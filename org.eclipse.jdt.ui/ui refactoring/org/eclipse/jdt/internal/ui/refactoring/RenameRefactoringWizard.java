/*
 * Licensed Materials - Property of IBM,
 * WebSphere Studio Workbench
 * (c) Copyright IBM Corp 2000
 */
package org.eclipse.jdt.internal.ui.refactoring;

import org.eclipse.jdt.core.JavaModelException;import org.eclipse.jdt.core.refactoring.RefactoringStatus;
import org.eclipse.jdt.core.refactoring.tagging.IRenameRefactoring;import org.eclipse.jdt.internal.core.refactoring.DebugUtils;import org.eclipse.jdt.internal.ui.util.JdtHackFinder;

public class RenameRefactoringWizard extends RefactoringWizard {
	
	public RenameRefactoringWizard(String name){
		super(name);
	}

	/**
	 * @see RefactoringWizard#addUserInputPages
	 */ 
	protected void addUserInputPages(){
		String initialSetting= getRenameRefactoring().getCurrentName();
		setPageTitle(getPageTitle() + ": "+ initialSetting);
		addPage( new RenameInputWizardPage(true, initialSetting) {
			protected RefactoringStatus validateTextField(String text) {
				return validateNewName(text);
			}	
		});
	}
	
	private IRenameRefactoring getRenameRefactoring(){
		return (IRenameRefactoring)getRefactoring();	
	}
	
	private RefactoringStatus validateNewName(String newName){
		IRenameRefactoring ref= getRenameRefactoring();
		ref.setNewName(newName);
		try{
			return ref.checkNewName();
		} catch (JavaModelException e){
			JdtHackFinder.fixMeSoon("should log the exception");
			String msg= e.getMessage() == null ? "": e.getMessage();
			RefactoringStatus result= new RefactoringStatus();
			result.addFatalError("JavaModelException during name checking:" + msg);
			return result;
		}	
	}
}