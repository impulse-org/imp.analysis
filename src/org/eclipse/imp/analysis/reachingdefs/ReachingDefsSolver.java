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

import java.util.Collection;
import java.util.List;

import org.eclipse.imp.analysis.constraints.ConstraintSolver;
import org.eclipse.imp.analysis.constraints.IConstraint;
import org.eclipse.imp.analysis.constraints.IConstraintTerm;
import org.eclipse.imp.analysis.constraints.IEstimateEnvironment;
import org.eclipse.imp.analysis.constraints.ITermEstimate;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Assignment;

public class ReachingDefsSolver extends ConstraintSolver {
    class RDEstimateEnvironment extends EstimateEnvironment<DefinitionSet> { };

    public ReachingDefsSolver(Collection<IConstraint> constraints) {
        super(constraints);
    }

    @Override
    public IEstimateEnvironment<DefinitionSet> getSolution() {
        return (IEstimateEnvironment<DefinitionSet>) super.getSolution();
    }

    @Override
    protected IEstimateEnvironment<DefinitionSet> createEstimateEnvironment() {
        return new RDEstimateEnvironment();
    }

    private boolean isLValue(ASTNode selectedNode) {
        ASTNode parent= selectedNode.getParent();

        return (parent.getNodeType() == ASTNode.ASSIGNMENT && ((Assignment) parent).getLeftHandSide() == selectedNode);
    }

    protected ITermEstimate getInitialEstimate(IConstraintTerm t) {
        if (t instanceof DefinitionLiteral) {
            return new DefinitionSet((DefinitionLiteral) t);
        } else {
            return new DefinitionSet();
        }
    }
}
