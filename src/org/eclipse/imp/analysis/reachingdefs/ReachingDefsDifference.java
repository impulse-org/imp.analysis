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

import org.eclipse.imp.analysis.constraints.ConstraintTerm;
import org.eclipse.imp.analysis.constraints.IConstraintTerm;
import org.eclipse.imp.analysis.constraints.IEstimateEnvironment;
import org.eclipse.imp.analysis.constraints.ITermProcessor;

public class ReachingDefsDifference extends ConstraintTerm {
    private IConstraintTerm fLHS;

    private IConstraintTerm fRHS;

    public ReachingDefsDifference(IConstraintTerm lhs, IConstraintTerm rhs) {
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
        env.setEstimate(this, ((DefinitionSet) env.getEstimate(fLHS)).without((DefinitionSet) env.getEstimate(fRHS)));
    }

    public String toString() {
        return fLHS + "\\" + fRHS;
    }
}
