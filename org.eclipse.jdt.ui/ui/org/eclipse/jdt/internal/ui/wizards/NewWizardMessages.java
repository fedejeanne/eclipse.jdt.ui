/*******************************************************************************
 * Copyright (c) 2000, 2024 IBM Corporation and others.
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
 *     Philippe Marschall <philippe.marschall@netcetera.ch> - [type wizards] Allow the creation of a compilation unit called package-info.java - https://bugs.eclipse.org/86168
 *     Michael Pellaton <michael.pellaton@netcetera.ch> - [type wizards] Allow the creation of a compilation unit called package-info.java - https://bugs.eclipse.org/86168
 *     Frits Jalvingh <jal@etc.to> - Contribution for Bug 459831 - [launching] Support attaching external annotations to a JRE container
 *     Stephan Herrmann - Contributions for
 *								Bug 463936 - ExternalAnnotationsAttachmentDialog should not allow virtual folders
 *								Bug 465293 - External annotation path per container and project
 *     Harald Albers <eclipse@albersweb.de> - [type wizards] New Annotation dialog could allow generating @Documented, @Retention and @Target - https://bugs.eclipse.org/339292
 *******************************************************************************/
package org.eclipse.jdt.internal.ui.wizards;

import org.eclipse.osgi.util.NLS;

public final class NewWizardMessages extends NLS {

	private static final String BUNDLE_NAME= "org.eclipse.jdt.internal.ui.wizards.NewWizardMessages";//$NON-NLS-1$

	private NewWizardMessages() {
		// Do not instantiate
	}

	public static String AbstractOpenWizardAction_noproject_title;
	public static String AbstractOpenWizardAction_noproject_message;
	public static String AbstractOpenWizardAction_createerror_title;
	public static String AbstractOpenWizardAction_createerror_message;
	public static String AddArchiveToBuildpathAction_DuplicateArchiveInfo_message;
	public static String AddArchiveToBuildpathAction_DuplicateArchivesInfo_message;
	public static String AddArchiveToBuildpathAction_ErrorTitle;
	public static String AddArchiveToBuildpathAction_InfoTitle;

	public static String AddLibraryToBuildpathAction_ErrorTitle;

	public static String AddSelectedLibraryToBuildpathAction_ErrorTitle;

	public static String AddSourceFolderToBuildpathAction_ErrorTitle;

	public static String AddSourceFolderWizardPage_description;
	public static String AddSourceFolderWizardPage_addSinglePattern;
	public static String AddSourceFolderWizardPage_dialog_title;
	public static String AddSourceFolderWizardPage_directory_message;
	public static String AddSourceFolderWizardPage_conflictWarning;
	public static String AddSourceFolderWizardPage_error_NotARelativePathName;
	public static String AddSourceFolderWizardPage_ignoreNestingConflicts;
	public static String AddSourceFolderWizardPage_replaceSourceFolderInfo;
	public static String BuildPathsBlock_RemoveOldOutputFolder_description;
	public static String BuildPathSupport_deprecated;
	public static String CPListLabelProvider_attribute_label;
	public static String CPVariableElementLabelProvider_appendix;
	public static String CPVariableElementLabelProvider_deprecated;
	public static String CPVariableElementLabelProvider_one_restriction;
	public static String CPVariableElementLabelProvider_read_only;
	public static String CPVariableElementLabelProvider_two_restrictions;

	public static String EditOutputFolderAction_DeleteOldOutputFolderQuestion;
	public static String EditOutputFolderAction_ErrorDescription;
	public static String EditOutputFolderAction_ProgressMonitorDescription;

	public static String ExcludeFromBuildathAction_ErrorTitle;

	public static String IncludeToBuildpathAction_ErrorTitle;
	public static String NewElementWizard_op_error_title;
	public static String NewElementWizard_op_error_message;
	public static String NewElementWizard_typecomment_deprecated_title;
	public static String NewElementWizard_typecomment_deprecated_message;

	public static String NewContainerWizardPage_container_label;
	public static String NewContainerWizardPage_container_button;
	public static String NewContainerWizardPage_error_EnterContainerName;
	public static String NewContainerWizardPage_error_ContainerIsBinary;
	public static String NewContainerWizardPage_error_ContainerDoesNotExist;
	public static String NewContainerWizardPage_error_NotAFolder;
	public static String NewContainerWizardPage_error_ProjectClosed;
	public static String NewContainerWizardPage_error_FolderIsVirtual;
	public static String NewContainerWizardPage_warning_NotAJavaProject;
	public static String NewContainerWizardPage_warning_NotInAJavaProject;
	public static String NewContainerWizardPage_warning_NotOnClassPath;
	public static String NewContainerWizardPage_ChooseSourceContainerDialog_title;
	public static String NewContainerWizardPage_ChooseSourceContainerDialog_description;

	public static String NewPackageCreationWizard_title;

	public static String NewPackageWizardPage_package_label;
	public static String NewPackageWizardPage_package_CreatePackageInfoJava;
	public static String NewPackageWizardPage_error_InvalidPackageName;
	public static String NewPackageWizardPage_error_IsOutputFolder;
	public static String NewPackageWizardPage_error_PackageExists;
	public static String NewPackageWizardPage_error_PackageExistsDifferentCase;
	public static String NewPackageWizardPage_error_EnterName;
	public static String NewPackageWizardPage_error_PackageNotShown;
	public static String NewPackageWizardPage_error_PackageNameFiltered;
	public static String NewPackageWizardPage_warning_DiscouragedPackageName;
	public static String NewPackageWizardPage_title;
	public static String NewPackageWizardPage_description;
	public static String NewPackageWizardPage_info;
	public static String NewSourceFolderWizardPage_error_ProjectNotOpen;

