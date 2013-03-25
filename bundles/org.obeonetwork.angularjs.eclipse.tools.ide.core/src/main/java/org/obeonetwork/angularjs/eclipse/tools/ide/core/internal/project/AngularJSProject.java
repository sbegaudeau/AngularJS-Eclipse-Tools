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
package org.obeonetwork.angularjs.eclipse.tools.ide.core.internal.project;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.obeonetwork.angularjs.eclipse.tools.ide.core.internal.AngularJSIDECorePlugin;

/**
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public class AngularJSProject {
	/**
	 * The project.
	 */
	private IProject project;

	/**
	 * The constructor.
	 * 
	 * @param project
	 *            The Eclipse project
	 */
	public AngularJSProject(IProject project) {
		this.project = project;
	}

	/**
	 * Initializes the project.
	 * 
	 * @param monitor
	 *            The progress monitor
	 */
	public void initialize(IProgressMonitor monitor) {
		IFolder appFolder = this.project.getFolder("app"); //$NON-NLS-1$
		if (!appFolder.exists()) {
			try {
				appFolder.create(true, false, monitor);

				IFolder scriptsFolder = appFolder.getFolder("scripts"); //$NON-NLS-1$
				scriptsFolder.create(true, false, monitor);

				IFolder controllersFolder = scriptsFolder.getFolder("controllers"); //$NON-NLS-1$
				controllersFolder.create(true, false, monitor);

				IFolder directivesFolder = scriptsFolder.getFolder("directives"); //$NON-NLS-1$
				directivesFolder.create(true, false, monitor);

				IFolder servicesFolder = scriptsFolder.getFolder("services"); //$NON-NLS-1$
				servicesFolder.create(true, false, monitor);

				IFolder stylesFolder = appFolder.getFolder("styles"); //$NON-NLS-1$
				stylesFolder.create(true, false, monitor);

				IFolder viewsFolder = appFolder.getFolder("views"); //$NON-NLS-1$
				viewsFolder.create(true, false, monitor);
			} catch (CoreException e) {
				AngularJSIDECorePlugin.log(e, true);
			}
		}

		IFolder testsFolder = this.project.getFolder("tests"); //$NON-NLS-1$
		if (!testsFolder.exists()) {
			try {
				testsFolder.create(true, false, monitor);

				IFolder unitFolder = testsFolder.getFolder("unit"); //$NON-NLS-1$
				unitFolder.create(true, false, monitor);

				IFolder controllersFolder = unitFolder.getFolder("controllers"); //$NON-NLS-1$
				controllersFolder.create(true, false, monitor);

				IFolder directivesFolder = unitFolder.getFolder("directives"); //$NON-NLS-1$
				directivesFolder.create(true, false, monitor);

				IFolder servicesFolder = unitFolder.getFolder("services"); //$NON-NLS-1$
				servicesFolder.create(true, false, monitor);

				IFolder end2endFolder = testsFolder.getFolder("end2end"); //$NON-NLS-1$
				end2endFolder.create(true, false, monitor);
			} catch (CoreException e) {
				AngularJSIDECorePlugin.log(e, true);
			}
		}
	}
}
