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
import org.eclipse.imp.analysis.type.constraints.bindings.BindingKeyFactory;
import org.eclipse.imp.analysis.type.constraints.bindings.BindingKeyFactory.BindingKey;
import org.eclipse.imp.analysis.type.constraints.fastrep.TType;

/**
 * Constraint variable that represents the return type of a method.
 */
public class ReturnTypeVariable extends TypeConstraintVariable {
    private final BindingKey fMethodKey;

    public ReturnTypeVariable(BindingKey methodKey, TType declaredType, ICompilationUnitRange range, BindingKeyFactory factory) {
        super(factory.getKeyForReturn(methodKey), declaredType, range);
        fMethodKey= methodKey;
    }

    /**
     * @return Returns the methodKey.
     */
    public BindingKey getMethodKey() {
        return fMethodKey;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "Return(" + fMethodKey.toString() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
    }
}
