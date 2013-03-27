package org.iplantc.core.uicommons.client.widgets;

import com.extjs.gxt.ui.client.widget.Label;

/**
 * A label that looks like a FormPanel label.
 * 
 * @deprecated Class needs to be deleted or ported to GXT3
 */
@Deprecated
public class FormLabel extends Label {

    /**
     * Creates a new FormLabel with a given text.
     * 
     * @param text the label text
     */
    public FormLabel(String text) {
        super(text + ":"); //$NON-NLS-1$
        setStyleName("x-form-item"); //$NON-NLS-1$
    }
}
