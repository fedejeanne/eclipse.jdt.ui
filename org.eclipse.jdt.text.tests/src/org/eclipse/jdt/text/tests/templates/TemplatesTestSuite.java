/*******************************************************************************
 * Copyright (c) 2007, 2020 IBM Corporation and others.
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
 *******************************************************************************/
package org.eclipse.jdt.text.tests.templates;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
/**
 * Template test suite.
 *
 * @since 3.4
 */

@Suite
@SelectClasses({
	//$JUnit-BEGIN$
	TemplateContributionTest.class,
	TemplateCompletionTests.class
	//$JUnit-END$
})
public class TemplatesTestSuite {
}
