/**
 * 
 */
package org.iplantc.core.uicommons.client.collaborators.models;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 * @author sriram
 * 
 */
public interface CollaboratorProperties extends PropertyAccess<Collaborator> {
    ValueProvider<Collaborator, String> email();

    ValueProvider<Collaborator, String> firstName();
}
