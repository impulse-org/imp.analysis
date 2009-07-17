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
import java.util.List;

import org.eclipse.imp.analysis.constraints.ConstraintFactory;
import org.eclipse.imp.analysis.constraints.IConstraint;
import org.eclipse.imp.analysis.constraints.IConstraintFactory;
import org.eclipse.imp.analysis.constraints.IConstraintTerm;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.ContinueStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

public class ReachingDefsConstraintCreator extends ConstraintCreatorBase {
    private final ReachingDefsVariableFactory fVariableFactory= new ReachingDefsVariableFactory();

    private final IConstraintFactory fConstraintFactory= new ConstraintFactory();

    public ReachingDefsVariableFactory getVariableFactory() {
        return fVariableFactory;
    }

    private IConstraint newSubsetConstraint(IConstraintTerm lhs, IConstraintTerm rhs) {
        return fConstraintFactory.createSimpleConstraint(lhs, rhs, SubsetOperator.getInstance());
    }

    private IConstraint[] createPassThroughConstraint(ASTNode node) {
        return new IConstraint[] { newSubsetConstraint(fVariableFactory.createEntryLabel(node), fVariableFactory.createExitLabel(node)) };
    }

    public IConstraint[] create(SimpleName name) {
        if (name.resolveBinding().getKind() == IBinding.VARIABLE)
            return createPassThroughConstraint(name);
        return EMPTY_ARRAY;
    }

    public IConstraint[] create(QualifiedName name) {
        if (name.resolveBinding().getKind() == IBinding.VARIABLE)
            return createPassThroughConstraint(name);
        return EMPTY_ARRAY;
    }

    public IConstraint[] create(BooleanLiteral node) {
        return createPassThroughConstraint(node);
    }

    public IConstraint[] create(NumberLiteral node) {
        return createPassThroughConstraint(node);
    }

    public IConstraint[] create(Assignment assign) {
        Expression lhs= assign.getLeftHandSide();
        Expression rhs= assign.getRightHandSide();

        if (lhs.getNodeType() != ASTNode.SIMPLE_NAME)
            return EMPTY_ARRAY;

        SimpleName name= (SimpleName) lhs;
        IBinding nameBinding= name.resolveBinding();

        if (nameBinding.getKind() != IBinding.VARIABLE)
            return EMPTY_ARRAY;

        IVariableBinding varBinding= (IVariableBinding) nameBinding;

        if (varBinding.isField())
            return EMPTY_ARRAY;

        DefinitionLiteral defLit= fVariableFactory.createDefinitionLiteral(varBinding, assign);
        ReachingDefsExit rdExit= fVariableFactory.createExitLabel(assign);

        return new IConstraint[] {
                newSubsetConstraint(fVariableFactory.createEntryLabel(assign), fVariableFactory.createEntryLabel(rhs)),
                newSubsetConstraint(defLit, rdExit),
                newSubsetConstraint(new ReachingDefsDifference(fVariableFactory.createExitLabel(rhs), fVariableFactory
                        .createDefinitionLiteral(varBinding, null)), rdExit) };
    }

    public IConstraint[] create(VariableDeclarationFragment vdf) {
        Name name= vdf.getName();
        IVariableBinding nameBinding= (IVariableBinding) name.resolveBinding();
        Expression init= vdf.getInitializer();

        return new IConstraint[] {
                newSubsetConstraint(new ReachingDefsDifference(fVariableFactory.createEntryLabel(vdf), fVariableFactory.createDefinitionLiteral(nameBinding,
                        null)), fVariableFactory.createExitLabel(vdf)),
                newSubsetConstraint(fVariableFactory.createDefinitionLiteral(nameBinding, vdf), fVariableFactory.createExitLabel(vdf)) };
    }

    public IConstraint[] create(VariableDeclarationExpression vde) {
        // TODO Handle multiple-fragment declarations
        ASTNode firstFrag= (ASTNode) vde.fragments().get(0);

        return new IConstraint[] { newSubsetConstraint(fVariableFactory.createEntryLabel(vde), fVariableFactory.createEntryLabel(firstFrag)),
                newSubsetConstraint(fVariableFactory.createExitLabel(firstFrag), fVariableFactory.createExitLabel(vde)) };
    }

