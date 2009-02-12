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

package org.eclipse.imp.analysis.type.constraints.variables;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.imp.analysis.ICompilationUnitRange;
import org.eclipse.imp.analysis.PolyglotUtils;
import org.eclipse.imp.analysis.constraints.IConstraintVariable;
import org.eclipse.imp.analysis.type.constraints.ITypeVariableFactory;
import org.eclipse.imp.analysis.type.constraints.bindings.BindingKeyFactory;
import org.eclipse.imp.analysis.type.constraints.bindings.BindingKeyFactory.BindingKey;
import org.eclipse.imp.analysis.type.constraints.fastrep.TType;
import org.eclipse.imp.analysis.type.constraints.fastrep.TypeEnvironment;
import org.eclipse.imp.core.Assert;
import org.eclipse.imp.model.ICompilationUnit;

import polyglot.ast.Expr;
import polyglot.ast.Field;
import polyglot.ast.Lit;
import polyglot.ast.Local;
import polyglot.types.ClassType;
import polyglot.types.FieldInstance;
import polyglot.types.MethodInstance;
import polyglot.types.Type;
import polyglot.types.VarInstance;

public class TypeVariableFactory implements ITypeVariableFactory {
    private final TypeEnvironment fTypeEnv= new TypeEnvironment();

    private final BindingKeyFactory fKeyFactory;

    private ICompilationUnit fCU;

    private final Map<Object/*BindingKey,ICompilationUnitRange*/,ITypeConstraintVariable> fExpressionMap= new HashMap<Object/*BindingKey,ICompilationUnitRange*/,ITypeConstraintVariable>();

    private final Map<ICompilationUnitRange,TypeParameterVariable> fTypeVariableMap= new HashMap<ICompilationUnitRange,TypeParameterVariable>();

    private final Map<BindingKey,DeclaringTypeVariable> fDeclaringTypeVariableMap= new HashMap<BindingKey,DeclaringTypeVariable>();

    private final Map<BindingKey,MethodParameterTypeVariable> fParameterMap= new HashMap<BindingKey,MethodParameterTypeVariable>();

    private final Map<BindingKey,ImmutableTypeVariable> fRawBindingMap= new HashMap<BindingKey,ImmutableTypeVariable>();

    private final Map<BindingKey,ReturnTypeVariable> fReturnVariableMap= new HashMap<BindingKey,ReturnTypeVariable>();

    private final Map<IConstraintVariable,IConstraintVariable> fArrayElementMap= new HashMap<IConstraintVariable,IConstraintVariable>();

    protected int nrCreated= 0;

    protected int nrRetrieved= 0;

    public static final boolean REPORT= false;

    public TypeVariableFactory(BindingKeyFactory keyFactory) {
        fKeyFactory= keyFactory;
    }

    public int getNumCreated() {
        return nrCreated;
    }