	public static String NewTypeWizardPage_package_label;
	public static String NewTypeWizardPage_package_button;
	public static String NewTypeWizardPage_enclosing_selection_label;
	public static String NewTypeWizardPage_enclosing_field_description;
	public static String NewTypeWizardPage_enclosing_button;
	public static String NewTypeWizardPage_error_InvalidPackageName;
	public static String NewTypeWizardPage_error_ClashOutputLocation;
	public static String NewTypeWizardPage_error_PackageNameEmptyForModule;
	public static String NewTypeWizardPage_warning_DiscouragedPackageName;
	public static String NewTypeWizardPage_warning_DefaultPackageDiscouraged;
	public static String NewTypeWizardPage_warning_NotJDKCompliant2;
	public static String NewTypeWizardPage_warning_EnumClassNotFound;
	public static String NewTypeWizardPage_warning_RecordClassNotFound;
	public static String NewTypeWizardPage_default;
	public static String NewTypeWizardPage_ChoosePackageDialog_title;
	public static String NewTypeWizardPage_ChoosePackageDialog_description;
	public static String NewTypeWizardPage_ChoosePackageDialog_empty;
	public static String NewTypeWizardPage_ChooseEnclosingTypeDialog_title;
	public static String NewTypeWizardPage_ChooseEnclosingTypeDialog_description;
	public static String NewTypeWizardPage_error_EnclosingTypeEnterName;
	public static String NewTypeWizardPage_error_EnclosingTypeNotExists;
	public static String NewTypeWizardPage_error_EnclosingNotInCU;
	public static String NewTypeWizardPage_error_EnclosingNotEditable;
	public static String NewTypeWizardPage_warning_EnclosingNotInSourceFolder;
	public static String NewTypeWizardPage_typename_label;
	public static String NewTypeWizardPage_superclass_label;
	public static String NewTypeWizardPage_superclass_button;
	public static String NewTypeWizardPage_interfaces_class_label;
	public static String NewTypeWizardPage_interfaces_ifc_label;
	public static String NewTypeWizardPage_interfaces_add;
	public static String NewTypeWizardPage_interfaces_remove;
	public static String NewTypeWizardPage_modifiers_acc_label;
	public static String NewTypeWizardPage_modifiers_public;
	public static String NewTypeWizardPage_modifiers_private;
	public static String NewTypeWizardPage_modifiers_protected;
	public static String NewTypeWizardPage_modifiers_default;
	public static String NewTypeWizardPage_modifiers_abstract;
	public static String NewTypeWizardPage_modifiers_final;
	public static String NewTypeWizardPage_modifiers_static;
	public static String NewTypeWizardPage_modifiers_sealed;
	public static String NewTypeWizardPage_modifiers_non_sealed;
	public static String NewTypeWizardPage_none_label;
	public static String NewTypeWizardPage_addcomment_label;
	public static String NewTypeWizardPage_addcomment_description;
	public static String NewTypeWizardPage_addcomment_description2;
	public static String NewTypeWizardPage_error_EnterTypeName;
	public static String NewTypeWizardPage_error_TypeNameExists;
	public static String NewTypeWizardPage_error_TypeNameExistsDifferentCase;
	public static String NewTypeWizardPage_error_InvalidTypeName;
	public static String NewTypeWizardPage_error_QualifiedName;
	public static String NewTypeWizardPage_info_FileExtensionNotRequired;
	public static String NewTypeWizardPage_warning_TypeNameDiscouraged;
	public static String NewTypeWizardPage_error_InvalidSuperClassName;
	public static String NewTypeWizardPage_error_InvalidSuperClassRecord;
	public static String NewTypeWizardPage_error_InvalidFinalSuperClass;
	public static String NewTypeWizardPage_error_InvalidSuperInterfaceName;
	public static String NewTypeWizardPage_error_ModifiersFinalAndAbstract;
	public static String NewTypeWizardPage_error_TypeNameFiltered;
	public static String NewTypeWizardPage_error_PackageNameFiltered;
	public static String NewTypeWizardPage_error_PackageIsVirtual;
	public static String NewTypeWizardPage_error_class_SealedSuperClassInDifferentModule;
	public static String NewTypeWizardPage_error_class_SealedSuperClassInDifferentPackage;
	public static String NewTypeWizardPage_error_class_SealedSuperClassInDifferentProject;
	public static String NewTypeWizardPage_error_class_SealedSuperInterfaceInDifferentModule;
	public static String NewTypeWizardPage_error_class_SealedSuperInterfaceInDifferentPackage;
	public static String NewTypeWizardPage_error_class_SealedSuperInterfaceInDifferentProject;
	public static String NewTypeWizardPage_error_interface_SealedSuperInterfaceInDifferentModule;
	public static String NewTypeWizardPage_error_interface_SealedSuperInterfaceInDifferentPackage;
	public static String NewTypeWizardPage_error_interface_SealedSuperInterfaceInDifferentProject;
	public static String NewTypeWizardPage_configure_templates_message;
	public static String NewTypeWizardPage_configure_templates_title;
	public static String NewTypeWizardPage_SuperClassDialog_title;
	public static String NewTypeWizardPage_SuperClassDialog_message;
	public static String NewTypeWizardPage_InterfacesDialog_class_title;
	public static String NewTypeWizardPage_InterfacesDialog_interface_title;
	public static String NewTypeWizardPage_InterfacesDialog_message;
	public static String NewTypeWizardPage_operationdesc;
	public static String NewTypeWizardPage_error_uri_location_unkown;
	public static String NewTypeWizardPage_error_SealedFinalNonSealedClass_extend_superclass_notSelected_message;
	public static String NewTypeWizardPage_error_SealedFinalNonSealedClass_implement_superinterface_notSelected_message;
	public static String NewTypeWizardPage_error_SealedFinalNonSealedInterface_extend_superinterface_notSelected_message;

	public static String OutputLocation_DotAsLocation;
	public static String OutputLocation_SettingsAsLocation;
	public static String OutputLocationDialog_removeProjectFromBP;

	public static String RemoveFromBuildpathAction_ErrorTitle;

	public static String SuperInterfaceSelectionDialog_addButton_label;
	public static String SuperInterfaceSelectionDialog_interfaceadded_info;
	public static String SuperInterfaceSelectionDialog_interfacealreadyadded_info;

	public static String NewClassCreationWizard_title;
	public static String NewClassWizardPage_title;
	public static String NewClassWizardPage_description;
	public static String NewClassWizardPage_methods_label;
	public static String NewClassWizardPage_methods_main;
	public static String NewClassWizardPage_methods_constructors;
	public static String NewClassWizardPage_methods_inherited;

	public static String NewRecordCreationWizard_title;
	public static String NewRecordWizardPage_title;
	public static String NewRecordWizardPage_description;
	public static String NewRecordWizardPage_methods_label;
	public static String NewRecordWizardPage_methods_inherited;

	public static String NewInterfaceCreationWizard_title;
	public static String NewInterfaceWizardPage_title;
	public static String NewInterfaceWizardPage_description;

	public static String NewEnumCreationWizard_title;
	public static String NewEnumWizardPage_title;
	public static String NewEnumWizardPage_description;
	public static String NewEnumWizardPage_error_invalidTypeParameters;

	public static String NewAnnotationCreationWizard_title;
	public static String NewAnnotationWizardPage_add_documented;
	public static String NewAnnotationWizardPage_add_retention;
	public static String NewAnnotationWizardPage_add_target;
	public static String NewAnnotationWizardPage_title;
	public static String NewAnnotationWizardPage_description;
	public static String NewAnnotationWizardPage_error_invalidTypeParameters;

	public static String JavaCapabilityConfigurationPage_title;
	public static String JavaCapabilityConfigurationPage_description;
	public static String JavaCapabilityConfigurationPage_op_desc_java;

	public static String JavaProjectWizard_title;
	public static String JavaProjectWizard_op_error_title;
	public static String JavaProjectWizard_op_error_create_message;

