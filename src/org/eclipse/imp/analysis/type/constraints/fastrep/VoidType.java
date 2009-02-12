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

package org.eclipse.imp.analysis.type.constraints.fastrep;

import org.eclipse.jdt.core.Signature;

public class VoidType extends TType {

    protected VoidType(TypeEnvironment environment) {
        super(environment, Signature.createTypeSignature("void", true)); //$NON-NLS-1$
    }

    public int getKind() {
        return VOID_TYPE;
    }

    public TType[] getSubTypes() {
        throw new UnsupportedOperationException();
    }

    protected boolean doEquals(TType type) {
        return true;
    }

    protected boolean doCanAssignTo(TType lhs) {
        return false;
    }

    public String getName() {
        return "void"; //$NON-NLS-1$
    }

    protected String getPlainPrettySignature() {
        return getName();
    }

}
