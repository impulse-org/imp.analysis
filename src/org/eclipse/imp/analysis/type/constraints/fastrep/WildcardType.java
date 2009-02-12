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

public abstract class WildcardType extends TType {

    protected TType fBound;

    protected WildcardType(TypeEnvironment environment) {
        super(environment);
    }

    protected void initialize(ITypeBinding binding) {
        Assert.isTrue(binding.isWildcardType());
        super.initialize(binding);
        ITypeBinding bound= binding.getBound();
        if (bound != null) {
            fBound= getEnvironment().create(bound);
        }
    }

    public TType getBound() {
        return fBound;
    }

    public TType[] getSubTypes() {
        throw new UnsupportedOperationException();
    }

    public boolean doEquals(TType type) {
        WildcardType other= (WildcardType) type;
        if (fBound == null)
            return other.fBound == null;
        return fBound.equals(other.fBound);
    }

    public int hashCode() {
        if (fBound == null)
            return super.hashCode();
        return fBound.hashCode() << WILDCARD_TYPE_SHIFT;
    }

    protected abstract boolean checkAssignmentBound(TType rhs);

    // protected abstract boolean checkTypeArgumentBound(TType rhs);

    protected String internalGetName(String keyword) {
        StringBuffer result= new StringBuffer("?"); //$NON-NLS-1$
        TType bound= getBound();
        if (bound != null) {
            result.append(" "); //$NON-NLS-1$
            result.append(keyword);
            result.append(" "); //$NON-NLS-1$
            result.append(bound.getName());
        }
        return result.toString();
    }

    protected String internalGetPrettySignature(String keyword) {
        StringBuffer result= new StringBuffer("?"); //$NON-NLS-1$
        TType bound= getBound();
        if (bound != null) {
            result.append(" "); //$NON-NLS-1$
            result.append(keyword);
            result.append(" "); //$NON-NLS-1$
            result.append(bound.getPlainPrettySignature());
        }
        return result.toString();
    }
}
