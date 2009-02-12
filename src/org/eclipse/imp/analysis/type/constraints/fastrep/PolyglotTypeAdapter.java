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

import polyglot.types.ArrayType;
import polyglot.types.ClassType;
import polyglot.types.PrimitiveType;
import polyglot.types.Type;

public class PolyglotTypeAdapter extends FastTypeAdapter {
    public PolyglotTypeAdapter() { }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.fastrep.FastTypeAdapter#getShortName(java.lang.Object)
     */
    @Override
    public String getShortName(Object type) {
        Type polyType_= (Type) type;

        if (polyType_.isClass()) {
            ClassType classType= (ClassType) polyType_;
            return classType.name();
        } else if (polyType_.isArray()) {
            ArrayType arrayType= (ArrayType) polyType_;
            return getShortName(arrayType.base()) + "[]";
        } else if (polyType_.isPrimitive()) {
            PrimitiveType primType= (PrimitiveType) polyType_;
            return primType.name();
        }
        return polyType_.toString();
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.fastrep.FastTypeAdapter#getFullyQualifiedName(java.lang.Object)
     */
    @Override
    public String getFullyQualifiedName(Object type) {
        Type polyType_= (Type) type;

        if (polyType_.isClass()) {
            ClassType classType= (ClassType) polyType_;
            return classType.fullName();
        } else if (polyType_.isArray()) {
            ArrayType arrayType= (ArrayType) polyType_;
            return getFullyQualifiedName(arrayType.base()) + "[]";
        } else if (polyType_.isPrimitive()) {
            PrimitiveType primType= (PrimitiveType) polyType_;
            return primType.name();
        }
        return polyType_.toString();
    }
}
