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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.imp.analysis.ISourceRange;

/**
 * Factory for binding keys, so that "binding objects" (e.g. IBinding implementations in the
 * JDT DOM, or TypeObjects in the Polyglot AST) are unique.
 * 
 * Sub-classes are responsible for creating BindingKeys for types, methods, packages, and variables.
 * This class handles return, declaring type and method argument keys itself. Sub-classes may find
 * the StringBindingKey class useful.
 */
public abstract class BindingKeyFactory {
    public interface BindingKey { } // can be anything an AST-specific impl needs

    protected Map<Object, BindingKey> fTypeBindingMap= new HashMap<Object, BindingKey>();
    protected Map<Object, BindingKey> fMethodBindingMap= new HashMap<Object, BindingKey>();
    protected Map<Object, BindingKey> fPackageBindingMap= new HashMap<Object, BindingKey>();
    protected Map<Object, BindingKey> fVariableBindingMap= new HashMap<Object, BindingKey>();
    protected Map<Object, BindingKey> fReturnBindingMap= new HashMap<Object, BindingKey>();
    protected Map<Object, BindingKey> fDeclTypeBindingMap= new HashMap<Object, BindingKey>();

    protected Map<Object, Map<Integer,BindingKey>> fMethodArgBindingMap= new HashMap<Object, Map<Integer,BindingKey>>();

    /**
     * Interface that encapsulates the creation of a given kind of BindingKey
     */
    private interface IKeyCreator {
        public BindingKey createKey(Object obj);
    }

    /*
     * Various interfaces follow that just wrap the derived-class key creation methods
     * in a class so that we can invoke them from the generic method getKeyFor(...).
     */

    private IKeyCreator fTypeCreator= new IKeyCreator() {
        public BindingKey createKey(Object obj) {
            return createKeyForType(obj);
        }
    };
    private IKeyCreator fMethodCreator= new IKeyCreator() {
        public BindingKey createKey(Object obj) {
            return createKeyForMethod(obj);
        }
    };
    private IKeyCreator fPackageCreator= new IKeyCreator() {
        public BindingKey createKey(Object obj) {
            return createKeyForPackage(obj);
        }
    };
    private IKeyCreator fVariableCreator= new IKeyCreator() {
        public BindingKey createKey(Object obj) {
            return createKeyForVariable(obj);
        }
    };
    private IKeyCreator fReturnCreator= new IKeyCreator() {
        public BindingKey createKey(Object methodKey) {
            return new ReturnKey((BindingKey) methodKey);
        }
    };
    private IKeyCreator fDeclTypeCreator= new IKeyCreator() {
        public BindingKey createKey(Object memberKey) {
            return new DeclaringTypeKey((BindingKey) memberKey);
        }
    };

    /**
     * Generic method that uses the given IKeyCreator to produce a key for the given object,
     * only if it doesn't already exist in the given map.
     */
    private BindingKey getKeyFor(Object obj, Map<Object,BindingKey> map, IKeyCreator creator) {
        if (map.containsKey(obj))
            return map.get(obj);
        BindingKey newKey= creator.createKey(obj);
        map.put(obj, newKey);
        return newKey;
    }
    //
    // ================================================================================
    //
    public BindingKey getKeyForType(Object type) {
        return getKeyFor(type, fTypeBindingMap, fTypeCreator);
    }
    public BindingKey getKeyForMethod(Object method) {
        return getKeyFor(method, fMethodBindingMap, fMethodCreator);
    }
    public BindingKey getKeyForPackage(Object pkg) {
        return getKeyFor(pkg, fPackageBindingMap, fPackageCreator);
    }
    public BindingKey getKeyForVariable(Object var) {
        return getKeyFor(var, fVariableBindingMap, fVariableCreator);
    }
    public BindingKey getKeyForReturn(BindingKey methodKey) {
        return getKeyFor(methodKey, fReturnBindingMap, fReturnCreator);
    }
    public BindingKey getKeyForDeclaringType(BindingKey memberKey) {
        return getKeyFor(memberKey, fDeclTypeBindingMap, fDeclTypeCreator);
    }
    public BindingKey getKeyForMethodArgument(BindingKey methodKey, int argIndex) {
        Map<Integer,BindingKey> argMap;
        if (fMethodArgBindingMap.containsKey(methodKey)) {
            argMap= fMethodArgBindingMap.get(methodKey);
        } else {
            argMap= new HashMap<Integer, BindingKey>();
            fMethodArgBindingMap.put(methodKey, argMap);
        }
        Integer boxedArgIndex= new Integer(argIndex);
        if (argMap.containsKey(boxedArgIndex)) {
            return argMap.get(boxedArgIndex);
        }
        BindingKey key= new MethodArgumentKey(methodKey, argIndex);
        argMap.put(boxedArgIndex, key);
        return key;
    }
    //
    // ================================================================================
    // The following abstract methods are the only responsibility of concrete factory classes.
    //
    protected abstract BindingKey createKeyForType(Object type);
    protected abstract BindingKey createKeyForMethod(Object method);
    protected abstract BindingKey createKeyForPackage(Object pkg);
    protected abstract BindingKey createKeyForVariable(Object var);

