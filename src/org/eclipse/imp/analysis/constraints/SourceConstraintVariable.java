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

package org.eclipse.imp.analysis.constraints;

import org.eclipse.imp.analysis.ICompilationUnitRange;
import org.eclipse.imp.model.ICompilationUnit;

/**
 * @author rfuhrer@watson.ibm.com
 */
public class SourceConstraintVariable extends ConstraintVariable implements ISourceConstraintVariable {
    private final ICompilationUnitRange fRange;

    public SourceConstraintVariable(ICompilationUnitRange range) {
        super();
        fRange= range;
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.constraints.ISourceConstraintVariable#getRange()
     */
    public ICompilationUnitRange getRange() {
        return fRange;
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.constraints.ISourceConstraintVariable#getCompilationUnit()
     */
    public ICompilationUnit getCompilationUnit() {
        return fRange.getCompilationUnit();
    }

    public String getSource() {
        return fRange.getSource();
    }
}
