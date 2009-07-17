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

package org.eclipse.imp.analysis.constraints;

/**
 * Represents a term in a constraint, e.g., in "a <= b", "a" and "b" are terms.
 * During constraint solution, each term has a corresponding "estimate", stored
 * in an {@link IEstimateEnvironment}.
 * @see IConstraint, ISimpleConstraint
 * @author rfuhrer@watson.ibm.com
 */
public interface IConstraintTerm {
    public static final String TO_STRING_KEY= "toString"; //$NON-NLS-1$

    /**
     * Associates the given key with the given data, on this constraint term.
     */
    void setData(String key, Object data);

    /**
     * Retrieves the data associated with the given key, if any, for this
     * constraint term. If no such key exists for this term, returns null.
     */
    Object getData(String key);

    /**
     * @return true if this constraint term has any sub-structure
     */
    boolean isComplexTerm();

    /**
     * Recompute the estimate for this constraint term, taking estimates for its
     * constituent parts from the given IEstimateEnvironment. Only called if
     * isComplexTerm() returns true.
     */
    void recomputeEstimate(IEstimateEnvironment env);

    /**
     * Invoke the given ITermProcessor on each of this constraint term's constituent
     * parts, if it has any. Only called if isComplexTerm() returns true.
     */
    void processTerms(ITermProcessor processor);
}