	public static String NewJavaProjectWizardPage_title;
	public static String NewJavaProjectWizardPage_description;
	public static String NewJavaProjectWizardPageTwo_error_message;
	public static String NewJavaProjectWizardPageOne_dialog_title;
	public static String NewJavaProjectWizardPageOne_directory_message;
	public static String NewJavaProjectWizardPageOne_JREGroup_link_description;
	public static String NewJavaProjectWizardPageOne_JREGroup_unspecified_compliance;
	public static String NewJavaProjectWizardPageOne_JREGroup_default_compliance;
	public static String NewJavaProjectWizardPageOne_JREGroup_specific_compliance;
	public static String NewJavaProjectWizardPageOne_JREGroup_specific_EE;
	public static String NewJavaProjectWizardPageOne_JREGroup_title;
	public static String NewJavaProjectWizardPageOne_page_description;
	public static String NewJavaProjectWizardPageOne_page_title;
	public static String NewJavaProjectWizardPageOne_NoJREFound_link;
	public static String NewJavaProjectWizardPageOne_LayoutGroup_link_description;
	public static String NewJavaProjectWizardPageOne_LayoutGroup_option_oneFolder;
	public static String NewJavaProjectWizardPageOne_DetectGroup_differendWorkspaceCC_message;
	public static String NewJavaProjectWizardPageOne_Message_invalidProjectNameForWorkspaceRoot;
	public static String NewJavaProjectWizardPageOne_Message_cannotCreateAtExternalLocation;
	public static String NewJavaProjectWizardPageOne_LayoutGroup_option_separateFolders;
	public static String NewJavaProjectWizardPageOne_LayoutGroup_title;
	public static String NewJavaProjectWizardPageOne_WorkingSets_group;
	public static String NewJavaProjectWizardPageOne_LocationGroup_location_desc;
	public static String NewJavaProjectWizardPageOne_LocationGroup_browseButton_desc;
	public static String NewJavaProjectWizardPageOne_LocationGroup_locationLabel_desc;
	public static String NewJavaProjectWizardPageOne_NameGroup_label_text;
	public static String NewJavaProjectWizardPageOne_DetectGroup_jre_message;
	public static String NewJavaProjectWizardPageOne_DetectGroup_message;
	public static String NewJavaProjectWizardPageOne_Message_enterLocation;
	public static String NewJavaProjectWizardPageOne_Message_enterProjectName;
	public static String NewJavaProjectWizardPageOne_Message_invalidDirectory;
	public static String NewJavaProjectWizardPageOne_Message_projectAlreadyExists;
	public static String NewJavaProjectWizardPageOne_UnknownDefaultJRE_name;
	public static String NewJavaProjectWizardPageOne_Module_group;
	public static String NewJavaProjectWizardPageOne_Module_group_empty_name;
	public static String NewJavaProjectWizardPageOne_Create_ModuleInfoFile_name;
	public static String NewJavaProjectWizardPageTwo_error_remove_message;
	public static String NewJavaProjectWizardPageTwo_error_remove_title;
	public static String NewJavaProjectWizardPageTwo_problem_backup;
	public static String NewJavaProjectWizardPageTwo_DeleteCorruptProjectFile_message;
	public static String NewJavaProjectWizardPageTwo_monitor_init_build_path;
	public static String NewJavaProjectWizardPageTwo_problem_restore_classpath;
	public static String NewJavaProjectWizardPageTwo_problem_restore_project;
	public static String NewJavaProjectWizardPageTwo_operation_create;
	public static String NewJavaProjectWizardPageTwo_operation_remove;
	public static String NewJavaProjectWizardPageTwo_operation_initialize;
	public static String NewJavaProjectWizardPageTwo_error_title;
	public static String NewJavaProjectWizardPage_op_desc;

	public static String NewJavaWorkingSetWizard_title;
	public static String NewSourceFolderCreationWizard_title;
	public static String NewSourceFolderCreationWizard_edit_title;
	public static String NewSourceFolderCreationWizard_link_title;
	public static String NewSourceFolderWizardPage_title;
	public static String NewSourceFolderWizardPage_description;
	public static String NewSourceFolderWizardPage_root_label;
	public static String NewSourceFolderWizardPage_root_button;
	public static String NewSourceFolderWizardPage_project_label;
	public static String NewSourceFolderWizardPage_project_button;
	public static String NewSourceFolderWizardPage_operation;
	public static String NewSourceFolderWizardPage_exclude_label;
	public static String NewSourceFolderWizardPage_ignore_optional_problems_label;
	public static String NewSourceFolderWizardPage_ChooseExistingRootDialog_title;
	public static String NewSourceFolderWizardPage_ChooseExistingRootDialog_description;
	public static String NewSourceFolderWizardPage_ChooseProjectDialog_title;
	public static String NewSourceFolderWizardPage_ChooseProjectDialog_description;
	public static String NewSourceFolderWizardPage_error_EnterRootName;
	public static String NewSourceFolderWizardPage_error_InvalidRootName;
	public static String NewSourceFolderWizardPage_error_NotAFolder;
	public static String NewSourceFolderWizardPage_error_AlreadyExisting;
	public static String NewSourceFolderWizardPage_error_AlreadyExistingDifferentCase;
	public static String NewSourceFolderWizardPage_error_EnterProjectName;
	public static String NewSourceFolderWizardPage_error_InvalidProjectPath;
	public static String NewSourceFolderWizardPage_error_NotAJavaProject;
	public static String NewSourceFolderWizardPage_error_ProjectNotExists;
	public static String NewSourceFolderWizardPage_error_FolderIsVirtual;
	public static String NewSourceFolderWizardPage_error_FolderNameFiltered;
	public static String NewSourceFolderWizardPage_warning_ReplaceSFandOL;
	public static String NewSourceFolderWizardPage_warning_ReplaceOL;
	public static String NewSourceFolderWizardPage_warning_ReplaceSF;
	public static String NewSourceFolderWizardPage_warning_AddedExclusions_singular;
	public static String NewSourceFolderWizardPage_warning_AddedExclusions_plural;
	public static String NewSourceFolderWizardPage_ReplaceExistingSourceFolder_label;
	public static String NewSourceFolderWizardPage_edit_description;

	public static String BuildPathsBlock_tab_source;
	public static String BuildPathsBlock_tab_projects;
	public static String BuildPathsBlock_tab_libraries;
	public static String BuildPathsBlock_tab_order;
	public static String BuildPathsBlock_tab_modules;
	public static String BuildPathsBlock_classpath_label;
	public static String BuildPathsBlock_classpath_up_button;
	public static String BuildPathsBlock_classpath_down_button;
	public static String BuildPathsBlock_classpath_top_button;
	public static String BuildPathsBlock_classpath_bottom_button;
	public static String BuildPathsBlock_classpath_checkall_button;
	public static String BuildPathsBlock_classpath_uncheckall_button;
	public static String BuildPathsBlock_buildpath_label;
	public static String BuildPathsBlock_buildpath_button;
	public static String BuildPathsBlock_error_InvalidBuildPath;
	public static String BuildPathsBlock_error_EnterBuildPath;
	public static String BuildPathsBlock_warning_EntryMissing;
	public static String BuildPathsBlock_warning_EntriesMissing;
	public static String BuildPathsBlock_operationdesc_project;
	public static String BuildPathsBlock_operationdesc_java;
	public static String BuildPathsBlock_ChooseOutputFolderDialog_title;
	public static String BuildPathsBlock_ChooseOutputFolderDialog_description;
	public static String BuildPathsBlock_RemoveBinariesDialog_title;
	public static String BuildPathsBlock_RemoveBinariesDialog_description;

	public static String CPListLabelProvider_new;
	public static String CPListLabelProvider_classcontainer;
	public static String CPListLabelProvider_twopart;
	public static String CPListLabelProvider_unbound_library;
	public static String CPListLabelProvider_systemlibrary;
	public static String CPListLabelProvider_native_library_path;

