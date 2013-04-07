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
public class DirectoriesSection extends AbstractFormPart {

	public DirectoriesSection(FormPage formPage, Composite parent) {
		FormToolkit toolkit = formPage.getManagedForm().getToolkit();
		Section section = toolkit.createSection(parent, ExpandableComposite.TITLE_BAR | Section.DESCRIPTION);
		section.setText("Directories");
		section.setLayout(FormLayoutFactory.createClearTableWrapLayout(false, 1));
		TableWrapData data = new TableWrapData(TableWrapData.FILL_GRAB);
		section.setLayoutData(data);

		section.setDescription("This section describes general information about this project");
		Composite client = toolkit.createComposite(section);
		client.setLayout(FormLayoutFactory.createSectionClientTableWrapLayout(false, 3));
		section.setClient(client);

		IActionBars actionBars = formPage.getEditorSite().getActionBars();
		this.createProjectLibDirectoriesEntry(client, toolkit, actionBars);
		this.createProjectBinDirectoriesEntry(client, toolkit, actionBars);
		this.createProjectManDirectoriesEntry(client, toolkit, actionBars);
		this.createProjectDocDirectoriesEntry(client, toolkit, actionBars);
		this.createProjectExampleDirectoriesEntry(client, toolkit, actionBars);
	}

	private void createProjectLibDirectoriesEntry(Composite client, FormToolkit toolkit,
			IActionBars actionBars) {
		Label label = toolkit.createLabel(client, "Librairies:"); //$NON-NLS-1$
		label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));

		Text text = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		TableWrapData layoutData = new TableWrapData(TableWrapData.FILL);
		layoutData.colspan = 2;
		layoutData.grabHorizontal = true;
		text.setLayoutData(layoutData);
	}

	private void createProjectBinDirectoriesEntry(Composite client, FormToolkit toolkit,
			IActionBars actionBars) {
		Label label = toolkit.createLabel(client, "Binaries:"); //$NON-NLS-1$
		label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));

		Text text = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		TableWrapData layoutData = new TableWrapData(TableWrapData.FILL);
		layoutData.colspan = 2;
		layoutData.grabHorizontal = true;
		text.setLayoutData(layoutData);
	}

	private void createProjectManDirectoriesEntry(Composite client, FormToolkit toolkit,
			IActionBars actionBars) {
		Label label = toolkit.createLabel(client, "Manual:"); //$NON-NLS-1$
		label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));

		Text text = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		TableWrapData layoutData = new TableWrapData(TableWrapData.FILL);
		layoutData.colspan = 2;
		layoutData.grabHorizontal = true;
		text.setLayoutData(layoutData);
	}

	private void createProjectDocDirectoriesEntry(Composite client, FormToolkit toolkit,
			IActionBars actionBars) {
		Label label = toolkit.createLabel(client, "Documentation:"); //$NON-NLS-1$
		label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));

		Text text = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		TableWrapData layoutData = new TableWrapData(TableWrapData.FILL);
		layoutData.colspan = 2;
		layoutData.grabHorizontal = true;
		text.setLayoutData(layoutData);
	}

	private void createProjectExampleDirectoriesEntry(Composite client, FormToolkit toolkit,
			IActionBars actionBars) {
		Label label = toolkit.createLabel(client, "Examples:"); //$NON-NLS-1$
		label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));

		Text text = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		TableWrapData layoutData = new TableWrapData(TableWrapData.FILL);
		layoutData.colspan = 2;
		layoutData.grabHorizontal = true;
		text.setLayoutData(layoutData);
	}
}
