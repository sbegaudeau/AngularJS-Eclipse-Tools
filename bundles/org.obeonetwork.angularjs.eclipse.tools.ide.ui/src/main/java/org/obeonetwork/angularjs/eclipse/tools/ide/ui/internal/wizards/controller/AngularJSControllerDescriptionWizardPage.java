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
package org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.wizards.controller;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.AngularJSIDEUIMessages;
import org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.AngularJSIDEUIPlugin;
import org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.wizards.AbstractAngularJSDescriptionWizardPage;
import org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.wizards.AngularJSDialogField;
import org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.wizards.AngularJSStringDialogField;

/**
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public class AngularJSControllerDescriptionWizardPage extends AbstractAngularJSDescriptionWizardPage {
	private AngularJSStringDialogField controllerNameDialogField;

	/**
	 * The constructor.
	 * 
	 * @param selection
	 *            The current selection
	 */
	public AngularJSControllerDescriptionWizardPage(IStructuredSelection selection) {
		super(AngularJSIDEUIMessages.getString("AngularJSControllerDescriptionWizardPage.Name")); //$NON-NLS-1$
		this.setTitle(AngularJSIDEUIMessages.getString("AngularJSControllerDescriptionWizardPage.Title")); //$NON-NLS-1$
		this.setDescription(AngularJSIDEUIMessages
				.getString("AngularJSControllerDescriptionWizardPage.Description")); //$NON-NLS-1$

		this.init(selection);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		this.initializeDialogUnits(parent);
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setFont(parent.getFont());

		int nColumns = 4;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = nColumns;
		composite.setLayout(gridLayout);

		this.createContainerControls(composite, nColumns);
		this.createModuleNameControls(composite, nColumns);
		this.createSeparatorControls(composite, nColumns);
		this.createControllerNameControls(composite, nColumns);
		this.createServiceDependenciesGroupControls(composite, nColumns);
		this.createCommentsLicenseAndCopyrightsControls(composite, nColumns);

		this.setControl(composite);
	}

	/**
	 * @param parent
	 * @param nColumns
	 */
	private void createControllerNameControls(Composite parent, int nColumns) {
		this.controllerNameDialogField = new AngularJSStringDialogField();
		this.controllerNameDialogField.setLabelText("Controller Name:");
		this.controllerNameDialogField.doFillIntoGrid(parent, nColumns - 1);
		AngularJSDialogField.createEmptySpace(parent);
	}

	public String getControllerName() {
		return this.controllerNameDialogField.getText();
	}

	public String getControllerFileContent() {
		final String ls = System.lineSeparator();

		StringBuilder stringBuilder = new StringBuilder();
		if (this.shouldGenerateCommentLicenseAndCopyrightHeader()) {
			stringBuilder.append("/***************************************************" + ls);
			stringBuilder.append(" * <copyright and license>" + ls);
			stringBuilder.append(" ***************************************************/" + ls);
			stringBuilder.append("" + ls);
			stringBuilder.append("'use strict';" + ls);
			stringBuilder.append("" + ls);
			stringBuilder.append("/*jslint indent: 2*/" + ls);
			stringBuilder.append("/*global angular*/" + ls);
			stringBuilder.append("" + ls);
			stringBuilder.append("/**" + ls);
			stringBuilder.append(" * The documentation of the controller." + ls);
			stringBuilder.append(" */" + ls);
		}
		stringBuilder.append("angular.module('" + this.getModuleName() + "').controller('"
				+ this.controllerNameDialogField.getText() + "', [" + this.controllerDependenciesName()
				+ " function(" + this.controllerDependenciesParameter() + ") {" + ls);
		stringBuilder.append("  $scope.somethingAmazing = ['one', 'two', 'three'];" + ls);
		stringBuilder.append("}]);" + ls);
		stringBuilder.append("" + ls);
		return stringBuilder.toString();
	}

	/**
	 * @return
	 */
	private String controllerDependenciesName() {
		StringBuilder stringBuilder = new StringBuilder();
		List<String> controllerDependencies = this.getDependencies();
		for (String controllerDependency : controllerDependencies) {
			stringBuilder.append("'" + controllerDependency + "', ");
		}
		return stringBuilder.toString().trim();
	}

	/**
	 * @return
	 */
	private String controllerDependenciesParameter() {
		StringBuilder stringBuilder = new StringBuilder();
		List<String> serviceDependencies = this.getDependencies();
		for (String serviceDependency : serviceDependencies) {
			if (serviceDependencies.indexOf(serviceDependency) == serviceDependencies.size() - 1) {
				stringBuilder.append(serviceDependency);
			} else {
				stringBuilder.append(serviceDependency + ", ");
			}
		}
		return stringBuilder.toString().trim();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.wizard.WizardPage#getImage()
	 */
	@Override
	public Image getImage() {
		return AngularJSIDEUIPlugin.getDefault().getImage(
				"icons/wizards/AngularJS-Wizard-Controller-Title.png"); //$NON-NLS-1$
	}
}
