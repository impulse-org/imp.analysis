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

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.imp.analysis.constraints.IConstraintOperator;
import org.eclipse.imp.core.Assert;

/**
 * @author rfuhrer@watson.ibm.com
 */
public class TypeConstraintOperator implements IConstraintOperator {
    private final String fOperatorString;

    private final int fOperatorCode;

    private static final int CODE_SUBTYPE= 0;

    private static final int CODE_EQUALS= 1;

    private static final int CODE_DEFINES= 2;

    private static final int CODE_STRICT_SUBTYPE= 3;

    private static final String STRING_SUBTYPE= "<=";//$NON-NLS-1$

    private static final String STRING_EQUALS= "==";//$NON-NLS-1$

    private static final String STRING_DEFINES= "=^=";//$NON-NLS-1$

    private static final String STRING_STRICT_SUBTYPE= "<";//$NON-NLS-1$

    private static final Collection fgOperatorStrings= new HashSet(Arrays.asList(new String[] { STRING_SUBTYPE, STRING_EQUALS, STRING_DEFINES,
            STRING_STRICT_SUBTYPE }));

    private static final TypeConstraintOperator fgSubtype= new TypeConstraintOperator(STRING_SUBTYPE, CODE_SUBTYPE);

    private static final TypeConstraintOperator fgEquals= new TypeConstraintOperator(STRING_EQUALS, CODE_EQUALS);

    private static final TypeConstraintOperator fgDefines= new TypeConstraintOperator(STRING_DEFINES, CODE_DEFINES);

    private static final TypeConstraintOperator fgStrictSubtype= new TypeConstraintOperator(STRING_STRICT_SUBTYPE, CODE_STRICT_SUBTYPE);

    public static TypeConstraintOperator createSubTypeOperator() {
        return fgSubtype;
    }

    public static TypeConstraintOperator createEqualsOperator() {
        return fgEquals;
    }

    public static TypeConstraintOperator createDefinesOperator() {
        return fgDefines;
    }

    public static TypeConstraintOperator createStrictSubtypeOperator() {
        return fgStrictSubtype;
    }

    private TypeConstraintOperator(String string, int code) {
        Assert.isTrue(fgOperatorStrings.contains(string));
        Assert.isTrue(code == CODE_DEFINES || code == CODE_EQUALS || code == CODE_STRICT_SUBTYPE || code == CODE_SUBTYPE);
        fOperatorString= string;
        fOperatorCode= code;
    }

    public String getOperatorString() {
        return fOperatorString;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return getOperatorString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof TypeConstraintOperator))
            return false;
        TypeConstraintOperator other= (TypeConstraintOperator) obj;
        return fOperatorCode == other.fOperatorCode;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return fOperatorString.hashCode();
    }

    public final boolean isSubtypeOperator() {
        return fOperatorCode == CODE_SUBTYPE;
    }

    public final boolean isStrictSubtypeOperator() {
        return fOperatorCode == CODE_STRICT_SUBTYPE;
    }

    public final boolean isEqualsOperator() {
        return fOperatorCode == CODE_EQUALS;
    }

    public final boolean isDefinesOperator() {
        return fOperatorCode == CODE_DEFINES;
    }
}
