package org.iplantc.core.uicommons.client;

import org.iplantc.de.client.CommonErrorStrings;

/** Display strings that are shared between applications */
public interface CommonUIErrorStrings extends CommonErrorStrings {

    /**
     * Error message displayed when an favorite service fails
     * 
     * @return localized error string.
     */
    String favServiceFailure();

    /**
     * Error message displayed when the application fails to retrieve user info.
     * 
     * @return localized error string.
     */
    String retrieveUserInfoFailed();

    /**
     * Localized error message shown when no application exists for a specified ID.
     * 
     * @return string representing the text
     */
    String appNotFound();

    /**
     * Error message for display in the error dialog details when a service call fails.
     * 
     * @param errorName
     * @param errorMessage
     * @return localized error string.
     */
    String errorReport(String errorName, String errorMessage);

    /**
     * Error message to indicate that a session keepalive request failed.
     * 
     * @return the localized error message.
     */
    String keepaliveRequestFailed();

    /**
     * Localized text for App name field validation messages.
     * 
     * @return string representing the text
     */
    String invalidAppNameMsg(String invalidStartingCharSet, String invalidCharSet);

    /**
     * Localized error message to show when something goes wrong with confluence
     * 
     * @return
     */
    String confluenceError();

    /**
     * Error message displayed when the application fails to retrieve folder contents.
     * 
     * @return localized error string.
     */
    String retrieveFolderInfoFailed();
    
    /**
     * Error message for display in the error dialog details when a service call fails.
     * 
     * @param status
     * @return localized error string.
     */
    String serviceErrorStatus(String status);

    /**
     * Error message for display in the error dialog details when a service call fails.
     * 
     * @param code
     * @return localized error string.
     */
    String serviceErrorCode(String code);

    /**
     * Error message for display in the error dialog details when a service call fails.
     * 
     * @param reason
     * @return localized error string.
     */
    String serviceErrorReason(String reason);
}
