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

import org.eclipse.imp.analysis.type.typesets.ITypeSet;
import org.eclipse.imp.model.ICompilationUnit;


public interface ISourceConstraintVariable {
    public ICompilationUnit getCompilationUnit();

    public Object getData(String name);

    public ITypeSet getTypeEstimate();

    public void setCompilationUnit(ICompilationUnit cu);

    public void setData(String name, Object data);
}
