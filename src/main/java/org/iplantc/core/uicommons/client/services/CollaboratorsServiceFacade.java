/**
 * 
 */
package org.iplantc.core.uicommons.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.json.client.JSONObject;
/**
 * @author sriram
 *
 */
public interface CollaboratorsServiceFacade {
    public void searchCollaborators(String term, AsyncCallback<String> callback) ;
      
    public void getCollaborators(AsyncCallback<String> callback);

    public void addCollaborators(JSONObject users, AsyncCallback<String> callback);

    public void removeCollaborators(JSONObject users, AsyncCallback<String> callback);

    public void getUserInfo(List<String> usernames, AsyncCallback<String> callback);

}
