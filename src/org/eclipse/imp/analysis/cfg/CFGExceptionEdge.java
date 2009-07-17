/*******************************************************************************
* Copyright (c) 2009 IBM Corporation.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*    Robert Fuhrer (rfuhrer@watson.ibm.com) - initial API and implementation
*******************************************************************************/

package org.eclipse.imp.analysis.cfg;

public class CFGExceptionEdge<T> extends CFGEdge {
    public final T fLabel;

    public CFGExceptionEdge(CFGNode src, CFGNode dst, T label) {
        super(src, dst);
        fLabel= label;
    }

    public boolean isPlainEdge() {
        return false;
    }

    public String getLabel() {
        return fLabel.toString(); // was getQualifiedName() when fLabel was an ITypeBinding
    }

    public String toString() {
        StringBuffer buff= new StringBuffer();
        buff.append(fSource.toString()).append(" ?(").append(fLabel).append(") => ").append(fDest);
        return buff.toString();
    }

    public boolean equals(Object o) {
        if (!(o instanceof CFGExceptionEdge))
            return false;
        CFGExceptionEdge<?> other= (CFGExceptionEdge<?>) o;
        return fSource.equals(other.fSource) && fDest.equals(other.fDest) && fLabel == other.fLabel; // ok to use == since both bindings came from the same CU
    }

    public int hashCode() {
        int result= 29;
        result= result * 13 + fSource.hashCode();
        result= result * 13 + fDest.hashCode();
        result= result * 13 + fLabel.hashCode();
        return result;
    }
}