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

import java.util.ArrayList;
import java.util.List;

public abstract class CFGNode {
    private static int fgNextID= 0;

    public final int fID= fgNextID++;

    public final List<CFGEdge> fOutgoingEdges= new ArrayList<CFGEdge>();

    public final List<CFGEdge> fIncomingEdges= new ArrayList<CFGEdge>();

    public void addIncomingEdge(CFGEdge e) {
        fIncomingEdges.add(e);
    }

    public CFGEdge getIncomingEdge(int idx) {
        return (CFGEdge) fIncomingEdges.get(idx);
    }

    public void addOutgoingEdge(CFGEdge e) {
        fOutgoingEdges.add(e);
    }

    public CFGEdge getOutgoingEdge(int idx) {
        return (CFGEdge) fOutgoingEdges.get(idx);
    }

    public abstract boolean isStartNode();

    public abstract boolean isEndNode();

    public String getShortName() {
        return "n" + Integer.toString(fID);
    }

    public abstract String getPrettyName(); // { return printRepFor(fASTNode); }

    public String toString() {
        return getPrettyName();
    }

    public static void resetID() {
        fgNextID= 0;
    }
}