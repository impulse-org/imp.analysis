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

package org.eclipse.imp.analysis.type.constraints;

import org.eclipse.imp.analysis.constraints.IConstraint;
import org.eclipse.imp.analysis.type.constraints.variables.ITypeConstraintVariable;

import polyglot.ast.*;

/**
 * @author rfuhrer@watson.ibm.com
 */
public class BasicPolyglotConstraintCreator extends PolyglotConstraintCreator {
    public BasicPolyglotConstraintCreator(TypeConstraintFactory constrFactory, ITypeVariableFactory varFactory) {
        super(constrFactory, varFactory);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.ArrayAccess)
     */
    @Override
    public IConstraint[] createFor(ArrayAccess node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.ArrayAccessAssign)
     */
    @Override
    public IConstraint[] createFor(ArrayAccessAssign node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.ArrayInit)
     */
    @Override
    public IConstraint[] createFor(ArrayInit node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.ArrayTypeNode)
     */
    @Override
    public IConstraint[] createFor(ArrayTypeNode node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Assert)
     */
    @Override
    public IConstraint[] createFor(Assert node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Assign)
     */
    @Override
    public IConstraint[] createFor(Assign node) {
        Expr lhs= node.left();
        Expr rhs= node.right();
        ITypeConstraintVariable lhsVar= fVarFactory.makeVariableForExpression(lhs);
        ITypeConstraintVariable rhsVar= fVarFactory.makeVariableForExpression(rhs);

        return fConstrFactory.createSubtypeConstraint(rhsVar, lhsVar);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Binary)
     */
    @Override
    public IConstraint[] createFor(Binary node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Block)
     */
    @Override
    public IConstraint[] createFor(Block node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.BooleanLit)
     */
    @Override
    public IConstraint[] createFor(BooleanLit node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Branch)
     */
    @Override
    public IConstraint[] createFor(Branch node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Call)
     */
    @Override
    public IConstraint[] createFor(Call node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.CanonicalTypeNode)
     */
    @Override
    public IConstraint[] createFor(CanonicalTypeNode node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Case)
     */
    @Override
    public IConstraint[] createFor(Case node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Cast)
     */
    @Override
    public IConstraint[] createFor(Cast node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Catch)
     */
    @Override
    public IConstraint[] createFor(Catch node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.CharLit)
     */
    @Override
    public IConstraint[] createFor(CharLit node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.ClassBody)
     */
    @Override
    public IConstraint[] createFor(ClassBody node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.ClassDecl)
     */
    @Override
    public IConstraint[] createFor(ClassDecl node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.ClassLit)
     */
    @Override
    public IConstraint[] createFor(ClassLit node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Conditional)
     */
    @Override
    public IConstraint[] createFor(Conditional node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.ConstructorCall)
     */
    @Override
    public IConstraint[] createFor(ConstructorCall node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.ConstructorDecl)
     */
    @Override
    public IConstraint[] createFor(ConstructorDecl node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Do)
     */
    @Override
    public IConstraint[] createFor(Do node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Empty)
     */
    @Override
    public IConstraint[] createFor(Empty node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Eval)
     */
    @Override
    public IConstraint[] createFor(Eval node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Field)
     */
    @Override
    public IConstraint[] createFor(Field node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.FieldAssign)
     */
    @Override
    public IConstraint[] createFor(FieldAssign node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.FieldDecl)
     */
    @Override
    public IConstraint[] createFor(FieldDecl node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.FloatLit)
     */
    @Override
    public IConstraint[] createFor(FloatLit node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.For)
     */
    @Override
    public IConstraint[] createFor(For node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Formal)
     */
    @Override
    public IConstraint[] createFor(Formal node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.If)
     */
    @Override
    public IConstraint[] createFor(If node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Import)
     */
    @Override
    public IConstraint[] createFor(Import node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Initializer)
     */
    @Override
    public IConstraint[] createFor(Initializer node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Instanceof)
     */
    @Override
    public IConstraint[] createFor(Instanceof node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.IntLit)
     */
    @Override
    public IConstraint[] createFor(IntLit node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Labeled)
     */
    @Override
    public IConstraint[] createFor(Labeled node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Local)
     */
    @Override
    public IConstraint[] createFor(Local node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.LocalAssign)
     */
    @Override
    public IConstraint[] createFor(LocalAssign node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.LocalClassDecl)
     */
    @Override
    public IConstraint[] createFor(LocalClassDecl node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.LocalDecl)
     */
    @Override
    public IConstraint[] createFor(LocalDecl node) {
        if (node.init() != null) {
            ITypeConstraintVariable declVar= fVarFactory.makeVariableForDeclaration(node.varInstance());
            ITypeConstraintVariable initVar= fVarFactory.makeVariableForExpression(node.init());

            return fConstrFactory.createSubtypeConstraint(initVar, declVar);
        }
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.MethodDecl)
     */
    @Override
    public IConstraint[] createFor(MethodDecl node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.New)
     */
    @Override
    public IConstraint[] createFor(New node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.NewArray)
     */
    @Override
    public IConstraint[] createFor(NewArray node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.NullLit)
     */
    @Override
    public IConstraint[] createFor(NullLit node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.NumLit)
     */
    @Override
    public IConstraint[] createFor(NumLit node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.PackageNode)
     */
    @Override
    public IConstraint[] createFor(PackageNode node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Prefix)
     */
    @Override
    public IConstraint[] createFor(Prefix node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Return)
     */
    @Override
    public IConstraint[] createFor(Return node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.SourceFile)
     */
    @Override
    public IConstraint[] createFor(SourceFile node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Special)
     */
    @Override
    public IConstraint[] createFor(Special node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.StringLit)
     */
    @Override
    public IConstraint[] createFor(StringLit node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Switch)
     */
    @Override
    public IConstraint[] createFor(Switch node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.SwitchBlock)
     */
    @Override
    public IConstraint[] createFor(SwitchBlock node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Synchronized)
     */
    @Override
    public IConstraint[] createFor(Synchronized node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Throw)
     */
    @Override
    public IConstraint[] createFor(Throw node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Try)
     */
    @Override
    public IConstraint[] createFor(Try node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Unary)
     */
    @Override
    public IConstraint[] createFor(Unary node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.While)
     */
    @Override
    public IConstraint[] createFor(While node) {
        // TODO Auto-generated method stub
        return super.createFor(node);
    }
}
