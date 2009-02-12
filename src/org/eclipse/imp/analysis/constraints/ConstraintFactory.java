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

import java.util.HashMap;
import java.util.Map;

/**
 * @author rfuhrer@watson.ibm.com
 */
public class ConstraintFactory implements IConstraintFactory {
    private Map<IConstraintVariable, Map<IConstraintVariable, Map<IConstraintOperator, IConstraint>>> fSimpleConstraints= new HashMap<IConstraintVariable,Map<IConstraintVariable,Map<IConstraintOperator,IConstraint>>>();

    private Map<IConstraintVariable, Map<String, OrConstraint>> fOrConstraints= new HashMap<IConstraintVariable, Map<String, OrConstraint>>();

    protected static final boolean PRINT_STATS= false;

    protected int fNrCreated= 0;

    protected int fNrFiltered= 0;

    protected int fNrRetrieved= 0;

    private SimpleConstraint doCreateSimpleConstraint(IConstraintVariable v1, IConstraintVariable v2, IConstraintOperator operator) {
        if (fSimpleConstraints.containsKey(v1)) {
            Map<IConstraintVariable, Map<IConstraintOperator,IConstraint>> m2= fSimpleConstraints.get(v1);
            if (m2.containsKey(v2)) {
                Map<IConstraintOperator,IConstraint> m3= m2.get(v2);

                if (m3.containsKey(operator)) {
                    if (PRINT_STATS)
                        fNrRetrieved++;
                    if (PRINT_STATS)
                        dumpStats();
                    return (SimpleConstraint) m3.get(operator);
                } else {
                    return storeConstraint(v1, v2, operator, m3);
                }
            } else {
                Map<IConstraintOperator,IConstraint> m3= new HashMap<IConstraintOperator,IConstraint>();
                m2.put(v2, m3);
                return storeConstraint(v1, v2, operator, m3);
            }
        } else {
            Map<IConstraintVariable,Map<IConstraintOperator,IConstraint>> m2= new HashMap<IConstraintVariable,Map<IConstraintOperator,IConstraint>>();
            fSimpleConstraints.put(v1, m2);
            Map<IConstraintOperator,IConstraint> m3= new HashMap<IConstraintOperator,IConstraint>();
            m2.put(v2, m3);
            return storeConstraint(v1, v2, operator, m3);
        }
    }

    private SimpleConstraint storeConstraint(IConstraintVariable v1, IConstraintVariable v2, IConstraintOperator operator, Map<IConstraintOperator,IConstraint> m3) {
        SimpleConstraint constraint= new SimpleConstraint(v1, v2, operator);
        m3.put(operator, constraint);
        if (PRINT_STATS)
            fNrCreated++;
        if (PRINT_STATS)
            dumpStats();
        return constraint;
    }

    public IConstraint[] createSimpleConstraint(IConstraintVariable v1, IConstraintVariable v2, IConstraintOperator operator) {
        if (filterSimple(v1, v2, operator)) {
            if (PRINT_STATS)
                fNrFiltered++;
            return new IConstraint[0];
        } else {
            return new IConstraint[] { doCreateSimpleConstraint(v1, v2, operator) };
        }
    }

    public boolean filterSimple(IConstraintVariable v1, IConstraintVariable v2, IConstraintOperator operator) {
        if (v1 == v2)
            return true;
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     */
    public OrConstraint createOrConstraint(IConstraint[] constraints) {
        IConstraintVariable left= ((SimpleConstraint) constraints[0]).getLeft();
        String signature= ""; //$NON-NLS-1$
        for(int i= 0; i < constraints.length; i++) {
            IConstraintVariable right= ((SimpleConstraint) constraints[i]).getRight();
            String varName= right.toString();
            signature= signature + varName + ","; //$NON-NLS-1$
        }

        if (fOrConstraints.containsKey(left)) {
            Map m2= (Map) fOrConstraints.get(left);
            if (m2.containsKey(signature)) {
                if (PRINT_STATS)
                    fNrRetrieved++;
                if (PRINT_STATS)
                    dumpStats();
                return (OrConstraint) m2.get(signature);
            } else {
                OrConstraint constraint= new OrConstraint(constraints);
                m2.put(signature, constraint);
                if (PRINT_STATS)
                    dumpStats();
                if (PRINT_STATS)
                    fNrCreated++;
                return constraint;
            }
        } else {
            Map m2= new HashMap();
            fOrConstraints.put(left, m2);
            OrConstraint constraint= new OrConstraint(constraints);
            m2.put(signature, constraint);
            if (PRINT_STATS)
                dumpStats();
            if (PRINT_STATS)
                fNrCreated++;
            return constraint;
        }
    }

    protected void dumpStats() {
        System.out.println("Constraints: " + fNrCreated + " created, " + fNrRetrieved + " retrieved, " + fNrFiltered + " filtered"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    }
}
