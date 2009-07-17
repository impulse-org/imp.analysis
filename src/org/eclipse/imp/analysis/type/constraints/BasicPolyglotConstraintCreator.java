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

import org.eclipse.imp.analysis.type.constraints.TypeConstraintCollector.IConstraintAcceptor;
import org.eclipse.imp.analysis.type.constraints.variables.ITypeConstraintVariable;

import polyglot.ast.ArrayAccess;
import polyglot.ast.ArrayAccessAssign;
import polyglot.ast.ArrayInit;
import polyglot.ast.ArrayTypeNode;
import polyglot.ast.Assert;
import polyglot.ast.Assign;
import polyglot.ast.Binary;
import polyglot.ast.Block;
import polyglot.ast.BooleanLit;
import polyglot.ast.Branch;
import polyglot.ast.Call;
import polyglot.ast.CanonicalTypeNode;
import polyglot.ast.Case;
import polyglot.ast.Cast;
import polyglot.ast.Catch;
import polyglot.ast.CharLit;
import polyglot.ast.ClassBody;
import polyglot.ast.ClassDecl;
import polyglot.ast.ClassLit;
import polyglot.ast.Conditional;
import polyglot.ast.ConstructorCall;
import polyglot.ast.ConstructorDecl;
import polyglot.ast.Do;
import polyglot.ast.Empty;
import polyglot.ast.Eval;
import polyglot.ast.Expr;
import polyglot.ast.Field;
import polyglot.ast.FieldAssign;
import polyglot.ast.FieldDecl;
import polyglot.ast.FloatLit;
import polyglot.ast.For;
import polyglot.ast.Formal;
import polyglot.ast.If;
import polyglot.ast.Import;
import polyglot.ast.Initializer;
import polyglot.ast.Instanceof;
import polyglot.ast.IntLit;
import polyglot.ast.Labeled;
import polyglot.ast.Local;
import polyglot.ast.LocalAssign;
import polyglot.ast.LocalClassDecl;
import polyglot.ast.LocalDecl;
import polyglot.ast.MethodDecl;
import polyglot.ast.New;
import polyglot.ast.NewArray;
import polyglot.ast.NullLit;
import polyglot.ast.NumLit;
import polyglot.ast.PackageNode;
import polyglot.ast.Prefix;
import polyglot.ast.Return;
import polyglot.ast.SourceFile;
import polyglot.ast.Special;
import polyglot.ast.StringLit;
import polyglot.ast.Switch;
import polyglot.ast.SwitchBlock;
import polyglot.ast.Synchronized;
import polyglot.ast.Throw;
import polyglot.ast.Try;
import polyglot.ast.Unary;
import polyglot.ast.While;

/**
 * @author rfuhrer@watson.ibm.com
 */
public class BasicPolyglotConstraintCreator extends PolyglotConstraintCreator {
    public BasicPolyglotConstraintCreator(TypeConstraintFactory constrFactory, ITypeVariableFactory varFactory) {
        this(constrFactory, varFactory, null);
    }

