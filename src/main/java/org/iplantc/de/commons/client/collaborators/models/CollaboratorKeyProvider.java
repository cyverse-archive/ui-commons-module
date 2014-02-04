/**
 * 
 */
package org.iplantc.de.commons.client.collaborators.models;

import com.sencha.gxt.data.shared.ModelKeyProvider;

public class CollaboratorKeyProvider implements ModelKeyProvider<Collaborator> {

    @Override
    public String getKey(Collaborator item) {
        return item.getId();
    }

}
