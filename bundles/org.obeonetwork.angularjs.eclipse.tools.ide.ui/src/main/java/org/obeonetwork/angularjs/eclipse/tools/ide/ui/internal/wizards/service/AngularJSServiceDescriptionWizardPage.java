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
package org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.wizards.service;

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
public class AngularJSServiceDescriptionWizardPage extends AbstractAngularJSDescriptionWizardPage {
	private AngularJSStringDialogField serviceNameDialogField;

	/**
	 * The constructor.
	 * 
	 * @param selection
	 *            The current selection
	 */
	public AngularJSServiceDescriptionWizardPage(IStructuredSelection selection) {
		super(AngularJSIDEUIMessages.getString("AngularJSServiceDescriptionWizardPage.Name")); //$NON-NLS-1$
		this.setTitle(AngularJSIDEUIMessages.getString("AngularJSServiceDescriptionWizardPage.Title")); //$NON-NLS-1$
		this.setDescription(AngularJSIDEUIMessages
				.getString("AngularJSServiceDescriptionWizardPage.Description")); //$NON-NLS-1$

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
		this.createModuleDependenciesControls(composite, nColumns);
		this.createSeparatorControls(composite, nColumns);
		this.createServiceNameControls(composite, nColumns);
		this.createServiceDependenciesGroupControls(composite, nColumns);
		this.createCommentsLicenseAndCopyrightsControls(composite, nColumns);

		this.setControl(composite);
	}

	protected void createServiceNameControls(Composite parent, int nColumns) {
		this.serviceNameDialogField = new AngularJSStringDialogField();
		this.serviceNameDialogField.setLabelText("Service Name:");
		this.serviceNameDialogField.doFillIntoGrid(parent, nColumns - 1);
		AngularJSDialogField.createEmptySpace(parent);
	}

	public String getServiceName() {
		return this.serviceNameDialogField.getText();
	}

	public String getServiceFileContent() {
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
			stringBuilder.append(" * The documentation of the service." + ls);
			stringBuilder.append(" */" + ls);
		}
		stringBuilder.append("angular.module('" + this.getModuleName() + '.'
				+ this.serviceNameDialogField.getText() + "', [" + this.getModuleDependencies()
				+ "]).factory('" + this.serviceNameDialogField.getText() + "', ["
				+ this.serviceDependenciesName() + " function(" + this.serviceDependenciesParameter() + ") {"
				+ ls);
		stringBuilder.append("  var " + this.serviceNameDialogField.getText() + " = {};" + ls);
		stringBuilder.append("  return " + this.serviceNameDialogField.getText() + ";" + ls);
		stringBuilder.append("}]);" + ls);
		stringBuilder.append("" + ls);
		return stringBuilder.toString();
	}

	/**
	 * @return
	 */
	private String serviceDependenciesName() {
		StringBuilder stringBuilder = new StringBuilder();
		List<String> serviceDependencies = this.getDependencies();
		for (String serviceDependency : serviceDependencies) {
			stringBuilder.append("'" + serviceDependency + "', ");
		}
		return stringBuilder.toString().trim();
	}

	/**
	 * @return
	 */
	private String serviceDependenciesParameter() {
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
		return AngularJSIDEUIPlugin.getDefault().getImage("icons/wizards/AngularJS-Wizard-Service-Title.png"); //$NON-NLS-1$
	}
}
