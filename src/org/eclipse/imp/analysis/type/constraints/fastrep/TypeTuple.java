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

public class TypeTuple {

    private TType fFirst;

    private TType fSecond;

    public TypeTuple(TType first, TType second) {
        super();
        fFirst= first;
        fSecond= second;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof TypeTuple))
            return false;
        TypeTuple other= (TypeTuple) obj;
        return fFirst.equals(other.fFirst) && fSecond.equals(other.fSecond);
    }

    public int hashCode() {
        return fFirst.hashCode() << 16 + fSecond.hashCode();
    }
}
