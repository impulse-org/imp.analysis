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

public class CFGPlainEdge extends CFGEdge {
    public CFGPlainEdge(CFGNode src, CFGNode dst) {
        super(src, dst);
    }

    public boolean isPlainEdge() {
        return true;
    }

    public boolean equals(Object o) {
        if (!(o instanceof CFGPlainEdge))
            return false;
        CFGPlainEdge other= (CFGPlainEdge) o;
        return fSource.equals(other.fSource) && fDest.equals(other.fDest);
    }

    public int hashCode() {
        int result= 29;
        result= result * 13 + fSource.hashCode();
        result= result * 13 + fDest.hashCode();
        return result;
    }
}