	public static String SourceContainerWorkbookPage_folders_label;
	public static String SourceContainerWorkbookPage_folders_remove_button;
	public static String SourceContainerWorkbookPage_folders_add_button;
	public static String SourceContainerWorkbookPage_folders_edit_button;
	public static String SourceContainerWorkbookPage_folders_toggle_button;
	public static String SourceContainerWorkbookPage_folders_check;
	public static String SourceContainerWorkbookPage_ExistingSourceFolderDialog_new_title;
	public static String SourceContainerWorkbookPage_ChangeOutputLocationDialog_title;
	public static String SourceContainerWorkbookPage_ChangeOutputLocationDialog_project_and_output_message;
	public static String SourceContainerWorkbookPage_ChangeOutputLocationDialog_project_message;
	public static String SourceContainerWorkbookPage_ExistingSourceFolderDialog_edit_description;
	public static String SourceContainerWorkbookPage_exclusion_added_title;
	public static String SourceContainerWorkbookPage_exclusion_added_message;
	public static String SourceContainerWorkbookPage_folders_link_source_button;

	public static String ProjectsWorkbookPage_projects_label;
	public static String ProjectsWorkbookPage_projects_add_button;
	public static String ProjectsWorkbookPage_projects_edit_button;
	public static String ProjectsWorkbookPage_projects_toggle_button;
	public static String ProjectsWorkbookPage_projects_remove_button;
	public static String ProjectsWorkbookPage_chooseProjects_message;
	public static String ProjectsWorkbookPage_chooseProjects_title;

	public static String LibrariesWorkbookPage_libraries_addextfolder_button;
	public static String LibrariesWorkbookPage_libraries_label;
	public static String LibrariesWorkbookPage_libraries_remove_button;
	public static String LibrariesWorkbookPage_libraries_addjar_button;
	public static String LibrariesWorkbookPage_libraries_addextjar_button;
	public static String LibrariesWorkbookPage_libraries_addvariable_button;
	public static String LibrariesWorkbookPage_libraries_addlibrary_button;
	public static String LibrariesWorkbookPage_libraries_addclassfolder_button;
	public static String LibrariesWorkbookPage_libraries_replace_button;
	public static String LibrariesWorkbookPage_configurecontainer_error_title;
	public static String LibrariesWorkbookPage_configurecontainer_error_message;
	public static String LibrariesWorkbookPage_libraries_edit_button;
	public static String LibrariesWorkbookPage_libraries_toggle_button;
	public static String LibrariesWorkbookPage_NewClassFolderDialog_new_title;
	public static String LibrariesWorkbookPage_NewClassFolderDialog_edit_title;
	public static String LibrariesWorkbookPage_NewClassFolderDialog_description;
	public static String LibrariesWorkbookPage_JavadocPropertyDialog_title;
	public static String LibrariesWorkbookPage_exclusion_added_title;
	public static String LibrariesWorkbookPage_exclusion_added_message;
	public static String LibrariesWorkbookPage_externalAnnotationNeedsNullAnnotationEnabled_title;
	public static String LibrariesWorkbookPage_externalAnnotationNeedsNullAnnotationEnabled_message;

	public static String BuildPathDialogAccess_ExistingSourceFolderDialog_new_title;
	public static String BuildPathDialogAccess_ExistingSourceFolderDialog_new_description;
	public static String BuildPathDialogAccess_ExistingClassFolderDialog_new_title;
	public static String BuildPathDialogAccess_ExistingClassFolderDialog_new_description;
	public static String BuildPathDialogAccess_JARArchiveDialog_new_title;
	public static String BuildPathDialogAccess_JARArchiveDialog_new_description;
	public static String BuildPathDialogAccess_JARArchiveDialog_edit_title;
	public static String BuildPathDialogAccess_JARArchiveDialog_edit_description;
	public static String BuildPathDialogAccess_ExtClassFolderDialog_edit_description;
	public static String BuildPathDialogAccess_ExtClassFolderDialog_edit_title;
	public static String BuildPathDialogAccess_ExtClassFolderDialog_new_description;
	public static String BuildPathDialogAccess_ExtClassFolderDialog_new_title;
	public static String BuildPathDialogAccess_ExtJARArchiveDialog_new_title;
	public static String BuildPathDialogAccess_ExtJARArchiveDialog_edit_title;

	public static String NewContainerDialog_error_invalidpath;
	public static String NewContainerDialog_error_enterpath;
	public static String NewContainerDialog_error_pathexists;

	public static String SourceAttachmentBlock_message;
	public static String SourceAttachmentBlock_filename_description;
	public static String SourceAttachmentBlock_filename_externalfile_button;
	public static String SourceAttachmentBlock_filename_externalfolder_button;
	public static String SourceAttachmentBlock_filename_varlabel;
	public static String SourceAttachmentBlock_filename_variable_button;
	public static String SourceAttachmentBlock_filename_external_label;
	public static String SourceAttachmentBlock_filename_external_varbutton;
	public static String SourceAttachmentBlock_filename_error_notvalid;
	public static String SourceAttachmentBlock_filename_error_notabsolute;
	public static String SourceAttachmentBlock_filename_error_filenotexists;
	public static String SourceAttachmentBlock_filename_error_varnotexists;
	public static String SourceAttachmentBlock_filename_error_deviceinpath;
	public static String SourceAttachmentBlock_filename_warning_varempty;
	public static String SourceAttachmentBlock_filename_workspace_browse;
	public static String SourceAttachmentBlock_filename_workspace_label;
	public static String SourceAttachmentBlock_intjardialog_title;
	public static String SourceAttachmentBlock_intjardialog_message;
	public static String SourceAttachmentBlock_encoding_default;
	public static String SourceAttachmentBlock_encoding_label;
	public static String SourceAttachmentBlock_external_radiolabel;
	public static String SourceAttachmentBlock_extvardialog_title;
	public static String SourceAttachmentBlock_extvardialog_description;
	public static String SourceAttachmentBlock_extjardialog_text;
	public static String SourceAttachmentBlock_extfolderdialog_text;

	public static String BuildPathSupport_putoncpdialog_title;
	public static String BuildPathSupport_putoncpdialog_message;

	public static String SourceAttachmentDialog_title;

	public static String ExternalAnnotationsDialog_title;

	public static String AnnotationsAttachmentBlock_message;
	public static String AnnotationsAttachmentBlock_artifact_label;
	public static String AnnotationsAttachmentBlock_artifactNotFound_error;
	public static String AnnotationsAttachmentBlock_container_label;
	public static String AnnotationsAttachmentBlock_containerRelative_radio;
	public static String AnnotationsAttachmentBlock_missingArtifact_error;
	public static String AnnotationsAttachmentBlock_missingContainer_error;
	public static String AnnotationsAttachmentBlock_filename_externalfile_button;
	public static String AnnotationsAttachmentBlock_filename_externalfolder_button;
	public static String AnnotationsAttachmentBlock_filename_external_label;
	public static String AnnotationsAttachmentBlock_filename_error_notvalid;
	public static String AnnotationsAttachmentBlock_filename_error_notabsolute;
	public static String AnnotationsAttachmentBlock_filename_error_filenotexists;
	public static String AnnotationsAttachmentBlock_filename_error_virtual;
	public static String AnnotationsAttachmentBlock_filename_workspace_browse;
	public static String AnnotationsAttachmentBlock_filename_workspace_label;
	public static String AnnotationsAttachmentBlock_intjardialog_title;
	public static String AnnotationsAttachmentBlock_intjardialog_message;
	public static String AnnotationsAttachmentBlock_external_radiolabel;
	public static String AnnotationsAttachmentBlock_extjardialog_text;
	public static String AnnotationsAttachmentBlock_extfolderdialog_text;
	public static String AnnotationsAttachmentBlock_extfolderdialog_message;
	public static String AnnotationsAttachmentBlock_workspace_radiolabel;

