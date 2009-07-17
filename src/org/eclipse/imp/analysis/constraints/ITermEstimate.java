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

package org.eclipse.imp.analysis.constraints;

/**
 * Represents the estimate of the value of a given {@link IConstraintTerm},
 * which typically varies during constraint solution until the iteration
 * quiesces and the final values have been obtained. Estimates are stored in
 * an {@link IEstimateEnvironment}.
 * Implementors must implement hashCode() and equals() properly.
 * @author rfuhrer@watson.ibm.com
 */
public interface ITermEstimate {

}
