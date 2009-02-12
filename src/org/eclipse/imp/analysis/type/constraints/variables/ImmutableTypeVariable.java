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

import org.eclipse.imp.analysis.type.constraints.bindings.BindingKeyFactory.BindingKey;
import org.eclipse.imp.analysis.type.constraints.fastrep.TType;

public class ImmutableTypeVariable extends TypeConstraintVariable {
    public ImmutableTypeVariable(BindingKey key, TType type) {
        super(key, type);
    }

    /*
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return getDeclaredType().hashCode();
    }

    /*
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other.getClass() != ImmutableTypeVariable.class)
            return false;

        return getDeclaredType() == ((ImmutableTypeVariable) other).getDeclaredType();
    }

    public String toString() {
        return getDeclaredType().getPrettySignature();
    }
}
