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

package org.eclipse.imp.analysis.reachingdefs;

import org.eclipse.imp.analysis.constraints.IEstimateEnvironment;


/**
 * Represents a constraint term of the form: Entry(label)
 * @author rfuhrer
 */
public class ReachingDefsEntry extends NodeLabel {
    ReachingDefsEntry(Object node) {
        super(node);
    }

    public String toString() {
        return "RD@entry[" + fNode.toString().replace('\n', ' ') + "]";
    }

    public boolean isComplexTerm() {
        return false;
    }
}
