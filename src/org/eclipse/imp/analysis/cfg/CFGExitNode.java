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

public class CFGExitNode extends CFGSimpleNode {
    private final boolean fIsEnd;

    public CFGExitNode(Object n) {
        this(n, false);
    }

    public CFGExitNode(Object n, boolean isEnd) {
        super(n);
        fIsEnd= isEnd;
    }

    public String getPrettyName() {
        return "exit[" + SourceCFG.printRepFor(fASTNode) + "]";
    }

    public boolean isStartNode() {
        return false;
    }

    public boolean isEndNode() {
        return fIsEnd; // fASTNode.getParent().getNodeType() != ASTNode.METHOD_DECLARATION;
    }

    public boolean equals(Object o) {
        if (!(o instanceof CFGExitNode))
            return false;
        CFGExitNode other= (CFGExitNode) o;
        return fASTNode == other.fASTNode;
    }

    public int hashCode() {
        int result= 17;
        result= result * 43 + fASTNode.hashCode();
        return result;
    }
}