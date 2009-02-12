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

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.imp.analysis.ConstraintsPlugin;
import org.eclipse.imp.analysis.constraints.IConstraint;
import org.eclipse.imp.model.ICompilationUnit;
import org.eclipse.imp.model.ISourceProject;
import org.eclipse.imp.model.ModelFactory;
import org.eclipse.imp.model.ModelFactory.ModelException;
import org.eclipse.imp.refactoring.IFileVisitor;

import polyglot.ast.*;
import polyglot.visit.NodeVisitor;

/**
 * Responsible for traversing the given Polyglot AST tree and collecting the constraints
 * created by the given ConstraintCreator.
 */
public class TypeConstraintCollector implements IFileVisitor {
    private final class ConstraintVisitor extends NodeVisitor {
        private final List<IConstraint> fConstraints;

        private final PolyglotConstraintCreator fCreator;

        private ConstraintVisitor(List<IConstraint> constraints, PolyglotConstraintCreator creator) {
            super();
            fConstraints= constraints;
            fCreator= creator;
        }

        private void addAll(IConstraint[] constraints) {
            for(int i= 0; i < constraints.length; i++) {
                IConstraint constraint= constraints[i];
                fConstraints.add(constraint);
            }
        }

        /* (non-Javadoc)
         * @see polyglot.visit.NodeVisitor#enter(polyglot.ast.Node)
         */
        @Override
        public NodeVisitor enter(Node n) {
            if (n instanceof ArrayAccess) {
                addAll(fCreator.createFor((ArrayAccess) n));
            } else if (n instanceof ArrayAccessAssign) {
                addAll(fCreator.createFor((ArrayAccessAssign) n));
            } else if (n instanceof ArrayInit) {
                addAll(fCreator.createFor((ArrayInit) n));
            } else if (n instanceof ArrayTypeNode) {
                addAll(fCreator.createFor((ArrayTypeNode) n));
            } else if (n instanceof Assert) {
                addAll(fCreator.createFor((Assert) n));
            } else if (n instanceof Assign) {
                addAll(fCreator.createFor((Assign) n));
            } else if (n instanceof Binary) {
                addAll(fCreator.createFor((Binary) n));
            } else if (n instanceof Block) {
                addAll(fCreator.createFor((Block) n));
            } else if (n instanceof BooleanLit) {
                addAll(fCreator.createFor((BooleanLit) n));
            } else if (n instanceof Branch) {
                addAll(fCreator.createFor((Branch) n));
            } else if (n instanceof Call) {
                addAll(fCreator.createFor((Call) n));
            } else if (n instanceof CanonicalTypeNode) {
                addAll(fCreator.createFor((CanonicalTypeNode) n));
            } else if (n instanceof Case) {
                addAll(fCreator.createFor((Case) n));
            } else if (n instanceof Cast) {
                addAll(fCreator.createFor((Cast) n));
            } else if (n instanceof Catch) {
                addAll(fCreator.createFor((Catch) n));
            } else if (n instanceof CharLit) {
                addAll(fCreator.createFor((CharLit) n));
            } else if (n instanceof ClassBody) {
                addAll(fCreator.createFor((ClassBody) n));
            } else if (n instanceof ClassDecl) {
                addAll(fCreator.createFor((ClassDecl) n));
            } else if (n instanceof ClassLit) {
                addAll(fCreator.createFor((ClassLit) n));
            } else if (n instanceof Conditional) {
                addAll(fCreator.createFor((Conditional) n));
            } else if (n instanceof ConstructorCall) {
                addAll(fCreator.createFor((ConstructorCall) n));
            } else if (n instanceof ConstructorDecl) {
                addAll(fCreator.createFor((ConstructorDecl) n));
            } else if (n instanceof Do) {
                addAll(fCreator.createFor((Do) n));
            } else if (n instanceof Empty) {
                addAll(fCreator.createFor((Empty) n));
            } else if (n instanceof Eval) {
                addAll(fCreator.createFor((Eval) n));
            } else if (n instanceof Field) {
                addAll(fCreator.createFor((Field) n));
            } else if (n instanceof FieldAssign) {
                addAll(fCreator.createFor((FieldAssign) n));
            } else if (n instanceof FieldDecl) {
                addAll(fCreator.createFor((FieldDecl) n));
            } else if (n instanceof FloatLit) {
                addAll(fCreator.createFor((FloatLit) n));
            } else if (n instanceof For) {
                addAll(fCreator.createFor((For) n));
            } else if (n instanceof Formal) {
                addAll(fCreator.createFor((Formal) n));
            } else if (n instanceof If) {
                addAll(fCreator.createFor((If) n));
            } else if (n instanceof Import) {
                addAll(fCreator.createFor((Import) n));
            } else if (n instanceof Initializer) {
                addAll(fCreator.createFor((Initializer) n));
            } else if (n instanceof Instanceof) {
                addAll(fCreator.createFor((Instanceof) n));
            } else if (n instanceof IntLit) {
                addAll(fCreator.createFor((IntLit) n));
            } else if (n instanceof Labeled) {
                addAll(fCreator.createFor((Labeled) n));
            } else if (n instanceof Local) {
                addAll(fCreator.createFor((Local) n));
            } else if (n instanceof LocalAssign) {
                addAll(fCreator.createFor((LocalAssign) n));
            } else if (n instanceof LocalClassDecl) {
                addAll(fCreator.createFor((LocalClassDecl) n));
            } else if (n instanceof LocalDecl) {
                addAll(fCreator.createFor((LocalDecl) n));
            } else if (n instanceof MethodDecl) {
                addAll(fCreator.createFor((MethodDecl) n));
            } else if (n instanceof New) {
                addAll(fCreator.createFor((New) n));
            } else if (n instanceof NewArray) {
                addAll(fCreator.createFor((NewArray) n));
            } else if (n instanceof NullLit) {
                addAll(fCreator.createFor((NullLit) n));
            } else if (n instanceof NumLit) {
                addAll(fCreator.createFor((NumLit) n));
            } else if (n instanceof PackageNode) {
                addAll(fCreator.createFor((PackageNode) n));
            } else if (n instanceof Prefix) {
                addAll(fCreator.createFor((Prefix) n));
            } else if (n instanceof Return) {
                addAll(fCreator.createFor((Return) n));
            } else if (n instanceof SourceFile) {
                addAll(fCreator.createFor((SourceFile) n));
            } else if (n instanceof Special) {
                addAll(fCreator.createFor((Special) n));
            } else if (n instanceof StringLit) {
                addAll(fCreator.createFor((StringLit) n));
            } else if (n instanceof Switch) {
                addAll(fCreator.createFor((Switch) n));
            } else if (n instanceof SwitchBlock) {
                addAll(fCreator.createFor((SwitchBlock) n));
            } else if (n instanceof Synchronized) {
                addAll(fCreator.createFor((Synchronized) n));
            } else if (n instanceof Throw) {
                addAll(fCreator.createFor((Throw) n));
            } else if (n instanceof Try) {
                addAll(fCreator.createFor((Try) n));
            } else if (n instanceof Unary) {
                addAll(fCreator.createFor((Unary) n));
            } else if (n instanceof While) {
                addAll(fCreator.createFor((While) n));
            }
            return this;
        }
    }

