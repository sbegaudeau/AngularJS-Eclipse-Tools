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

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

/**
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public class AngularJSDialogField {
	/**
	 * The label.
	 */
	private Label label;

	private boolean enabled = true;

	/**
	 * The text of the label.
	 */
	private String labelText = "";

	/**
	 * The lister that will be called each time a change occurs on the field.
	 */
	private IAngularJSDialogFieldListener angularJSDialogFieldListener;

	public void setLabelText(String newLabelText) {
		this.labelText = newLabelText;
		if (this.isOkToUse(this.label)) {
			this.label.setText(newLabelText);
		}
	}

	public String getLabelText() {
		return this.labelText;
	}

	public final void setDialogFieldListener(IAngularJSDialogFieldListener newAngularJSDialogFieldListener) {
		this.angularJSDialogFieldListener = newAngularJSDialogFieldListener;
	}

	public void dialogFieldChanged() {
		if (this.angularJSDialogFieldListener != null) {
			this.angularJSDialogFieldListener.dialogFieldChanged(this);
		}
	}

	/**
	 * Tries to set the focus to the dialog field. Returns <code>true</code> if the dialog field can take
	 * focus. To be reimplemented by dialog field implementors.
	 * 
	 * @return <code>true</code> if the dialog field can take focus
	 */
	public boolean setFocus() {
		return false;
	}

	/**
	 * Posts <code>setFocus</code> to the display event queue.
	 * 
	 * @param display
	 *            the Display
	 */
	public void postSetFocusOnDialogField(Display display) {
		if (display != null) {
			display.asyncExec(new Runnable() {
				@Override
				public void run() {
					setFocus();
				}
			});
		}
	}

	public Control[] doFillIntoGrid(Composite parent, int nColumns) {
		this.assertEnoughColumns(nColumns);

		Label label = this.getLabelControl(parent);
		label.setLayoutData(AngularJSDialogField.gridDataForLabel(nColumns));

		return new Control[] {label };
	}

	public int getNumberOfControls() {
		return 1;
	}

	protected static GridData gridDataForLabel(int spam) {
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan = spam;
		return gridData;
	}

	/**
	 * Creates or returns the created label widget.
	 * 
	 * @param parent
	 *            The parent composite or <code>null</code> if the widget has already been created.
	 * @return the label widget
	 */
	public Label getLabelControl(Composite parent) {
		if (label == null) {
			assertCompositeNotNull(parent);

			label = new Label(parent, SWT.LEFT | SWT.WRAP);
			label.setFont(parent.getFont());
			label.setEnabled(enabled);
			if (labelText != null && !"".equals(labelText)) { //$NON-NLS-1$
				label.setText(labelText);
			} else {
				// To avoid a 16 pixel wide empty label
				label.setText("."); //$NON-NLS-1$
				label.setVisible(false);
			}
		}
		return label;
	}

	/**
	 * Creates a spacer control.
	 * 
	 * @param parent
	 *            The parent composite
	 * @return the spacer control
	 */
	public static Control createEmptySpace(Composite parent) {
		return createEmptySpace(parent, 1);
	}

	/**
	 * Creates a spacer control with the given span. The composite is assumed to have <code>GridLayout</code>
	 * as layout.
	 * 
	 * @param parent
	 *            The parent composite
	 * @param span
	 *            the given span
	 * @return the spacer control
	 */
	public static Control createEmptySpace(Composite parent, int span) {
		Label label = new Label(parent, SWT.LEFT);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.grabExcessHorizontalSpace = false;
		gridData.horizontalSpan = span;
		gridData.horizontalIndent = 0;
		gridData.widthHint = 0;
		gridData.heightHint = 0;
		label.setLayoutData(gridData);
		return label;
	}

	/**
	 * Tests is the control is not <code>null</code> and not disposed.
	 * 
	 * @param control
	 *            the Control
	 * @return <code>true</code> if the control is not <code>null</code> and not disposed.
	 */
	protected final boolean isOkToUse(Control control) {
		return (control != null) && (Display.getCurrent() != null) && !control.isDisposed();
	}

	/**
	 * Sets the enable state of the dialog field.
	 * 
	 * @param isEnabled
	 *            enable state
	 */
	public final void setEnabled(boolean isEnabled) {
		if (isEnabled != this.enabled) {
			this.enabled = isEnabled;
			updateEnableState();
		}
	}

	/**
	 * Called when the enable state changed. To be extended by dialog field implementors.
	 */
	protected void updateEnableState() {
		if (label != null) {
			label.setEnabled(enabled);
		}
	}

	/**
	 * Brings the UI in sync with the model. Only needed when model was changed in different thread whil UI
	 * was lready created.
	 */
	public void refresh() {
		updateEnableState();
	}

	/**
	 * Gets the enable state of the dialog field.
	 * 
	 * @return the enable state
	 */
	public final boolean isEnabled() {
		return enabled;
	}

	protected final void assertCompositeNotNull(Composite comp) {
		Assert.isNotNull(comp, "uncreated control requested with composite null"); //$NON-NLS-1$
	}

	protected final void assertEnoughColumns(int nColumns) {
		Assert.isTrue(nColumns >= getNumberOfControls(), "given number of columns is too small"); //$NON-NLS-1$
	}
}
