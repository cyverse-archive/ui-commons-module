package org.iplantc.core.uicommons.client.models;

import org.iplantc.core.jsonutil.JsonUtil;
import org.iplantc.core.uicommons.client.Constants;

import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

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
    private Character dataShortCut;
    private Character appShortCut;
    private Character analysesShortCut;
    private Character notifyShortCut;
    private Character closeShortCut;

    public static final String EMAIL_NOTIFCATOIN = "enableEmailNotification";
    public static final String DEFAULT_FIFLE_SELECTOR_APTH = "defaultFileSelectorPath";
    public static final String REMEMBER_LAST_PATH = "rememberLastPath";
    public static final String SAVE_SESSION = "saveSession";
    public static final String DEFAULT_OUTPUT_FOLDER = "defaultOutputFolder";
    public static final String DATA_KB_SHORTCUT = "dataKBShortcut";
    public static final String APPS_KB_SHORTCUT = "appsKBShortcut";
    public static final String ANALYSIS_KB_SHORTCUT = "analysisKBShortcut";
    public static final String NOTIFICATION_KB_SHORTCUT = "notificationKBShortcut";
    public static final String CLOSE_KB_SHORTCU_STRING = "closeKBShortcut";


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
            parseDataShortCut(obj);
            parseAnalysisShortCut(obj);
            parseAppsShortCut(obj);
            parseNotifyShortCut(obj);
            parseCloseShortcut(obj);
        }
    }


    private void parseCloseShortcut(JSONObject obj) {
        JSONValue temp = obj.get(CLOSE_KB_SHORTCU_STRING);
        if (temp != null) {
            String stringValue = temp.isString().stringValue();
            if (stringValue != null) {
                setCloseShortCut(stringValue.charAt(0));
            } else {
                setCloseShortCut(Constants.CLIENT.closeKeyShortCut().charAt(0));
            }
        } else {
            setCloseShortCut(Constants.CLIENT.closeKeyShortCut().charAt(0));
        }

    }

    private void parseDataShortCut(JSONObject obj) {
        JSONValue temp = obj.get(DATA_KB_SHORTCUT);
        if (temp != null) {
            String stringValue = temp.isString().stringValue();
            if (stringValue != null) {
                setDataShortCut(stringValue.charAt(0));
            } else {
                setDataShortCut(Constants.CLIENT.dataKeyShortCut().charAt(0));
            }
        } else {
            setDataShortCut(Constants.CLIENT.dataKeyShortCut().charAt(0));
        }
    }
    
    private void parseAppsShortCut(JSONObject obj) {
        JSONValue temp = obj.get(APPS_KB_SHORTCUT);
        if (temp != null) {
            String stringValue = temp.isString().stringValue();
            if (stringValue != null) {
                setAppsShortCut(stringValue.charAt(0));
            } else {
                setAppsShortCut(Constants.CLIENT.appsKeyShortCut().charAt(0));
            }
        } else {
            setAppsShortCut(Constants.CLIENT.appsKeyShortCut().charAt(0));
        }

    }

    private void parseAnalysisShortCut(JSONObject obj) {
        JSONValue temp = obj.get(ANALYSIS_KB_SHORTCUT);
        if (temp != null) {
            String stringValue = temp.isString().stringValue();
            if (stringValue != null) {
                setAnalysesShortCut(stringValue.charAt(0));
            } else {
                setAnalysesShortCut(Constants.CLIENT.analysisKeyShortCut().charAt(0));
            }
        } else {
            setAnalysesShortCut(Constants.CLIENT.analysisKeyShortCut().charAt(0));
        }

    }

    private void parseNotifyShortCut(JSONObject obj) {
        JSONValue temp = obj.get(NOTIFICATION_KB_SHORTCUT);
        if (temp != null) {
            String stringValue = temp.isString().stringValue();
            if (stringValue != null) {
                setNotifiShortCut(stringValue.charAt(0));
            } else {
                setNotifiShortCut(Constants.CLIENT.notifyKeyShortCut().charAt(0));
            }
        } else {
            setNotifiShortCut(Constants.CLIENT.notifyKeyShortCut().charAt(0));
        }

    }

    public void setDataShortCut(Character c) {
        this.dataShortCut = c;
        
    }

    public Character getDataShortCut() {
        return dataShortCut;
    }

    public void setAppsShortCut(Character c) {
        this.appShortCut = c;
    }

    public Character getAppsShortCut() {
        return appShortCut;
    }

    public void setAnalysesShortCut(Character c) {
        this.analysesShortCut = c;
    }

    public Character getAnalysesShortCut() {
        return analysesShortCut;
    }

    public void setNotifiShortCut(Character c) {
        this.notifyShortCut = c;
    }

    public Character getNotifiShortCut() {
        return notifyShortCut;
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

    /**
     * @return the closeShortCut
     */
    public Character getCloseShortCut() {
        return closeShortCut;
    }

    /**
     * @param closeShortCut the closeShortCut to set
     */
    public void setCloseShortCut(Character closeShortCut) {
        this.closeShortCut = closeShortCut;
    }
}
