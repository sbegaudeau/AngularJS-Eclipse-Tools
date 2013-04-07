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
package org.obeonetwork.angularjs.eclipse.tools.ide.ui.internal.wizards;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.PixelConverter;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public class AngularJSSelectionButtonDialogField extends AngularJSDialogField {
	private Button button;

	private boolean isSelected;

	private AngularJSDialogField[] attachedDialogFields;

	private int buttonStyle;

	/**
	 * Creates a selection button. Allowed button styles: SWT.RADIO, SWT.CHECK, SWT.TOGGLE, SWT.PUSH
	 */
	public AngularJSSelectionButtonDialogField(int buttonStyle) {
		super();
		this.isSelected = false;
		this.attachedDialogFields = null;
		this.buttonStyle = buttonStyle;
	}

	/**
	 * Attaches a field to the selection state of the selection button. The attached field will be disabled if
	 * the selection button is not selected.
	 */
	public void attachDialogField(AngularJSDialogField dialogField) {
		this.attachDialogFields(new AngularJSDialogField[] {dialogField });
	}

	/**
	 * Attaches fields to the selection state of the selection button. The attached fields will be disabled if
	 * the selection button is not selected.
	 */
	public void attachDialogFields(AngularJSDialogField[] dialogFields) {
		this.attachedDialogFields = dialogFields;
		for (AngularJSDialogField dialogField : dialogFields) {
			dialogField.setEnabled(this.isSelected);
		}
	}

	/**
	 * Returns <code>true</code> is teh gived field is attached to the selection button.
	 */
	public boolean isAttached(AngularJSDialogField editor) {
		if (this.attachedDialogFields != null) {
			for (AngularJSDialogField attachedDialogField : this.attachedDialogFields) {
				if (attachedDialogField == editor) {
					return true;
				}
			}
		}
		return false;
	}

	// ------- layout helpers

	/*
	 * @see DialogField#doFillIntoGrid
	 */
	@Override
	public Control[] doFillIntoGrid(Composite parent, int nColumns) {
		assertEnoughColumns(nColumns);

		Button button = getSelectionButton(parent);
		GridData gd = new GridData();
		gd.horizontalSpan = nColumns;
		gd.horizontalAlignment = GridData.FILL;
		if (this.buttonStyle == SWT.PUSH) {
			this.button.setFont(JFaceResources.getDialogFont());
			PixelConverter converter = new PixelConverter(button);
			int widthHint = converter.convertHorizontalDLUsToPixels(IDialogConstants.BUTTON_WIDTH);
			widthHint = Math.max(widthHint, button.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x);
			gd.widthHint = widthHint;
		}

		button.setLayoutData(gd);

		return new Control[] {button };
	}

	@Override
	public int getNumberOfControls() {
		return 1;
	}

	/**
	 * Returns the selection button widget. When called the first time, the widget will be created.
	 * 
	 * @param group
	 *            The parent composite when called the first time, or <code>null</code> after.
	 */
	public Button getSelectionButton(Composite group) {
		if (this.button == null) {
			assertCompositeNotNull(group);

			this.button = new Button(group, this.buttonStyle);
			this.button.setFont(group.getFont());
			this.button.setText(this.getLabelText());
			this.button.setEnabled(isEnabled());
			this.button.setSelection(this.isSelected);
			this.button.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					doWidgetSelected(e);
				}

				@Override
				public void widgetSelected(SelectionEvent e) {
					doWidgetSelected(e);
				}
			});
		}
		return this.button;
	}

	private void doWidgetSelected(SelectionEvent e) {
		if (isOkToUse(this.button)) {
			changeValue(this.button.getSelection());
		}
	}

	private void changeValue(boolean newState) {
		if (this.isSelected != newState) {
			this.isSelected = newState;
			if (this.attachedDialogFields != null) {
				boolean focusSet = false;
				for (AngularJSDialogField attachedDialogField : this.attachedDialogFields) {
					attachedDialogField.setEnabled(this.isSelected);
					if (this.isSelected && !focusSet) {
						focusSet = attachedDialogField.setFocus();
					}
				}
			}
			dialogFieldChanged();
		} else if (this.buttonStyle == SWT.PUSH) {
			dialogFieldChanged();
		}
	}

	@Override
	public void setLabelText(String labeltext) {
		super.setLabelText(labeltext);
		if (isOkToUse(this.button)) {
			this.button.setText(labeltext);
		}
	}

	/**
	 * Returns the selection state of the button.
	 */
	public boolean isSelected() {
		return this.isSelected;
	}

	/**
	 * Sets the selection state of the button.
	 */
	public void setSelection(boolean selected) {
		changeValue(selected);
		if (isOkToUse(this.button)) {
			this.button.setSelection(selected);
		}
	}

	@Override
	protected void updateEnableState() {
		super.updateEnableState();
		if (isOkToUse(this.button)) {
			this.button.setEnabled(isEnabled());
		}
	}

	@Override
	public void refresh() {
		super.refresh();
		if (isOkToUse(this.button)) {
			this.button.setSelection(this.isSelected);
		}
	}
}
