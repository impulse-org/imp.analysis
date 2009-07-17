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

import java.util.HashMap;
import java.util.Map;

public class CFGNodeEdgeFactory {
    private Map<Object,CFGNode> fPlainMap= new HashMap<Object,CFGNode>();

    private Map<Object,CFGNode> fEntryMap= new HashMap<Object,CFGNode>();

    private Map<Object,CFGNode> fExitMap= new HashMap<Object,CFGNode>();

    private SourceCFG fCFG;

    public CFGNodeEdgeFactory(SourceCFG cfg) {
        fCFG= cfg;
    }

    public CFGNode getEntryNodeFor(Object astNode) {
        CFGNode cfgNode= (CFGNode) fEntryMap.get(astNode);
        if (cfgNode == null) {
            fEntryMap.put(astNode, cfgNode= new CFGEntryNode(astNode));
            fCFG.addNode(cfgNode);
        }
        return cfgNode;
    }

    public CFGNode getExitNodeFor(Object astNode) {
        CFGNode cfgNode= (CFGNode) fExitMap.get(astNode);
        if (cfgNode == null) {
            fExitMap.put(astNode, cfgNode= new CFGExitNode(astNode));
            fCFG.addNode(cfgNode);
        }
        return cfgNode;
    }

    public CFGNode mergeNodes(CFGNode n1, CFGNode n2) {
        if (n1 instanceof CFGSimpleNode) {
            CFGSimpleNode node1= (CFGSimpleNode) n1;
            if (n2 instanceof CFGSimpleNode) {
                CFGSimpleNode node2= (CFGSimpleNode) n2;

                if (node1.fASTNode == node2.fASTNode)
                    return getPlainNodeFor(node1.fASTNode);

                return getBasicBlockNodeFor(node1.fASTNode, node2.fASTNode);
            } else
                return getBasicBlockNodeFor(node1.fASTNode, (CFGBasicBlockNode) n2);
        } else {
            CFGBasicBlockNode bb1= (CFGBasicBlockNode) n1;

            if (n2 instanceof CFGSimpleNode) {
                CFGSimpleNode node2= (CFGSimpleNode) n2;

                return getBasicBlockNodeFor(bb1, node2.fASTNode);
            } else {
                CFGBasicBlockNode bb2= (CFGBasicBlockNode) n2;

                return getBasicBlockNodeFor(bb1, bb2);
            }
        }
    }

    public CFGNode getPlainNodeFor(Object n) {
        CFGNode cfgNode= (CFGNode) fPlainMap.get(n);
        if (cfgNode == null) {
            fPlainMap.put(n, cfgNode= new CFGPlainNode(n));
            fCFG.addNode(cfgNode);
        }
        return cfgNode;
    }

    public CFGBasicBlockNode getBasicBlockNodeFor(Object n1, Object n2) {
        CFGBasicBlockNode newNode= new CFGBasicBlockNode(n1, n2);
        fCFG.addNode(newNode);
        return newNode;
    }

    public CFGBasicBlockNode getBasicBlockNodeFor(CFGBasicBlockNode bb, Object n2) {
        CFGBasicBlockNode newNode= new CFGBasicBlockNode(bb);
        newNode.appendASTNode(n2);
        fCFG.addNode(newNode);
        return newNode;
    }

    public CFGBasicBlockNode getBasicBlockNodeFor(Object n1, CFGBasicBlockNode bb) {
        CFGBasicBlockNode newNode= new CFGBasicBlockNode(n1);
        newNode.appendASTNodesFrom(bb);
        fCFG.addNode(newNode);
        return newNode;
    }

    public CFGBasicBlockNode getBasicBlockNodeFor(CFGBasicBlockNode bb1, CFGBasicBlockNode bb2) {
        CFGBasicBlockNode newNode= new CFGBasicBlockNode(bb1);
        newNode.appendASTNodesFrom(bb2);
        fCFG.addNode(newNode);
        return newNode;
    }

    public void addEdge(CFGNode from, CFGNode to) {
        CFGEdge edge= new CFGPlainEdge(from, to);
        if (fCFG.fDebug)
            System.out.println(edge);
        fCFG.addEdge(edge);
    }

    public void addConditionalEdge(CFGNode from, CFGNode to, boolean label) {
        CFGConditionalEdge edge= new CFGConditionalEdge(from, to, label);
        if (fCFG.fDebug)
            System.out.println(edge);
        fCFG.addEdge(edge);
    }

    public void addExceptionEdge(CFGNode from, CFGNode to, Object label) {
        CFGExceptionEdge<?> edge= new CFGExceptionEdge(from, to, label);
        if (fCFG.fDebug)
            System.out.println(edge);
        fCFG.addEdge(edge);
    }
}
