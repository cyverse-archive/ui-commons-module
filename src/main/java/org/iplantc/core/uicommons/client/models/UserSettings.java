package org.iplantc.core.uicommons.client.models;

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

    private static UserSettings instance;

    private UserSettings() {
        this.enableEmailNotification = false;
        this.defaultFileSelectorPath = null;
    }

    public static UserSettings getInstance() {
        if (instance == null) {
            instance = new UserSettings();
        }

        return instance;
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

}
