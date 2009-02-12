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
import org.eclipse.imp.analysis.constraints.SourceConstraintVariable;
import org.eclipse.imp.analysis.type.constraints.bindings.BindingKeyFactory.BindingKey;
import org.eclipse.imp.analysis.type.constraints.fastrep.TType;

/**
 * @author rfuhrer@watson.ibm.com
 */
public class TypeConstraintVariable extends SourceConstraintVariable implements ITypeConstraintVariable {
    private final BindingKey fBindingKey;

    private final TType fDeclaredType;
    
    public TypeConstraintVariable(BindingKey bindingKey, TType declaredType, ICompilationUnitRange range) {
        super(range);
        fBindingKey= bindingKey;
        fDeclaredType= declaredType;
    }

    public TypeConstraintVariable(BindingKey bindingKey, TType declaredType) {
        this(bindingKey, declaredType, null);
    }

    /**
     * @return Returns the bindingKey.
     */
    public BindingKey getBindingKey() {
        return fBindingKey;
    }

    /**
     * @return Returns the declaredType.
     */
    public TType getDeclaredType() {
        return fDeclaredType;
    }
}
