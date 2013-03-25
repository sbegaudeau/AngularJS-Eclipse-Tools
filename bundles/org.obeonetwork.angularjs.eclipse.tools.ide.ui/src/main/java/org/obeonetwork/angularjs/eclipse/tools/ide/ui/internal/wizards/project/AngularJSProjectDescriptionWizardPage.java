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
package org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.wizards.project;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.AngularJSIDEUIMessages;
import org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.AngularJSIDEUIPlugin;

/**
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public class AngularJSProjectDescriptionWizardPage extends WizardNewProjectCreationPage {
	/**
	 * The "Resource working set" ID.
	 */
	protected static final String RESOURCE_WORKING_SET_ID = "org.eclipse.ui.resourceWorkingSetPage"; //$NON-NLS-1$

	/**
	 * The "Java working set" ID.
	 */
	protected static final String JAVA_WORKING_SET_ID = "org.eclipse.jdt.ui.JavaWorkingSetPage"; //$NON-NLS-1$

	/**
	 * The current selection.
	 */
	private IStructuredSelection selection;

	/**
	 * The constructor.
	 * 
	 * @param structuredSelection
	 *            The current selection
	 */
	public AngularJSProjectDescriptionWizardPage(IStructuredSelection structuredSelection) {
		super(AngularJSIDEUIMessages.getString("AngularJSProjectDescriptionWizardPage.Name")); //$NON-NLS-1$
		this.setTitle(AngularJSIDEUIMessages.getString("AngularJSProjectDescriptionWizardPage.Title")); //$NON-NLS-1$
		this.setDescription(AngularJSIDEUIMessages
				.getString("AngularJSProjectDescriptionWizardPage.Description")); //$NON-NLS-1$
		this.selection = structuredSelection;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.dialogs.WizardNewProjectCreationPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		this.createWorkingSetGroup((Composite)getControl(), this.selection, new String[] {
				RESOURCE_WORKING_SET_ID, JAVA_WORKING_SET_ID });
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.wizard.WizardPage#getImage()
	 */
	@Override
	public Image getImage() {
		return AngularJSIDEUIPlugin.getDefault().getImage("icons/wizards/AngularJS-Wizard-Project-Title.png"); //$NON-NLS-1$
	}
}
