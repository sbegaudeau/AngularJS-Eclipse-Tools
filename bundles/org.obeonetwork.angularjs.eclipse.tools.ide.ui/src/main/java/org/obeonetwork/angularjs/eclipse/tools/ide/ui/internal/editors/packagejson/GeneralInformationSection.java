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
public class GeneralInformationSection extends AbstractFormPart {

	/**
	 * The constructor.
	 * 
	 * @param formPage
	 * @param left
	 *            The composite in which the section will be created.
	 */
	public GeneralInformationSection(FormPage formPage, Composite parent) {
		FormToolkit toolkit = formPage.getManagedForm().getToolkit();
		Section section = toolkit.createSection(parent, ExpandableComposite.TITLE_BAR | Section.DESCRIPTION);
		section.setText("General Information");
		section.setLayout(FormLayoutFactory.createClearTableWrapLayout(false, 1));
		TableWrapData data = new TableWrapData(TableWrapData.FILL_GRAB);
		section.setLayoutData(data);

		section.setDescription("This section describes general information about this project");
		Composite client = toolkit.createComposite(section);
		client.setLayout(FormLayoutFactory.createSectionClientTableWrapLayout(false, 3));
		section.setClient(client);

		IActionBars actionBars = formPage.getEditorSite().getActionBars();
		this.createProjectNameEntry(client, toolkit, actionBars);
		this.createProjectDescriptionEntry(client, toolkit, actionBars);
		this.createProjectVersionEntry(client, toolkit, actionBars);
		this.createProjectKeywordsEntry(client, toolkit, actionBars);
		this.createProjectMainEntry(client, toolkit, actionBars);
	}

	/**
	 * Creates the projectId entry of the section.
	 * 
	 * @param client
	 *            The client
	 * @param toolkit
	 *            The toolkit
	 * @param actionBars
	 *            The actions bar
	 */
	private void createProjectNameEntry(Composite client, FormToolkit toolkit, IActionBars actionBars) {
		Label label = toolkit.createLabel(client, "Project Name:"); //$NON-NLS-1$
		label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));

		Text text = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		TableWrapData layoutData = new TableWrapData(TableWrapData.FILL);
		layoutData.colspan = 2;
		layoutData.grabHorizontal = true;
		text.setLayoutData(layoutData);

	}

	private void createProjectDescriptionEntry(Composite client, FormToolkit toolkit, IActionBars actionBars) {
		Label label = toolkit.createLabel(client, "Description:"); //$NON-NLS-1$
		label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));

		Text text = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		TableWrapData layoutData = new TableWrapData(TableWrapData.FILL);
		layoutData.colspan = 2;
		layoutData.grabHorizontal = true;
		text.setLayoutData(layoutData);
	}

	private void createProjectVersionEntry(Composite client, FormToolkit toolkit, IActionBars actionBars) {
		Label label = toolkit.createLabel(client, "Version:"); //$NON-NLS-1$
		label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));

		Text text = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		TableWrapData layoutData = new TableWrapData(TableWrapData.FILL);
		layoutData.colspan = 2;
		layoutData.grabHorizontal = true;
		text.setLayoutData(layoutData);
	}

	private void createProjectKeywordsEntry(Composite client, FormToolkit toolkit, IActionBars actionBars) {
		Label label = toolkit.createLabel(client, "Keywords:"); //$NON-NLS-1$
		label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));

		Text text = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		TableWrapData layoutData = new TableWrapData(TableWrapData.FILL);
		layoutData.colspan = 2;
		layoutData.grabHorizontal = true;
		text.setLayoutData(layoutData);
	}

	private void createProjectMainEntry(Composite client, FormToolkit toolkit, IActionBars actionBars) {
		Label label = toolkit.createLabel(client, "Main:"); //$NON-NLS-1$
		label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));

		Text text = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		TableWrapData layoutData = new TableWrapData(TableWrapData.FILL);
		layoutData.colspan = 2;
		layoutData.grabHorizontal = true;
		text.setLayoutData(layoutData);
	}
}
