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

import polyglot.ast.Expr;

/**
 * 
 */
public class ExpressionVariable extends TypeConstraintVariable {
    /**
     * @param bindingKey
     * @param declaredType
     * @param range
     */
    public ExpressionVariable(Expr expr, TType declaredType, ICompilationUnitRange range) {
        super(null, declaredType, range);
    }

    public String toString() {
        return getSource();
    }
}
