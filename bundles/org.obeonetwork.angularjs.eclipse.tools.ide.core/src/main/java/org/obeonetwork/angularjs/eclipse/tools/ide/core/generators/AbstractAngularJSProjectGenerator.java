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
package org.obeonetwork.angularjs.eclipse.tools.ide.core.generators;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public abstract class AbstractAngularJSProjectGenerator {

	protected String name;

	private IPath location;

	public void setProjectName(String projectName) {
		this.name = projectName;
	}

	public void setProjectLocation(IPath projectLocation) {
		this.location = projectLocation;
	}

	public abstract boolean createProject(IProgressMonitor monitor);

}
