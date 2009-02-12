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

import java.util.Map;

import org.eclipse.imp.analysis.type.constraints.bindings.ITypeBinding;
import org.eclipse.imp.core.Assert;

public abstract class HierarchyType<T> extends TType {

    private HierarchyType fSuperclass;

    private HierarchyType[] fInterfaces;

    /**
     * The compiler's representation of this type
     */
    private T fTypeRepresentation;

    protected HierarchyType(TypeEnvironment environment) {
        super(environment);
    }

    protected void initialize(ITypeBinding binding, T typeRep) {
        super.initialize(binding);
        Assert.isNotNull(typeRep);
        fTypeRepresentation= typeRep;
        TypeEnvironment environment= getEnvironment();
        ITypeBinding superclass= binding.getSuperclass();
        if (superclass != null) {
            fSuperclass= (HierarchyType) environment.create(superclass);
        }
        ITypeBinding[] interfaces= binding.getInterfaces();
        fInterfaces= new HierarchyType[interfaces.length];
        for(int i= 0; i < interfaces.length; i++) {
            fInterfaces[i]= (HierarchyType) environment.create(interfaces[i]);
        }
    }

    public TType getSuperclass() {
        return fSuperclass;
    }

    public TType[] getInterfaces() {
        return fInterfaces;
    }

    public T getTypeRepresentation() {
        return fTypeRepresentation;
    }

    public boolean isSubType(HierarchyType other) {
        if (getEnvironment() == other.getEnvironment()) {
            Map cache= getEnvironment().getSubTypeCache();
            TypeTuple key= new TypeTuple(this, other);
            Boolean value= (Boolean) cache.get(key);
            if (value != null)
                return value.booleanValue();
            boolean isSub= doIsSubType(other);
            value= Boolean.valueOf(isSub);
            cache.put(key, value);
            return isSub;
        }
        return doIsSubType(other);
    }

    private boolean doIsSubType(HierarchyType other) {
        if (fSuperclass != null && (other.isTypeEquivalentTo(fSuperclass) || fSuperclass.doIsSubType(other)))
            return true;
        for(int i= 0; i < fInterfaces.length; i++) {
            if (other.isTypeEquivalentTo(fInterfaces[i]) || fInterfaces[i].doIsSubType(other))
                return true;
        }
        return false;
    }

    protected boolean canAssignToStandardType(StandardType target) {
        if (target.isJavaLangObject())
            return true;
        return isSubType(target);
    }
}
