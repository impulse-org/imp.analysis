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

import org.eclipse.imp.analysis.constraints.ConstraintSolver;
import org.eclipse.imp.analysis.constraints.IConstraint;
import org.eclipse.imp.analysis.constraints.IConstraintOperator;
import org.eclipse.imp.analysis.constraints.IConstraintTerm;
import org.eclipse.imp.analysis.constraints.IEstimateEnvironment;
import org.eclipse.imp.analysis.constraints.ISimpleConstraint;

public class SubsetOperator implements IConstraintOperator {
    private static final SubsetOperator fgInstance= new SubsetOperator();

    private SubsetOperator() { } // clients don't instantiate

    public static SubsetOperator getInstance() {
        return fgInstance;
    }

    public void satisfyConstraint(ISimpleConstraint sc, IEstimateEnvironment env, ConstraintSolver solver) {
        IConstraintTerm lhs= sc.getLeft();
        IConstraintTerm rhs= sc.getRight();
        IConstraintOperator op= sc.getOperator();
        IEstimateEnvironment<DefinitionSet> estimates= env;

        if (lhs.isComplexTerm()) {
            lhs.recomputeEstimate(estimates);
        }
        if (rhs.isComplexTerm()) {
            rhs.recomputeEstimate(estimates);
        }

        DefinitionSet lhsEst= estimates.getEstimate(lhs);
        DefinitionSet rhsEst= estimates.getEstimate(rhs);

        if (solver.debug()) {
            System.out.println("  Satisfying constraint " + sc);
            System.out.println("    " + lhs + " => " + lhsEst);
            System.out.println("    " + rhs + " => " + rhsEst);
        }

        if (!(op instanceof SubsetOperator))
            throw new UnsupportedOperationException("Unable to process constraint operator " + op);

        if (!rhsEst.containsAll(lhsEst)) {
            estimates.setEstimate(rhs, rhsEst.unionWith(lhsEst));
        }
    }

    public String toString() {
        return "<=";
    }
}
