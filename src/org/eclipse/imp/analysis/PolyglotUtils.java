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

package org.eclipse.imp.analysis;

import org.eclipse.imp.model.ICompilationUnit;

import polyglot.ast.Node;
import polyglot.util.Position;

/**
 * Miscellaneous utilities for dealing with Polyglot AST nodes and types.
 */
public class PolyglotUtils {
    private PolyglotUtils() { }

    public static ISourceRange srcRangeForNode(Node n, ICompilationUnit unit) {
        Position pos= n.position();
    
        return PolyglotUtils.srcRangeForPosition(pos, unit);
    }

    public static ISourceRange srcRangeForPosition(Position pos, ICompilationUnit unit) {
        if (pos == null)
            return null;
        int correctedOffset= pos.offset() - pos.line();
        int correctedEnd= pos.endOffset() - pos.endLine();
        return new SourceRange((unit != null ? unit.getFile() : null), correctedOffset, correctedEnd - correctedOffset + 1 + 1);
    }

    public static ICompilationUnitRange unitRangeForNode(Node n, ICompilationUnit unit) {
        Position pos= n.position();
    
        return PolyglotUtils.unitRangeForPosition(pos, unit);
    }

    public static ICompilationUnitRange unitRangeForPosition(Position pos, ICompilationUnit unit) {
        if (pos == null || unit == null)
            return null;
        return new CompilationUnitRange(unit, srcRangeForPosition(pos, unit));
    }
}
