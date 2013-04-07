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
package org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.editors.packagejson;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.AngularJSIDEUIPlugin;
import org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.editors.FormLayoutFactory;

/**
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public class AngularJSPackageJsonDevelopmentFormPage extends FormPage {
	/**
	 * The identifier of the form page.
	 */
	private static final String FORM_PAGE_ID = "org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.editors.packagejson.development"; //$NON-NLS-1$

	private DevelopmentDependenciesSection developmentDependenciesSection;

	private ScriptsSection scriptsSection;

	public AngularJSPackageJsonDevelopmentFormPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, "Development");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.forms.editor.FormPage#createFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void createFormContent(IManagedForm managedForm) {
		super.createFormContent(managedForm);
		ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		toolkit.decorateFormHeading(form.getForm());

		form.setImage(AngularJSIDEUIPlugin.getDefault().getImage(
				"/icons/editors/AngularJS-PackageJsonEditor-Icon.png"));
		form.setText("Dependencies");

		this.fillBody(managedForm, toolkit);
	}

	/**
	 * Fills the body of the form.
	 * 
	 * @param managedForm
	 *            The managed form
	 * @param toolkit
	 *            The toolkit
	 */
	private void fillBody(IManagedForm managedForm, FormToolkit toolkit) {
		Composite body = managedForm.getForm().getBody();
		body.setLayout(FormLayoutFactory.createFormGridLayout(false, 2));

		this.developmentDependenciesSection = new DevelopmentDependenciesSection(this, body);
		managedForm.addPart(this.developmentDependenciesSection);

		this.scriptsSection = new ScriptsSection(this, body);
		managedForm.addPart(this.scriptsSection);
	}
}
