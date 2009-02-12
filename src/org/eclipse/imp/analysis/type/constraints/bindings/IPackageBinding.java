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

package org.eclipse.imp.analysis.type.constraints.bindings;

public interface IPackageBinding extends IBinding {

    /**
     * Returns the name of the package represented by this binding. For named packages, this is the fully qualified
     * package name (using "." for separators). For unnamed packages, this is an empty string.
     * 
     * @return the name of the package represented by this binding, or an empty string for an unnamed package
     */
    public String getName();

    /**
     * Returns whether this package is an unnamed package. See <em>The Java Language Specification</em> section 7.4.2
     * for details.
     * 
     * @return <code>true</code> if this is an unnamed package, and <code>false</code> otherwise
     */
    public boolean isUnnamed();

    /**
     * Returns the list of name component making up the name of the package represented by this binding. For example,
     * for the package named "com.example.tool", this method returns {"com", "example", "tool"}. Returns the empty list
     * for unnamed packages.
     * 
     * @return the name of the package represented by this binding, or the empty list for unnamed packages
     */
    public String[] getNameComponents();
}