    public BasicPolyglotConstraintCreator(TypeConstraintFactory constrFactory, ITypeVariableFactory varFactory, IConstraintAcceptor constraintAcceptor) {
        super(constrFactory, varFactory, constraintAcceptor);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.ArrayAccess)
     */
    @Override
    public void createFor(ArrayAccess node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.ArrayAccessAssign)
     */
    @Override
    public void createFor(ArrayAccessAssign node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.ArrayInit)
     */
    @Override
    public void createFor(ArrayInit node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.ArrayTypeNode)
     */
    @Override
    public void createFor(ArrayTypeNode node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Assert)
     */
    @Override
    public void createFor(Assert node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Assign)
     */
    @Override
    public void createFor(Assign node) {
        Expr lhs= node.left();
        Expr rhs= node.right();
        ITypeConstraintVariable lhsVar= fVarFactory.makeVariableForExpression(lhs);
        ITypeConstraintVariable rhsVar= fVarFactory.makeVariableForExpression(rhs);

        fConstraintAcceptor.accept(fConstrFactory.createSubtypeConstraint(rhsVar, lhsVar));
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Binary)
     */
    @Override
    public void createFor(Binary node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Block)
     */
    @Override
    public void createFor(Block node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.BooleanLit)
     */
    @Override
    public void createFor(BooleanLit node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Branch)
     */
    @Override
    public void createFor(Branch node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Call)
     */
    @Override
    public void createFor(Call node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.CanonicalTypeNode)
     */
    @Override
    public void createFor(CanonicalTypeNode node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Case)
     */
    @Override
    public void createFor(Case node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Cast)
     */
    @Override
    public void createFor(Cast node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Catch)
     */
    @Override
    public void createFor(Catch node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.CharLit)
     */
    @Override
    public void createFor(CharLit node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.ClassBody)
     */
    @Override
    public void createFor(ClassBody node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.ClassDecl)
     */
    @Override
    public void createFor(ClassDecl node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.ClassLit)
     */
    @Override
    public void createFor(ClassLit node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Conditional)
     */
    @Override
    public void createFor(Conditional node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.ConstructorCall)
     */
    @Override
    public void createFor(ConstructorCall node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.ConstructorDecl)
     */
    @Override
    public void createFor(ConstructorDecl node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Do)
     */
    @Override
    public void createFor(Do node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Empty)
     */
    @Override
    public void createFor(Empty node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Eval)
     */
    @Override
    public void createFor(Eval node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Field)
     */
    @Override
    public void createFor(Field node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.FieldAssign)
     */
    @Override
    public void createFor(FieldAssign node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.FieldDecl)
     */
    @Override
    public void createFor(FieldDecl node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.FloatLit)
     */
    @Override
    public void createFor(FloatLit node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.For)
     */
    @Override
    public void createFor(For node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Formal)
     */
    @Override
    public void createFor(Formal node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.If)
     */
    @Override
    public void createFor(If node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Import)
     */
    @Override
    public void createFor(Import node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Initializer)
     */
    @Override
    public void createFor(Initializer node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Instanceof)
     */
    @Override
    public void createFor(Instanceof node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.IntLit)
     */
    @Override
    public void createFor(IntLit node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Labeled)
     */
    @Override
    public void createFor(Labeled node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Local)
     */
    @Override
    public void createFor(Local node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.LocalAssign)
     */
    @Override
    public void createFor(LocalAssign node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.LocalClassDecl)
     */
    @Override
    public void createFor(LocalClassDecl node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.LocalDecl)
     */
    @Override
    public void createFor(LocalDecl node) {
        if (node.init() != null) {
            ITypeConstraintVariable declVar= fVarFactory.makeVariableForDeclaration(node.varInstance());
            ITypeConstraintVariable initVar= fVarFactory.makeVariableForExpression(node.init());

            fConstraintAcceptor.accept(fConstrFactory.createSubtypeConstraint(initVar, declVar));
        }
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.MethodDecl)
     */
    @Override
    public void createFor(MethodDecl node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.New)
     */
    @Override
    public void createFor(New node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.NewArray)
     */
    @Override
    public void createFor(NewArray node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.NullLit)
     */
    @Override
    public void createFor(NullLit node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.NumLit)
     */
    @Override
    public void createFor(NumLit node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.PackageNode)
     */
    @Override
    public void createFor(PackageNode node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Prefix)
     */
    @Override
    public void createFor(Prefix node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Return)
     */
    @Override
    public void createFor(Return node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.SourceFile)
     */
    @Override
    public void createFor(SourceFile node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Special)
     */
    @Override
    public void createFor(Special node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.StringLit)
     */
    @Override
    public void createFor(StringLit node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Switch)
     */
    @Override
    public void createFor(Switch node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.SwitchBlock)
     */
    @Override
    public void createFor(SwitchBlock node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Synchronized)
     */
    @Override
    public void createFor(Synchronized node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Throw)
     */
    @Override
    public void createFor(Throw node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Try)
     */
    @Override
    public void createFor(Try node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.Unary)
     */
    @Override
    public void createFor(Unary node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.PolyglotConstraintCreator#createFor(polyglot.ast.While)
     */
    @Override
    public void createFor(While node) {
        // TODO Auto-generated method stub
        super.createFor(node);
    }
}