	// module related:
	public static String BuildPathBasePage_addNow_button;
	public static String BuildPathBasePage_cancel_button;
	public static String BuildPathBasePage_notAddedQuestion_description;
	public static String BuildPathBasePage_notAddedQuestion_title;
	public static String BuildPathBasePage_proceedWithoutAdding_button;
	public static String BuildPathBasePage_unexpectedAnswer_error;

	public static String ModuleDialog_title;
	public static String ModuleDialog_description;
	public static String ModuleDialog_container_description;
	public static String ModuleDialog_project_description;
	public static String ModuleDialog_defines_modules_label;
	public static String ModuleDialog_errorOnContentsTab_error;
	public static String ModuleDialog_errorOnDetailsTab_error;

	public static String ModuleDialog_deprecated_warning;
	public static String ModuleDialog_switchToTab_button;

	public static String ModuleDialog_contents_tab;
	public static String ModuleDialog_availableModules_list;
	public static String ModuleDialog_availableModules_tooltip;
	public static String ModuleDialog_explicitlyIncludedModules_list;
	public static String ModuleDialog_explicitlyIncludedModules_tooltip;
	public static String ModuleDialog_implicitelyIncludedModules_list;
	public static String ModuleDialog_implicitlyIncludedModule_tooltip;
	public static String ModuleDialog_addToIncluded_tooltip;
	public static String ModuleDialog_removeFromIncluded_tooltip;
	public static String ModuleDialog_addToExplicitlyIncluded_tooltip;
	public static String ModuleDialog_cannotLimitSingle_error;
	public static String ModuleDialog_unknownModules_info;

	public static String ModuleDialog_details_tab;
	public static String ModuleDialog_patches_module_label;
	public static String ModuleDialog_patched_module_label;
	public static String ModuleDialog_patchedModuleExcluded_error;
	public static String ModuleDialog_source_module_header;
	public static String ModuleDialog_package_header;
	public static String ModuleDialog_target_module_header;
	public static String ModuleDialog_exports_label;
	public static String ModuleDialog_exportSourceModuleExcluded_error;
	public static String ModuleDialog_reads_label;
	public static String ModuleDialog_readsSourceModuleExcluded_error;
	public static String ModuleDialog_detail_add;
	public static String ModuleDialog_detail_edit;
	public static String ModuleDialog_detail_remove;
	public static String ModuleDialog_duplicatePackage_error;
	public static String ModuleDialog_duplicateReads_error;
	public static String ModuleDialog_missingPatch_error;
	public static String ModuleDialog_wrongPatch_error;
	public static String ModuleDialog_cannotEditDetails_info;
	public static String ModuleDialog_mustIncludeModule_error;

	public static String ModuleAddExportsBlock_packageEmpty_error;
	public static String ModuleAddExportsBlock_sourceModuleEmpty_error;
	public static String ModuleAddExportsBlock_wrongPackage_error;
	public static String ModuleAddExportsBlock_wrongSourceModule_error;
	public static String ModuleAddExportsBlock_targetModuleEmpty_error;
	public static String ModuleAddExportsBlock_illegalTargetModule_error;

	public static String AddModuleDetailsDialog_notPersisted_warning;
	public static String AddExportsDialog_title;

	public static String AddExportsBlock_message;
	public static String AddExportsBlock_sourceModule_label;
	public static String AddExportsBlock_package_label;
	public static String AddExportsBlock_targetModules_label;

	public static String AddReadsDialog_title;
	public static String AddReadsBlock_message;
	public static String AddReadsBlock_sourceModule_label;
	public static String AddReadsBlock_targetModule_label;
	//
	public static String ModuleDependenciesPage_modules_label;
	public static String ModuleDependenciesPage_addExport_notWith_release_info;
	public static String ModuleDependenciesPage_addSystemModule_button;
	public static String ModuleDependenciesPage_details_label;
	public static String ModuleDependenciesPage_nonModularProject_dummy;
	public static String ModuleDependenciesPage_outOfSync_warning;
	// buttons:
	public static String ModuleDependenciesPage_modules_remove_button;
	public static String ModuleDependenciesPage_modules_read_button;
	public static String ModuleDependenciesPage_modules_expose_package_button;
	public static String ModuleDependenciesPage_modules_patch_button;
	public static String ModuleDependenciesPage_modules_edit_button;
	public static String ModuleDependenciesPage_showJPMSOptions_button;
	// dialogs:
	public static String ModuleSelectionDialog_addSystemModules_title;
	public static String ModuleSelectionDialog_addSystemModules_message;
	public static String ModuleSelectionDialog_selectAll_button;
	public static String ModuleSelectionDialog_add_button;

	public static String ModuleSelectionDialog_selectModule_title;
	public static String ModuleSelectionDialog_selectReadModule_message;
	public static String ModuleSelectionDialog_searchModules_job;
	public static String ModuleSelectionDialog_searchModules_temp_message;

	public static String ModuleDependenciesAdapter_addSystemModule_title;
	public static String ModuleDependenciesAdapter_addSystemModules_question;
	public static String ModuleDependenciesAdapter_add_button;
	public static String ModuleDependenciesAdapter_addReadsNotOnModulepath_error;
	public static String ModuleDependenciesAdapter_goToLibrariesTab_button;
	public static String ModuleDependenciesAdapter_goToProjectsTab_button;

	public static String ModulePatchSourceSelectionDialog_patchSourceLocation_message;
	public static String ModulePatchSourceSelectionDialog_patchSourceLocation_title;

	public static String ShowJPMSOptionsDialog_dialog_title;
	public static String ShowJPMSOptionsDialog_explanation_label;
	public static String ShowJPMSOptionsDialog_close_button;
	public static String ShowJPMSOptionsDialog_copyAndCopy_button;
	public static String ShowJPMSOptionsDialog_empty_message;
	public static String ShowJPMSOptionsDialog_retrieve_error;

	public static String ModuleDependenciesPage_removeModule_dialog_title;
	public static String ModuleDependenciesPage_removingModule_message;
	public static String ModuleDependenciesPage_removingModuleTransitive_message;
	public static String ModuleDependenciesPage_remove_button;
	public static String ModuleDependenciesPage_removeCurrentModule_error;
	public static String ModuleDependenciesPage_removeModule_error_with_hint;
	public static String ModuleDependenciesPage_removeSystemModule_error_hint;
	public static String ModuleDependenciesPage_moduleIsRequired_warning;

	public static String ModuleDependenciesAdapter_patchConflict_title;
	public static String ModuleDependenciesAdapter_patchConflict_message;
	public static String ModuleDependenciesAdapter_patchOutputConflict_validationError;
	public static String ModuleDependenciesAdapter_project_kind;
	public static String ModuleDependenciesAdapter_sourceFolder_kind;
	public static String ModuleDependenciesAdapter_configure_error;

	// detail tree:
	public static String ModuleDependenciesAdapter_declared_node;
	public static String ModuleDependenciesAdapter_configured_node;
	//

