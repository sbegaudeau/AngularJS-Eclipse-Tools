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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public class AngularJSStringButtonDialogField extends AngularJSStringDialogField {
	private Button browseButton;

	private String browseButtonLabel = "Browse...";

	private IAngularJSStringButtonAdapter angularJSStringButtonAdapter;

	private boolean buttonEnabled = true;

	public AngularJSStringButtonDialogField(IAngularJSStringButtonAdapter adapter) {
		super();
		this.angularJSStringButtonAdapter = adapter;
		this.browseButtonLabel = "!Browse...!";
		this.buttonEnabled = true;
	}

	/**
	 * Sets the label of the button.
	 */
	public void setButtonLabel(String label) {
		this.browseButtonLabel = label;
	}

	/**
	 * Programmatical pressing of the button
	 */
	public void changeControlPressed() {
		this.angularJSStringButtonAdapter.changeControlPressed(this);
	}

	// ------- layout helpers

	/*
	 * @see DialogField#doFillIntoGrid
	 */
	@Override
	public Control[] doFillIntoGrid(Composite parent, int nColumns) {
		assertEnoughColumns(nColumns);

		Label label = getLabelControl(parent);
		label.setLayoutData(gridDataForLabel(1));
		Text text = getTextControl(parent);
		text.setLayoutData(gridDataForText(nColumns - 2));
		Button button = getChangeControl(parent);
		button.setLayoutData(gridDataForButton(button, 1));

		return new Control[] {label, text, button };
	}

	/*
	 * @see DialogField#getNumberOfControls
	 */
	@Override
	public int getNumberOfControls() {
		return 3;
	}

	protected static GridData gridDataForButton(Button button, int span) {
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = false;
		gd.horizontalSpan = span;
		button.setFont(JFaceResources.getDialogFont());
		PixelConverter converter = new PixelConverter(button);
		int widthHint = converter.convertHorizontalDLUsToPixels(IDialogConstants.BUTTON_WIDTH);
		widthHint = Math.max(widthHint, button.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x);
		gd.widthHint = widthHint;
		return gd;
	}

	// ------- ui creation

	/**
	 * Creates or returns the created buttom widget.
	 * 
	 * @param parent
	 *            The parent composite or <code>null</code> if the widget has already been created.
	 */
	public Button getChangeControl(Composite parent) {
		if (this.browseButton == null) {
			assertCompositeNotNull(parent);

			this.browseButton = new Button(parent, SWT.PUSH);
			this.browseButton.setFont(parent.getFont());
			this.browseButton.setText(this.browseButtonLabel);
			this.browseButton.setEnabled(isEnabled() && this.buttonEnabled);
			this.browseButton.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					changeControlPressed();
				}

				@Override
				public void widgetSelected(SelectionEvent e) {
					changeControlPressed();
				}
			});

		}
		return this.browseButton;
	}

	// ------ enable / disable management

	/**
	 * Sets the enable state of the button.
	 */
	public void enableButton(boolean enable) {
		if (isOkToUse(this.browseButton)) {
			this.browseButton.setEnabled(isEnabled() && enable);
		}
		this.buttonEnabled = enable;
	}

	/*
	 * @see DialogField#updateEnableState
	 */
	@Override
	protected void updateEnableState() {
		super.updateEnableState();
		if (isOkToUse(this.browseButton)) {
			this.browseButton.setEnabled(isEnabled() && this.buttonEnabled);
		}
	}
}
