package org.iplantc.core.uicommons.client.collaborators.views;

import java.util.List;

import org.iplantc.core.uicommons.client.I18N;
import org.iplantc.core.uicommons.client.collaborators.models.Collaborator;
import org.iplantc.core.uicommons.client.collaborators.presenter.ManageCollaboratorsPresenter;
import org.iplantc.core.uicommons.client.collaborators.presenter.ManageCollaboratorsPresenter.MODE;
import org.iplantc.core.uicommons.client.collaborators.util.CollaboratorsUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.Style.LayoutRegion;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class ManageCollaboratorsViewImpl extends Composite implements ManageCollaboratorsView {

    Presenter presenter;

    @UiField(provided = true)
    final ListStore<Collaborator> listStore;

    @UiField(provided = true)
    final ColumnModel<Collaborator> cm;

    @UiField
    Grid<Collaborator> grid;

    @UiField
    TextButton searchBtn;

    @UiField
    TextButton showCollabsBtn;

    @UiField
    TextButton addBtn;

    @UiField
    TextButton deleteBtn;

    @UiField
    TextButton manageBtn;

    @UiField
    TextField searchField;

    @UiField
    FramedPanel collaboratorListPnl;

    @UiField
    HorizontalLayoutContainer searchPanel;

    @UiField
    BorderLayoutContainer con;

    @UiField
    ToolBar toolbar;

    private final Widget widget;

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    private ManageCollaboratorsPresenter.MODE mode;

    private final class SearchFieldKeyPressDownImpl implements KeyDownHandler {
        @Override
        public void onKeyDown(KeyDownEvent event) {
            if (event.getNativeKeyCode() == 13) {
                submitSearch(null);
            }
        }
    }

    @UiTemplate("ManageCollaboratorsView.ui.xml")
    interface MyUiBinder extends UiBinder<Widget, ManageCollaboratorsViewImpl> {
    }

    public ManageCollaboratorsViewImpl(CheckBoxSelectionModel<Collaborator> checkBoxModel,
            ColumnModel<Collaborator> cm, final ListStore<Collaborator> store, MODE mode) {
        this.cm = cm;
        this.listStore = store;
        widget = uiBinder.createAndBindUi(this);
        grid.setSelectionModel(checkBoxModel);
        grid.getSelectionModel().setSelectionMode(SelectionMode.MULTI);
        grid.getView().setEmptyText(I18N.DISPLAY.noCollaborators());
        init();
        setMode(mode);
    }

    private void init() {
        searchField.addKeyDownHandler(new SearchFieldKeyPressDownImpl());
        searchField.setAutoValidate(true);
        collaboratorListPnl.setHeadingText(I18N.DISPLAY.myCollaborators());
        grid.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<Collaborator>() {

            @Override
            public void onSelectionChanged(SelectionChangedEvent<Collaborator> event) {
                if (event.getSelection() != null && event.getSelection().size() > 0) {
                    if (mode.equals(MODE.MANAGE)) {
                        deleteBtn.setVisible(true);
                    } else {
                        deleteBtn.setVisible(false);
                    }

                    if (mode.equals(MODE.SEARCH)) {
                        addBtn.setVisible(true);
                    } else {
                        addBtn.setVisible(false);
                    }

                    if (mode.equals(MODE.SELECT)) {
                        manageBtn.setVisible(true);
                        addBtn.setVisible(false);
                        deleteBtn.setVisible(false);
                    } else {
                        manageBtn.setVisible(false);
                    }

                } else {
                    deleteBtn.setVisible(false);
                    addBtn.setVisible(false);
                }

            }
        });
    }

    @UiHandler("searchBtn")
    void submitSearch(SelectEvent event) {
        String searchTerm = searchField.getCurrentValue();
        searchField.clearInvalid();
        if (searchTerm != null && !searchTerm.isEmpty() && searchTerm.length() > 2) {
            collaboratorListPnl.setHeadingText(I18N.DISPLAY.search() + ": " + searchTerm);
            presenter.searchUsers(searchTerm);
        } else {
            searchField.forceInvalid("3 chars minimum");

        }
    }
    
    @UiHandler("manageBtn")
    void manageCollaborators(SelectEvent event) {
        setMode(MODE.MANAGE);
    }

    @UiHandler("addBtn")
    void addCollaborator(SelectEvent event) {
        presenter.addAsCollaborators(grid.getSelectionModel().getSelectedItems());
    }

    @UiHandler("deleteBtn")
    void deleteCollaborator(SelectEvent event) {
        presenter.removeFromCollaborators(grid.getSelectionModel().getSelectedItems());
    }

    @UiHandler("showCollabsBtn")
    void showCurrentCollaborators(SelectEvent event) {
        loadData(CollaboratorsUtil.getCurrentCollaborators());
        setMode(MODE.MANAGE);

        showCollabsBtn.setVisible(false);
    }

    @Override
    public void setMode(ManageCollaboratorsPresenter.MODE mode) {
        this.mode = mode;
        switch (mode) {
            case MANAGE:
                grid.getView().setEmptyText(I18N.DISPLAY.noCollaborators());
                collaboratorListPnl.setHeadingText(I18N.DISPLAY.myCollaborators());
                showCollabsBtn.setVisible(false);
                manageBtn.setVisible(false);
                con.show(LayoutRegion.NORTH);
                break;
            case SEARCH:
                grid.getView().setEmptyText(I18N.DISPLAY.noCollaboratorsSearchResult());
                showCollabsBtn.setVisible(true);
                con.show(LayoutRegion.NORTH);
                manageBtn.setVisible(false);
                break;
            case SELECT:
                grid.getView().setEmptyText(I18N.DISPLAY.noCollaborators());
                con.hide(LayoutRegion.NORTH);
                manageBtn.setVisible(true);
                collaboratorListPnl.setHeadingText("Select Collaborators");
                break;
        }
    }

    @Override
    public void setPresenter(Presenter p) {
        this.presenter = p;
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public void loadData(List<Collaborator> models) {
        listStore.clear();
        listStore.addAll(models);
    }

    @Override
    public void mask(String maskText) {
        if (maskText == null || maskText.isEmpty()) {
            collaboratorListPnl.mask(I18N.DISPLAY.loadingMask());
        } else {
            collaboratorListPnl.mask(maskText);
        }
    }

    @Override
    public void unmask() {
        collaboratorListPnl.unmask();
    }

    @Override
    public void removeCollaborators(List<Collaborator> models) {
        if (models != null && !models.isEmpty()) {
            for (Collaborator c : models) {
                if (listStore.findModel(c) != null) {
                    listStore.remove(c);
                }
            }
        }
    }

    @Override
    public MODE getMode() {
        return mode;
    }

    @Override
    public List<Collaborator> getSelectedCollaborators() {
        return grid.getSelectionModel().getSelectedItems();
    }

}
