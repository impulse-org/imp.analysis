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

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IVariableBinding;

public class DefinitionLiteral extends ConstraintVariable {
    private IVariableBinding fVarBinding;

    private ASTNode fLabel;

    /**
     * @param var
     * @param label may be null, to indicate a wildcard (definition of given var anywhere in program)
     */
    DefinitionLiteral(IVariableBinding var, ASTNode label) {
	fVarBinding= var;
	fLabel= label;
    }

    public void processTerms(ITermProcessor processor) {
	processor.processTerm(this);
    }

    public boolean isWildcard() {
	return fLabel == null;
    }

    public ASTNode getLabel() {
	return fLabel;
    }

    public IVariableBinding getVarBinding() {
	return fVarBinding;
    }

    public boolean matches(DefinitionLiteral other) {
	return fVarBinding.isEqualTo(other.fVarBinding) && (fLabel == null || fLabel == other.fLabel);
    }

    public boolean equals(Object o) {
	if (!(o instanceof DefinitionLiteral))
	    return false;
	DefinitionLiteral other= (DefinitionLiteral) o;

	return fVarBinding.isEqualTo(other.fVarBinding) && (fLabel == other.fLabel);
    }

    public int hashCode() {
	int result= 37;
	result= 13 * result + fVarBinding.hashCode();
	if (fLabel != null)
	    result= 13 * result + fLabel.hashCode();
	return result;
    }

    public String toString() {
	return "(" + fVarBinding.getName() + "," + (fLabel == null ? "?" : fLabel.toString()) + ")";
    }
}
