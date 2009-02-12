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

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Assignment;

public class ReachingDefsSolver {
    private IConstraint[] fConstraints;

    private ConstraintGraph fGraph;

    private final boolean fgDebug= false;

    class EstimateEnvironment implements IEstimateEnvironment {
	private Map<ConstraintTerm, DefinitionSet> fEstimates= new LinkedHashMap<ConstraintTerm, DefinitionSet>();

	public void setEstimate(ConstraintTerm t, DefinitionSet newEst) {
	    DefinitionSet curEst= (DefinitionSet) fEstimates.get(t);

	    if (curEst == null || !curEst.equals(newEst)) {
		if (false)
		    System.out.println("      New estimate for " + t + " => " + newEst);
		fEstimates.put(t, newEst);
		if (!fWorkList.contains(t))
		    fWorkList.push(t);
	    }
	}

	public DefinitionSet getEstimate(ConstraintTerm t) {
	    return (DefinitionSet) fEstimates.get(t);
	}
    }

    private EstimateEnvironment fEstimates= new EstimateEnvironment();

    private Stack<ConstraintTerm> fWorkList= new Stack<ConstraintTerm>();

    public ReachingDefsSolver(IConstraint[] constraints) {
	fConstraints= constraints;
    }

    private boolean isLValue(ASTNode selectedNode) {
	ASTNode parent= selectedNode.getParent();

	return (parent.getNodeType() == ASTNode.ASSIGNMENT && ((Assignment) parent).getLeftHandSide() == selectedNode);
    }

    public void solve() {
	fGraph= new ConstraintGraph(Arrays.asList(fConstraints));

	initializeEstimates(fGraph);

	if (fgDebug)
	    System.out.println("*** Beginning solution ***");

	while (!fWorkList.empty()) {
	    ConstraintTerm t= (ConstraintTerm) fWorkList.pop();
	    List/*<IConstraint>*/usedIn= fGraph.getUsedIn(t);

	    if (fgDebug)
		System.out.println("Popped " + t + " off work-list.");

	    for(Iterator iter= usedIn.iterator(); iter.hasNext();) {
		IConstraint c= (IConstraint) iter.next();

		satisfyConstraint(c);
	    }
	}
    }

    private void satisfyConstraint(IConstraint c) {
	ConstraintTerm lhs= c.getLeft();
	ConstraintTerm rhs= c.getRight();
	ConstraintOperator op= c.getOperator();

	if (lhs.isComplexTerm())
	    lhs.recomputeEstimate(fEstimates);
	if (rhs.isComplexTerm())
	    rhs.recomputeEstimate(fEstimates);

	DefinitionSet lhsEst= fEstimates.getEstimate(lhs);
	DefinitionSet rhsEst= fEstimates.getEstimate(rhs);

	if (fgDebug) {
	    System.out.println("  Satisfying constraint " + c);
	    System.out.println("    " + lhs + " => " + lhsEst);
	    System.out.println("    " + rhs + " => " + rhsEst);
	}

	if (!(op instanceof SubsetOperator))
	    throw new UnsupportedOperationException("Unable to process constraint operator " + op);

	if (!rhsEst.containsAll(lhsEst))
	    fEstimates.setEstimate(rhs, rhsEst.unionWith(lhsEst));
    }

    private void initializeEstimates(ConstraintGraph graph) {
	for(Iterator iter= graph.getVariables().iterator(); iter.hasNext();) {
	    ConstraintTerm v= (ConstraintTerm) iter.next();

	    if (v instanceof DefinitionLiteral)
		fEstimates.setEstimate(v, new DefinitionSet((DefinitionLiteral) v));
	    else
		fEstimates.setEstimate(v, new DefinitionSet());
	}
    }

    public void reportResults(ASTNode selectedNode) {
	for(Iterator iter= ConstraintGraph.sortedConstraintVariables(fGraph.getVariables()).iterator(); iter.hasNext();) {
	    ConstraintTerm t= (ConstraintTerm) iter.next();

	    System.out.println(t.toString() + " => " + fEstimates.getEstimate(t));
	}
	if (isLValue(selectedNode)) {
	    // find what references this definition reaches
	} else {
	    // find what definitions reach this reference
	}
    }

    public IEstimateEnvironment getReachingDefinitions() {
	return fEstimates;
    }
}
