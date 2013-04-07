/*******************************************************************************
 * Copyright (c) 2013 Stephane Begaudeau (Obeo).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Stephane Begaudeau (Obeo) - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.wizards;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

/**
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public abstract class AbstractAngularJSDescriptionWizardPage extends WizardPage {

	/**
	 * The container.
	 */
	private IContainer container;

	private AngularJSStringButtonDialogField containerDialogField;

	private AngularJSStringDialogField moduleNameDialogField;

	private AngularJSStringDialogField moduleDependenciesDialogField;

	private AngularJSSelectionButtonDialogField commentLicenseAndCopyrightDialogField;

	private AngularJSSelectionButtonDialogField scopeDependencyDialogField;

	private AngularJSSelectionButtonDialogField rootScopeDependencyDialogField;

	private AngularJSSelectionButtonDialogField qDependencyDialogField;

	private AngularJSSelectionButtonDialogField routeDependencyDialogField;

	private AngularJSSelectionButtonDialogField routeParamsDependencyDialogField;

	private AngularJSSelectionButtonDialogField httpParamsDependencyDialogField;

	private AngularJSSelectionButtonDialogField resourceParamsDependencyDialogField;

	/**
	 * The constructor.
	 * 
	 * @param pageName
	 *            The name of the page.
	 */
	protected AbstractAngularJSDescriptionWizardPage(String pageName) {
		super(pageName);
	}

	protected void init(IStructuredSelection iStructuredSelection) {
		Object firstElement = iStructuredSelection.getFirstElement();
		if (firstElement instanceof IContainer) {
			IContainer container = (IContainer)firstElement;
			this.container = container;
		} else if (firstElement instanceof IFile) {
			IFile file = (IFile)firstElement;
			this.container = file.getParent();
		}
	}

	protected void createContainerControls(Composite parent, int nColumns) {
		ContainerFieldAdapter adapter = new ContainerFieldAdapter();
		this.containerDialogField = new AngularJSStringButtonDialogField(adapter);
		this.containerDialogField.setDialogFieldListener(adapter);
		this.containerDialogField.setLabelText("Location: ");
		this.containerDialogField.setButtonLabel("Browse...");
		this.containerDialogField.setTextWithoutUpdate(this.container.getFullPath().toString());
		this.containerDialogField.doFillIntoGrid(parent, nColumns);
	}

	protected void createModuleNameControls(Composite parent, int nColumns) {
		this.moduleNameDialogField = new AngularJSStringDialogField();
		this.moduleNameDialogField.setLabelText("Module Name:");
		this.moduleNameDialogField.setText("");
		this.moduleNameDialogField.doFillIntoGrid(parent, nColumns - 1);
		AngularJSDialogField.createEmptySpace(parent);
	}

	protected void createModuleDependenciesControls(Composite parent, int nColumns) {
		this.moduleDependenciesDialogField = new AngularJSStringDialogField();
		this.moduleDependenciesDialogField.setLabelText("Module Dependencies:");
		this.moduleDependenciesDialogField.setText("");
		this.moduleDependenciesDialogField.doFillIntoGrid(parent, nColumns - 1);
		AngularJSDialogField.createEmptySpace(parent);
	}

	protected void createSeparatorControls(Composite parent, int nColumns) {
		AngularJSSeparator separator = new AngularJSSeparator(SWT.SEPARATOR | SWT.HORIZONTAL);
		separator.doFillIntoGrid(parent, nColumns, convertHeightInCharsToPixels(1));
	}

	protected void createServiceDependenciesGroupControls(Composite parent, int nColumns) {
		Label label = new Label(parent, SWT.NONE);
		label.setText("Which standard AngularJS services would you like to inject in your service?");
		label.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false, nColumns, 1));

		this.scopeDependencyDialogField = new AngularJSSelectionButtonDialogField(SWT.CHECK);
		this.scopeDependencyDialogField.setLabelText("$scope - the current scope");
		AngularJSDialogField.createEmptySpace(parent);
		this.scopeDependencyDialogField.doFillIntoGrid(parent, nColumns - 1);
		this.scopeDependencyDialogField.setSelection(true);

		this.rootScopeDependencyDialogField = new AngularJSSelectionButtonDialogField(SWT.CHECK);
		this.rootScopeDependencyDialogField.setLabelText("$rootScope - the root scope of the module");
		AngularJSDialogField.createEmptySpace(parent);
		this.rootScopeDependencyDialogField.doFillIntoGrid(parent, nColumns - 1);

		this.qDependencyDialogField = new AngularJSSelectionButtonDialogField(SWT.CHECK);
		this.qDependencyDialogField.setLabelText("$q - AngularJS promise/deferred implementation");
		AngularJSDialogField.createEmptySpace(parent);
		this.qDependencyDialogField.doFillIntoGrid(parent, nColumns - 1);

		this.routeDependencyDialogField = new AngularJSSelectionButtonDialogField(SWT.CHECK);
		this.routeDependencyDialogField.setLabelText("$route - routes manipulation");
		AngularJSDialogField.createEmptySpace(parent);
		this.routeDependencyDialogField.doFillIntoGrid(parent, nColumns - 1);

		this.routeParamsDependencyDialogField = new AngularJSSelectionButtonDialogField(SWT.CHECK);
		this.routeParamsDependencyDialogField.setLabelText("$routeParams - current set of route parameters");
		AngularJSDialogField.createEmptySpace(parent);
		this.routeParamsDependencyDialogField.doFillIntoGrid(parent, nColumns - 1);

		this.httpParamsDependencyDialogField = new AngularJSSelectionButtonDialogField(SWT.CHECK);
		this.httpParamsDependencyDialogField.setLabelText("$http - HTTP communication");
		AngularJSDialogField.createEmptySpace(parent);
		this.httpParamsDependencyDialogField.doFillIntoGrid(parent, nColumns - 1);

		this.resourceParamsDependencyDialogField = new AngularJSSelectionButtonDialogField(SWT.CHECK);
		this.resourceParamsDependencyDialogField.setLabelText("$resource - High-level RESTful communication");
		AngularJSDialogField.createEmptySpace(parent);
		this.resourceParamsDependencyDialogField.doFillIntoGrid(parent, nColumns - 1);
	}

	protected void createCommentsLicenseAndCopyrightsControls(Composite parent, int nColumns) {
		Label label = new Label(parent, SWT.NONE);
		label.setText("Do you want to generate jslint, jshint comments and the copyright header?");
		label.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false, nColumns, 1));

		this.commentLicenseAndCopyrightDialogField = new AngularJSSelectionButtonDialogField(SWT.CHECK);
		this.commentLicenseAndCopyrightDialogField
				.setLabelText("Generate the comments, license and copyright header");
		AngularJSDialogField.createEmptySpace(parent);
		this.commentLicenseAndCopyrightDialogField.doFillIntoGrid(parent, nColumns - 1);
		this.commentLicenseAndCopyrightDialogField.setSelection(true);
	}

	private class ContainerFieldAdapter implements IAngularJSStringButtonAdapter, IAngularJSDialogFieldListener {

		@Override
		public void changeControlPressed(AngularJSDialogField field) {
			containerChangeControlPressed(field);
		}

		@Override
		public void dialogFieldChanged(AngularJSDialogField field) {
			containerDialogFieldChanged(field);
		}
	}

	private void containerChangeControlPressed(AngularJSDialogField field) {
		if (field == this.containerDialogField) {
			// Look for a container in the workspace
			ContainerSelectionDialog dialog = new ContainerSelectionDialog(getShell(), this.container, false,
					"Select the folder in which the file will be created");
			if (dialog.open() == Window.OK) {
				Object[] result = dialog.getResult();
				if (result.length > 0 && result[0] instanceof IPath) {
					IPath path = (IPath)result[0];

					IFolder folder = ResourcesPlugin.getWorkspace().getRoot().getFolder(path);
					this.container = folder;
					this.containerDialogField.setText(this.container.getFullPath().toString());
				}
			}
		}
	}

	private void containerDialogFieldChanged(AngularJSDialogField field) {
		if (field == this.containerDialogField) {
			// do nothing
		}
	}

	public IContainer getFileContainer() {
		return this.container;
	}

	public String getModuleName() {
		return this.moduleNameDialogField.getText();
	}

	public String getModuleDependencies() {
		return this.moduleDependenciesDialogField.getText();
	}

	protected List<String> getDependencies() {
		List<String> dependencies = new ArrayList<>();

		if (this.scopeDependencyDialogField.isSelected()) {
			dependencies.add("$scope");
		}
		if (this.rootScopeDependencyDialogField.isSelected()) {
			dependencies.add("$rootScope");
		}
		if (this.qDependencyDialogField.isSelected()) {
			dependencies.add("$q");
		}
		if (this.routeDependencyDialogField.isSelected()) {
			dependencies.add("$route");
		}
		if (this.routeParamsDependencyDialogField.isSelected()) {
			dependencies.add("$routeParams");
		}
		if (this.httpParamsDependencyDialogField.isSelected()) {
			dependencies.add("$http");
		}
		if (this.resourceParamsDependencyDialogField.isSelected()) {
			dependencies.add("$resource");
		}

		return dependencies;
	}

	public boolean shouldGenerateCommentLicenseAndCopyrightHeader() {
		return this.commentLicenseAndCopyrightDialogField.isSelected();
	}
}
