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

/**
 * 
 */
package org.eclipse.imp.analysis.reachingdefs;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.imp.analysis.constraints.ITermEstimate;

class DefinitionSet implements ITermEstimate {
    private Set<DefinitionLiteral> fElements= new LinkedHashSet<DefinitionLiteral>();

    public DefinitionSet() {
    }

    public DefinitionSet(DefinitionLiteral d) {
        fElements.add(d);
    }

    public DefinitionSet copy() {
        DefinitionSet s= new DefinitionSet();
        s.fElements.addAll(this.fElements);
        return s;
    }

    public Iterator iterator() {
        return fElements.iterator();
    }

    public DefinitionSet unionWith(DefinitionLiteral d) {
        DefinitionSet result= copy();
        result.fElements.add(d);
        return result;
    }

    public DefinitionSet unionWith(DefinitionSet s) {
        DefinitionSet result= copy();
        result.fElements.addAll(s.fElements);
        return result;
    }

    public DefinitionSet without(DefinitionLiteral d) {
        if (d.isWildcard()) {
            DefinitionSet result= new DefinitionSet();
            for(Iterator iter= fElements.iterator(); iter.hasNext();) {
                DefinitionLiteral e= (DefinitionLiteral) iter.next();
                if (!d.matches(e))
                    result.fElements.add(e);
            }
            return result;
        } else {
            DefinitionSet result= copy();
            result.fElements.remove(d);
            return result;
        }
    }

    private boolean hasWildcard() {
        for(Iterator iter= fElements.iterator(); iter.hasNext();) {
            DefinitionLiteral element= (DefinitionLiteral) iter.next();
            if (element.isWildcard())
                return true;
        }
        return false;
    }

    public DefinitionSet without(DefinitionSet s) {
        if (s.hasWildcard()) {
            if (s.fElements.size() != 1)
                throw new UnsupportedOperationException("DefinitionSet.without() can't handle non-singleton operand set.");
            DefinitionLiteral wildcard= (DefinitionLiteral) s.fElements.iterator().next();
            DefinitionSet result= new DefinitionSet();

            for(Iterator iter= fElements.iterator(); iter.hasNext();) {
                DefinitionLiteral element= (DefinitionLiteral) iter.next();
                if (!wildcard.matches(element))
                    result.fElements.add(element);
            }
            return result;
        }
        DefinitionSet result= copy();
        result.fElements.removeAll(s.fElements);
        return result;
    }

    public boolean containsAll(DefinitionSet s) {
        return fElements.containsAll(s.fElements);
    }

    public boolean contains(DefinitionLiteral defLit) {
        return fElements.contains(defLit);
    }

    public boolean equals(Object o) {
        if (!(o instanceof DefinitionSet))
            return false;
        DefinitionSet other= (DefinitionSet) o;

        return fElements.equals(other.fElements);
    }

    public String toString() {
        StringBuffer b= new StringBuffer();
        b.append("{ ");
        for(Iterator iter= fElements.iterator(); iter.hasNext();) {
            DefinitionLiteral d= (DefinitionLiteral) iter.next();
            b.append(d);
            if (iter.hasNext())
                b.append(", ");
        }
        b.append(" }");
        return b.toString();
    }
}