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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public class AngularJSStringDialogField extends AngularJSDialogField {
	private String text = "";

	private Text textControl;

	private ModifyListener modifyListener;

	@Override
	public Control[] doFillIntoGrid(Composite parent, int nColumns) {
		assertEnoughColumns(nColumns);

		Label label = getLabelControl(parent);
		label.setLayoutData(gridDataForLabel(1));
		Text text = getTextControl(parent);
		text.setLayoutData(gridDataForText(nColumns - 1));

		return new Control[] {label, text };
	}

	/*
	 * @see DialogField#getNumberOfControls
	 */
	@Override
	public int getNumberOfControls() {
		return 2;
	}

	protected static GridData gridDataForText(int span) {
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = span;
		return gd;
	}

	@Override
	public boolean setFocus() {
		if (isOkToUse(this.textControl)) {
			this.textControl.setFocus();
			this.textControl.setSelection(0, this.textControl.getText().length());
		}
		return true;
	}

	// ------- ui creation

	/**
	 * Creates or returns the created text control.
	 * 
	 * @param parent
	 *            The parent composite or <code>null</code> when the widget has already been created.
	 * @return the text control
	 */
	public Text getTextControl(Composite parent) {
		if (this.textControl == null) {
			assertCompositeNotNull(parent);
			this.modifyListener = new ModifyListener() {
				@Override
				public void modifyText(ModifyEvent e) {
					doModifyText();
				}
			};

			this.textControl = createTextControl(parent);
			// moved up due to 1GEUNW2
			this.textControl.setText(this.text);
			this.textControl.setFont(parent.getFont());
			this.textControl.addModifyListener(this.modifyListener);

			this.textControl.setEnabled(isEnabled());
		}
		return this.textControl;
	}

	/**
	 * Creates and returns a new text control.
	 * 
	 * @param parent
	 *            the parent
	 * @return the text control
	 * @since 3.6
	 */
	protected Text createTextControl(Composite parent) {
		return new Text(parent, SWT.SINGLE | SWT.BORDER);
	}

	private void doModifyText() {
		if (isOkToUse(this.textControl)) {
			this.text = this.textControl.getText();
		}
		dialogFieldChanged();
	}

	// ------ enable / disable management

	/*
	 * @see DialogField#updateEnableState
	 */
	@Override
	protected void updateEnableState() {
		super.updateEnableState();
		if (isOkToUse(this.textControl)) {
			this.textControl.setEnabled(isEnabled());
		}
	}

	// ------ text access

	/**
	 * @return the text, can not be <code>null</code>
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * Sets the text. Triggers a dialog-changed event.
	 * 
	 * @param text
	 *            the new text
	 */
	public void setText(String text) {
		this.text = text;
		if (isOkToUse(this.textControl)) {
			this.textControl.setText(text);
		} else {
			dialogFieldChanged();
		}
	}

	/**
	 * Sets the text without triggering a dialog-changed event.
	 * 
	 * @param text
	 *            the new text
	 */
	public void setTextWithoutUpdate(String text) {
		this.text = text;
		if (isOkToUse(this.textControl)) {
			this.textControl.removeModifyListener(this.modifyListener);
			this.textControl.setText(text);
			this.textControl.addModifyListener(this.modifyListener);
		}
	}

	@Override
	public void refresh() {
		super.refresh();
		if (isOkToUse(this.textControl)) {
			setTextWithoutUpdate(this.text);
		}
	}
}
