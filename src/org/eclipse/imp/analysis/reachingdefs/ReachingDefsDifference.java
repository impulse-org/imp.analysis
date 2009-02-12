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

public class ReachingDefsDifference extends ConstraintTerm {
    private ConstraintTerm fLHS;

    private ConstraintTerm fRHS;

    public ReachingDefsDifference(ConstraintTerm lhs, ConstraintTerm rhs) {
	fLHS= lhs;
	fRHS= rhs;
    }

    public void processTerms(ITermProcessor processor) {
	processor.processTerm(this);
	fLHS.processTerms(processor);
	fRHS.processTerms(processor);
    }

    public boolean isComplexTerm() {
	return true;
    }

    public void recomputeEstimate(IEstimateEnvironment env) {
	env.setEstimate(this, env.getEstimate(fLHS).without(env.getEstimate(fRHS)));
    }

    public String toString() {
	return fLHS + "\\" + fRHS;
    }
}
