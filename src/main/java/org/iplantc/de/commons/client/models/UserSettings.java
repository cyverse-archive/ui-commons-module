package org.iplantc.de.commons.client.models;

import org.iplantc.de.jsonutil.client.JsonUtil;
import org.iplantc.de.commons.client.Constants;
import org.iplantc.de.commons.client.models.diskresources.DiskResourceAutoBeanFactory;
import org.iplantc.de.commons.client.models.diskresources.Folder;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;

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
    private Folder defaultOutputFolder;
    private String dataShortCut;
    private String appShortCut;
    private String analysesShortCut;
    private String notifyShortCut;
    private String closeShortCut;
    private Folder systemDefaultOutputFolder;
    private String lastPathId;
    
    
    public static final String EMAIL_NOTIFCATOIN = "enableEmailNotification";
    public static final String DEFAULT_FILE_SELECTOR_APTH = "defaultFileSelectorPath";
    public static final String REMEMBER_LAST_PATH = "rememberLastPath";
    public static final String SAVE_SESSION = "saveSession";
    public static final String DEFAULT_OUTPUT_FOLDER = "defaultOutputFolder";
    public static final String DATA_KB_SHORTCUT = "dataKBShortcut";
    public static final String APPS_KB_SHORTCUT = "appsKBShortcut";
    public static final String ANALYSIS_KB_SHORTCUT = "analysisKBShortcut";
    public static final String NOTIFICATION_KB_SHORTCUT = "notificationKBShortcut";
    public static final String CLOSE_KB_SHORTCUT_STRING = "closeKBShortcut";
    public static final String SYSTEM_DEFAULT_OUTPUT_DIR = "systemDefaultOutputDir";
    public static final String LAST_PATH_ID = "lastPathId";


    private static UserSettings instance;

    private UserSettings() {
        this.enableEmailNotification = false;
        this.rememberLastPath = false;
        this.saveSession = true;
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
            setDefaultFileSelectorPath(JsonUtil.getString(obj, DEFAULT_FILE_SELECTOR_APTH));
            setRememberLastPath(JsonUtil.getBoolean(obj, REMEMBER_LAST_PATH, true));
            setSaveSession(JsonUtil.getBoolean(obj, SAVE_SESSION, true));
            setDefaultOutputFolder(buildFolder(JsonUtil.getObject(obj, DEFAULT_OUTPUT_FOLDER)));
            setSystemDefaultOutputFolder(buildFolder(JsonUtil.getObject(obj,SYSTEM_DEFAULT_OUTPUT_DIR)));
            setLastPathId(JsonUtil.getString(obj, LAST_PATH_ID));
            parseDataShortCut(obj);
            parseAnalysisShortCut(obj);
            parseAppsShortCut(obj);
            parseNotifyShortCut(obj);
            parseCloseShortcut(obj);
        }
    }


    private void parseCloseShortcut(JSONObject obj) {
        JSONValue temp = obj.get(CLOSE_KB_SHORTCUT_STRING);
        if (temp != null) {
            String stringValue = temp.isString().stringValue();
            if (stringValue != null) {
                setCloseShortCut(stringValue);
            } else {
                setCloseShortCut(Constants.CLIENT.closeKeyShortCut());
            }
        } else {
            setCloseShortCut(Constants.CLIENT.closeKeyShortCut());
        }

    }

    private void parseDataShortCut(JSONObject obj) {
        JSONValue temp = obj.get(DATA_KB_SHORTCUT);
        if (temp != null) {
            String stringValue = temp.isString().stringValue();
            if (stringValue != null) {
                setDataShortCut(stringValue);
            } else {
                setDataShortCut(Constants.CLIENT.dataKeyShortCut());
            }
        } else {
            setDataShortCut(Constants.CLIENT.dataKeyShortCut());
        }
    }
    
    private void parseAppsShortCut(JSONObject obj) {
        JSONValue temp = obj.get(APPS_KB_SHORTCUT);
        if (temp != null) {
            String stringValue = temp.isString().stringValue();
            if (stringValue != null) {
                setAppsShortCut(stringValue);
            } else {
                setAppsShortCut(Constants.CLIENT.appsKeyShortCut());
            }
        } else {
            setAppsShortCut(Constants.CLIENT.appsKeyShortCut());
        }

    }

    private void parseAnalysisShortCut(JSONObject obj) {
        JSONValue temp = obj.get(ANALYSIS_KB_SHORTCUT);
        if (temp != null) {
            String stringValue = temp.isString().stringValue();
            if (stringValue != null) {
                setAnalysesShortCut(stringValue);
            } else {
                setAnalysesShortCut(Constants.CLIENT.analysisKeyShortCut());
            }
        } else {
            setAnalysesShortCut(Constants.CLIENT.analysisKeyShortCut());
        }

    }

    private void parseNotifyShortCut(JSONObject obj) {
        JSONValue temp = obj.get(NOTIFICATION_KB_SHORTCUT);
        if (temp != null) {
            String stringValue = temp.isString().stringValue();
            if (stringValue != null) {
                setNotifiShortCut(stringValue);
            } else {
                setNotifiShortCut(Constants.CLIENT.notifyKeyShortCut());
            }
        } else {
            setNotifiShortCut(Constants.CLIENT.notifyKeyShortCut());
        }

    }

    public void setDataShortCut(String c) {
        this.dataShortCut = c;
        
    }

    public String getDataShortCut() {
        return dataShortCut;
    }

    public void setAppsShortCut(String c) {
        this.appShortCut = c;
    }

    public String getAppsShortCut() {
        return appShortCut;
    }

    public void setAnalysesShortCut(String c) {
        this.analysesShortCut = c;
    }

    public String getAnalysesShortCut() {
        return analysesShortCut;
    }

    public void setNotifiShortCut(String c) {
        this.notifyShortCut = c;
    }

    public String getNotifiShortCut() {
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
        obj.put(DEFAULT_FILE_SELECTOR_APTH, new JSONString(getDefaultFileSelectorPath()));
        obj.put(REMEMBER_LAST_PATH, JSONBoolean.getInstance(isRememberLastPath()));
        obj.put(SAVE_SESSION, JSONBoolean.getInstance(isSaveSession()));
        obj.put(DEFAULT_OUTPUT_FOLDER, JsonUtil.getObject(AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(getDefaultOutputFolder())).getPayload()));
        obj.put(APPS_KB_SHORTCUT, new JSONString(getAppsShortCut()));
        obj.put(ANALYSIS_KB_SHORTCUT, new JSONString(getAnalysesShortCut()));
        obj.put(DATA_KB_SHORTCUT, new JSONString(getDataShortCut()));
        obj.put(NOTIFICATION_KB_SHORTCUT, new JSONString(getNotifiShortCut()));
        obj.put(CLOSE_KB_SHORTCUT_STRING, new JSONString(getCloseShortCut()));
        obj.put(LAST_PATH_ID, new JSONString(getLastPathId()));
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
    public void setDefaultOutputFolder(Folder defaultOutputFolder) {
        this.defaultOutputFolder = defaultOutputFolder;
    }

    private Folder buildFolder(JSONObject defaultOutputFolder) {
        DiskResourceAutoBeanFactory factory = GWT.create(DiskResourceAutoBeanFactory.class);
        AutoBean<Folder> FolderBean = factory.folder();
        Folder folder = FolderBean.as();
        folder.setId(JsonUtil.getString(defaultOutputFolder, "id"));
        folder.setPath(JsonUtil.getString(defaultOutputFolder, "path"));
        return folder;
    }

    /**
     * @return the default output folder.
     */
    public Folder getDefaultOutputFolder() {
        return defaultOutputFolder;
    }

    /**
     * @return the closeShortCut
     */
    public String getCloseShortCut() {
        return closeShortCut;
    }

    /**
     * @param closeShortCut the closeShortCut to set
     */
    public void setCloseShortCut(String closeShortCut) {
        this.closeShortCut = closeShortCut;
    }

    /**
     * @return the systemDefaultOutputFolder
     */
    public Folder getSystemDefaultOutputFolder() {
        return systemDefaultOutputFolder;
    }

    /**
     * @param systemDefaultOutputFolder the systemDefaultOutputFolder to set
     */
    public void setSystemDefaultOutputFolder(Folder systemDefaultOutputFolder) {
        this.systemDefaultOutputFolder = systemDefaultOutputFolder;
    }

    /**
     * @return the lastPathId
     */
    public String getLastPathId() {
        return lastPathId;
    }

    /**
     * @param lastPathId the lastPathId to set
     */
    public void setLastPathId(String lastPathId) {
        this.lastPathId = lastPathId;
    }
}