	public static String EditVariableEntryDialog_title;
	public static String EditVariableEntryDialog_filename_varlabel;
	public static String EditVariableEntryDialog_filename_variable_button;
	public static String EditVariableEntryDialog_filename_external_varbutton;
	public static String EditVariableEntryDialog_extvardialog_title;
	public static String EditVariableEntryDialog_extvardialog_description;
	public static String EditVariableEntryDialog_filename_error_notvalid;
	public static String EditVariableEntryDialog_filename_error_filenotexists;
	public static String EditVariableEntryDialog_filename_error_varnotexists;
	public static String EditVariableEntryDialog_filename_error_deviceinpath;
	public static String EditVariableEntryDialog_filename_warning_varempty;
	public static String EditVariableEntryDialog_filename_error_alreadyexists;

	public static String VariableBlock_vars_label;
	public static String VariableBlock_vars_add_button;
	public static String VariableBlock_vars_edit_button;
	public static String VariableBlock_vars_remove_button;
	public static String VariableBlock_operation_desc;
	public static String VariableBlock_needsbuild_title;
	public static String VariableBlock_needsbuild_message;
	public static String VariableBlock_variableSettingError_titel;
	public static String VariableBlock_variableSettingError_message;
	public static String VariablePathDialogField_variabledialog_title;

	public static String CPVariableElementLabelProvider_empty;

	public static String VariableCreationDialog_titlenew;
	public static String VariableCreationDialog_titleedit;
	public static String VariableCreationDialog_name_label;
	public static String VariableCreationDialog_path_label;
	public static String VariableCreationDialog_path_file_button;
	public static String VariableCreationDialog_path_dir_button;
	public static String VariableCreationDialog_error_entername;
	public static String VariableCreationDialog_error_whitespace;
	public static String VariableCreationDialog_error_invalidname;
	public static String VariableCreationDialog_error_nameexists;
	public static String VariableCreationDialog_error_invalidpath;
	public static String VariableCreationDialog_warning_pathnotexists;
	public static String VariableCreationDialog_extjardialog_text;
	public static String VariableCreationDialog_extdirdialog_text;
	public static String VariableCreationDialog_extdirdialog_message;

	public static String NewVariableEntryDialog_title;
	public static String NewVariableEntryDialog_vars_extend;
	public static String NewVariableEntryDialog_configbutton_label;
	public static String NewVariableEntryDialog_vars_label;
	public static String NewVariableEntryDialog_ExtensionDialog_title;
	public static String NewVariableEntryDialog_ExtensionDialog_description;
	public static String NewVariableEntryDialog_info_notexists;
	public static String NewVariableEntryDialog_info_noselection;
	public static String NewVariableEntryDialog_info_selected;

	public static String OutputLocationDialog_title;
	public static String OutputLocationDialog_usedefault_label;
	public static String OutputLocationDialog_usespecific_label;
	public static String OutputLocationDialog_location_button;
	public static String OutputLocationDialog_error_existingisfile;
	public static String OutputLocationDialog_error_invalidpath;
	public static String OutputLocationDialog_ChooseOutputFolder_title;
	public static String OutputLocationDialog_ChooseOutputFolder_description;

	public static String ExclusionInclusionDialog_title;
	public static String ExclusionInclusionDialog_description;
	public static String ExclusionInclusionDialog_description2;
	public static String ExclusionInclusionDialog_exclusion_pattern_label;
	public static String ExclusionInclusionDialog_inclusion_pattern_label;
	public static String ExclusionInclusionDialog_inclusion_pattern_add;
	public static String ExclusionInclusionDialog_inclusion_pattern_add_multiple;
	public static String ExclusionInclusionDialog_inclusion_pattern_remove;
	public static String ExclusionInclusionDialog_inclusion_pattern_edit;
	public static String ExclusionInclusionDialog_exclusion_pattern_add;
	public static String ExclusionInclusionDialog_exclusion_pattern_add_multiple;
	public static String ExclusionInclusionDialog_exclusion_pattern_remove;
	public static String ExclusionInclusionDialog_exclusion_pattern_edit;
	public static String ExclusionInclusionDialog_ChooseExclusionPattern_title;
	public static String ExclusionInclusionDialog_ChooseExclusionPattern_description;
	public static String ExclusionInclusionDialog_ChooseInclusionPattern_title;
	public static String ExclusionInclusionDialog_ChooseInclusionPattern_description;
	public static String ExclusionInclusionEntryDialog_exclude_add_title;
	public static String ExclusionInclusionEntryDialog_exclude_edit_title;
	public static String ExclusionInclusionEntryDialog_exclude_description;
	public static String ExclusionInclusionEntryDialog_exclude_pattern_label;
	public static String ExclusionInclusionEntryDialog_include_add_title;
	public static String ExclusionInclusionEntryDialog_include_edit_title;
	public static String ExclusionInclusionEntryDialog_include_description;
	public static String ExclusionInclusionEntryDialog_include_pattern_label;
	public static String ExclusionInclusionEntryDialog_pattern_button;
	public static String ExclusionInclusionEntryDialog_error_empty;
	public static String ExclusionInclusionEntryDialog_error_notrelative;
	public static String ExclusionInclusionEntryDialog_error_exists;
	public static String ExclusionInclusionEntryDialog_ChooseExclusionPattern_title;
	public static String ExclusionInclusionEntryDialog_ChooseExclusionPattern_description;
	public static String ExclusionInclusionEntryDialog_ChooseInclusionPattern_title;
	public static String ExclusionInclusionEntryDialog_ChooseInclusionPattern_description;

	public static String AccessRulesDialog_title;
	public static String AccessRulesDialog_container_description;
	public static String AccessRulesDialog_project_description;
	public static String AccessRulesDialog_description;
	public static String AccessRulesDialog_rules_label;
	public static String AccessRulesDialog_rules_add;
	public static String AccessRulesDialog_rules_up;
	public static String AccessRulesDialog_rules_remove;
	public static String AccessRulesDialog_combine_label;
	public static String AccessRulesDialog_rules_edit;
	public static String AccessRulesDialog_rules_down;
	public static String AccessRulesLabelProvider_kind_accessible;
	public static String AccessRulesLabelProvider_kind_discouraged;
	public static String AccessRulesLabelProvider_kind_non_accessible;

	public static String TypeRestrictionEntryDialog_add_title;
	public static String TypeRestrictionEntryDialog_edit_title;
	public static String TypeRestrictionEntryDialog_pattern_label;
	public static String TypeRestrictionEntryDialog_description;
	public static String TypeRestrictionEntryDialog_description2;
	public static String TypeRestrictionEntryDialog_error_empty;
	public static String TypeRestrictionEntryDialog_error_notrelative;
	public static String TypeRestrictionEntryDialog_kind_accessible;
	public static String TypeRestrictionEntryDialog_kind_label;
	public static String TypeRestrictionEntryDialog_kind_discourraged;
	public static String TypeRestrictionEntryDialog_kind_non_accessible;

	public static String ClasspathContainerDefaultPage_title;
	public static String ClasspathContainerDefaultPage_description;
	public static String ClasspathContainerDefaultPage_path_label;
	public static String ClasspathContainerDefaultPage_path_error_enterpath;
	public static String ClasspathContainerDefaultPage_path_error_invalidpath;
	public static String ClasspathContainerDefaultPage_path_error_needssegment;
	public static String ClasspathContainerDefaultPage_path_error_alreadyexists;
	public static String ClasspathContainerSelectionPage_title;
	public static String ClasspathContainerSelectionPage_description;
	public static String ClasspathContainerWizard_pagecreationerror_title;
	public static String ClasspathContainerWizard_pagecreationerror_message;
	public static String ClasspathContainerWizard_new_title;
	public static String ClasspathContainerWizard_edit_title;

