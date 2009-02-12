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

import org.eclipse.imp.runtime.PluginBase;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class ConstraintsPlugin extends PluginBase {
    public static final String kPluginID= "org.eclipse.imp.analysis";

    private static final String kLanguageID= "impAnalysis";

    /**
     * The unique instance of this plugin class
     */
    protected static ConstraintsPlugin sPlugin;

    public static ConstraintsPlugin getInstance() {
        return sPlugin;
    }

    /**
     * The constructor.
     */
    public ConstraintsPlugin() {
        sPlugin= this;
    }

    @Override
    public String getID() {
        return kPluginID;
    }

    @Override
    public String getLanguageID() {
        return kLanguageID;
    }

    /**
     * This method is called upon plug-in activation
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
    }

    /**
     * This method is called when the plug-in is stopped
     */
    public void stop(BundleContext context) throws Exception {
        super.stop(context);
        sPlugin= null;
    }
}
