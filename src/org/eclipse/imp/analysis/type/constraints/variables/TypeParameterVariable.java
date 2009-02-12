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

package org.eclipse.imp.analysis.type.constraints.variables;

import org.eclipse.imp.analysis.ICompilationUnitRange;
import org.eclipse.imp.analysis.type.constraints.bindings.ITypeBinding;
import org.eclipse.imp.analysis.type.constraints.bindings.BindingKeyFactory.BindingKey;

/**
 * Constraint variable that represents a type parameter on a generic type.
 */
public class TypeParameterVariable extends TypeConstraintVariable {

    public static final int NOT_DECLARED_TYPE_VARIABLE_INDEX= -1;

    private final TypeConstraintVariable fParentCv;

    private final int fDeclarationTypeVariableIndex;

    private final String fTypeParameterKey;

    /**
     * @param parentCv
     *            the parent constraint variable
     * @param typeVariable
     *            the type variable for this constraint
     * @param declarationTypeVariableIndex
     */
    public TypeParameterVariable(TypeConstraintVariable parentCv, int declarationTypeVariableIndex, ITypeBinding typeParameter, ICompilationUnitRange range) {
        super(computeKey(parentCv, declarationTypeVariableIndex), null, range);
        fParentCv= parentCv;
        fTypeParameterKey= typeParameter.getKey();
        fDeclarationTypeVariableIndex= declarationTypeVariableIndex;
    }

    /**
     * @param parentCv
     * @param declarationTypeVariableIndex
     * @return
     */
    private static BindingKey computeKey(TypeConstraintVariable parentCv, int declarationTypeVariableIndex) {
        if (true)
            throw new IllegalStateException("Unfinished implementation: TypeParameterVariable");
        return parentCv.getBindingKey();
    }

    /*
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        if (true)
            throw new IllegalStateException("Unfinished implementation: TypeParameterVariable");
        return fParentCv.hashCode() * 19 + fTypeParameterKey.hashCode();
    }

    /*
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object other) {
        if (true)
            throw new IllegalStateException("Unfinished implementation: TypeParameterVariable");
        if (this == other)
            return true;
        if (other.getClass() != TypeParameterVariable.class)
            return false;

        TypeParameterVariable other2= (TypeParameterVariable) other;
        return fParentCv == other2.fParentCv && fTypeParameterKey.equals(other2.fTypeParameterKey);
    }

    public int getDeclarationTypeVariableIndex() {
        if (true)
            throw new IllegalStateException("Unfinished implementation: TypeParameterVariable");
        return fDeclarationTypeVariableIndex;
    }

    public TypeConstraintVariable getParentConstraintVariable() {
        if (true)
            throw new IllegalStateException("Unfinished implementation: TypeParameterVariable");
        return fParentCv;
    }

    public String toString() {
        return "Param[" + fParentCv.toString() + ", " + fTypeParameterKey + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
}
