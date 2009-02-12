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


public class CompilationUnitRange implements ICompilationUnitRange {
    private final ICompilationUnit fUnit;
    private final ISourceRange fRange;
    public CompilationUnitRange(ICompilationUnit unit, ISourceRange range) {
        fUnit= unit;
        fRange= range;
    }
    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.variables.TypeVariableFactory.ICompilationUnitRange#getCompilationUnit()
     */
    public ICompilationUnit getCompilationUnit() {
        return fUnit;
    }
    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.variables.TypeVariableFactory.ICompilationUnitRange#getSourceRange()
     */
    public ISourceRange getSourceRange() {
        return fRange;
    }

    public String getSource() {
        return fUnit.getSource().substring(fRange.getOffset(), fRange.getOffset()+fRange.getLength());
    }

    public String toString() {
        return "<unitRange " + fUnit + ": " + fRange.getOffset() + "#" + fRange.getLength() + ">";
    }
}
