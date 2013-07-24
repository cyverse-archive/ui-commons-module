package org.iplantc.core.uicommons.client.models;

import java.util.List;

import org.iplantc.core.jsonutil.JsonUtil;

import com.google.gwt.json.client.JSONObject;
import  org.iplantc.core.uicommons.client.models.WindowState;

/**
 * Holds all the information about an user.
 *
 * Note: init() must be called when using this class for the first time in the application.
 *
 * @author sriram
 *
 */
@SuppressWarnings("nls")
public class UserInfo {
   
    /**
     * Defines an attribute for User Email
     */
    public static String ATTR_EMAIL = "email";

    /**
     * Defines an attribute for the fully qualified username.
     */
    public static String ATTR_USERNAME = "username";

    /**
     * Defines an attribute for the User's First Name.
     */
    public static String ATTR_FIRSTNAME = "firstName";

    /**
     * Defines an attribute for the User's Last Name.
     */
    public static String ATTR_LASTNAME = "lastName";

    /**
     * Defines an attribute for new user identification
     */
    public static String NEW_USER ="newWorkspace";

    /**
     * Defines an attribute for a users login Time
     *
     */
    public static String LOGIN_TIME = "loginTime";


    private static UserInfo instance;

    private String workspaceId;
    private String email;
    private String username;
    private String fullUsername;
    private String firstName;
    private String lastName;
    private String homePath;
    private String trashPath;
    private String loginTime;
    private Boolean newUser;
    private List<WindowState> savedOrderedWindowStates;

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
            newUser = JsonUtil.getBoolean(obj, NEW_USER, false);
            loginTime	= JsonUtil.getString(obj,LOGIN_TIME);
            setUsername(JsonUtil.getString(obj, UserInfo.ATTR_USERNAME));
            setEmail(JsonUtil.getString(obj, UserInfo.ATTR_EMAIL));
            setFullUsername(JsonUtil.getString(obj, UserInfo.ATTR_USERNAME));
            setFirstName(JsonUtil.getString(obj, UserInfo.ATTR_FIRSTNAME));
            setLastName(JsonUtil.getString(obj, UserInfo.ATTR_LASTNAME));
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

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the trashPath
     */
    public String getTrashPath() {
        return trashPath;
    }

    /**
     * @param trashPath the trashPath to set
     */
    public void setTrashPath(String trashPath) {
        this.trashPath = trashPath;
    }

    /**
     * @return the newUser
     */
    public Boolean isNewUser() {
        return newUser;
    }

    /**
     * @param newUser the newUser to set
     */
    public void setNewUser(Boolean newUser) {
        this.newUser = newUser;
    }

    /**
     * @return the savedOrderedWindowStates
     */
    public List<WindowState> getSavedOrderedWindowStates() {
        return savedOrderedWindowStates;
    }

    /**
     * @param savedOrderedWindowStates the savedOrderedWindowStates to set
     */
    public void setSavedOrderedWindowStates(List<WindowState> savedOrderedWindowStates) {
        this.savedOrderedWindowStates = savedOrderedWindowStates;
    }

    /**
     * @return the path to the user's home directory.
     */
    public String getHomePath() {
        return homePath;
    }

    /**
     * @param homePath the path to the user's home directory.
     */
    public void setHomePath(String homePath) {
        this.homePath = homePath;
    }

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
}

