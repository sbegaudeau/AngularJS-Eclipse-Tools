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

import com.google.common.collect.Lists;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.obeonetwork.angularjs.eclipse.tools.ide.core.internal.AngularJSIDECorePlugin;
import org.obeonetwork.angularjs.eclipse.tools.ide.core.utils.IAngularJSConstants;

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

				String lineSeparator = System.lineSeparator();

				String appJsContent = this.createApplication("angularJsEclipseTools", Lists //$NON-NLS-1$
						.newArrayList(new AngularJSRouteMapping("/:path", "views/main.html", //$NON-NLS-1$ //$NON-NLS-2$
								"MainCtrl")), "/404.html", lineSeparator); //$NON-NLS-1$ //$NON-NLS-2$

				IFile appJsFile = scriptsFolder.getFile("app.js"); //$NON-NLS-1$
				appJsFile.create(new ByteArrayInputStream(appJsContent.getBytes()), true, monitor);

				String mainControllerJsContent = this.createController("MainCtrl", "angularJsEclipseTools",
						Lists.newArrayList("$scope"), lineSeparator);

				IFile mainJsFile = controllersFolder.getFile("main.js");
				mainJsFile
						.create(new ByteArrayInputStream(mainControllerJsContent.getBytes()), true, monitor);
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

		try {
			String packageJsonContent = this.createPackageJson();
			IFile packageJsonFile = this.project.getFile(IAngularJSConstants.PACKAGE_JSON_FILENAME);
			packageJsonFile.create(new ByteArrayInputStream(packageJsonContent.getBytes()), true, monitor);
		} catch (CoreException e) {
			AngularJSIDECorePlugin.log(e, true);
		}

	}

	/**
	 * Returns the content of an AngularJS application.
	 * 
	 * @param moduleName
	 *            The name of the module
	 * @param routes
	 *            The route mappings
	 * @param defaultRoute
	 *            The default route
	 * @param lineSeparator
	 *            The line separator
	 * @return The content of the application file.
	 */
	public String createApplication(String moduleName, List<AngularJSRouteMapping> routes,
			String defaultRoute, String lineSeparator) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("/***********************************" + lineSeparator); //$NON-NLS-1$
		stringBuilder.append(" * " + lineSeparator); //$NON-NLS-1$
		stringBuilder.append(" ***********************************/" + lineSeparator); //$NON-NLS-1$
		stringBuilder.append(lineSeparator);
		stringBuilder.append("'use string;'" + lineSeparator); //$NON-NLS-1$
		stringBuilder.append("" + lineSeparator); //$NON-NLS-1$
		stringBuilder.append("/*jslint index: 2*/" + lineSeparator); //$NON-NLS-1$
		stringBuilder.append("/*global angular*/" + lineSeparator); //$NON-NLS-1$
		stringBuilder.append("" + lineSeparator); //$NON-NLS-1$
		stringBuilder.append("/***********************************" + lineSeparator); //$NON-NLS-1$
		stringBuilder.append(" * " + lineSeparator); //$NON-NLS-1$
		stringBuilder.append(" ***********************************/" + lineSeparator); //$NON-NLS-1$
		if (!routes.isEmpty() && defaultRoute != null && defaultRoute.length() > 0) {
			stringBuilder.append("angular.module('" + moduleName + "'), [])" + lineSeparator); //$NON-NLS-1$ //$NON-NLS-2$
			stringBuilder.append("  .config(['$routeProvider', function ($routeProvider) {" + lineSeparator); //$NON-NLS-1$
			stringBuilder.append("    $routeProvider" + lineSeparator); //$NON-NLS-1$
			for (AngularJSRouteMapping routeMapping : routes) {
				stringBuilder.append("      .when('" + routeMapping.getRoute() + "', {" + lineSeparator); //$NON-NLS-1$ //$NON-NLS-2$
				stringBuilder.append("        templateUrl: '" + routeMapping.getTemplateUrl() + "'," //$NON-NLS-1$ //$NON-NLS-2$
						+ lineSeparator);
				stringBuilder.append("        controller: '" + routeMapping.getControllerName() + "'" //$NON-NLS-1$ //$NON-NLS-2$
						+ lineSeparator);
				stringBuilder.append("      })" + lineSeparator); //$NON-NLS-1$
			}
			stringBuilder.append("      .otherwise({" + lineSeparator); //$NON-NLS-1$
			stringBuilder.append("        redirectTo: '" + defaultRoute + "'" + lineSeparator); //$NON-NLS-1$ //$NON-NLS-2$
			stringBuilder.append("      });" + lineSeparator); //$NON-NLS-1$
			stringBuilder.append("  }]);" + lineSeparator); //$NON-NLS-1$
		} else {
			stringBuilder.append("angular.module('" + moduleName + "'), []);" + lineSeparator); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return stringBuilder.toString();
	}

	/**
	 * Returns the content of an AngularJS controller.
	 * 
	 * @param controllerName
	 *            The name of the controller
	 * @param moduleName
	 *            The name of the module containing the controller
	 * @param dependenciesName
	 *            The name of the dependencies of this controller
	 * @param lineSeparator
	 *            The line separator
	 * @return The content of an AngularJS controller
	 */
	public String createController(String controllerName, String moduleName, List<String> dependenciesName,
			String lineSeparator) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("/***********************************" + lineSeparator); //$NON-NLS-1$
		stringBuilder.append(" * " + lineSeparator); //$NON-NLS-1$
		stringBuilder.append(" ***********************************/" + lineSeparator); //$NON-NLS-1$
		stringBuilder.append(lineSeparator);
		stringBuilder.append("'use string;'" + lineSeparator); //$NON-NLS-1$
		stringBuilder.append("" + lineSeparator); //$NON-NLS-1$
		stringBuilder.append("/*jslint index: 2*/" + lineSeparator); //$NON-NLS-1$
		stringBuilder.append("/*global angular*/" + lineSeparator); //$NON-NLS-1$
		stringBuilder.append("" + lineSeparator); //$NON-NLS-1$
		stringBuilder.append("/***********************************" + lineSeparator); //$NON-NLS-1$
		stringBuilder.append(" * " + lineSeparator); //$NON-NLS-1$
		stringBuilder.append(" ***********************************/" + lineSeparator); //$NON-NLS-1$

		StringBuilder dependencies = new StringBuilder("[");
		int count = 1;
		for (String dependencyName : dependenciesName) {
			dependencies.append("'" + dependencyName + "'");
			if (count != dependenciesName.size()) {
				dependencies.append(", ");
			}
			count++;
		}
		dependencies.append(", function (");
		count = 1;
		for (String dependencyName : dependenciesName) {
			dependencies.append(dependencyName);
			if (count != dependenciesName.size()) {
				dependencies.append(", ");
			}
			count++;
		}
		dependencies.append(") {");

		stringBuilder.append("angular.module('" + moduleName + "').controller('" + controllerName + "', "
				+ dependencies + lineSeparator);
		stringBuilder.append("}]);" + lineSeparator); //$NON-NLS-1$

		return stringBuilder.toString();
	}

	public String createDirective(String moduleName, List<String> modulesDependencies,
			List<String> dependenciesName) {
		StringBuilder stringBuilder = new StringBuilder();
		return stringBuilder.toString();
	}

	public String createService(String moduleName, List<String> moduleDependencies, String serviceName,
			List<String> dependenciesName) {
		StringBuilder stringBuilder = new StringBuilder();
		return stringBuilder.toString();
	}

	public String createPackageJson() {
		StringBuilder stringBuilder = new StringBuilder();
		return stringBuilder.toString();
	}

	public String createGruntJs() {
		StringBuilder stringBuilder = new StringBuilder();
		return stringBuilder.toString();
	}

	public String createTestsconfiguration() {
		StringBuilder stringBuilder = new StringBuilder();
		return stringBuilder.toString();
	}

	public String createMainScss() {
		StringBuilder stringBuilder = new StringBuilder();
		return stringBuilder.toString();
	}

	public String createMainView() {
		StringBuilder stringBuilder = new StringBuilder();
		return stringBuilder.toString();
	}

	public String createIndexHtml() {
		StringBuilder stringBuilder = new StringBuilder();
		return stringBuilder.toString();
	}
}
