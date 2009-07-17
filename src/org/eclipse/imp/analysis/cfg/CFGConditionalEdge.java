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

public class CFGConditionalEdge extends CFGEdge {
    public final boolean fLabel;

    public CFGConditionalEdge(CFGNode src, CFGNode dst, boolean label) {
        super(src, dst);
        fLabel= label;
    }

    public boolean isPlainEdge() {
        return false;
    }

    public String getLabel() {
        return fLabel ? "true" : "false";
    }

    public String toString() {
        StringBuffer buff= new StringBuffer();
        buff.append(fSource.toString()).append(" ?(").append(fLabel).append(") => ").append(fDest);
        return buff.toString();
    }

    public boolean equals(Object o) {
        if (!(o instanceof CFGConditionalEdge))
            return false;
        CFGConditionalEdge other= (CFGConditionalEdge) o;
        return fSource.equals(other.fSource) && fDest.equals(other.fDest) && fLabel == other.fLabel;
    }

    public int hashCode() {
        int result= 29;
        result= result * 13 + fSource.hashCode();
        result= result * 13 + fDest.hashCode();
        result= result * 13 + (fLabel ? 11 : 0);
        return result;
    }
}