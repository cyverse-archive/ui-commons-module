package org.iplantc.core.uicommons.client.images;

import org.iplantc.core.uicommons.client.images.Icons;

import com.google.gwt.core.client.GWT;

/**
 * Image resources singleton object.
 */
public class Resources {
    /** The singleton instance. */
    public static final Icons ICONS = GWT.create(Icons.class);

}
