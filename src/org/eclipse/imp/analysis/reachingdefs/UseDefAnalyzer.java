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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.SimpleName;

public class UseDefAnalyzer {
    static final List EMPTY_LIST= new ArrayList();

    static final Set EMPTY_SET= new HashSet();

    private CompilationUnit fCompilationUnit;

    private boolean USE_TRIVIAL_REF_FINDER= false;

    private IConstraint[] fConstraints;

    private ReachingDefsVariableFactory fVariableFactory;

    public UseDefAnalyzer(CompilationUnit unit) {
	fCompilationUnit= unit;
    }

    public Set<ASTNode> findUsesDefsOf(ASTNode selectedNode) {
	if (selectedNode.getNodeType() != ASTNode.SIMPLE_NAME)
	    return Collections.emptySet();

	Name selectedName= (Name) selectedNode;
	IBinding nodeBinding= selectedName.resolveBinding();

	if (!(nodeBinding instanceof IVariableBinding))
	    return Collections.emptySet();

	IVariableBinding varBinding= (IVariableBinding) nodeBinding;
	MethodDeclaration method= getOwningMethod(selectedNode);

	if (USE_TRIVIAL_REF_FINDER)
	    return findRefsToVariable(varBinding, method);
	else {
	    collectConstraints();
	    ReachingDefsSolver solver= new ReachingDefsSolver(fConstraints);
	    solver.solve();
	    //			solver.reportResults(selectedNode);

	    IEstimateEnvironment reachingDefs= solver.getReachingDefinitions();

	    if (isDefinition(selectedNode)) {
		return findRefsToDef(selectedNode.getParent(), reachingDefs);
	    } else {
		return findDefsForRef(selectedNode, varBinding, reachingDefs);
	    }
	}
    }

    private Set<ASTNode> findRefsToDef(ASTNode def, final IEstimateEnvironment reachingDefs) {
	final Set<ASTNode> result= new HashSet<ASTNode>();
	ASTNode method= getOwningMethod(def);
	SimpleName name= (SimpleName) ((Assignment) def).getLeftHandSide();
	final IVariableBinding defBinding= (IVariableBinding) name.resolveBinding();
	final DefinitionLiteral defLit= new DefinitionLiteral(defBinding, def);

	method.accept(new ASTVisitor() {
	    public boolean visit(SimpleName node) {
		if (!node.resolveBinding().isEqualTo(defBinding))
		    return false;
		DefinitionSet rds= reachingDefs.getEstimate(fVariableFactory.createEntryLabel(node));

		if (rds.contains(defLit))
		    result.add(node);
		return false;
	    }
	});

	return result;
    }

    private Set<ASTNode> findDefsForRef(ASTNode ref, IVariableBinding varBinding, IEstimateEnvironment reachingDefs) {
	DefinitionSet defs= reachingDefs.getEstimate(fVariableFactory.createEntryLabel(ref));
	Set<ASTNode> result= new HashSet<ASTNode>();

	for(Iterator iter= defs.iterator(); iter.hasNext();) {
	    DefinitionLiteral def= (DefinitionLiteral) iter.next();

	    if (varBinding.isEqualTo(def.getVarBinding()))
		result.add(def.getLabel());
	}
	return result;
    }

    private Set<ASTNode> defsToNodes(Set<DefinitionLiteral> defs) {
	Set<ASTNode> result= new HashSet<ASTNode>();

	for(Iterator<DefinitionLiteral> iter= defs.iterator(); iter.hasNext(); ) {
	    DefinitionLiteral def= iter.next();

	    if (!def.isWildcard()) // these really shouldn't reach the results anyway...
		result.add(def.getLabel());
	}
	return result;
    }

    private boolean isDefinition(ASTNode selectedNode) {
	return selectedNode.getParent().getNodeType() == ASTNode.ASSIGNMENT;
    }

    private void dumpConstraints() {
	System.out.println("*** Constraints ***");
	for(int i= 0; i < fConstraints.length; i++) {
	    System.out.println(fConstraints[i]);
	}
    }

    private void collectConstraints() {
	ReachingDefsConstraintCreator rdcc= new ReachingDefsConstraintCreator();
	ConstraintVisitor cv= new ConstraintVisitor(rdcc);

	fCompilationUnit.accept(cv);
	fConstraints= cv.getConstraints();
	fVariableFactory= rdcc.getVariableFactory();
    }

    private MethodDeclaration getOwningMethod(ASTNode selectedNode) {
	ASTNode parent= selectedNode;
	while (parent.getNodeType() != ASTNode.METHOD_DECLARATION)
	    parent= parent.getParent();
	return (MethodDeclaration) parent;
    }

    private Set<ASTNode> findRefsToVariable(final IVariableBinding varBinding, MethodDeclaration method) {
	final Set<ASTNode> refs= new HashSet<ASTNode>();

	method.accept(new ASTVisitor() {
	    public boolean visit(SimpleName node) {
		if (node.resolveBinding().equals(varBinding))
		    refs.add(node);
		return false;
	    }
	});
	return refs;
    }
}
