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

import org.eclipse.imp.analysis.constraints.IConstraintVariable;
import org.eclipse.imp.analysis.type.constraints.bindings.BindingKeyFactory.BindingKey;

/**
 * Interface representing an arbitrary constraint variable in a type constraint.
 */
public interface ITypeConstraintVariable extends IConstraintVariable {
    /**
     * @return the unique bindingKey
     */
    public BindingKey getBindingKey();
}
