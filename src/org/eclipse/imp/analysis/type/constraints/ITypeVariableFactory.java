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

import org.eclipse.imp.analysis.ICompilationUnitRange;
import org.eclipse.imp.analysis.constraints.IConstraintTermFactory;
import org.eclipse.imp.analysis.type.constraints.variables.ArrayElementVariable;
import org.eclipse.imp.analysis.type.constraints.variables.DeclaringTypeVariable;
import org.eclipse.imp.analysis.type.constraints.variables.ITypeConstraintVariable;
import org.eclipse.imp.analysis.type.constraints.variables.ImmutableTypeVariable;
import org.eclipse.imp.analysis.type.constraints.variables.MethodParameterTypeVariable;
import org.eclipse.imp.analysis.type.constraints.variables.ReturnTypeVariable;
import org.eclipse.imp.model.ICompilationUnit;

import polyglot.ast.Expr;
import polyglot.types.FieldInstance;
import polyglot.types.MethodInstance;
import polyglot.types.Type;
import polyglot.types.VarInstance;

/**
 * A interface for a factory that creates type constraint variables, and is
 * responsible for canonicalizing variables (to avoid unwanted duplication
 * and to speed equality detection).
 */
// TODO Make this interface independent of Polyglot-specific types
public interface ITypeVariableFactory extends IConstraintTermFactory {
    void setCompilationUnit(ICompilationUnit unit);

    ITypeConstraintVariable makeVariableForExpression(Expr expression);

    ITypeConstraintVariable makeVariableForDeclaration(VarInstance varDecl);

    ArrayElementVariable makeArrayElementVariable(ITypeConstraintVariable arrayVar, ICompilationUnitRange range);

    DeclaringTypeVariable makeDeclaringTypeVariable(Type memberType);

    DeclaringTypeVariable makeDeclaringTypeVariable(FieldInstance field);

    DeclaringTypeVariable makeDeclaringTypeVariable(MethodInstance method);

    MethodParameterTypeVariable makeParameterTypeVariable(MethodInstance method, int parameterIndex, ICompilationUnitRange range);

    ImmutableTypeVariable makeImmutableTypeVariable(Type binding);

    ReturnTypeVariable makeReturnTypeVariable(MethodInstance method);
}
