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
import java.util.Iterator;
import java.util.List;

public class CFGBasicBlockNode extends CFGNode {
    public final List<Object> fASTNodes= new ArrayList<Object>();

    public CFGBasicBlockNode(Object n1) {
        fASTNodes.add(n1);
    }

    public CFGBasicBlockNode(CFGBasicBlockNode bb) {
        fASTNodes.addAll(bb.fASTNodes);
    }

    public CFGBasicBlockNode(Object n1, Object n2) {
        fASTNodes.add(n1);
        fASTNodes.add(n2);
    }

    public void appendASTNode(Object n) {
        fASTNodes.add(n);
    }

    public void appendASTNodesFrom(CFGBasicBlockNode n) {
        fASTNodes.addAll(n.fASTNodes);
    }

    public String getPrettyName() {
        StringBuffer buff= new StringBuffer();

        buff.append("{");
        for(Iterator<Object> iter= fASTNodes.iterator(); iter.hasNext(); ) {
            Object n= iter.next();
            buff.append(SourceCFG.printRepFor(n));
            if (iter.hasNext())
                buff.append(";");
        }
        buff.append("}");
        return buff.toString();
    }

    public boolean isStartNode() {
        return false;
    }

    public boolean isEndNode() {
        return false;
    }

    public boolean equals(Object o) {
        if (!(o instanceof CFGBasicBlockNode))
            return false;
        CFGBasicBlockNode other= (CFGBasicBlockNode) o;
        if (fASTNodes.size() != other.fASTNodes.size())
            return false;
        for(int i= 0; i < fASTNodes.size(); i++)
            if (fASTNodes.get(i) != other.fASTNodes.get(i))
                return false;
        return true;
    }

    public int hashCode() {
        int result= 13;
        for(Object n: fASTNodes) {
            result= result * 29 + n.hashCode();
        }
        return result;
    }
}
