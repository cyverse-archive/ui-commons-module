package org.iplantc.core.uicommons.client;

import com.google.gwt.core.client.GWT;

/**
 * Static access to client constants.
 * 
 * @author hariolf
 * 
 */
public class Constants {
    /** CommonConstants, auto-populated from .properties by GWT */
    public static final CommonUiConstants CLIENT = (CommonUiConstants)GWT
            .create(CommonUiConstants.class);
}
