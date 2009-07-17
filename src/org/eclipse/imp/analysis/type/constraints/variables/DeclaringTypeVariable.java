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
import org.eclipse.imp.analysis.type.constraints.bindings.BindingKeyFactory;
import org.eclipse.imp.analysis.type.constraints.bindings.BindingKeyFactory.BindingKey;
import org.eclipse.imp.analysis.type.constraints.fastrep.TType;

public class DeclaringTypeVariable extends TypeConstraintVariable {
    private final BindingKey fMemberKey;

    protected DeclaringTypeVariable(BindingKey memberTypeKey, TType declaredType, ICompilationUnitRange range, BindingKeyFactory factory) {
        super(factory.getKeyForDeclaringType(memberTypeKey), declaredType, range);
        fMemberKey= memberTypeKey;
    }

    public BindingKey getMemberKey() {
        return fMemberKey;
    }

    public boolean isComplexTerm() {
        return false;
    }

    public void processTerms(ITermProcessor processor) { }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "Decl(" + fMemberKey.toString() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
    }
}
