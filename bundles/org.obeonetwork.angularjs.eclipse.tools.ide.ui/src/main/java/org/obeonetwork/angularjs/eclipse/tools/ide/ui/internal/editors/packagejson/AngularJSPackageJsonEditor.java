package org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.editors.packagejson;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;
import org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.AngularJSIDEUIPlugin;

/**
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public class AngularJSPackageJsonEditor extends FormEditor {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#getTitle()
	 */
	@Override
	public String getTitle() {
		return "package.json";
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.forms.editor.FormEditor#addPages()
	 */
	@Override
	protected void addPages() {
		try {
			this.addPage(new AngularJSPackageJsonOverviewFormPage(this));
			this.addPage(new AngularJSPackageJsonDependenciesFormPage(this));
			this.addPage(new AngularJSPackageJsonDevelopmentFormPage(this));
			// this.addPage(this.createDependenciesFormPage());
			// this.addPage(this.createDevelopmentDependenciesFormPage());
			// this.addPage(this.createTextEditorPage());
		} catch (PartInitException e) {
			AngularJSIDEUIPlugin.log(e, true);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		// do nothing for now
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.part.EditorPart#doSaveAs()
	 */
	@Override
	public void doSaveAs() {
		// do nothing for now
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

}
