package org.iplantc.core.uicommons.client.models;

import org.iplantc.core.jsonutil.JsonUtil;

import com.google.gwt.json.client.JSONObject;

/**
 * Holds all the information about an user.
 * 
 * Note: init() must be called when using this class for the first time in the application.
 * 
 * @author sriram
 * 
 */
public class UserInfo {
    /**
     * Defines an attribute for User ID.
     */
    public static String ATTR_UID = "shibbolethUid";
    /**
     * Defines an attribute for User Email
     */
    public static String ATTR_EMAIL = "shibbolethMail";
    /**
     * Defines an attribute for the fully qualified username.
     */
    public static String ATTR_USERNAME = "shibbolethEppn";

    private static UserInfo instance;

    private String workspaceId;
    private String email;
    private String username;
    private String fullUsername;

    /**
     * Constructs a default instance of the object with all fields being set to null.
     */
    private UserInfo() {
        workspaceId = null;
        email = null;
    }

    /**
     * Gets the username for the user.
     * 
     * This value corresponds to an entry in LDAP.
     * 
     * @return a string representing the username for the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for the user.
     * 
     * @param usr a string representing the username
     */
    public void setUsername(String usr) {
        this.username = usr;
    }

    /**
     * Gets the workspace id for the user.
     * 
     * @return a string representing the identifier for workspace.
     */
    public String getWorkspaceId() {
        return workspaceId;
    }

    /**
     * Get an instance of UserInfo.
     * 
     * @return a singleton instance of the object.
     */
    public static UserInfo getInstance() {
        if (instance == null) {
            instance = new UserInfo();
        }

        return instance;
    }

    /**
     * Initializes UserInfo object.
     * 
     * This method must be called before using any other member functions of this class
     * 
     * @param userInfoJson json to initialize user info.
     */
    public void init(String userInfoJson) {
        if (userInfoJson != null && !userInfoJson.equals("")) {
            JSONObject obj = JsonUtil.getObject(userInfoJson);
            workspaceId = JsonUtil.getString(obj, "workspaceId");
        }
    }

    /**
     * Get user's email address.
     * 
     * @return email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set user's email address.
     * 
     * @param email email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the full username.
     * 
     * @return the fully qualified username.
     */
    public String getFullUsername() {
        return fullUsername;
    }

    /**
     * Sets the full username.
     * 
     * @param fullUsername the fully qualified username.
     */
    public void setFullUsername(String fullUsername) {
        this.fullUsername = fullUsername;
    }
}