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

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConstraintGraph {
    private boolean DEBUG_DECORATION= false;

    private boolean DEBUG_DUMP_RAW= false;

    private boolean DEBUG_DUMP_USEDIN= false;

    private final Collection<IConstraint> fConstraints;

    /**
     * Collection of all <code>ConstraintVariables</code>.
     */
    private final Set<IConstraintTerm> fAllVarbles= new LinkedHashSet<IConstraintTerm>();

    private Map<IConstraintTerm,List<IConstraint>> fUsedIn= new LinkedHashMap<IConstraintTerm,List<IConstraint>>();

    public ConstraintGraph(Collection<IConstraint> constraints) {
        super();
        fConstraints= constraints;
        initialize();
    }

    void clearAll() {
        fAllVarbles.clear();
        fUsedIn= new LinkedHashMap<IConstraintTerm,List<IConstraint>>();
    }

    void initialize() {
        decorateVarbles();

        if (DEBUG_DUMP_RAW) {
            dumpVariableList(System.out);
            dumpConstraints(System.out);
        }

        if (DEBUG_DUMP_USEDIN) {
            dumpUsedInLists(System.out);
        }
    }

    public List<IConstraint> getUsedIn(IConstraintTerm v) {
        return fUsedIn.get(v);
    }

    public Collection<IConstraint> getConstraints() {
        return fConstraints;
    }

    /**
     * Returns the <code>Set</code> of <code>ConstraintVariable</code>'s
     * representing all relevant elements of the original program, including
     * declared variables, expressions, and so on.
     */
    public Set<IConstraintTerm> getVariables() {
        return fAllVarbles;
    }

    /**
     * Adds the given ITypeConstraint to the "used-in" set of the given
     * ConstraintVariable. Used to form the dependency linkage among type
     * constraints, so that when the type bound of a node (ConstraintVariable)
     * in the constraint graph gets updated, we know which other type
     * constraints must be (re-)processed.
     * 
     * @param v
     * @param constraint
     */
    private void addToUsedInSet(IConstraintTerm v, IConstraint constraint) {
        List<IConstraint> usedIn= fUsedIn.get(v);

        if (usedIn == null) {
            usedIn= new ArrayList<IConstraint>();
            fUsedIn.put(v, usedIn);
        }
        if (DEBUG_DECORATION)
            System.out.println("  Adding constraint " + constraint.toString() + //$NON-NLS-1$
                    " to used-in set of " + v.toString()); //$NON-NLS-1$
        usedIn.add(constraint);
    }

    private class VariableDecorator implements ITermProcessor {
        private IConstraint fConstraint;

        void setConstraint(IConstraint c) {
            fConstraint= c;
        }

        public void processTerm(IConstraintTerm term) {
            addToUsedInSet(term, fConstraint);
            if (!fAllVarbles.contains(term))
                fAllVarbles.add(term);
        }
    }

    private VariableDecorator fVariableDecorator= new VariableDecorator();

    /**
     * Associates <code>ConstraintVariable</code>'s appearing in the given
     * <code>IConstraint</code> with the constraint by adding the constraint
     * to the variables' "used-in" lists. Also adds the variables to various
     * lists of <code>ConstraintVariable</code>'s in the program.
     */
    private void decorateConstraintVarbles(IConstraint c) {
        fVariableDecorator.setConstraint(c);
        c.processTerms(fVariableDecorator);
    }

    //	/**
    //	 * Associates <code>ConstraintVariable</code>'s appearing in the given
    //	 * <code>CompositeOrTypeConstraint</code> with the constraint by adding the
    //	 * constraint to the variables' "used-in" lists. Also adds the variables
    //	 * to various lists of <code>ConstraintVariable</code>'s in the program.
    //	 */
    //	private void decorateOrConstraintVarbles(CompositeOrTypeConstraint coc) {
    //		IConstraint[] disjuncts= coc.getConstraints();
    //
    //		for(int i=0; i < disjuncts.length; i++) {
    //			IConstraint term= disjuncts[i];
    //			SimpleTypeConstraint stc= (SimpleTypeConstraint) term;
    //
    //			decorateVariable(stc.getLeft(), coc);
    //			decorateVariable(stc.getRight(), coc);
    //		}
    //	}

    /**
     * Associates all <code>ConstraintVariable</code>'s appearing in the various
     * <code>ITypeConstraints</code> with their "used-in sets".
     */
    private void decorateVarbles() {
        if (DEBUG_DECORATION) {
            System.out.println();
            System.out.println("*** Collecting & annotating constraint variables ***"); //$NON-NLS-1$
        }

        for(Iterator it= fConstraints.iterator(); it.hasNext();) {
            final IConstraint constraint= (IConstraint) it.next();

            if (DEBUG_DECORATION)
                System.out.println("Pre-processing constraint " + constraint.toString()); //$NON-NLS-1$

            decorateConstraintVarbles(constraint);
        }
    }

    /**
     * Utility function to sort a Collection of ConstraintVariables by their
     * print representation (i.e. what toString() returns).
     * 
     * @param varbles the variables to sort
     * @return a sorted Collection of ConstraintVariable's
     */
    public static List<IConstraintTerm> sortedConstraintVariables(Collection<IConstraintTerm> varbles) {
        IConstraintTerm[] va= varbles.toArray(new IConstraintTerm[varbles.size()]);
        Arrays.sort(va, new Comparator<IConstraintTerm>() {
            public int compare(IConstraintTerm t1, IConstraintTerm t2) {
                return t1.toString().compareTo(t2.toString());
            }
        });

        return Arrays.asList(va);
    }

    public void dumpVariableList(PrintStream ps) {
        ps.println();
        ps.println("*** Constraint Variables: ***"); //$NON-NLS-1$

        List<IConstraintTerm> scv= sortedConstraintVariables(fAllVarbles);

        for(IConstraintTerm t: scv) {
            ps.println(t.toString());
        }
    }

    public void dumpConstraints(PrintStream ps) {
        ps.println(toString());
    }

    public String toString() {
        StringBuffer buf= new StringBuffer();
        buf.append("\n");
        buf.append("*** Constraints: ***\n"); //$NON-NLS-1$
        List<String> cs= new ArrayList<String>();
        for(IConstraint c: fConstraints) {
            cs.add(c.toString());
        }
        Collections.sort(cs);
        for(String s: cs) {
            buf.append(s).append("\n");
        }
        return buf.toString();
    }

    private void dumpUsedInLists(PrintStream ps) {
        ps.println();
        ps.println("*** Used-In Lists: ***"); //$NON-NLS-1$

        List<IConstraintTerm> scv= sortedConstraintVariables(fAllVarbles);

        for(Iterator iter= scv.iterator(); iter.hasNext();) {
            ConstraintTerm v= (ConstraintTerm) iter.next();
            boolean gotOne= false;
            List usedIn= (List) fUsedIn.get(v);

            if (usedIn == null)
                continue;
            for(Iterator eqIt= usedIn.iterator(); eqIt.hasNext();) {
                IConstraint eq= (IConstraint) eqIt.next();

                if (!gotOne) {
                    ps.println(v.toString() + ":"); //$NON-NLS-1$
                    gotOne= true;
                }
                ps.println("              " + eq.toString()); //$NON-NLS-1$
            }
        }
    }
}
