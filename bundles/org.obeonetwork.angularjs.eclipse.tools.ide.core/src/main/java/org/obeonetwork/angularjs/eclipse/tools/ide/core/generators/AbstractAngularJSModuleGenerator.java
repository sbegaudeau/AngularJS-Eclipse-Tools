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

import java.util.LinkedHashSet;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public abstract class AbstractAngularJSModuleGenerator {
	private String name;

	private boolean shouldGenerateComments;

	private LinkedHashSet<String> requiredModules;

	private LinkedHashSet<String> dependencies;

	public void setModuleName(String moduleName) {
		this.name = moduleName;
	}

	public void setGenerateComments(boolean generateComments) {
		this.shouldGenerateComments = generateComments;
	}

	public void setRequiredModules(LinkedHashSet<String> allRequiredModules) {
		this.requiredModules = allRequiredModules;
	}

	public void setDependencies(LinkedHashSet<String> allDependencies) {
		this.dependencies = allDependencies;
	}

	public abstract boolean createModule(IProgressMonitor monitor);
}
