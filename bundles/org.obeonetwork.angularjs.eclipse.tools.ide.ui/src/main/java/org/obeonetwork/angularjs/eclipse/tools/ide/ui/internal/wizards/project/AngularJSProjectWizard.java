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
package org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.wizards.project;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.obeonetwork.angularjs.eclipse.tools.ide.core.internal.project.AngularJSProject;
import org.obeonetwork.angularjs.eclipse.tools.ide.core.utils.IAngularJSConstants;
import org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.AngularJSIDEUIPlugin;

/**
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public class AngularJSProjectWizard extends Wizard implements INewWizard {

	/**
	 * The current selection when the wizard was opened.
	 */
	private IStructuredSelection selection;

	/**
	 * The first page of the wizard where the user will enter the name of the project and its location.
	 */
	private AngularJSProjectDescriptionWizardPage angularJSProjectDescriptionWizardPage;

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection structuredSelection) {
		this.selection = structuredSelection;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		this.angularJSProjectDescriptionWizardPage = new AngularJSProjectDescriptionWizardPage(this.selection);
		this.addPage(this.angularJSProjectDescriptionWizardPage);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		final String projectName = this.angularJSProjectDescriptionWizardPage.getProjectName();
		if (projectName != null && projectName.length() > 0) {
			try {
				this.getContainer().run(false, false, new IRunnableWithProgress() {

					@Override
					public void run(IProgressMonitor monitor) throws InvocationTargetException,
							InterruptedException {
						IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
						if (!project.exists()) {
							IProjectDescription projectDescription = ResourcesPlugin.getWorkspace()
									.newProjectDescription(projectName);
							IPath locationPath = AngularJSProjectWizard.this.angularJSProjectDescriptionWizardPage
									.getLocationPath();
							if (ResourcesPlugin.getWorkspace().getRoot().getLocation().equals(locationPath)) {
								locationPath = null;
							}
							projectDescription.setLocation(locationPath);
							projectDescription.setNatureIds(new String[] {IAngularJSConstants.NATURE_ID });
							ICommand command = projectDescription.newCommand();
							command.setBuilderName(IAngularJSConstants.BUILDER_ID);
							projectDescription.setBuildSpec(new ICommand[] {command });
							try {
								project.create(projectDescription, monitor);
								project.open(monitor);

								AngularJSProject angularJSProject = new AngularJSProject(project);
								angularJSProject.initialize(monitor);
							} catch (CoreException e) {
								AngularJSIDEUIPlugin.log(e, true);
							}
						}
					}
				});
			} catch (InvocationTargetException | InterruptedException e) {
				AngularJSIDEUIPlugin.log(e, true);
			}
			return true;
		}
		return false;
	}
}
