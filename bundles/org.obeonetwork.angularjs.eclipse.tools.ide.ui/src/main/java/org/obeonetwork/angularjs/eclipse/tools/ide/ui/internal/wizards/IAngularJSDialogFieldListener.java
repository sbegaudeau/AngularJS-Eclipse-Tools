/*******************************************************************************
 * Copyright (c) 2000, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Stephane Begaudeau (Obeo) - Refactoring for AngularJS
 *******************************************************************************/
package org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.wizards;

/**
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public interface IAngularJSDialogFieldListener {
	/**
	 * The dialog field has changed.
	 * 
	 * @param field
	 *            the dialog field that changed
	 */
	void dialogFieldChanged(AngularJSDialogField field);
}
