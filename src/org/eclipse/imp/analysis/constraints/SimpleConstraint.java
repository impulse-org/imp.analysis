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

/**
 * @author rfuhrer@watson.ibm.com
 */
public class SimpleConstraint implements IConstraint {
    private final IConstraintVariable fLeft;
    private final IConstraintVariable fRight;
    private final IConstraintOperator fOperator;

    /**
     * @param v1
     * @param v2
     * @param operator
     */
    public SimpleConstraint(IConstraintVariable left, IConstraintVariable right, IConstraintOperator operator) {
        fLeft= left;
        fRight= right;
        fOperator= operator;
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.constraints.IConstraint#getLeft()
     */
    public IConstraintVariable getLeft() {
        return fLeft;
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.constraints.IConstraint#getRight()
     */
    public IConstraintVariable getRight() {
        return fRight;
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.constraints.IConstraint#getOperator()
     */
    public IConstraintOperator getOperator() {
        return fOperator;
    }

    public String toString() {
        return fLeft.toString() + fOperator + fRight;
    }
}
