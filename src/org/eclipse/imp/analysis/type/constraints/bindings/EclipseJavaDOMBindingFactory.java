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

package org.eclipse.imp.analysis.type.constraints.bindings;

import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.IPackageBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;


/**
 * @author rfuhrer@watson.ibm.com
 */
public class EclipseJavaDOMBindingFactory extends BindingKeyFactory {
    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.bindings.BindingFactory#createType(org.eclipse.safari.java.analysis.type.bindings.BindingFactory.BindingKey)
     */
    @Override
    public Object findType(BindingKey typeKey) {
        throw new IllegalStateException("Unimplemented");
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.bindings.BindingFactory#createMethod(org.eclipse.safari.java.analysis.type.bindings.BindingFactory.BindingKey)
     */
    @Override
    public Object findMethod(BindingKey methodKey) {
        throw new IllegalStateException("Unimplemented");
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.bindings.BindingFactory#createPackage(org.eclipse.safari.java.analysis.type.bindings.BindingFactory.BindingKey)
     */
    @Override
    public Object findPackage(BindingKey pkgKey) {
        throw new IllegalStateException("Unimplemented");
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.bindings.BindingFactory#createVariable(org.eclipse.safari.java.analysis.type.bindings.BindingFactory.BindingKey)
     */
    @Override
    public Object findVariable(BindingKey varKey) {
        throw new IllegalStateException("Unimplemented");
    }

    //
    // ================================================================================================
    //

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.bindings.BindingFactory#createKeyForType(java.lang.Object)
     */
    @Override
    public BindingKey createKeyForType(Object type) {
        ITypeBinding typeBinding= (ITypeBinding) type;

        return new StringBindingKey(typeBinding.getKey());
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.bindings.BindingFactory#createKeyForMethod(java.lang.Object)
     */
    @Override
    public BindingKey createKeyForMethod(Object method) {
        IMethodBinding methodBinding= (IMethodBinding) method;

        return new StringBindingKey(methodBinding.getKey());
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.bindings.BindingFactory#createKeyForPackage(java.lang.Object)
     */
    @Override
    public BindingKey createKeyForPackage(Object pkg) {
        IPackageBinding pkgBinding= (IPackageBinding) pkg;

        return new StringBindingKey(pkgBinding.getKey());
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.bindings.BindingFactory#createKeyForVariable(java.lang.Object)
     */
    @Override
    public BindingKey createKeyForVariable(Object var) {
        IVariableBinding varBinding= (IVariableBinding) var;
        return new StringBindingKey(varBinding.getKey());
    }
}
