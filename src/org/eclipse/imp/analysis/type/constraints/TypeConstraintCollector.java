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
    public interface IConstraintAcceptor {
        void accept(IConstraint cons);
    }

    private final class ConstraintVisitor extends NodeVisitor {
        private final PolyglotConstraintCreator fCreator;

        private ConstraintVisitor(List<IConstraint> constraints, PolyglotConstraintCreator creator) {
            super();
            fCreator= creator;
        }

        /* (non-Javadoc)
         * @see polyglot.visit.NodeVisitor#enter(polyglot.ast.Node)
         */
        @Override
        public NodeVisitor enter(Node n) {
            if (n instanceof ArrayAccess) {
                fCreator.createFor((ArrayAccess) n);
            } else if (n instanceof ArrayAccessAssign) {
                fCreator.createFor((ArrayAccessAssign) n);
            } else if (n instanceof ArrayInit) {
                fCreator.createFor((ArrayInit) n);
            } else if (n instanceof ArrayTypeNode) {
                fCreator.createFor((ArrayTypeNode) n);
            } else if (n instanceof Assert) {
                fCreator.createFor((Assert) n);
            } else if (n instanceof Assign) {
                fCreator.createFor((Assign) n);
            } else if (n instanceof Binary) {
                fCreator.createFor((Binary) n);
            } else if (n instanceof Block) {
                fCreator.createFor((Block) n);
            } else if (n instanceof BooleanLit) {
                fCreator.createFor((BooleanLit) n);
            } else if (n instanceof Branch) {
                fCreator.createFor((Branch) n);
            } else if (n instanceof Call) {
                fCreator.createFor((Call) n);
            } else if (n instanceof CanonicalTypeNode) {
                fCreator.createFor((CanonicalTypeNode) n);
            } else if (n instanceof Case) {
                fCreator.createFor((Case) n);
            } else if (n instanceof Cast) {
                fCreator.createFor((Cast) n);
            } else if (n instanceof Catch) {
                fCreator.createFor((Catch) n);
            } else if (n instanceof CharLit) {
                fCreator.createFor((CharLit) n);
            } else if (n instanceof ClassBody) {
                fCreator.createFor((ClassBody) n);
            } else if (n instanceof ClassDecl) {
                fCreator.createFor((ClassDecl) n);
            } else if (n instanceof ClassLit) {
                fCreator.createFor((ClassLit) n);
            } else if (n instanceof Conditional) {
                fCreator.createFor((Conditional) n);
            } else if (n instanceof ConstructorCall) {
                fCreator.createFor((ConstructorCall) n);
            } else if (n instanceof ConstructorDecl) {
                fCreator.createFor((ConstructorDecl) n);
            } else if (n instanceof Do) {
                fCreator.createFor((Do) n);
            } else if (n instanceof Empty) {
                fCreator.createFor((Empty) n);
            } else if (n instanceof Eval) {
                fCreator.createFor((Eval) n);
            } else if (n instanceof Field) {
                fCreator.createFor((Field) n);
            } else if (n instanceof FieldAssign) {
                fCreator.createFor((FieldAssign) n);
            } else if (n instanceof FieldDecl) {
                fCreator.createFor((FieldDecl) n);
            } else if (n instanceof FloatLit) {
                fCreator.createFor((FloatLit) n);
            } else if (n instanceof For) {
                fCreator.createFor((For) n);
            } else if (n instanceof Formal) {
                fCreator.createFor((Formal) n);
            } else if (n instanceof If) {
                fCreator.createFor((If) n);
            } else if (n instanceof Import) {
                fCreator.createFor((Import) n);
            } else if (n instanceof Initializer) {
                fCreator.createFor((Initializer) n);
            } else if (n instanceof Instanceof) {
                fCreator.createFor((Instanceof) n);
            } else if (n instanceof IntLit) {
                fCreator.createFor((IntLit) n);
            } else if (n instanceof Labeled) {
                fCreator.createFor((Labeled) n);
            } else if (n instanceof Local) {
                fCreator.createFor((Local) n);
            } else if (n instanceof LocalAssign) {
                fCreator.createFor((LocalAssign) n);
            } else if (n instanceof LocalClassDecl) {
                fCreator.createFor((LocalClassDecl) n);
            } else if (n instanceof LocalDecl) {
                fCreator.createFor((LocalDecl) n);
            } else if (n instanceof MethodDecl) {
                fCreator.createFor((MethodDecl) n);
            } else if (n instanceof New) {
                fCreator.createFor((New) n);
            } else if (n instanceof NewArray) {
                fCreator.createFor((NewArray) n);
            } else if (n instanceof NullLit) {
                fCreator.createFor((NullLit) n);
            } else if (n instanceof NumLit) {
                fCreator.createFor((NumLit) n);
            } else if (n instanceof PackageNode) {
                fCreator.createFor((PackageNode) n);
            } else if (n instanceof Prefix) {
                fCreator.createFor((Prefix) n);
            } else if (n instanceof Return) {
                fCreator.createFor((Return) n);
            } else if (n instanceof SourceFile) {
                fCreator.createFor((SourceFile) n);
            } else if (n instanceof Special) {
                fCreator.createFor((Special) n);
            } else if (n instanceof StringLit) {
                fCreator.createFor((StringLit) n);
            } else if (n instanceof Switch) {
                fCreator.createFor((Switch) n);
            } else if (n instanceof SwitchBlock) {
                fCreator.createFor((SwitchBlock) n);
            } else if (n instanceof Synchronized) {
                fCreator.createFor((Synchronized) n);
            } else if (n instanceof Throw) {
                fCreator.createFor((Throw) n);
            } else if (n instanceof Try) {
                fCreator.createFor((Try) n);
            } else if (n instanceof Unary) {
                fCreator.createFor((Unary) n);
            } else if (n instanceof While) {
                fCreator.createFor((While) n);
            }
            return this;
        }
    }

    private final ConstraintVisitor fConstraintVisitor;
    private final List<IConstraint> fConstraints= new ArrayList<IConstraint>();
    private final PolyglotConstraintCreator fCreator;

    private final IConstraintAcceptor fConstraintAcceptor= new IConstraintAcceptor() {
        public void accept(IConstraint cons) {
            if (cons != null) {
                fConstraints.add(cons);
            }
        }
    };

    public TypeConstraintCollector(PolyglotConstraintCreator creator) {
        fCreator= creator;
        creator.setConstraintAcceptor(fConstraintAcceptor);
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
