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

package org.eclipse.imp.analysis.reachingdefs;

public class SimpleConstraint implements IConstraint {
    private ConstraintTerm fLHS;

    private ConstraintTerm fRHS;

    private ConstraintOperator fOperator;

    public SimpleConstraint(ConstraintTerm lhs, ConstraintTerm rhs, ConstraintOperator op) {
	fLHS= lhs;
	fRHS= rhs;
	fOperator= op;
    }

    public ConstraintTerm getLeft() {
	return fLHS;
    }

    public ConstraintTerm getRight() {
	return fRHS;
    }

    public ConstraintOperator getOperator() {
	return fOperator;
    }

    public String toString() {
	return fLHS.toString() + ' ' + fOperator + ' ' + fRHS;
    }
}
