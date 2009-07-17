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

package org.eclipse.imp.analysis.cfg;

public abstract class CFGEdge {
    public CFGNode fSource;

    public CFGNode fDest;

    public CFGEdge(CFGNode src, CFGNode dst) {
        fSource= src;
        fDest= dst;
        fSource.addOutgoingEdge(this);
        fDest.addIncomingEdge(this);
    }

    public abstract boolean isPlainEdge();

    public String getLabel() {
        return "";
    }

    public String toString() {
        StringBuffer buff= new StringBuffer();
        buff.append(fSource.toString()).append(" => ").append(fDest);
        return buff.toString();
    }
}