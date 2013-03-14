/**
 * 
 */
package org.iplantc.core.uicommons.client.collaborators.presenter;

import java.util.List;

import org.iplantc.core.uicommons.client.ErrorHandler;
import org.iplantc.core.uicommons.client.I18N;
import org.iplantc.core.uicommons.client.collaborators.models.Collaborator;
import org.iplantc.core.uicommons.client.collaborators.util.CollaboratorsUtil;
import org.iplantc.core.uicommons.client.collaborators.views.ManageCollaboratorsView;
import org.iplantc.core.uicommons.client.collaborators.views.ManageCollaboratorsView.Presenter;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasOneWidget;

/**
 * @author sriram
 * 
 */
public class ManageCollaboratorsPresenter implements Presenter {

    private final ManageCollaboratorsView view;

    public static enum MODE {
        MANAGE, SEARCH, SELECT
    };

    public ManageCollaboratorsPresenter(ManageCollaboratorsView view) {
        this.view = view;
        view.setPresenter(this);
        loadCurrentCollaborators();
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.iplantc.de.client.collaborators.views.ManageCollaboratorsView.Presenter#searchUsers(java.lang
     * .String)
     */
    @Override
    public void searchUsers(String searchTerm) {
        view.mask(I18N.DISPLAY.searching());
        CollaboratorsUtil.search(searchTerm, new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable caught) {
                view.unmask();
            }

            @Override
            public void onSuccess(Void result) {
                setCurrentMode(MODE.SEARCH);
                view.unmask();
                view.loadData(CollaboratorsUtil.getSearchResutls());
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
