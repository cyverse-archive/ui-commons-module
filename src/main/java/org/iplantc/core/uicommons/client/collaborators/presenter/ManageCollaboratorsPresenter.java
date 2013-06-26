/**
 * 
 */
package org.iplantc.core.uicommons.client.collaborators.presenter;

import java.util.Arrays;
import java.util.List;

import org.iplantc.core.resources.client.messages.I18N;
import org.iplantc.core.uicommons.client.ErrorHandler;
import org.iplantc.core.uicommons.client.collaborators.events.UserSearchResultSelected;
import org.iplantc.core.uicommons.client.collaborators.models.Collaborator;
import org.iplantc.core.uicommons.client.collaborators.util.CollaboratorsUtil;
import org.iplantc.core.uicommons.client.collaborators.views.ManageCollaboratorsView;
import org.iplantc.core.uicommons.client.collaborators.views.ManageCollaboratorsView.Presenter;
import org.iplantc.core.uicommons.client.events.EventBus;
import org.iplantc.core.uicommons.client.info.ErrorAnnouncementConfig;
import org.iplantc.core.uicommons.client.info.IplantAnnouncer;
import org.iplantc.core.uicommons.client.models.UserInfo;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasOneWidget;

/**
 * @author sriram
 * 
 */
public class ManageCollaboratorsPresenter implements Presenter {

    private final ManageCollaboratorsView view;

    public static enum MODE {
        MANAGE, SELECT
    };

    public ManageCollaboratorsPresenter(ManageCollaboratorsView view) {
        this.view = view;
        view.setPresenter(this);
        loadCurrentCollaborators();
        addEventHandlers();
    }

    private void addEventHandlers() {
        EventBus.getInstance().addHandler(UserSearchResultSelected.TYPE, new UserSearchResultSelected.UserSearchResultSelectedEventHandler() {
            
            @Override
            public void onUserSearchResultSelected(UserSearchResultSelected userSearchResultSelected) {
                        if (userSearchResultSelected.getTag().equalsIgnoreCase(
                                UserSearchResultSelected.USER_SEARCH_EVENT_TAG.MANAGE.toString())) {
                            Collaborator collaborator = userSearchResultSelected.getCollaborator();
                            if(!UserInfo.getInstance().getUsername().equals(collaborator.getUserName())) {
                                addAsCollaborators(Arrays.asList(collaborator));
                            } else {
                                IplantAnnouncer.getInstance().schedule(I18N.DISPLAY.collaboratorSelfAdd(), new ErrorAnnouncementConfig());
                            }
                        }
                
            }
                });
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.iplantc.core.uicommons.client.presenter.Presenter#go(com.google.gwt.user.client.ui.HasOneWidget
     * )
     */
    @Override
    public void go(HasOneWidget container) {
        container.setWidget(view.asWidget());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.iplantc.de.client.collaborators.views.ManageCollaboratorsView.Presenter#addAsCollaborators
     * (java.util.List)
     */
    @Override
    public void addAsCollaborators(final List<Collaborator> models) {
        CollaboratorsUtil.addCollaborators(models, new AsyncCallback<Void>() {

            @Override
            public void onSuccess(Void result) {
                // remove added models from search results
                view.addCollaborators(models);
            }

            @Override
            public void onFailure(Throwable caught) {
                ErrorHandler.post(caught);
            }
        });

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.iplantc.de.client.collaborators.views.ManageCollaboratorsView.Presenter#removeFromCollaborators
     * (java.util.List)
     */
    @Override
    public void removeFromCollaborators(final List<Collaborator> models) {
        CollaboratorsUtil.removeCollaborators(models, new AsyncCallback<Void>() {

            @Override
            public void onSuccess(Void result) {
                view.removeCollaborators(models);

            }

            @Override
            public void onFailure(Throwable caught) {
                ErrorHandler.post(caught);
            }
        });

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.iplantc.de.client.collaborators.views.ManageCollaboratorsView.Presenter#loadCurrentCollaborators
     * ()
     */
    @Override
    public void loadCurrentCollaborators() {
        view.mask(null);
        CollaboratorsUtil.getCollaborators(new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable caught) {
                view.unmask();
            }

            @Override
            public void onSuccess(Void result) {
                view.unmask();
                view.loadData(CollaboratorsUtil.getCurrentCollaborators());
            }

        });

    }


    @Override
    public void setCurrentMode(MODE m) {
        view.setMode(m);
    }

    @Override
    public MODE getCurrentMode() {
        return view.getMode();
    }

    @Override
    public List<Collaborator> getSelectedCollaborators() {
        return view.getSelectedCollaborators();
    }

}
