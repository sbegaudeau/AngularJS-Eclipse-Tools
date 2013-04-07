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
package org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.wizards.module;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.AngularJSIDEUIMessages;
import org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.AngularJSIDEUIPlugin;
import org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.wizards.AbstractAngularJSDescriptionWizardPage;
import org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.wizards.AngularJSDialogField;
import org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.wizards.AngularJSSelectionButtonDialogField;

/**
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public class AngularJSModuleDescriptionWizardPage extends AbstractAngularJSDescriptionWizardPage {
	private AngularJSSelectionButtonDialogField routeProviderDialogField;

	/**
	 * The constructor.
	 * 
	 * @param selection
	 *            The current selection
	 */
	public AngularJSModuleDescriptionWizardPage(IStructuredSelection selection) {
		super(AngularJSIDEUIMessages.getString("AngularJSModuleDescriptionWizardPage.Name")); //$NON-NLS-1$
		this.setTitle(AngularJSIDEUIMessages.getString("AngularJSModuleDescriptionWizardPage.Title")); //$NON-NLS-1$
		this.setDescription(AngularJSIDEUIMessages
				.getString("AngularJSModuleDescriptionWizardPage.Description")); //$NON-NLS-1$

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
		this.createSeparatorControls(composite, nColumns);
		this.createModuleNameControls(composite, nColumns);
		this.createModuleDependenciesControls(composite, nColumns);
		this.createServiceProviderDependenciesGroupControls(composite, nColumns);
		this.createCommentsLicenseAndCopyrightsControls(composite, nColumns);

		this.setControl(composite);
	}

	/**
	 * @param composite
	 * @param nColumns
	 */
	private void createServiceProviderDependenciesGroupControls(Composite parent, int nColumns) {
		Label label = new Label(parent, SWT.NONE);
		label.setText("Which standard AngularJS services provider would you like to inject in your module?");
		label.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false, nColumns, 1));

		this.routeProviderDialogField = new AngularJSSelectionButtonDialogField(SWT.CHECK);
		this.routeProviderDialogField.setLabelText("$routeProvider - Used for configuring routes");
		AngularJSDialogField.createEmptySpace(parent);
		this.routeProviderDialogField.doFillIntoGrid(parent, nColumns - 1);
		this.routeProviderDialogField.setSelection(true);
	}

	public String getModuleFileContent() {
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
			stringBuilder.append(" * The documentation of the module." + ls);
			stringBuilder.append(" */" + ls);
		}
		stringBuilder.append("angular.module('" + this.getModuleName() + "', ["
				+ this.getModuleDependencies() + "]).config([" + this.moduleServiceProviderDependenciesName()
				+ "function(" + this.moduleServiceProviderDependenciesParameter() + ") {" + ls);
		if (this.routeProviderDialogField.isSelected()) {
			stringBuilder.append("  /*$routeProvider" + ls);
			stringBuilder.append("    .when('home', {" + ls);
			stringBuilder.append("      templateUrl: 'views/home.html'," + ls);
			stringBuilder.append("      controller: 'HomeCtrl'" + ls);
			stringBuilder.append("    })" + ls);
			stringBuilder.append("    .otherwise({" + ls);
			stringBuilder.append("      redirectTo: '/404.html'" + ls);
			stringBuilder.append("    });*/" + ls);
		}
		stringBuilder.append("}]);" + ls);
		stringBuilder.append("" + ls);
		return stringBuilder.toString();
	}

	/**
	 * @return
	 */
	private String moduleServiceProviderDependenciesName() {
		StringBuilder stringBuilder = new StringBuilder();
		if (this.routeProviderDialogField.isSelected()) {
			stringBuilder.append("'$routeProvider', ");
		}
		return stringBuilder.toString();
	}

	/**
	 * @return
	 */
	private String moduleServiceProviderDependenciesParameter() {
		StringBuilder stringBuilder = new StringBuilder();
		if (this.routeProviderDialogField.isSelected()) {
			stringBuilder.append("$routeProvider");
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
		return AngularJSIDEUIPlugin.getDefault().getImage("icons/wizards/AngularJS-Wizard-Module-Title.png"); //$NON-NLS-1$
	}
}