    public abstract Object findType(BindingKey typeKey);
    public abstract Object findMethod(BindingKey methodKey);
    public abstract Object findPackage(BindingKey pkgKey);
    public abstract Object findVariable(BindingKey varKey);

    //
    // ================================================================================
    //

    /**
     * A trivial BindingKey implementation that just wraps a String.
     */
    protected final class StringBindingKey implements BindingKey {
        private final String fTheKey;

        public StringBindingKey(String key) {
            fTheKey= key;
        }
        public String getKey() {
            return fTheKey;
        }
        /* (non-Javadoc)
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            return 311 * fTheKey.hashCode() + 839;
        }
        /* (non-Javadoc)
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof StringBindingKey))
                return false;
            StringBindingKey other= (StringBindingKey) obj;
            return other.fTheKey.equals(fTheKey);
        }
        public String toString() {
            return fTheKey;
        }
    }

    /**
     * A trivial BindingKey implementation that just wraps an ISourceRange.
     */
    public static class SourceRangeKey implements BindingKey {
        private final ISourceRange fRange;
        public SourceRangeKey(ISourceRange range) {
            fRange= range;
        }
        public boolean equals(Object o) {
            if (!(o instanceof SourceRangeKey))
                return false;
            SourceRangeKey other= (SourceRangeKey) o;
            return fRange.equals(other.fRange);
        }
        public int hashCode() {
            return 61 + 4049 * fRange.hashCode();
        }
        public String toString() {
            return "<rangeKey " + fRange + ">";
        }
    }

    /**
     * A trivial BindingKey implementation that just wraps two other BindingKeys.
     */
    public static class CompositeKey implements BindingKey {
        private final BindingKey fKey1;
        private final BindingKey fKey2;
        public CompositeKey(BindingKey k1, BindingKey k2) {
            fKey1= k1;
            fKey2= k2;
        }
        public boolean equals(Object o) {
            if (!(o instanceof CompositeKey))
                return false;
            CompositeKey other= (CompositeKey) o;
            return fKey1.equals(other.fKey1) && fKey1.equals(other.fKey2);
        }
        public int hashCode() {
            return 61 + 157 * (4049 * fKey1.hashCode() + 6691 * fKey2.hashCode());
        }
        public String toString() {
            return "<compoundKey: " + fKey1 + "#" + fKey2 + ">";
        }
    }

    /**
     * A "derived key" BindingKey implementation for representing the return type of a function/method.
     */
    protected final class ReturnKey implements BindingKey {
        private final BindingKey fMethodKey; // Could be a function too, doesn't matter
        public ReturnKey(BindingKey methodKey) {
            fMethodKey= methodKey;
        }
        /* (non-Javadoc)
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            return 17 * fMethodKey.hashCode() + 391;
        }
        /* (non-Javadoc)
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof ReturnKey))
                return false;
            ReturnKey other= (ReturnKey) obj;
            return other.fMethodKey.equals(fMethodKey);
        }
        public String toString() {
            return "return[" + fMethodKey + "]";
        }
    }

    /**
     * A "derived key" BindingKey implementation for representing the type that declares a given member.
     */
    protected final class DeclaringTypeKey implements BindingKey {
        private final BindingKey fMemberKey;
        public DeclaringTypeKey(BindingKey memberKey) {
            fMemberKey= memberKey;
        }
        /* (non-Javadoc)
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            return 23 * fMemberKey.hashCode() + 419;
        }
        /* (non-Javadoc)
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof DeclaringTypeKey))
                return false;
            DeclaringTypeKey other= (DeclaringTypeKey) obj;
            return other.fMemberKey.equals(fMemberKey);
        }
        public String toString() {
            return "declaringTypeOf[" + fMemberKey + "]";
        }
    }

    /**
     * A "derived key" BindingKey implementation for representing a given function/method argument.
     */
    protected final class MethodArgumentKey implements BindingKey {
        private final BindingKey fMethodKey; // Could be a plain function too; doesn't matter
        private final int fArgIndex;
        public MethodArgumentKey(BindingKey methodKey, int argIndex) {
            fMethodKey= methodKey;
            fArgIndex= argIndex;
        }
        /* (non-Javadoc)
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            return 53 * fMethodKey.hashCode() + 461 * fArgIndex + 167;
        }
        /* (non-Javadoc)
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof MethodArgumentKey))
                return false;
            MethodArgumentKey other= (MethodArgumentKey) obj;
            return other.fMethodKey.equals(fMethodKey) && (other.fArgIndex == fArgIndex);
        }
        public String toString() {
            return "arg[" + fMethodKey + "," + fArgIndex + "]";
        }
    }
}
