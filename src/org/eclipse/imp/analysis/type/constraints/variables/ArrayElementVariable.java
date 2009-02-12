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
import org.eclipse.imp.analysis.type.constraints.fastrep.TType;

/**
 * Constraint variable that represents the element type of an array type.
 */
public class ArrayElementVariable extends TypeConstraintVariable {

    private final ITypeConstraintVariable fParent;

    public ArrayElementVariable(ITypeConstraintVariable parent, ICompilationUnitRange range) {
        this(parent, null, range);
    }

    public ArrayElementVariable(ITypeConstraintVariable parent, TType declaredType, ICompilationUnitRange range) {
        super(parent.getBindingKey(), declaredType, range);
        fParent= parent;
    }

    /**
     * @return Returns the parent array type.
     */
    public ITypeConstraintVariable getParent() {
        return fParent;
    }

    public String toString() {
        return "Elem[" + fParent.toString() + "]";
    }
}
