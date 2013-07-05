package org.iplantc.core.uicommons.client.services;

import org.iplantc.core.uicommons.client.models.toolrequests.RequestedToolDetails;
import org.iplantc.core.uicommons.client.models.toolrequests.NewToolRequest;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Objects of this type can provide the tool request remote services.
 */
public interface ToolRequestProvider {

    /**
     * Asynchronously requests the installation of a tool.
     * 
     * @param request the tool installation request
     * @param callback the callback with the response from the provider
     */
    void requestInstallation(NewToolRequest request, AsyncCallback<RequestedToolDetails> callback);
    
}