    public IConstraint[] create(MethodInvocation inv) {
        // Assumes there is a receiver expression
        // TODO handle "this.", "super." and implicit-this calls
        Expression rcvr= inv.getExpression();
        List<Expression> arguments= inv.arguments();
        List<IConstraint> result= new ArrayList<IConstraint>();

        IConstraintTerm invEntry= fVariableFactory.createEntryLabel(inv);
        IConstraintTerm invExit= fVariableFactory.createExitLabel(inv);
        IConstraintTerm rcvrEntry= fVariableFactory.createEntryLabel(rcvr);
        IConstraintTerm rcvrExit= fVariableFactory.createExitLabel(rcvr);

        result.add(newSubsetConstraint(invEntry, rcvrEntry));

        if (arguments.size() > 0) {
            Expression prevArg= (Expression) arguments.get(0);
            result.add(newSubsetConstraint(rcvrExit, fVariableFactory.createEntryLabel(prevArg)));
            for(int i= 1; i < arguments.size(); i++) {
                Expression thisArg= (Expression) arguments.get(i);
                result.add(newSubsetConstraint(fVariableFactory.createExitLabel(prevArg), fVariableFactory.createEntryLabel(thisArg)));
                prevArg= thisArg;
            }
            Expression lastArg= (Expression) arguments.get(arguments.size() - 1);

            result.add(newSubsetConstraint(fVariableFactory.createExitLabel(lastArg), invExit));
        } else
            result.add(newSubsetConstraint(rcvrExit, invExit));

        return (IConstraint[]) result.toArray(new IConstraint[result.size()]);
    }

    private IConstraint[] createUnaryOpDefinitionConstraints(Expression pfe, Expression operand) {
        if (operand.getNodeType() != ASTNode.SIMPLE_NAME)
            return createPassThroughConstraint(pfe);

        Name name= (Name) operand;
        IVariableBinding nameBinding= (IVariableBinding) name.resolveBinding();
        ReachingDefsExit pfeExit= fVariableFactory.createExitLabel(pfe);
        DefinitionLiteral defWildcard= fVariableFactory.createDefinitionLiteral(nameBinding, null);
        DefinitionLiteral defLit= fVariableFactory.createDefinitionLiteral(nameBinding, pfe);

        return new IConstraint[] {
                // Don't we also need the constraint
                //   RD@entry(pfe) <= RD@entry(operand) ??? (see assignment above)
                newSubsetConstraint(new ReachingDefsDifference(fVariableFactory.createEntryLabel(pfe), defWildcard), pfeExit),
                newSubsetConstraint(defLit, pfeExit) };
    }

    public IConstraint[] create(PostfixExpression pfe) {
        Expression operand= pfe.getOperand();

        return createUnaryOpDefinitionConstraints(pfe, operand);
    }

    public IConstraint[] create(PrefixExpression pfe) {
        PrefixExpression.Operator operator= pfe.getOperator();

        if (operator == PrefixExpression.Operator.INCREMENT || operator == PrefixExpression.Operator.DECREMENT)
            return createUnaryOpDefinitionConstraints(pfe, pfe.getOperand());
        return createPassThroughConstraint(pfe);
    }

    public IConstraint[] create(InfixExpression ife) {
        if (false)
            return createPassThroughConstraint(ife);
        Expression left= ife.getLeftOperand();
        Expression right= ife.getRightOperand();

        return new IConstraint[] { newSubsetConstraint(fVariableFactory.createEntryLabel(ife), fVariableFactory.createEntryLabel(left)),
                newSubsetConstraint(fVariableFactory.createExitLabel(left), fVariableFactory.createEntryLabel(right)),
                newSubsetConstraint(fVariableFactory.createExitLabel(right), fVariableFactory.createExitLabel(ife)), };
    }

    public IConstraint[] create(ExpressionStatement es) {
        Expression expression= es.getExpression();

        return new IConstraint[] { newSubsetConstraint(fVariableFactory.createEntryLabel(es), fVariableFactory.createEntryLabel(expression)),
                newSubsetConstraint(fVariableFactory.createExitLabel(expression), fVariableFactory.createExitLabel(es)) };
    }

    public IConstraint[] create(VariableDeclarationStatement node) {
        // TODO Handle multiple-fragment declarations
        ASTNode firstFrag= (ASTNode) node.fragments().get(0);

        return new IConstraint[] { newSubsetConstraint(fVariableFactory.createEntryLabel(node), fVariableFactory.createEntryLabel(firstFrag)),
                newSubsetConstraint(fVariableFactory.createExitLabel(firstFrag), fVariableFactory.createExitLabel(node)) };
    }