	public static String ClasspathFixSelectionDialog_apply_proposal_error_message;
	public static String ClasspathFixSelectionDialog_apply_proposal_error_title;
	public static String ClasspathFixSelectionDialog_dialog_title;
	public static String ClasspathFixSelectionDialog_eval_proposals_error_message;
	public static String ClasspathFixSelectionDialog_no_proposals_message;
	public static String ClasspathFixSelectionDialog_open_buld_path_dialog_message;
	public static String ClasspathFixSelectionDialog_process_fix_description;
	public static String ClasspathFixSelectionDialog_proposals_message;
	public static String FolderSelectionDialog_button;
	public static String MultipleFolderSelectionDialog_button;

	public static String CPListLabelProvider_ignore_optional_problems_label;
	public static String CPListLabelProvider_ignore_optional_problems_yes;
	public static String CPListLabelProvider_ignore_optional_problems_no;
	public static String CPListLabelProvider_none;
	public static String CPListLabelProvider_all;
	public static String CPListLabelProvider_source_attachment_label;
	public static String CPListLabelProvider_javadoc_location_label;
	public static String CPListLabelProvider_output_folder_label;
	public static String CPListLabelProvider_default_output_folder_label;
	public static String CPListLabelProvider_exclusion_filter_label;
	public static String CPListLabelProvider_exclusion_filter_separator;
	public static String CPListLabelProvider_inclusion_filter_label;
	public static String CPListLabelProvider_inclusion_filter_separator;
	public static String CPListLabelProvider_unknown_element_label;
	public static String CPListLabelProvider_access_rules_enabled_singular;
	public static String CPListLabelProvider_access_rules_enabled_plural;
	public static String CPListLabelProvider_project_access_rules_combined_singular;
	public static String CPListLabelProvider_project_access_rules_combined_plural;
	public static String CPListLabelProvider_project_access_rules_no_rules;
	public static String CPListLabelProvider_project_access_rules_not_combined_singular;
	public static String CPListLabelProvider_project_access_rules_not_combined_plural;
	public static String CPListLabelProvider_access_rules_disabled;

	public static String CPListLabelProvider_module_label;
	public static String CPListLabelProvider_module_yes;
	public static String CPListLabelProvider_module_no;
	public static String CPListLabelProvider_modular_modifiesEncapsulation_label;
	public static String CPListLabelProvider_modular_label;
	public static String CPListLabelProvider_modular_modifiesContentsAndEncapsulation_label;
	public static String CPListLabelProvider_modular_modifiesContents_label;
	public static String CPListLabelProvider_not_modular_label;

	public static String CPListLabelProvider_patch_module_full_label;
	public static String CPListLabelProvider_add_exports_full_label;
	public static String CPListLabelProvider_add_reads_full_label;
	public static String CPListLabelProvider_limitModules_full_label;

	public static String CPListLabelProvider_test_sources_label;
	public static String CPListLabelProvider_test_dependency_label;
	public static String CPListLabelProvider_without_test_code_label;
	public static String CPListLabelProvider_test_yes;
	public static String CPListLabelProvider_test_no;

	public static String HintTextGroup_NoAction;

	public static String NewSourceContainerWorkbookPage_HintTextGroup_title;
	public static String NewSourceContainerWorkbookPage_ToolBar_ConfigureBP_tooltip;
	public static String NewSourceContainerWorkbookPage_ToolBar_ConfigureBP_label;
	public static String NewSourceContainerWorkbookPage_ToolBar_Edit_tooltip;
	public static String NewSourceContainerWorkbookPage_ToolBar_Edit_label;
	public static String NewSourceContainerWorkbookPage_ToolBar_EditOutput_tooltip;
	public static String NewSourceContainerWorkbookPage_ToolBar_EditOutput_label;
	public static String NewSourceContainerWorkbookPage_ToolBar_AddSelSFToCP_tooltip;
	public static String NewSourceContainerWorkbookPage_ToolBar_AddSelSFToCP_label;
	public static String NewSourceContainerWorkbookPage_ToolBar_AddSelLibToCP_tooltip;
	public static String NewSourceContainerWorkbookPage_ToolBar_AddSelLibToCP_label;
	public static String NewSourceContainerWorkbookPage_ToolBar_AddSelLibToTestCP_tooltip;
	public static String NewSourceContainerWorkbookPage_ToolBar_AddSelLibToTestCP_label;

	public static String NewSourceContainerWorkbookPage_ToolBar_AddJarCP_tooltip;
	public static String NewSourceContainerWorkbookPage_ToolBar_AddJarCP_label;
	public static String NewSourceContainerWorkbookPage_ToolBar_AddLibCP_tooltip;
	public static String NewSourceContainerWorkbookPage_ToolBar_AddLibCP_label;
	public static String NewSourceContainerWorkbookPage_ToolBar_RemoveFromCP_tooltip;
	public static String NewSourceContainerWorkbookPage_ToolBar_RemoveFromCP_label;
	public static String NewSourceContainerWorkbookPage_ToolBar_Exclude_tooltip;
	public static String NewSourceContainerWorkbookPage_ToolBar_Exclude_label;
	public static String NewSourceContainerWorkbookPage_ToolBar_Unexclude_tooltip;
	public static String NewSourceContainerWorkbookPage_ToolBar_Unexclude_label;
	public static String NewSourceContainerWorkbookPage_ToolBar_Reset_tooltip;
	public static String NewSourceContainerWorkbookPage_ToolBar_ClearAll_tooltip;
	public static String NewSourceContainerWorkbookPage_ToolBar_ClearAll_label;
	public static String NewSourceContainerWorkbookPage_ToolBar_Link_tooltip;
	public static String NewSourceContainerWorkbookPage_ToolBar_Link_label;
	public static String NewSourceContainerWorkbookPage_ToolBar_Help_tooltip;
	public static String NewSourceContainerWorkbookPage_ToolBar_Help_label;
	public static String NewSourceContainerWorkbookPage_ToolBar_Help_link;

	public static String NewFolderDialog_linkTargetNonExistent;
	public static String NewFolderDialog_linkTargetNotFolder;
	public static String NewFolderDialog_folderNameEmpty_alreadyExists;
	public static String NewFolderDialog_createIn;

	public static String LinkFolderDialog_dependenciesGroup_locationLabel_desc;
	public static String LinkFolderDialog_dependenciesGroup_browseButton_desc;
	public static String LinkFolderDialog_dependenciesGroup_variables_desc;