    private final ConstraintVisitor fConstraintVisitor;
    private final List<IConstraint> fConstraints= new ArrayList<IConstraint>();
    private final PolyglotConstraintCreator fCreator;

    public TypeConstraintCollector(PolyglotConstraintCreator creator) {
        fCreator= creator;
        fConstraintVisitor= new ConstraintVisitor(fConstraints, fCreator);
    }

    public void clearConstraints() {
        fConstraints.clear();
    }

    /* (non-Javadoc)
     * @see org.eclipse.uide.refactoring.IFileVisitor#enterFile(org.eclipse.core.resources.IFile)
     */
    public void enterFile(IFile file) {
        System.out.println("Generating constraints for file" + file.getLocation().toPortableString());
        try {
            ISourceProject srcProject= ModelFactory.open(file.getProject());
            ICompilationUnit unit= ModelFactory.open(file, srcProject);

            fCreator.getVariableFactory().setCompilationUnit(unit);
        } catch (ModelException e) {
            ConstraintsPlugin.getInstance().logException("Error while generating type constraints for " + file.getProjectRelativePath(), e);
//          throw new CoreException(new Status(IStatus.ERROR, "", 0, e.getMessage(), e));
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.uide.refactoring.IFileVisitor#leaveFile(org.eclipse.core.resources.IFile)
     */
    public void leaveFile(IFile file) {
        System.out.println("Done generating constraints for file" + file.getLocation().toPortableString());
        dumpConstraints(System.out);
        // Maybe print some statistics?
    }

    public NodeVisitor getVisitor() {
        return fConstraintVisitor;
    }

    public List<IConstraint> getConstraints() {
        return fConstraints;
    }

    /**
     * @param out
     */
    public void dumpConstraints(PrintStream out) {
        for(IConstraint constraint : fConstraints) {
            out.println(constraint.toString());
        }
    }
}
