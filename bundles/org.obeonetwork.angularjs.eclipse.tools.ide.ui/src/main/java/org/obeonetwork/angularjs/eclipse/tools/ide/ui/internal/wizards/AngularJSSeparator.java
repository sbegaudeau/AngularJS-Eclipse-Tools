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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

/**
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public class AngularJSSeparator extends AngularJSDialogField {
	private Label separator;

	private int style;

	public AngularJSSeparator() {
		this(SWT.NONE);
	}

	/**
	 * @param newStyle
	 *            of the separator. See <code>Label</code> for possible styles.
	 */
	public AngularJSSeparator(int newStyle) {
		super();
		this.style = newStyle;
	}

	// ------- layout helpers

	/**
	 * Creates the separator and fills it in a MGridLayout.
	 * 
	 * @param height
	 *            The height of the separator
	 */
	public Control[] doFillIntoGrid(Composite parent, int nColumns, int height) {
		assertEnoughColumns(nColumns);

		Control separator = getSeparator(parent);
		separator.setLayoutData(gridDataForSeperator(nColumns, height));

		return new Control[] {separator };
	}

	@Override
	public Control[] doFillIntoGrid(Composite parent, int nColumns) {
		return doFillIntoGrid(parent, nColumns, 4);
	}

	@Override
	public int getNumberOfControls() {
		return 1;
	}

	protected static GridData gridDataForSeperator(int span, int height) {
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.BEGINNING;
		gd.heightHint = height;
		gd.horizontalSpan = span;
		return gd;
	}

	/**
	 * Creates or returns the created separator.
	 * 
	 * @param parent
	 *            The parent composite or <code>null</code> if the widget has already been created.
	 */
	public Control getSeparator(Composite parent) {
		if (separator == null) {
			assertCompositeNotNull(parent);
			separator = new Label(parent, style);
		}
		return separator;
	}
}
