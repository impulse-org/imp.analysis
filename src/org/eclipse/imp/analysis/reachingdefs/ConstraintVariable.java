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

package org.eclipse.imp.analysis.reachingdefs;

public abstract class ConstraintVariable extends ConstraintTerm {
    public static final String TO_STRING= "toString"; //$NON-NLS-1$

    private Object[] fDatas;

    public Object getData(String name) {
	if (fDatas == null) {
	    return null;
	} else {
	    for(int i= 0; i < fDatas.length; i+= 2) {
		String key= (String) fDatas[i];
		if (key.equals(name))
		    return fDatas[i + 1];
	    }
	    return null;
	}
    }

    public void setData(String name, Object data) {
	int index= 0;
	if (fDatas != null) {
	    while (index < fDatas.length) {
		if (name.equals(fDatas[index]))
		    break;
		index+= 2;
	    }
	}
	if (data != null) { // add
	    if (fDatas != null) {
		if (index == fDatas.length) {
		    Object[] newTable= new Object[fDatas.length + 2];
		    System.arraycopy(fDatas, 0, newTable, 0, fDatas.length);
		    fDatas= newTable;
		}
	    } else {
		fDatas= new Object[2];
	    }
	    fDatas[index]= name;
	    fDatas[index + 1]= data;
	} else { // remove
	    if (fDatas != null) {
		if (index != fDatas.length) {
		    int length= fDatas.length - 2;
		    if (length == 0) {
			fDatas= null;
		    } else {
			Object[] newTable= new Object[length];
			System.arraycopy(fDatas, 0, newTable, 0, index);
			System.arraycopy(fDatas, index + 2, newTable, index, length - index);
			fDatas= newTable;
		    }
		}
	    }
	}
    }

    public abstract String toString(); // Force implementation in derived classes
}
