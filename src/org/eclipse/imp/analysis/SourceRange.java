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

import org.eclipse.core.resources.IFile;

/**
 * @author rfuhrer@watson.ibm.com
 */
public class SourceRange implements ISourceRange {

    private final IFile fFile;
    private final int fOffset;
    private final int fLength;

    public SourceRange(IFile file, int offset, int length) {
        fFile= file;
        fOffset= offset;
        fLength= length;
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.refactoring.ISourceRange#getFile()
     */
    public IFile getFile() {
        return fFile;
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.refactoring.ISourceRange#getOffset()
     */
    public int getOffset() {
        return fOffset;
    }

    /* (non-Javadoc)
     * @see org.eclipse.safari.java.refactoring.ISourceRange#getLength()
     */
    public int getLength() {
        return fLength;
    }

    public String toString() {
        return "<srcRange: " + (fFile != null ? fFile.getLocation().toPortableString() : "<none>") + ": " + fOffset + ":" + fLength + ">";
    }
}
