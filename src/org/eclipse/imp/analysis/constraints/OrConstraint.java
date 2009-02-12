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

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.eclipse.imp.core.Assert;

/**
 * @author rfuhrer@watson.ibm.com
 */
public class OrConstraint implements IConstraint {
    private final IConstraint[] fConstraints;

    /* package */ OrConstraint(IConstraint[] constraints) {
        Assert.isNotNull(constraints);
        fConstraints= sort(getCopy(constraints));
    }

    private static IConstraint[] getCopy(IConstraint[] constraints) {
        List<IConstraint> l= Arrays.asList(constraints);
        return (IConstraint[]) l.toArray(new IConstraint[l.size()]);
    }

    private static IConstraint[] sort(IConstraint[] constraints) {
        // TODO bogus to sort by toString - will have to come up with something better
        Arrays.sort(constraints, new Comparator() {
            public int compare(Object o1, Object o2) {
                return o2.toString().compareTo(o1.toString());
            }
        });
        return constraints;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jdt.internal.corext.refactoring.experiments.ITypeConstraint#isSimpleTypeConstraint()
     */
    public boolean isSimpleTypeConstraint() {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuffer buff= new StringBuffer();
        for(int i= 0; i < fConstraints.length; i++) {
            IConstraint constraint= fConstraints[i];
            if (i > 0)
                buff.append(" or "); //$NON-NLS-1$
            buff.append(constraint.toString());
        }
        return buff.toString();
    }

    public IConstraint[] getConstraints() {
        return fConstraints;
    }
}
