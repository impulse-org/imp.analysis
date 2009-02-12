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

import org.eclipse.imp.core.Assert;

/**
 *
 */
public abstract class FastTypeAdapter {
    protected FastTypeAdapter() { }

    protected static FastTypeAdapter ADAPTER;

    public static FastTypeAdapter getInstance() {
        Assert.isNotNull(ADAPTER, "No type adapter exists yet!");
        return ADAPTER;
    }

    abstract public String getShortName(Object type);

    abstract public String getFullyQualifiedName(Object type);
}
