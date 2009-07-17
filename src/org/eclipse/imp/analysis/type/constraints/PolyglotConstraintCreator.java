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

import java.util.Collection;
import java.util.Collections;

import org.eclipse.imp.analysis.constraints.IConstraint;
import org.eclipse.imp.analysis.type.constraints.TypeConstraintCollector.IConstraintAcceptor;

import polyglot.ast.*;

public class PolyglotConstraintCreator {
    public static final Collection<IConstraint> EMPTY_LIST= Collections.emptyList();

    protected final TypeConstraintFactory fConstrFactory;
    protected final ITypeVariableFactory fVarFactory;
    protected IConstraintAcceptor fConstraintAcceptor;

    public PolyglotConstraintCreator(TypeConstraintFactory constrFactory, ITypeVariableFactory varFactory) {
        this(constrFactory, varFactory, null);
    }

    public PolyglotConstraintCreator(TypeConstraintFactory constrFactory, ITypeVariableFactory varFactory, IConstraintAcceptor constraintAcceptor) {
        fConstrFactory= constrFactory;
        fVarFactory= varFactory;
        fConstraintAcceptor= constraintAcceptor;
    }

    public ITypeVariableFactory getVariableFactory() {
        return fVarFactory;
    }

    public void setConstraintAcceptor(IConstraintAcceptor constraintAcceptor) {
        fConstraintAcceptor= constraintAcceptor;
    }

    public void createFor(ArrayAccess node) { }

    public void createFor(ArrayAccessAssign node) { }

    public void createFor(ArrayInit node) { }

    public void createFor(ArrayTypeNode node) { }

    public void createFor(Assert node) { }

    public void createFor(Assign node) { }

    public void createFor(Binary node) { }

    public void createFor(Block node) { }

    public void createFor(BooleanLit node) { }

    public void createFor(Branch node) { }

    public void createFor(Call node) { }

    public void createFor(CanonicalTypeNode node) { }

    public void createFor(Case node) { }

    public void createFor(Cast node) { }

    public void createFor(Catch node) { }

    public void createFor(CharLit node) { }

    public void createFor(ClassBody node) { }

    public void createFor(ClassDecl node) {
        // TODO account for enums and annotations
    }

    public void createFor(ClassLit node) { }

    public void createFor(Conditional node) { }

    public void createFor(ConstructorCall node) { }

    public void createFor(ConstructorDecl node) { }

    public void createFor(Do node) { }

    public void createFor(Empty node) { }

    public void createFor(Eval node) { }

    public void createFor(Field node) { }

    public void createFor(FieldAssign node) { }

    public void createFor(FieldDecl node) { }

    public void createFor(FloatLit node) { }

    public void createFor(For node) { }

    public void createFor(Formal node) { }

    public void createFor(Id node) { }

    public void createFor(If node) { }

    public void createFor(Import node) { }

    public void createFor(Initializer node) { }

    public void createFor(Instanceof node) { }

    public void createFor(IntLit node) { }

    public void createFor(Labeled node) { }

    public void createFor(Local node) { }

    public void createFor(LocalAssign node) { }

    public void createFor(LocalClassDecl node) { }

    public void createFor(LocalDecl node) { }

    public void createFor(MethodDecl node) { }

    public void createFor(New node) { }

    public void createFor(NewArray node) { }

    public void createFor(NullLit node) { }

    public void createFor(NumLit node) { }

    public void createFor(PackageNode node) { }

    public void createFor(Prefix node) { }

    public void createFor(QualifierNode node) { }

    public void createFor(Return node) { }

    public void createFor(SourceCollection node) { }

    public void createFor(SourceFile node) { }

    public void createFor(Special node) { }

    public void createFor(StringLit node) { }

    public void createFor(Switch node) { }

    public void createFor(SwitchBlock node) { }

    public void createFor(Synchronized node) { }

    public void createFor(Throw node) { }

    public void createFor(Try node) { }

    public void createFor(Unary node) { }

    public void createFor(While node) { }
}
