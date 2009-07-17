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

public class CFGPlainNode extends CFGSimpleNode {
    public CFGPlainNode(Object n) {
        super(n);
    }

    public boolean isStartNode() {
        return false;
    }

    public boolean isEndNode() {
        return false;
    }

    public String getPrettyName() {
        return SourceCFG.printRepFor(fASTNode);
    }

    public boolean equals(Object o) {
        if (!(o instanceof CFGPlainNode))
            return false;
        CFGPlainNode other= (CFGPlainNode) o;
        return fASTNode == other.fASTNode;
    }

    public int hashCode() {
        int result= 19;
        result= result * 23 + fASTNode.hashCode();
        return result;
    }
}