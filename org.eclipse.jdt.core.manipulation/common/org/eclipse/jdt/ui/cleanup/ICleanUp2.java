/*******************************************************************************
* Copyright (c) 2024 Vector Informatik GmbH and others.
*
* This program and the accompanying materials
* are made available under the terms of the Eclipse Public License 2.0
* which accompanies this distribution, and is available at
* https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
*
* Contributors:
*     Vector Informatik GmbH  - initial API and implementation
*******************************************************************************/
package org.eclipse.jdt.ui.cleanup;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Extension of {@linkplain ICleanUp}
 *
 * @since 1.24
 *
 */
public interface ICleanUp2 extends ICleanUp {

	/**
	 * Same as {@link ICleanUp#createFix(CleanUpContext)} but with a progress monitor.
	 *
	 * @since 1.24
	 */
	ICleanUpFix createFix(CleanUpContext context, IProgressMonitor monitor) throws CoreException;
}
