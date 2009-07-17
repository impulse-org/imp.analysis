/*******************************************************************************
* Copyright (c) 2009 IBM Corporation.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*    Robert Fuhrer (rfuhrer@watson.ibm.com) - initial API and implementation
*******************************************************************************/

package org.eclipse.imp.analysis.constraints;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author rfuhrer@watson.ibm.com
 */
public abstract class ConstraintSolver {
    public class EstimateEnvironment<T extends ITermEstimate> implements IEstimateEnvironment<T> {
        private Map<IConstraintTerm,T> fEstimates= new LinkedHashMap<IConstraintTerm,T>();

        public void setEstimate(IConstraintTerm t, T newEst) {
            T curEst= fEstimates.get(t);

            if (curEst == null || !curEst.equals(newEst)) {
                if (false) {
                    System.out.println("      New estimate for " + t + " => " + newEst);
                }
                fEstimates.put(t, newEst);
                if (!fWorkList.contains(t)) {
                    fWorkList.push(t);
                }
            }
        }

        public T getEstimate(IConstraintTerm t) {
            return fEstimates.get(t);
        }
    }

    protected final Collection<IConstraint> fConstraints= new LinkedList<IConstraint>();

    protected ConstraintGraph fGraph;

    protected boolean fgDebug= false;

    protected Stack<IConstraintTerm> fWorkList= new Stack<IConstraintTerm>();

    private IEstimateEnvironment fEstimates;

    public ConstraintSolver(Collection<IConstraint> constraints) {
        fConstraints.addAll(constraints);
    }

    public boolean debug() { return fgDebug; }

    public <T extends ITermEstimate> IEstimateEnvironment<T> getEstimates() {
        return fEstimates;
    }

    protected abstract <T extends ITermEstimate> IEstimateEnvironment<T> createEstimateEnvironment();

    protected abstract ITermEstimate getInitialEstimate(IConstraintTerm t);

    private void initializeEstimates(ConstraintGraph graph) {
        for(IConstraintTerm t: graph.getVariables()) {
            fEstimates.setEstimate(t, getInitialEstimate(t));
        }
    }

    public void solve() {
        fGraph= new ConstraintGraph(fConstraints);

        initializeEstimates(fGraph);

        if (fgDebug) {
            System.out.println("*** Beginning solution ***");
        }

        while (!fWorkList.empty()) {
            IConstraintTerm t= fWorkList.pop();
            List<IConstraint> usedIn= fGraph.getUsedIn(t);

            if (fgDebug) {
                System.out.println("Popped " + t + " off work-list.");
            }

            for(IConstraint c: usedIn) {
                c.satisfy(fEstimates, this);
            }
        }
    }

    public IEstimateEnvironment<?> getSolution() {
        return getEstimates();
    }

    public void reportResults() {
        IEstimateEnvironment<?> estimates= getEstimates();

        for(IConstraintTerm t: ConstraintGraph.sortedConstraintVariables(fGraph.getVariables())) {
            System.out.println(t.toString() + " => " + estimates.getEstimate(t));
        }
    }
}