    protected void dumpConstraintStats() {
        System.out.println("Constraint variables created: " + nrCreated + ", retrieved: " + nrRetrieved); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public void setCompilationUnit(ICompilationUnit unit) {
        fCU= unit;
    }

    public ITypeConstraintVariable makeVariableForExpression(Expr expr) {
        if (expr instanceof Lit) {
            // Optimization for literals: represent them by an ImmutableTypeVariable
            return makeImmutableTypeVariable(expr.type());
        }

        BindingKey bindingKey= resolveExprBinding(expr);

        // For expressions, there are two cases. If the expression has a binding
        // we use that as the key. Otherwise, we use the CompilationUnitRange. See
        // also ExpressionVariable.equals()
        ExpressionVariable ev;
        Object mapKey;

        if (bindingKey != null) {
            mapKey= bindingKey;
        } else {
            mapKey= PolyglotUtils.unitRangeForNode(expr, fCU);
        }
        ev= (ExpressionVariable) fExpressionMap.get(mapKey);

        if (ev != null) {
            if (REPORT)
                nrRetrieved++;
        } else {
            Type type= expr.type();
            ev= new ExpressionVariable(expr, fTypeEnv.createForType(type), PolyglotUtils.unitRangeForNode(expr, fCU));
            fExpressionMap.put(mapKey, ev);
            if (REPORT)
                nrCreated++;
        }
        return ev;
    }

    private BindingKey resolveExprBinding(Expr expr) {
        if (expr instanceof Local) {
            Local local= (Local) expr;
            return fKeyFactory.getKeyForVariable(local);
        } else if (expr instanceof Field) {
            Field field= (Field) expr;
            return fKeyFactory.getKeyForVariable(field);
        }
        return null;
    }

    public ITypeConstraintVariable makeVariableForDeclaration(VarInstance varDecl) {
        BindingKey bindingKey= fKeyFactory.getKeyForVariable(varDecl);

        Assert.isNotNull(bindingKey);

        ITypeConstraintVariable ev= (ExpressionVariable) fExpressionMap.get(bindingKey);

        if (ev != null) {
            if (REPORT)
                nrRetrieved++;
        } else {
            Type type= varDecl.type();
            ev= new TypeConstraintVariable(bindingKey, fTypeEnv.createForType(type), PolyglotUtils.unitRangeForPosition(varDecl.position(), fCU));
            fExpressionMap.put(bindingKey, ev);
            if (REPORT)
                nrCreated++;
        }
        return ev;
    }

    public ArrayElementVariable makeArrayElementVariable(ITypeConstraintVariable arrayVar, ICompilationUnitRange range) {
        if (!fArrayElementMap.containsKey(arrayVar)) {
            fArrayElementMap.put(arrayVar, new ArrayElementVariable(arrayVar, range));
            if (REPORT)
                nrCreated++;
        } else {
            if (REPORT)
                nrRetrieved++;
        }
        return (ArrayElementVariable) fArrayElementMap.get(arrayVar);
    }

    public DeclaringTypeVariable makeDeclaringTypeVariable(Type memberType) {
        BindingKey typeKey= fKeyFactory.getKeyForType(memberType);

        if (!fDeclaringTypeVariableMap.containsKey(typeKey)) {
            ClassType memberClass= (ClassType) memberType;
            TType declType= fTypeEnv.createForType(memberClass.container());
            ICompilationUnitRange range= PolyglotUtils.unitRangeForPosition(memberType.position(), fCU);

            fDeclaringTypeVariableMap.put(typeKey, new DeclaringTypeVariable(typeKey, declType, range, fKeyFactory));
            if (REPORT)
                nrCreated++;
        } else {
            if (REPORT)
                nrRetrieved++;
        }
        return (DeclaringTypeVariable) fDeclaringTypeVariableMap.get(typeKey);
    }

    public DeclaringTypeVariable makeDeclaringTypeVariable(FieldInstance field) {
        BindingKey fieldKey= fKeyFactory.getKeyForVariable(field);

        if (!fDeclaringTypeVariableMap.containsKey(fieldKey)) {
            TType declType= fTypeEnv.createForType(field.type());

            fDeclaringTypeVariableMap.put(fieldKey, new DeclaringTypeVariable(fieldKey, declType, PolyglotUtils.unitRangeForPosition(field.position(), fCU), fKeyFactory));
            if (REPORT)
                nrCreated++;
        } else {
            if (REPORT)
                nrRetrieved++;
        }
        return (DeclaringTypeVariable) fDeclaringTypeVariableMap.get(fieldKey);
    }

    public DeclaringTypeVariable makeDeclaringTypeVariable(MethodInstance method) {
        BindingKey methodKey= fKeyFactory.getKeyForMethod(method);

        if (!fDeclaringTypeVariableMap.containsKey(methodKey)) {
            fDeclaringTypeVariableMap.put(methodKey, new DeclaringTypeVariable(methodKey, null, PolyglotUtils.unitRangeForPosition(method.position(), fCU), fKeyFactory));
            if (REPORT)
                nrCreated++;
        } else {
            if (REPORT)
                nrRetrieved++;
        }
        return (DeclaringTypeVariable) fDeclaringTypeVariableMap.get(methodKey);
    }

    public MethodParameterTypeVariable makeParameterTypeVariable(MethodInstance methodInstance, int parameterIndex, ICompilationUnitRange range) {
        BindingKey methodKey= fKeyFactory.getKeyForMethod(methodInstance);
        BindingKey paramKey= fKeyFactory.getKeyForMethodArgument(methodKey, parameterIndex);

        if (!fParameterMap.containsKey(paramKey)) {
            TType declType= fTypeEnv.createForType((Type) methodInstance.formalTypes().get(parameterIndex));

            fParameterMap.put(methodKey, new MethodParameterTypeVariable(methodKey, parameterIndex, declType, range, fKeyFactory));
            if (REPORT)
                nrCreated++;
        } else {
            if (REPORT)
                nrRetrieved++;
        }
        return (MethodParameterTypeVariable) fParameterMap.get(methodKey);
    }

    public ImmutableTypeVariable makeImmutableTypeVariable(Type type) {
        Assert.isNotNull(type);
        BindingKey typeKey= fKeyFactory.getKeyForType(type);

        if (!fRawBindingMap.containsKey(typeKey)) {
            TType fastType= fTypeEnv.createForType(type);
//          ICompilationUnitRange range= PolyglotUtils.srcRangeForPosition(type.position(), fCU);

            fRawBindingMap.put(typeKey, new ImmutableTypeVariable(typeKey, fastType));
            if (REPORT)
                nrCreated++;
        } else {
            if (REPORT)
                nrRetrieved++;
        }
        return (ImmutableTypeVariable) fRawBindingMap.get(typeKey);
    }

    public ReturnTypeVariable makeReturnTypeVariable(MethodInstance method) {
        BindingKey methodKey= fKeyFactory.getKeyForMethod(method);

        if (!fReturnVariableMap.containsKey(methodKey)) {
            ICompilationUnitRange range= PolyglotUtils.unitRangeForPosition(method.position(), fCU);
            TType declaredReturnType= fTypeEnv.createForType(method.returnType());

            fReturnVariableMap.put(methodKey, new ReturnTypeVariable(methodKey, declaredReturnType, range, fKeyFactory));
            if (REPORT)
                nrCreated++;
        } else {
            if (REPORT)
                nrRetrieved++;
        }
        return (ReturnTypeVariable) fReturnVariableMap.get(methodKey);
    }
}
