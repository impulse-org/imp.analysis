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
import org.eclipse.imp.analysis.constraints.ITermProcessor;
import org.eclipse.imp.analysis.type.constraints.bindings.BindingKeyFactory.BindingKey;
import org.eclipse.imp.analysis.type.constraints.fastrep.TType;

/**
 * Constraint variable that represents a local variable.
 * @author rfuhrer@watson.ibm.com
 */
public class VariableTypeVariable extends TypeConstraintVariable {
    public VariableTypeVariable(BindingKey key, TType declaredType, ICompilationUnitRange range) {
        super(key, declaredType, range);
    }

    public boolean isComplexTerm() {
        return false;
    }

    public void processTerms(ITermProcessor processor) { }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }
}
