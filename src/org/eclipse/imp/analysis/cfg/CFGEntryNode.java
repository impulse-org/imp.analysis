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

public class CFGEntryNode extends CFGSimpleNode {
    private final boolean fIsStart;

    public CFGEntryNode(Object n) {
        this(n, false);
    }

    public CFGEntryNode(Object n, boolean isStart) {
        super(n);
        fIsStart= isStart;
    }

    public String getPrettyName() {
        return "entry[" + SourceCFG.printRepFor(fASTNode) + "]";
    }

    public boolean isStartNode() {
        return fIsStart; // fASTNode.getParent().getNodeType() != ASTNode.METHOD_DECLARATION;
    }

    public boolean isEndNode() {
        return false;
    }

    public boolean equals(Object o) {
        if (!(o instanceof CFGEntryNode))
            return false;
        CFGEntryNode other= (CFGEntryNode) o;
        return fASTNode == other.fASTNode;
    }

    public int hashCode() {
        int result= 13;
        result= result * 41 + fASTNode.hashCode();
        return result;
    }
}