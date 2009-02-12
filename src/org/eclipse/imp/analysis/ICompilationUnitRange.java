/*******************************************************************************
* Copyright (c) 2007 IBM Corporation.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*    Robert Fuhrer (rfuhrer@watson.ibm.com) - initial API and implementation

*******************************************************************************/

package org.eclipse.imp.analysis;

import org.eclipse.imp.model.ICompilationUnit;

/**
 * Simple representation of a given range of source text within a given ICompilationUnit
 */
public interface ICompilationUnitRange {
    /**
     * @return the associated compilation unit containing this source range
     */
    ICompilationUnit getCompilationUnit();

    /**
     * @return the textual range (offset, length) of this ICompilationUnitRange
     */
    ISourceRange getSourceRange();

    /**
     * @return the source code corresponding to the given range
     */
    String getSource();
}