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
import org.eclipse.imp.analysis.constraints.ITermProcessor;

public abstract class NodeLabel extends ConstraintTerm {
    protected Object fNode;

    public NodeLabel(Object node) {
        fNode= node;
    }

    public void processTerms(ITermProcessor processor) {
        processor.processTerm(this);
    }
}
