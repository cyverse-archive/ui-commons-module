/**
 * 
 */
package org.iplantc.core.uicommons.client.collaborators.views;

import java.util.List;

import org.iplantc.core.uicommons.client.collaborators.models.Collaborator;
import org.iplantc.core.uicommons.client.collaborators.presenter.ManageCollaboratorsPresenter.MODE;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author sriram
 * 
 */
public interface ManageCollaboratorsView extends IsWidget {

    public interface Presenter extends org.iplantc.core.uicommons.client.presenter.Presenter {

        void addAsCollaborators(List<Collaborator> models);

        void removeFromCollaborators(List<Collaborator> models);

        void loadCurrentCollaborators();

        void setCurrentMode(MODE mode);

        MODE getCurrentMode();

        List<Collaborator> getSelectedCollaborators();

    }

    void setPresenter(Presenter p);

    void loadData(List<Collaborator> models);

    void removeCollaborators(List<Collaborator> models);

    void mask(String maskText);

    void unmask();

    void setMode(MODE mode);

    List<Collaborator> getSelectedCollaborators();

    MODE getMode();

    void addCollaborators(List<Collaborator> models);
}
