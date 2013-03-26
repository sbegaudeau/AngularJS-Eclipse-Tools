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

/**
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public class AngularJSRouteMapping {
	/**
	 * The route.
	 */
	private String route;

	/**
	 * The URL of the template.
	 */
	private String templateUrl;

	/**
	 * The name of the controller.
	 */
	private String controllerName;

	/**
	 * The constructor.
	 * 
	 * @param route
	 *            The route
	 * @param templateUrl
	 *            The url of the template
	 * @param controllerName
	 *            The name of the controller
	 */
	public AngularJSRouteMapping(String route, String templateUrl, String controllerName) {
		this.route = route;
		this.templateUrl = templateUrl;
		this.controllerName = controllerName;
	}

	/**
	 * Returns the route.
	 * 
	 * @return The route.
	 */
	public String getRoute() {
		return this.route;
	}

	/**
	 * Returns the template url.
	 * 
	 * @return The template url.
	 */
	public String getTemplateUrl() {
		return this.templateUrl;
	}

	/**
	 * Returns the name of the controller.
	 * 
	 * @return The name of the controller.
	 */
	public String getControllerName() {
		return this.controllerName;
	}

}
