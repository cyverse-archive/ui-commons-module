package org.iplantc.core.uicommons.client;

import com.google.gwt.core.client.GWT;

/**
 * Provides static access to localized strings.
 * 
 * @author lenards
 * 
 */
public class I18N {
    /** Strings displayed in the UI */
    public static final CommonUIDisplayStrings DISPLAY = (CommonUIDisplayStrings)GWT.create(CommonUIDisplayStrings.class);
    /** Error messages */
    public static final CommonUIErrorStrings ERROR = (CommonUIErrorStrings)GWT.create(CommonUIErrorStrings.class);
}