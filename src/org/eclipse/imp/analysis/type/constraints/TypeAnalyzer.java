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

package org.eclipse.imp.analysis.type.constraints;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.imp.analysis.search.PolyglotSourceFinder;
import org.eclipse.imp.language.Language;
import org.eclipse.imp.language.LanguageRegistry;
import org.eclipse.imp.model.ISourceProject;
import org.eclipse.ui.editors.text.TextFileDocumentProvider;

public class TypeAnalyzer {

    private final ISourceProject fSrcProject;

    public TypeAnalyzer(ISourceProject srcProject) {
        fSrcProject= srcProject;
    }

    public void analyze() {
        ITypeVariableFactory varFactory= null;
        TypeConstraintFactory constrFactory= null;
        TypeConstraintCollector collector= new TypeConstraintCollector(new BasicPolyglotConstraintCreator(constrFactory, varFactory));
        Language lang= LanguageRegistry.findLanguage("java");
        PolyglotSourceFinder srcFinder= new PolyglotSourceFinder(new TextFileDocumentProvider(), fSrcProject, collector, collector.getVisitor(), lang);

        try {
            fSrcProject.getRawProject().accept(srcFinder);
            collector.dumpConstraints(System.out);
            solveConstraints();
        } catch (CoreException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    private void solveConstraints() {
        // TODO Auto-generated method stub

    }
}
