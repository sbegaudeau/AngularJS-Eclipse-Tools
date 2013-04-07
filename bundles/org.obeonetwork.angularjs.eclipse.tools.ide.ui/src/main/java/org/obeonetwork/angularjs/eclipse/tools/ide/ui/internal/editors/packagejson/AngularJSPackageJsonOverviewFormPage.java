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
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.AngularJSIDEUIMessages;
import org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.AngularJSIDEUIPlugin;
import org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.editors.FormLayoutFactory;

/**
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public class AngularJSPackageJsonOverviewFormPage extends FormPage {

	/**
	 * The identifier of the form page.
	 */
	private static final String FORM_PAGE_ID = "org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.editors.packagejson.overview"; //$NON-NLS-1$

	private GeneralInformationSection informationSection;

	private ProjectManagementSection projectManagementSection;

	private BugsManagementSection bugsManagementSection;

	private DirectoriesSection directoriesSection;

	private ArchitectureSection architectureSection;

	private ProjectContentHyperlinks projectContentHyperlinksSection;

	private ProjectDependenciesHyperlink projectDependenciesHyperlinkSection;

	/**
	 * The constructor.
	 * 
	 * @param editor
	 *            The form editor
	 */
	public AngularJSPackageJsonOverviewFormPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AngularJSIDEUIMessages
				.getString("AngularJSPackageJsonOverviewFormPage.Title")); //$NON-NLS-1$
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
		form.setText(AngularJSIDEUIMessages.getString("AngularJSPackageJsonOverviewFormPage.Title"));

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
		body.setLayout(FormLayoutFactory.createFormTableWrapLayout(true, 2));

		Composite left = toolkit.createComposite(body);
		left.setLayout(FormLayoutFactory.createFormPaneTableWrapLayout(false, 1));
		left.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));

		this.informationSection = new GeneralInformationSection(this, left);
		managedForm.addPart(this.informationSection);

		this.projectManagementSection = new ProjectManagementSection(this, left);
		managedForm.addPart(this.projectManagementSection);

		this.bugsManagementSection = new BugsManagementSection(this, left);
		managedForm.addPart(this.bugsManagementSection);

		Composite right = toolkit.createComposite(body);
		right.setLayout(FormLayoutFactory.createFormPaneTableWrapLayout(false, 1));
		right.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));

		this.directoriesSection = new DirectoriesSection(this, right);
		managedForm.addPart(this.directoriesSection);

		this.architectureSection = new ArchitectureSection(this, right);
		managedForm.addPart(this.architectureSection);

		this.projectContentHyperlinksSection = new ProjectContentHyperlinks(this, right);
		managedForm.addPart(this.projectContentHyperlinksSection);

		this.projectDependenciesHyperlinkSection = new ProjectDependenciesHyperlink(this, right);
		managedForm.addPart(this.projectDependenciesHyperlinkSection);
	}

}
