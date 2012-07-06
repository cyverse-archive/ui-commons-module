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
    private boolean rememberLastPath;
    private boolean saveSession;
    private String defaultOutputFolder;

    public static final String EMAIL_NOTIFCATOIN = "enableEmailNotification";
    public static final String DEFAULT_FIFLE_SELECTOR_APTH = "defaultFileSelectorPath";
    public static final String REMEMBER_LAST_PATH = "rememberLastPath";
    public static final String SAVE_SESSION = "saveSession";
    public static final String DEFAULT_OUTPUT_FOLDER = "defaultOutputFolder";

    private static UserSettings instance;

    private UserSettings() {
        this.enableEmailNotification = false;
        this.defaultFileSelectorPath = "";
        this.rememberLastPath = false;
        this.saveSession = true;
        this.defaultOutputFolder = "";
    }

    public static UserSettings getInstance() {
        if (instance == null) {
            instance = new UserSettings();
        }

        return instance;
    }

    public void setValues(JSONObject obj) {
        if (obj != null) {
            setEnableEmailNotification(JsonUtil.getBoolean(obj, EMAIL_NOTIFCATOIN, true));
            setDefaultFileSelectorPath(JsonUtil.getString(obj, DEFAULT_FIFLE_SELECTOR_APTH));
            setRememberLastPath(JsonUtil.getBoolean(obj, REMEMBER_LAST_PATH, true));
            setSaveSession(JsonUtil.getBoolean(obj, SAVE_SESSION, true));
            setDefaultOutputFolder(JsonUtil.getString(obj, DEFAULT_OUTPUT_FOLDER));
        }
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
        return (defaultFileSelectorPath == null) ? "" : defaultFileSelectorPath;
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
        obj.put(REMEMBER_LAST_PATH, JSONBoolean.getInstance(isRememberLastPath()));
        obj.put(SAVE_SESSION, JSONBoolean.getInstance(isSaveSession()));
        obj.put(DEFAULT_OUTPUT_FOLDER, new JSONString(getDefaultOutputFolder()));
        return obj;
    }

    /**
     * @param rememberLastPath the rememberLastPath to set
     */
    public void setRememberLastPath(boolean rememberLastPath) {
        this.rememberLastPath = rememberLastPath;
    }

    /**
     * @return the rememberLastPath
     */
    public boolean isRememberLastPath() {
        return rememberLastPath;
    }

    /**
     * 
     * 
     * @param saveSession
     */
    public void setSaveSession(boolean saveSession) {
        this.saveSession = saveSession;
    }

    public boolean isSaveSession() {
        return saveSession;
    }

    /**
     * @param defaultOutputFolder the new default output folder.
     */
    public void setDefaultOutputFolder(String defaultOutputFolder) {
        this.defaultOutputFolder = defaultOutputFolder;
    }

    /**
     * @return the default output folder.
     */
    public String getDefaultOutputFolder() {
        return defaultOutputFolder;
    }
}
