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

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IVariableBinding;

public class ReachingDefsVariableFactory {
    private interface IVarNodeToLiteral extends Map<IVariableBinding,Map<ASTNode,DefinitionLiteral>> {
    }

    private class VarNodeToLiteral extends LinkedHashMap<IVariableBinding,Map<ASTNode,DefinitionLiteral>> implements IVarNodeToLiteral {
    }

    private IVarNodeToLiteral fVarMap= new VarNodeToLiteral();

    public DefinitionLiteral createDefinitionLiteral(IVariableBinding var, ASTNode label) {
        Map<ASTNode,DefinitionLiteral> label2DefLit= fVarMap.get(var);

        if (label2DefLit == null)
            fVarMap.put(var, label2DefLit= new LinkedHashMap<ASTNode,DefinitionLiteral>());

        DefinitionLiteral d= (DefinitionLiteral) label2DefLit.get(label);

        if (d == null) {
            d= new DefinitionLiteral(var, label);
            label2DefLit.put(label, d);
        }
        return d;
    }

    private Map<ASTNode,ReachingDefsEntry> fEntryMap= new LinkedHashMap<ASTNode,ReachingDefsEntry>();

    public ReachingDefsEntry createEntryLabel(ASTNode node) {
        ReachingDefsEntry e= (ReachingDefsEntry) fEntryMap.get(node);

        if (e == null)
            fEntryMap.put(node, e= new ReachingDefsEntry(node));
        return e;
    }

    private Map<ASTNode,ReachingDefsExit> fExitMap= new LinkedHashMap<ASTNode,ReachingDefsExit>();

    public ReachingDefsExit createExitLabel(ASTNode node) {
        ReachingDefsExit e= (ReachingDefsExit) fExitMap.get(node);

        if (e == null)
            fExitMap.put(node, e= new ReachingDefsExit(node));
        return e;
    }
}
