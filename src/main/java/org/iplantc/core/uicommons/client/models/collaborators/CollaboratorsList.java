/**
 * 
 */
package org.iplantc.core.uicommons.client.models.collaborators;

import java.util.List;

import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;

/**
 * @author sriram
 * 
 */
public interface CollaboratorsList {

    @PropertyName("users")
    public List<Collaborator> getCollaborators();
}
