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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.ide.ResourceUtil;
import org.obeonetwork.angularjs.eclipse.tools.ide.core.utils.IAngularJSConstants;
import org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.AngularJSIDEUIPlugin;

/**
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public class AngularJSPackageJsonEditorMatchingStrategy implements IEditorMatchingStrategy {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.IEditorMatchingStrategy#matches(org.eclipse.ui.IEditorReference,
	 *      org.eclipse.ui.IEditorInput)
	 */
	@Override
	public boolean matches(IEditorReference editorRef, IEditorInput input) {
		IFile inputFile = ResourceUtil.getFile(input);
		if (input instanceof IFileEditorInput && inputFile != null) {
			try {
				boolean isFileNameValid = IAngularJSConstants.PACKAGE_JSON_FILENAME.equals(inputFile
						.getName());
				boolean isProjetNatureValid = inputFile.getProject().hasNature(IAngularJSConstants.NATURE_ID);
				boolean isUnderTheProjectRoot = inputFile.getParent().equals(inputFile.getProject());

				boolean matches = isFileNameValid && isProjetNatureValid && isUnderTheProjectRoot;
				matches = matches || input.equals(editorRef.getEditorInput());
				return matches;
			} catch (CoreException e) {
				AngularJSIDEUIPlugin.log(e, true);
			}
		}
		return false;
	}

}
