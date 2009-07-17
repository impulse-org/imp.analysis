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
import java.util.Stack;

/**
 * Representation of and computation of a control-flow graph (CFG) for a
 * JDT AST MethodDeclaration.
 * @author rfuhrer@watson.ibm.com
 */
public class SourceCFG {
    public boolean fDebug= true;

    protected final List<CFGNode> fAllNodes= new ArrayList<CFGNode>();

    protected final List<CFGEdge> fAllEdges= new ArrayList<CFGEdge>();

    protected final CFGNodeEdgeFactory fFactory;

    public SourceCFG() {
        fFactory= new CFGNodeEdgeFactory(this);
    }

    static String printRepFor(Object n) {
        String ns= n.toString();

        return ns.replaceAll("\n", " ").replaceAll("\"", "\\\\\"");
    }

    public void addNode(CFGNode n) {
        fAllNodes.add(n);
    }

    public void addEdge(CFGEdge e) {
        fAllEdges.add(e);
    }

    protected void reduce() {
        final Stack<CFGEdge> edgeStack= new Stack<CFGEdge>();

        edgeStack.addAll(fAllEdges);
        while (!edgeStack.isEmpty()) {
            final CFGEdge e= edgeStack.pop();
            // Merge node pairs of the form:    (pred)  -->  (succ)
            // i.e. node pairs connected by a single simple edge that is the sole outgoing edge
            // of the predecessor and the sole incoming edge of the successor. In general, we'll
            // need to mark that the merged node now covers more than one ASTNode. Performing
            // this merging repeatedly has the effect of determining basic blocks.
            final CFGNode src= e.fSource;
            final CFGNode dest= e.fDest;

            if (src.fOutgoingEdges.size() == 1 && dest.fIncomingEdges.size() == 1 && e.isPlainEdge()) {
                // merge src and dest
                // TODO Following won't merge sequences like { entry[x+y], x, y, exit[x+y] } but it should.
                if (src instanceof CFGEntryNode && dest instanceof CFGExitNode && ((CFGEntryNode) src).fASTNode == ((CFGExitNode) dest).fASTNode
                        || src instanceof CFGPlainNode && dest instanceof CFGPlainNode) {
                    System.out.println("Merge node " + src.getPrettyName() + " with node " + dest.getPrettyName());

                    final CFGNode newNode= fFactory.mergeNodes(src, dest);

                    // patch all incoming edges to src to target the merged node
                    for(CFGEdge in: src.fIncomingEdges) {
                        in.fDest= newNode;
                        newNode.addIncomingEdge(in);
                        if (!edgeStack.contains(in))
                            edgeStack.push(in);
                    }
                    // patch all outgoing edges from dest to emanate from the merged node
                    for(CFGEdge out: dest.fOutgoingEdges) {
                        out.fSource= newNode;
                        newNode.addOutgoingEdge(out);
                        if (!edgeStack.contains(out))
                            edgeStack.push(out);
                    }
                    fAllEdges.remove(e);
                    fAllNodes.remove(src);
                    fAllNodes.remove(dest);
                }
            }
        }
    }

    public String asDot() {
        StringBuffer buff= new StringBuffer();

        buff.append("digraph foo {\n");
        buff.append("    node [shape = ellipse, arrowhead = normal];\n");
        // n0 [label="s0"];
        for(CFGNode n: fAllNodes) {
            buff.append("    ").append(n.getShortName()).append(' ').append("[label=\"").append(n.toString()).append("\"];\n");
        }
        for(CFGNode src: fAllNodes) {
            for(Iterator<CFGEdge> edgeIter= src.fOutgoingEdges.iterator(); edgeIter.hasNext(); ) {
                // n0 -> n1 [label="i0"];
                CFGEdge e= edgeIter.next();
                CFGNode dest= e.fDest;
                String label= e.getLabel();
                buff.append("    ").append(src.getShortName()).append(" -> ").append(dest.getShortName());
                if (label.length() > 0)
                    buff.append(" [label=\"").append(label).append("\"]");
                buff.append(";\n");
            }
        }
        buff.append("}\n");
        return buff.toString();
    }

    public boolean sanityCheck() {
        boolean hasErrors= false;
        for(CFGNode node: fAllNodes) {
            if (node.fIncomingEdges.isEmpty() && node.isStartNode()) {
                System.err.println("Node " + node + " has no incoming edges!");
                hasErrors= true;
            }
            if (node.fOutgoingEdges.isEmpty() && node.isEndNode()) {
                System.err.println("Node " + node + " has no outgoing edges!");
                hasErrors= true;
            }
        }
        return !hasErrors;
    }

    public String toString() {
        StringBuffer buff= new StringBuffer();

        for(CFGEdge e: fAllEdges) {
            buff.append(e).append('\n');
        }
        return buff.toString();
    }
}
