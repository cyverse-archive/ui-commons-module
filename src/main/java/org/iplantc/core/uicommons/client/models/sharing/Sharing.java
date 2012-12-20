package org.iplantc.core.uicommons.client.models.sharing;

import org.iplantc.core.uicommons.client.models.collaborators.Collaborator;

/**
 * 
 * @author sriram
 * 
 */
public class Sharing {

    private Collaborator collaborator;

    public Sharing(Collaborator c) {
        this.collaborator = c;

    }

    public String getUserName() {
        return collaborator.getUserName();
    }

//    public String getName() {
//        return collaborator.getName();
//    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Sharing)) {
            return false;
        }
        Sharing s = (Sharing)o;
        return getKey().equals(s.getKey());
    }

    /**
     * 
     * get the collaborator object
     * 
     * */
    public Collaborator getCollaborator() {
        return collaborator;
    }

    public String getKey() {
        return getUserName();
    }

    public Sharing copy() {
        return new Sharing(getCollaborator());
    }
}
