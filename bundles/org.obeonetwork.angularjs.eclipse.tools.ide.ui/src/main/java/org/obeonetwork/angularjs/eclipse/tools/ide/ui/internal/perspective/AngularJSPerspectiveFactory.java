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
package org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public class AngularJSPerspectiveFactory implements IPerspectiveFactory {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
	 */
	@Override
	public void createInitialLayout(IPageLayout layout) {
		final float oneQuarter = 0.25f;
		final float threeQuarter = 0.75f;

		String editorArea = layout.getEditorArea();

		IFolderLayout leftFolder = layout.createFolder("left", IPageLayout.LEFT, oneQuarter, editorArea); //$NON-NLS-1$
		leftFolder.addView("org.eclipse.jdt.ui.PackageExplorer");

		IFolderLayout bottomFolder = layout.createFolder(
				"bottom", IPageLayout.BOTTOM, threeQuarter, editorArea); //$NON-NLS-1$
		bottomFolder.addView(IPageLayout.ID_PROBLEM_VIEW);
		bottomFolder.addPlaceholder("org.eclipse.ui.console.ConsoleView");

		layout.addView(IPageLayout.ID_OUTLINE, IPageLayout.RIGHT, threeQuarter, editorArea);

		layout.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET);
		// views - console
		layout.addShowViewShortcut("org.eclipse.ui.console.ConsoleView");

		// views - workbench
		layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
		layout.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);

		layout.addNewWizardShortcut("org.obeonetwork.angularjs.eclipse.tools.ide.ui.wizards.project"); //$NON-NLS-1$
		layout.addNewWizardShortcut("org.obeonetwork.angularjs.eclipse.tools.ide.ui.wizards.module"); //$NON-NLS-1$
		layout.addNewWizardShortcut("org.obeonetwork.angularjs.eclipse.tools.ide.ui.wizards.directive"); //$NON-NLS-1$
		layout.addNewWizardShortcut("org.obeonetwork.angularjs.eclipse.tools.ide.ui.wizards.controller"); //$NON-NLS-1$
		layout.addNewWizardShortcut("org.obeonetwork.angularjs.eclipse.tools.ide.ui.wizards.service"); //$NON-NLS-1$

	}

}
