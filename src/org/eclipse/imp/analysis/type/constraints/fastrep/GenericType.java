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

package org.eclipse.imp.analysis.type.constraints.fastrep;

import org.eclipse.imp.analysis.type.constraints.bindings.ITypeBinding;
import org.eclipse.imp.core.Assert;

public final class GenericType<T> extends HierarchyType<T> {

    private TypeVariable[] fTypeParameters;

    protected GenericType(TypeEnvironment environment) {
        super(environment);
    }

    protected void initialize(ITypeBinding binding, T javaElementType) {
        Assert.isTrue(binding.isGenericType());
        super.initialize(binding, javaElementType);
        TypeEnvironment environment= getEnvironment();
        ITypeBinding[] typeParameters= binding.getTypeParameters();
        fTypeParameters= new TypeVariable[typeParameters.length];
        for(int i= 0; i < typeParameters.length; i++) {
            fTypeParameters[i]= (TypeVariable) environment.create(typeParameters[i]);
        }
    }

    public int getKind() {
        return GENERIC_TYPE;
    }

    public TypeVariable[] getTypeParameters() {
        return (TypeVariable[]) fTypeParameters.clone();
    }

    public boolean doEquals(TType type) {
        return getTypeRepresentation().equals(((GenericType) type).getTypeRepresentation());
    }

    public int hashCode() {
        return getTypeRepresentation().hashCode();
    }

    protected boolean doCanAssignTo(TType type) {
        return false;
    }

    protected boolean isTypeEquivalentTo(TType other) {
        int otherElementType= other.getKind();
        if (otherElementType == RAW_TYPE || otherElementType == PARAMETERIZED_TYPE)
            return getErasure().isTypeEquivalentTo(other.getErasure());
        return super.isTypeEquivalentTo(other);
    }

    public String getName() {
         return getTypeRepresentation().toString();
    }

    protected String getPlainPrettySignature() {
        StringBuffer result= new StringBuffer(FastTypeAdapter.getInstance().getFullyQualifiedName(getTypeRepresentation()));
        result.append("<"); //$NON-NLS-1$
        result.append(fTypeParameters[0].getPrettySignature());
        for(int i= 1; i < fTypeParameters.length; i++) {
            result.append(", "); //$NON-NLS-1$
            result.append(fTypeParameters[i].getPrettySignature());
        }
        result.append(">"); //$NON-NLS-1$
        return result.toString();
    }
}