    public IConstraint[] create(Block block) {
        List<Statement> stmts= block.statements();
        List<IConstraint> result= new ArrayList<IConstraint>();

        if (stmts.size() < 1)
            return createPassThroughConstraint(block);

        result.add(newSubsetConstraint(fVariableFactory.createEntryLabel(block), fVariableFactory.createEntryLabel((ASTNode) stmts.get(0))));
        for(int i= 1; i < stmts.size(); i++) {
            result.add(newSubsetConstraint(fVariableFactory.createExitLabel((ASTNode) stmts.get(i - 1)), fVariableFactory.createEntryLabel((ASTNode) stmts
                    .get(i))));
        }
        result.add(newSubsetConstraint(fVariableFactory.createExitLabel((ASTNode) stmts.get(stmts.size() - 1)), fVariableFactory.createExitLabel(block)));

        return (IConstraint[]) result.toArray(new IConstraint[result.size()]);
    }

    public IConstraint[] create(ForStatement forStmt) {
        // Assumes: exactly one init, a condition, exactly one update
        // TODO flesh out to handle various other possibilities
        Statement body= forStmt.getBody();
        Expression cond= forStmt.getExpression();
        List<Expression> inits= forStmt.initializers();
        List<Expression> updates= forStmt.updaters();
        Expression init= (Expression) inits.get(0);
        Expression update= (Expression) updates.get(0);
        List<IConstraint> result= new ArrayList<IConstraint>();

        IConstraintTerm forEntry= fVariableFactory.createEntryLabel(forStmt);
        IConstraintTerm forExit= fVariableFactory.createExitLabel(forStmt);
        IConstraintTerm condEntry= fVariableFactory.createEntryLabel(cond);
        IConstraintTerm condExit= fVariableFactory.createExitLabel(cond);
        IConstraintTerm bodyEntry= fVariableFactory.createEntryLabel(body);
        IConstraintTerm bodyExit= fVariableFactory.createExitLabel(body);

        result.add(newSubsetConstraint(forEntry, fVariableFactory.createEntryLabel(init)));
        result.add(newSubsetConstraint(fVariableFactory.createExitLabel(init), condEntry));
        result.add(newSubsetConstraint(condExit, bodyEntry));
        result.add(newSubsetConstraint(bodyExit, fVariableFactory.createEntryLabel(update)));
        result.add(newSubsetConstraint(fVariableFactory.createExitLabel(update), condEntry));
        result.add(newSubsetConstraint(condExit, forExit));

        return (IConstraint[]) result.toArray(new IConstraint[result.size()]);
    }

    public IConstraint[] create(IfStatement ifStmt) {
        Expression cond= ifStmt.getExpression();
        Statement thenStmt= ifStmt.getThenStatement();
        Statement elseStmt= ifStmt.getElseStatement();

        IConstraintTerm condExit= fVariableFactory.createExitLabel(cond);

        IConstraint ifEntryToCondEntry= newSubsetConstraint(fVariableFactory.createEntryLabel(ifStmt), fVariableFactory.createEntryLabel(cond));
        IConstraint condExitToThenEntry= newSubsetConstraint(fVariableFactory.createEntryLabel(thenStmt), condExit);

        if (elseStmt == null) {
            return new IConstraint[] { ifEntryToCondEntry, condExitToThenEntry, newSubsetConstraint(fVariableFactory.createExitLabel(elseStmt), condExit) };
        } else
            return new IConstraint[] { ifEntryToCondEntry, condExitToThenEntry };
    }

    private Statement findInnermostEnclosingLoop(ASTNode node) {
        while (node != null) {
            switch (node.getNodeType()) {
            case ASTNode.WHILE_STATEMENT:
            case ASTNode.FOR_STATEMENT:
            case ASTNode.DO_STATEMENT:
                return (Statement) node;
            }
            node= node.getParent();
        }
        throw new IllegalStateException("Can't find loop enclosing loop control statement " + node);
    }

    public IConstraint[] create(ContinueStatement cont) {
        return new IConstraint[] { newSubsetConstraint(fVariableFactory.createExitLabel(cont), fVariableFactory
                .createEntryLabel(findInnermostEnclosingLoop(cont))) };
    }

    public IConstraint[] create(BreakStatement brk) {
        return new IConstraint[] { newSubsetConstraint(fVariableFactory.createExitLabel(brk), fVariableFactory
                .createEntryLabel(findInnermostEnclosingLoop(brk))) };
    }
}
