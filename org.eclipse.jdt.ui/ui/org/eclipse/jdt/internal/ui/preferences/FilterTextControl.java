/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Alexander Fedorov <alexander.fedorov@arsysop.ru> - Bug 549442
 *******************************************************************************/
package org.eclipse.jdt.internal.ui.preferences;

import java.util.Optional;

import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.ACC;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleControlAdapter;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ResourceLocator;

import org.eclipse.ui.PlatformUI;

/**
 * A simple filter text widget.
 *
 * TODO: Remove this class once Bug 293230 is fixed
 */
public class FilterTextControl {

	/**
	 * Image descriptor for enabled clear button.
	 */
	private static Optional<ImageDescriptor> fgClearIconDescriptor= ResourceLocator
			.imageDescriptorFromBundle(PlatformUI.PLUGIN_ID, "$nl$/icons/full/etool16/clear_co.svg"); //$NON-NLS-1$

	/**
	 * Image descriptor for disabled clear button.
	 */
	private static Optional<ImageDescriptor> fgDisabledClearIconDescriptor= fgClearIconDescriptor
			.map(enabledDescriptor -> ImageDescriptor.createWithFlags(enabledDescriptor, SWT.IMAGE_DISABLE));

	private static Boolean fgUseNativeSearchField;

	private static boolean useNativeSearchField(Composite composite) {
		if (fgUseNativeSearchField == null) { // https://github.com/vi-eclipse/Eclipse-Platform/issues/178#issuecomment-2586992567
			fgUseNativeSearchField= Boolean.FALSE;
			Text testText= null;
			try {
				testText= new Text(composite, SWT.SEARCH | SWT.ICON_CANCEL);
				fgUseNativeSearchField= (testText.getStyle() & SWT.ICON_CANCEL) != 0;
			} finally {
				if (testText != null) {
					testText.dispose();
				}
			}
		}
		return fgUseNativeSearchField;
	}

	/**
	 * The text widget.
	 */
	private Text fTextControl;

	/**
	 * The control representing the clear button for the filter text entry.
	 */
	private Control fClearButton;

	/**
	 * The Composite on which the filter controls are created.
	 */
	private Composite fComposite;

