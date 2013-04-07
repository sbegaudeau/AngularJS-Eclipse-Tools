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

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.forms.AbstractFormPart;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.editors.FormLayoutFactory;

/**
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public class ProjectManagementSection extends AbstractFormPart {

	public ProjectManagementSection(FormPage formPage, Composite parent) {
		FormToolkit toolkit = formPage.getManagedForm().getToolkit();
		Section section = toolkit.createSection(parent, ExpandableComposite.TITLE_BAR | Section.DESCRIPTION);
		section.setText("Project Management");
		section.setLayout(FormLayoutFactory.createClearTableWrapLayout(false, 1));
		TableWrapData data = new TableWrapData(TableWrapData.FILL_GRAB);
		section.setLayoutData(data);

		section.setDescription("This section describes the metadata of the project");
		Composite client = toolkit.createComposite(section);
		client.setLayout(FormLayoutFactory.createSectionClientTableWrapLayout(false, 3));
		section.setClient(client);

		IActionBars actionBars = formPage.getEditorSite().getActionBars();
		this.createProjectHomepageEntry(client, toolkit, actionBars);
		this.createProjectAuthorEntry(client, toolkit, actionBars);
		this.createProjectRepositoryTypeEntry(client, toolkit, actionBars);
		this.createProjectRepositoryURLEntry(client, toolkit, actionBars);
		this.createProjectLicenseTypeEntry(client, toolkit, actionBars);
		this.createProjectLicenseURLEntry(client, toolkit, actionBars);
		this.createProjectContributorsEntry(client, toolkit, actionBars);
	}

	private void createProjectHomepageEntry(Composite client, FormToolkit toolkit, IActionBars actionBars) {
		Label label = toolkit.createLabel(client, "Homepage:"); //$NON-NLS-1$
		label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));

		Text text = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		TableWrapData layoutData = new TableWrapData(TableWrapData.FILL);
		layoutData.colspan = 2;
		layoutData.grabHorizontal = true;
		text.setLayoutData(layoutData);
	}

	private void createProjectAuthorEntry(Composite client, FormToolkit toolkit, IActionBars actionBars) {
		Label label = toolkit.createLabel(client, "Author:"); //$NON-NLS-1$
		label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));

		Text text = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		TableWrapData layoutData = new TableWrapData(TableWrapData.FILL);
		layoutData.colspan = 2;
		layoutData.grabHorizontal = true;
		text.setLayoutData(layoutData);
	}

	private void createProjectRepositoryTypeEntry(Composite client, FormToolkit toolkit,
			IActionBars actionBars) {
		Label label = toolkit.createLabel(client, "Repository Type:"); //$NON-NLS-1$
		label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));

		Text text = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		TableWrapData layoutData = new TableWrapData(TableWrapData.FILL);
		layoutData.colspan = 2;
		layoutData.grabHorizontal = true;
		text.setLayoutData(layoutData);
	}

	private void createProjectRepositoryURLEntry(Composite client, FormToolkit toolkit, IActionBars actionBars) {
		Label label = toolkit.createLabel(client, "Repository URL:"); //$NON-NLS-1$
		label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));

		Text text = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		TableWrapData layoutData = new TableWrapData(TableWrapData.FILL);
		layoutData.colspan = 2;
		layoutData.grabHorizontal = true;
		text.setLayoutData(layoutData);
	}

	private void createProjectLicenseTypeEntry(Composite client, FormToolkit toolkit, IActionBars actionBars) {
		Label label = toolkit.createLabel(client, "License Type:"); //$NON-NLS-1$
		label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));

		Text text = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		TableWrapData layoutData = new TableWrapData(TableWrapData.FILL);
		layoutData.colspan = 2;
		layoutData.grabHorizontal = true;
		text.setLayoutData(layoutData);
	}

	private void createProjectLicenseURLEntry(Composite client, FormToolkit toolkit, IActionBars actionBars) {
		Label label = toolkit.createLabel(client, "License URL:"); //$NON-NLS-1$
		label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));

		Text text = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		TableWrapData layoutData = new TableWrapData(TableWrapData.FILL);
		layoutData.colspan = 2;
		layoutData.grabHorizontal = true;
		text.setLayoutData(layoutData);
	}

	private void createProjectContributorsEntry(Composite client, FormToolkit toolkit, IActionBars actionBars) {
		Label label = toolkit.createLabel(client, "Contributors:"); //$NON-NLS-1$
		label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));

		Text text = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		TableWrapData layoutData = new TableWrapData(TableWrapData.FILL);
		layoutData.colspan = 2;
		layoutData.grabHorizontal = true;
		text.setLayoutData(layoutData);
	}
}
