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
    public static final CommonUIDisplayStrings DISPLAY = GWT.create(CommonUIDisplayStrings.class);
    
    /** Error messages */
    public static final CommonUIErrorStrings ERROR = GWT.create(CommonUIErrorStrings.class);
    
    /** Validation messages */
    public static final CommonUiValidationMessages VALIDATION = GWT.create(CommonUiValidationMessages.class);
    
}