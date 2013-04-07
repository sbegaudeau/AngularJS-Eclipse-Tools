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
package org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.wizards.module;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.AngularJSIDEUIPlugin;

/**
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public class AngularJSModuleWizard extends Wizard implements INewWizard {

	/**
	 * The current selection when the wizard was opened.
	 */
	private IStructuredSelection selection;

	/**
	 * The first page of the wizard where the user will enter the name of the module along with its
	 * container's path.
	 */
	private AngularJSModuleDescriptionWizardPage angularJSModuleDescriptionWizardPage;

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
		this.angularJSModuleDescriptionWizardPage = new AngularJSModuleDescriptionWizardPage(this.selection);
		this.addPage(this.angularJSModuleDescriptionWizardPage);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		try {
			this.getContainer().run(false, false, new IRunnableWithProgress() {

				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException,
						InterruptedException {
					String content = angularJSModuleDescriptionWizardPage.getModuleFileContent();
					IContainer container = angularJSModuleDescriptionWizardPage.getFileContainer();
					IFile file = container.getFile(new Path(angularJSModuleDescriptionWizardPage
							.getModuleName().toLowerCase()
							+ ".js"));
					try {
						file.create(new ByteArrayInputStream(content.getBytes()), true, monitor);

						IFileEditorInput iFileEditorInput = new FileEditorInput(file);
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(
								iFileEditorInput, "org.eclipse.ui.DefaultTextEditor");
					} catch (CoreException e) {
						AngularJSIDEUIPlugin.log(e, true);
					}
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			AngularJSIDEUIPlugin.log(e, true);
		}
		return true;
	}

}