	public static String PackageExplorerActionGroup_NoAction_NullSelection;
	public static String PackageExplorerActionGroup_NoAction_MultiSelection;
	public static String PackageExplorerActionGroup_NoAction_NoReason;
	public static String PackageExplorerActionGroup_FormText_createLinkedFolder;
	public static String PackageExplorerActionGroup_FormText_FolderToBuildpath;
	public static String PackageExplorerActionGroup_FormText_ArchiveToBuildpath;
	public static String PackageExplorerActionGroup_FormText_PackageToBuildpath;
	public static String PackageExplorerActionGroup_FormText_ProjectToBuildpath;
	public static String PackageExplorerActionGroup_FormText_fromBuildpath;
	public static String PackageExplorerActionGroup_FormText_ProjectFromBuildpath;
	public static String PackageExplorerActionGroup_FormText_ExcludePackage;
	public static String PackageExplorerActionGroup_FormText_ExcludeFile;
	public static String PackageExplorerActionGroup_FormText_Edit;
	public static String PackageExplorerActionGroup_FormText_UnexcludeFolder;
	public static String PackageExplorerActionGroup_FormText_ResetFilters;
	public static String PackageExplorerActionGroup_FormText_UnexcludeFile;
	public static String PackageExplorerActionGroup_FormText_EditOutputFolder;
	public static String PackageExplorerActionGroup_FormText_SetOutputToDefault;
	public static String PackageExplorerActionGroup_FormText_Default_Unexclude;
	public static String PackageExplorerActionGroup_FormText_Default_ResetAllOutputFolders;
	public static String PackageExplorerActionGroup_FormText_Default_FromBuildpath;
	public static String PackageExplorerActionGroup_FormText_Default_Exclude;
	public static String PackageExplorerActionGroup_FormText_Default_toBuildpath;
	public static String PackageExplorerActionGroup_FormText_Default_toBuildpath_archives;
	public static String PackageExplorerActionGroup_FormText_Default_toBuildpath_library;
	public static String PackageExplorerActionGroup_FormText_Default_Reset;
	public static String PackageExplorerActionGroup_FormText_Default_ResetAll;
	public static String PackageExplorerActionGroup_FormText_createNewSourceFolder;
	public static String PathRootWorkbookPage_classpath;
	public static String PathRootWorkbookPage_modulepath;

	public static String DialogPackageExplorer_LabelProvider_Excluded;
	public static String DialogPackageExplorer_LabelProvider_SingleExcluded;
	public static String DialogPackageExplorer_LabelProvider_MultiExcluded;

	public static String ClasspathModifier_Monitor_AddToBuildpath;
	public static String ClasspathModifier_Monitor_RemoveFromBuildpath;
	public static String ClasspathModifier_Monitor_ResetFilters;
	public static String ClasspathModifier_Monitor_Including;
	public static String ClasspathModifier_Monitor_Excluding;
	public static String ClasspathModifier_Monitor_RemoveExclusion;
	public static String ClasspathModifier_Monitor_ContainsPath;
	public static String ClasspathModifier_Monitor_ExamineInputFilters;
	public static String ClasspathModifier_Monitor_RemovePath;
	public static String ClasspathModifier_Monitor_CheckOutputFolders;
	public static String ClasspathModifier_Monitor_Resetting;
	public static String ClasspathModifier_Monitor_SetNewEntry;
	public static String ClasspathModifier_Monitor_ComparePaths;
	public static String ClasspathModifier_Monitor_ResetOutputFolder;
	public static String ClasspathModifier_ChangeOutputLocationDialog_title;
	public static String ClasspathModifier_ChangeOutputLocationDialog_project_message;
	public static String ClasspathModifier_ChangeOutputLocationDialog_project_outputLocation;
	public static String ClasspathModifier_Error_NoNatures;

	public static String ClassPathDetector_operation_description;
	public static String ClassPathDetector_error_closing_file;

	public static String UserLibraryWizardPage_title;
	public static String UserLibraryWizardPage_list_config_button;
	public static String UserLibraryWizardPage_list_label;
	public static String UserLibraryWizardPage_description_new;
	public static String UserLibraryWizardPage_description_edit;
	public static String UserLibraryWizardPage_error_selectentry;
	public static String UserLibraryWizardPage_error_selectonlyone;
	public static String UserLibraryWizardPage_error_alreadyoncp;
	public static String UserLibraryMarkerResolutionGenerator_changetouserlib_label;
	public static String UserLibraryMarkerResolutionGenerator_createuserlib_label;
	public static String UserLibraryMarkerResolutionGenerator_changetoother;
	public static String UserLibraryMarkerResolutionGenerator_error_creationfailed_message;
	public static String UserLibraryMarkerResolutionGenerator_error_title;
	public static String UserLibraryMarkerResolutionGenerator_error_applyingfailed_message;

	public static String GenerateBuildPathActionGroup_no_action_available;

	public static String NativeLibrariesDialog_extfiledialog_text;
	public static String NativeLibrariesDialog_intfiledialog_title;
	public static String NativeLibrariesDialog_intfiledialog_message;
	public static String NativeLibrariesDialog_location_label;
	public static String NativeLibrariesDialog_workspace_browse;
	public static String NativeLibrariesDialog_external_browse;
	public static String NativeLibrariesDialog_description;
	public static String NativeLibrariesDialog_title;
	public static String NativeLibrariesDialog_error_external_not_existing;
	public static String NativeLibrariesDialog_error_internal_not_existing;

	public static String NewContainerWizardPage_warning_inside_classfolder;

	public static String CPListLabelProvider_non_modifiable_attribute;
	public static String CPListLabelProvider_access_rules_label;
	public static String CPListLabelProvider_container_access_rules_singular;
	public static String CPListLabelProvider_container_access_rules_plural;
	public static String CPListLabelProvider_container_no_access_rules;
	public static String CPListLabelProvider_missing;

	public static String NativeLibrariesDialog_external_message;
	public static String SourceAttachmentBlock_extfolderdialog_message;
	public static String SourceAttachmentBlock_workspace_radiolabel;
	public static String AccessRulesDialog_severity_info_with_link;
	public static String AccessRulesDialog_severity_info_no_link;
	public static String AccessRulesDialog_severity_error;
	public static String AccessRulesDialog_severity_warning;
	public static String AccessRulesDialog_severity_info;
	public static String AccessRulesDialog_severity_ignore;
	public static String AccessRulesDialog_switch_dialog_title;
	public static String AccessRulesDialog_switch_dialog_message;
	public static String ClasspathModifierQueries_confirm_remove_linked_folder_label;
	public static String ClasspathModifierQueries_confirm_remove_linked_folder_message;
	public static String ClasspathModifierQueries_delete_linked_folder;
	public static String ClasspathModifierQueries_do_not_delete_linked_folder;
	public static String EditVariableEntryDialog_filename_empty;

	public static String CPListLabelProvider_external_annotations_location_label;

	public static String NewModuleInfoWizard_title;
	public static String NewModuleInfoWizard_updateProject_job;
	public static String NewModuleInfoWizardPage_title;
	public static String NewModuleInfoWizardPage_description;
	public static String NewModuleInfoWizardPage_module_label;
	public static String NewModuleInfoWizardPage_error_InvalidModuleName;
	public static String NewModuleInfoWizardPage_warning_DiscouragedModuleName;
	public static String NewModuleInfoWizardPage_error_EnterName;

	public static String ReleaseAttributeConfiguration_defaultReleaseName;
	public static String ReleaseAttributeConfiguration_dialogTitle;
	public static String ReleaseAttributeConfiguration_nameLabel;
	public static String ReleaseAttributeConfiguration_path;
	public static String ReleaseAttributeConfiguration_warning;

	static {
		NLS.initializeMessages(BUNDLE_NAME, NewWizardMessages.class);
	}
}