	public FilterTextControl(Composite parent) {
		final boolean nativeField= useNativeSearchField(parent);
		fComposite= new Composite(parent, nativeField ? SWT.NONE : SWT.BORDER);
		if (!nativeField)
			fComposite.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND));

		GridLayout filterLayout= new GridLayout(2, false);
		filterLayout.marginHeight= 0;
		filterLayout.marginWidth= 0;
		fComposite.setLayout(filterLayout);
		fComposite.setFont(parent.getFont());

		createControls(fComposite);
		fComposite.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
	}

	/**
	 * Create the filter controls.
	 *
	 * @param parent parent <code>Composite</code> of the filter controls
	 */
	private void createControls(Composite parent) {
		createTextControl(parent);
		createClearButton(parent);
		updateClearButtonVisibility(false);
	}

	/**
	 * Creates the text control.
	 *
	 * @param parent <code>Composite</code> of the filter text
	 */
	private void createTextControl(Composite parent) {
		if (useNativeSearchField(parent)) {
			fTextControl= new Text(parent, SWT.SINGLE | SWT.BORDER | SWT.SEARCH | SWT.ICON_CANCEL);
		} else {
			fTextControl= new Text(parent, SWT.SINGLE);
		}
		fTextControl.setFont(parent.getFont());

		GridData gridData= new GridData(SWT.FILL, SWT.CENTER, true, false);
		// if the text widget supported cancel then it will have it's own
		// integrated button. We can take all of the space.
		if ((fTextControl.getStyle() & SWT.ICON_CANCEL) != 0)
			gridData.horizontalSpan= 2;
		fTextControl.setLayoutData(gridData);

		fTextControl.addModifyListener(e -> updateClearButtonVisibility(fTextControl.getText().length() != 0));
	}

	/**
	 * Creates the button that clears the text.
	 *
	 * @param parent parent <code>Composite</code> of button
	 */
	private void createClearButton(Composite parent) {
		// only create the button if the text widget doesn't support one natively
		if ((fTextControl.getStyle() & SWT.ICON_CANCEL) == 0) {
			final Image inactiveImage= fgDisabledClearIconDescriptor.isPresent() ? fgDisabledClearIconDescriptor.get().createImage() : null;
			final Image activeImage= fgClearIconDescriptor.isPresent()? fgClearIconDescriptor.get().createImage(): null;
			final Image pressedImage= activeImage != null ? new Image(parent.getDisplay(), activeImage, SWT.IMAGE_GRAY) : null;

			final Label clearButton= new Label(parent, SWT.NONE);
			clearButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
			clearButton.setImage(inactiveImage);
			clearButton.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND));
			clearButton.setToolTipText(PreferencesMessages.FilterTextControl_Clear);
			clearButton.addMouseListener(new MouseAdapter() {
				private MouseMoveListener fMoveListener;

				@Override
				public void mouseDown(MouseEvent e) {
					clearButton.setImage(pressedImage);
					fMoveListener= new MouseMoveListener() {
						private boolean fMouseInButton= true;

						@Override
						public void mouseMove(MouseEvent e1) {
							boolean mouseInButton= isMouseInButton(e1);
							if (mouseInButton != fMouseInButton) {
								fMouseInButton= mouseInButton;
								clearButton.setImage(mouseInButton ? pressedImage : inactiveImage);
							}
						}
					};
					clearButton.addMouseMoveListener(fMoveListener);
				}

				@Override
				public void mouseUp(MouseEvent e) {
					if (fMoveListener != null) {
						clearButton.removeMouseMoveListener(fMoveListener);
						fMoveListener= null;
						boolean mouseInButton= isMouseInButton(e);
						clearButton.setImage(mouseInButton ? activeImage : inactiveImage);
						if (mouseInButton) {
							fTextControl.setText(""); //$NON-NLS-1$
							fTextControl.setFocus();
						}
					}
				}

				private boolean isMouseInButton(MouseEvent e) {
					Point buttonSize= clearButton.getSize();
					return 0 <= e.x && e.x < buttonSize.x && 0 <= e.y && e.y < buttonSize.y;
				}
			});
			clearButton.addMouseTrackListener(new MouseTrackListener() {
				@Override
				public void mouseEnter(MouseEvent e) {
					clearButton.setImage(activeImage);
				}

				@Override
				public void mouseExit(MouseEvent e) {
					clearButton.setImage(inactiveImage);
				}

				@Override
				public void mouseHover(MouseEvent e) {
				}
			});
			clearButton.addDisposeListener(e -> {
				if (inactiveImage != null) {
					inactiveImage.dispose();
				}
				if (activeImage != null) {
					activeImage.dispose();
				}
				if (pressedImage != null) {
					pressedImage.dispose();
				}
			});
			clearButton.getAccessible().addAccessibleListener(
					new AccessibleAdapter() {
						@Override
						public void getName(AccessibleEvent e) {
							e.result= PreferencesMessages.FilterTextControl_ClearFilterField;
						}
					});
			clearButton.getAccessible().addAccessibleControlListener(
					new AccessibleControlAdapter() {
						@Override
						public void getRole(AccessibleControlEvent e) {
							e.detail= ACC.ROLE_PUSHBUTTON;
						}
					});
			this.fClearButton= clearButton;
		}
	}

	/**
	 * Get the text control for the receiver, if it was created. Otherwise return <code>null</code>.
	 *
	 * @return the Text control, or null if it was not created
	 */
	public Text getFilterControl() {
		return fTextControl;
	}

	/**
	 * Convenience method to return the text of the filter control. If the text widget is not
	 * created, then null is returned.
	 *
	 * @return String in the text, or null if the text does not exist
	 */
	public String getFilterString() {
		return fTextControl != null ? fTextControl.getText() : null;
	}

	private void updateClearButtonVisibility(boolean visible) {
		if (fClearButton != null) {
			fClearButton.setVisible(visible);
		}
	}

	/**
	 * Enables the filter text control if the argument is <code>true</code>, and disables it
	 * otherwise.
	 *
	 * @param enabled the new enabled state
	 *
	 * @since 3.16
	 */
	public void setEnabled(boolean enabled) {
		if (fTextControl != null) {
			fTextControl.setEnabled(enabled);
		}
		if (fClearButton != null) {
			fClearButton.setEnabled(enabled);
		}
		boolean nativeField= fgUseNativeSearchField;
		if (!nativeField) {
			// in the case of native field, composite is initialized with blank, we need to manage background to avoid having a blank square on the right when the control is disabled.
			fComposite.setBackground(enabled ? fComposite.getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND) : null);
		}
	}
}
