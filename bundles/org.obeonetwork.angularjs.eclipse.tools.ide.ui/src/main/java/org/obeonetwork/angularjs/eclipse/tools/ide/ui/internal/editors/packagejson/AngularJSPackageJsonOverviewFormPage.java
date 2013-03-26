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

import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.AngularJSIDEUIMessages;

/**
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public class AngularJSPackageJsonOverviewFormPage extends FormPage {

	/**
	 * The identifier of the form page.
	 */
	private static final String FORM_PAGE_ID = "org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.editors.packagejson.overview"; //$NON-NLS-1$

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

}
