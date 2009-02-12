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

package org.eclipse.imp.analysis.type.constraints.bindings;

import org.eclipse.imp.analysis.PolyglotUtils;

import polyglot.ast.Field;
import polyglot.ast.Local;
import polyglot.types.ArrayType;
import polyglot.types.ClassType;
import polyglot.types.FieldInstance;
import polyglot.types.LocalInstance;
import polyglot.types.MethodInstance;
import polyglot.types.Package;
import polyglot.types.PrimitiveType;
import polyglot.types.ReferenceType;
import polyglot.types.Type;
import polyglot.util.Position;

/**
 * 
 */
public class PolyglotBindingKeyFactory extends BindingKeyFactory {
    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.bindings.BindingKeyFactory#createKeyForType(java.lang.Object)
     */
    @Override
    protected BindingKey createKeyForType(Object type) {
        Type t= (Type) type;
        if (t.isPrimitive()) {
            PrimitiveType pt= (PrimitiveType) t;
            return new StringBindingKey(pt.toString());
        } else if (t.isClass()) {
            ClassType ct= (ClassType) t;
            return new StringBindingKey(ct.fullName());
        } else if (t.isArray()) {
            ArrayType at= (ArrayType) t;
            Type base= at.base();
            int dims= at.dims();
            BindingKey baseKey= createKeyForType(base);
            String dimSuffix= "";
            for(int i=0; i < dims; i++)
                dimSuffix += "[]";
            return new CompositeKey(baseKey, new StringBindingKey(dimSuffix));
        }
        throw new IllegalArgumentException("Unable to create key for type " + type);
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.bindings.BindingKeyFactory#createKeyForMethod(java.lang.Object)
     */
    @Override
    protected BindingKey createKeyForMethod(Object method) {
        MethodInstance mi= (MethodInstance) method;
        BindingKey containerKey= getKeyForType(mi.container());

        return new CompositeKey(containerKey, new StringBindingKey(mi.signature()));
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.bindings.BindingKeyFactory#createKeyForPackage(java.lang.Object)
     */
    @Override
    protected BindingKey createKeyForPackage(Object pkg) {
        Package p= (Package) pkg;
        return new StringBindingKey(p.fullName());
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.bindings.BindingKeyFactory#createKeyForVariable(java.lang.Object)
     */
    @Override
    protected BindingKey createKeyForVariable(Object var) {
        if (var instanceof Local) {
            Local local= (Local) var;
            LocalInstance li= local.localInstance();
            Position pos= li.declaration().position(); // use the position of the decl, not the reference

            return new SourceRangeKey(PolyglotUtils.srcRangeForPosition(pos, null)); // where to get an ICU?
        } else if (var instanceof LocalInstance) {
            LocalInstance li= (LocalInstance) var;
            Position pos= li.declaration().position(); // use the position of the decl, not the reference

            return new SourceRangeKey(PolyglotUtils.srcRangeForPosition(pos, null)); // where to get an ICU?
        } else if (var instanceof Field) {
            Field field= (Field) var;
            ReferenceType container= field.fieldInstance().container();

            return new CompositeKey(getKeyForType(container), new StringBindingKey(field.name()));
        } else if (var instanceof FieldInstance) {
            FieldInstance field= (FieldInstance) var;
            ReferenceType container= field.container();

            return new CompositeKey(getKeyForType(container), new StringBindingKey(field.name()));
        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.bindings.BindingKeyFactory#findType(org.eclipse.safari.java.analysis.type.bindings.BindingKeyFactory.BindingKey)
     */
    @Override
    public Object findType(BindingKey typeKey) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.bindings.BindingKeyFactory#findMethod(org.eclipse.safari.java.analysis.type.bindings.BindingKeyFactory.BindingKey)
     */
    @Override
    public Object findMethod(BindingKey methodKey) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.bindings.BindingKeyFactory#findPackage(org.eclipse.safari.java.analysis.type.bindings.BindingKeyFactory.BindingKey)
     */
    @Override
    public Object findPackage(BindingKey pkgKey) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.analysis.type.bindings.BindingKeyFactory#findVariable(org.eclipse.safari.java.analysis.type.bindings.BindingKeyFactory.BindingKey)
     */
    @Override
    public Object findVariable(BindingKey varKey) {
        // TODO Auto-generated method stub
        return null;
    }
}
