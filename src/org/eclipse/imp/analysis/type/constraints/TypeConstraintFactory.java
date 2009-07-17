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

package org.eclipse.imp.analysis.type.constraints;

import org.eclipse.imp.analysis.constraints.ConstraintFactory;
import org.eclipse.imp.analysis.constraints.IConstraint;
import org.eclipse.imp.analysis.constraints.IConstraintOperator;
import org.eclipse.imp.analysis.constraints.IConstraintTerm;
import org.eclipse.imp.analysis.type.constraints.variables.ITypeConstraintVariable;

/**
 * @author rfuhrer@watson.ibm.com
 */
public class TypeConstraintFactory extends ConstraintFactory {

    public TypeConstraintFactory() {
        super();
    }

    /**
     * {@inheritDoc} Avoid creating constraints involving primitive types and self-constraints.
     */
    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.constraints.ConstraintFactory#filterSimple(org.eclipse.safari.java.analysis.constraints.IConstraintVariable, org.eclipse.safari.java.analysis.constraints.IConstraintVariable, org.eclipse.safari.java.analysis.constraints.IConstraintOperator)
     */
    @Override
    public boolean filterSimple(IConstraintTerm v1, IConstraintTerm v2, IConstraintOperator operator) {
//        ITypeConstraintVariable tv1= (ITypeConstraintVariable) v1;
//        ITypeConstraintVariable tv2= (ITypeConstraintVariable) v2;
//        if ((tv1.getBinding() != null && tv1.getBinding().isPrimitive() && tv2.getBinding() != null && tv2.getBinding().isPrimitive())) {
//            return true;
//        }
        return super.filterSimple(v1, v2, operator);
    }
    
    public IConstraint createSubtypeConstraint(ITypeConstraintVariable v1, ITypeConstraintVariable v2) {
        return createSimpleConstraint(v1, v2, TypeConstraintOperator.createSubTypeOperator());
    }

    public IConstraint createStrictSubtypeConstraint(ITypeConstraintVariable v1, ITypeConstraintVariable v2) {
        return createSimpleConstraint(v1, v2, TypeConstraintOperator.createStrictSubtypeOperator());
    }

    public IConstraint createEqualsConstraint(ITypeConstraintVariable v1, ITypeConstraintVariable v2) {
        return createSimpleConstraint(v1, v2, TypeConstraintOperator.createEqualsOperator());
    }

    public IConstraint createDefinesConstraint(ITypeConstraintVariable v1, ITypeConstraintVariable v2) {
        return createSimpleConstraint(v1, v2, TypeConstraintOperator.createDefinesOperator());
    }
}
