package org.iplantc.core.uicommons.client.models;

import org.iplantc.core.jsonutil.JsonUtil;

import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

/**
 * 
 * A singleton hold user general settings
 * 
 * @author sriram
 * 
 */
public class UserSettings {

    private boolean enableEmailNotification;
    private String defaultFileSelectorPath;

    public static final String EMAIL_NOTIFCATOIN = "enableEmailNotification";
    public static final String DEFAULT_FIFLE_SELECTOR_APTH = "defaultFileSelectorPath";

    private static UserSettings instance;

    private UserSettings() {
        this.enableEmailNotification = false;
        this.defaultFileSelectorPath = "";
    }

    public static UserSettings getInstance() {
        if (instance == null) {
            instance = new UserSettings();
        }

        return instance;
    }

    public void setValues(JSONObject obj) {
        setEnableEmailNotification(JsonUtil.getBoolean(obj, EMAIL_NOTIFCATOIN, false));
        setDefaultFileSelectorPath(JsonUtil.getString(obj, DEFAULT_FIFLE_SELECTOR_APTH));
    }

    /**
     * @param enableEmailNotification the enableEmailNotification to set
     */
    public void setEnableEmailNotification(boolean enableEmailNotification) {
        this.enableEmailNotification = enableEmailNotification;
    }

    /**
     * @return the enableEmailNotification
     */
    public boolean isEnableEmailNotification() {
        return enableEmailNotification;
    }

    /**
     * @param defaultFileSelectorPath the defaultFileSelectorPath to set
     */
    public void setDefaultFileSelectorPath(String defaultFileSelectorPath) {
        this.defaultFileSelectorPath = defaultFileSelectorPath;
    }

    /**
     * @return the defaultFileSelectorPath
     */
    public String getDefaultFileSelectorPath() {
        return defaultFileSelectorPath;
    }

    /**
     * Get json representation
     * 
     * @return JSONObject json representation of this obbject
     */
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put(EMAIL_NOTIFCATOIN, JSONBoolean.getInstance(isEnableEmailNotification()));
        obj.put(DEFAULT_FIFLE_SELECTOR_APTH, new JSONString(getDefaultFileSelectorPath()));
        return obj;
    }

}
