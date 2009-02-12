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

import polyglot.ast.*;

public class PolyglotConstraintCreator {
    public static final IConstraint[] EMPTY_LIST= new IConstraint[0]; // Collections.emptyList();

    protected final TypeConstraintFactory fConstrFactory;
    protected final ITypeVariableFactory fVarFactory;

    public PolyglotConstraintCreator(TypeConstraintFactory constrFactory, ITypeVariableFactory varFactory) {
        fConstrFactory= constrFactory;
        fVarFactory= varFactory;
    }

    public ITypeVariableFactory getVariableFactory() {
        return fVarFactory;
    }

    public IConstraint[] createFor(ArrayAccess node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(ArrayAccessAssign node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(ArrayInit node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(ArrayTypeNode node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Assert node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Assign node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Binary node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Block node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(BooleanLit node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Branch node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Call node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(CanonicalTypeNode node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Case node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Cast node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Catch node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(CharLit node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(ClassBody node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(ClassDecl node) {
        return EMPTY_LIST;

        // TODO account for enums and annotations
    }

    public IConstraint[] createFor(ClassLit node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Conditional node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(ConstructorCall node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(ConstructorDecl node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Do node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Empty node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Eval node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Field node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(FieldAssign node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(FieldDecl node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(FloatLit node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(For node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Formal node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Id node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(If node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Import node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Initializer node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Instanceof node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(IntLit node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Labeled node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Local node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(LocalAssign node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(LocalClassDecl node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(LocalDecl node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(MethodDecl node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(New node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(NewArray node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(NullLit node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(NumLit node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(PackageNode node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Prefix node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(QualifierNode node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Return node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(SourceCollection node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(SourceFile node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Special node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(StringLit node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Switch node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(SwitchBlock node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Synchronized node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Throw node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Try node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(Unary node) {
        return EMPTY_LIST;
    }

    public IConstraint[] createFor(While node) {
        return EMPTY_LIST;
    }
}
