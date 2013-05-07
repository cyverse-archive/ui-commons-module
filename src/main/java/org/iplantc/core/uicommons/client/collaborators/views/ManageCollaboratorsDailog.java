package org.iplantc.core.uicommons.client.collaborators.views;

import java.util.ArrayList;
import java.util.List;

import org.iplantc.core.resources.client.messages.I18N;
import org.iplantc.core.uicommons.client.collaborators.models.Collaborator;
import org.iplantc.core.uicommons.client.collaborators.models.CollaboratorKeyProvider;
import org.iplantc.core.uicommons.client.collaborators.models.CollaboratorProperties;
import org.iplantc.core.uicommons.client.collaborators.presenter.ManageCollaboratorsPresenter;
import org.iplantc.core.uicommons.client.collaborators.presenter.ManageCollaboratorsPresenter.MODE;
import org.iplantc.core.uicommons.client.collaborators.views.ManageCollaboratorsView.Presenter;
import org.iplantc.core.uicommons.client.views.gxt3.dialogs.IPlantDialog;
import org.iplantc.core.uicommons.client.widgets.ContextualHelpPopup;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.HTML;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;

/**
 * @author sriram
 *
 */
public class ManageCollaboratorsDailog extends IPlantDialog {

    private CheckBoxSelectionModel<Collaborator> checkBoxModel;
    private CollaboratorProperties properties;
    private Presenter p;
    private ToolButton tool_help;

    public ManageCollaboratorsDailog(MODE mode) {
        initDialog();
        ListStore<Collaborator> store = new ListStore<Collaborator>(new CollaboratorKeyProvider());
        ColumnModel<Collaborator> cm = buildColumnModel();
        ManageCollaboratorsView view = new ManageCollaboratorsViewImpl(checkBoxModel, cm, store, mode);
        p = new ManageCollaboratorsPresenter(view);
        p.go(this);
    }



    private void initDialog() {
        properties = GWT.create(CollaboratorProperties.class);
        setPredefinedButtons(PredefinedButton.OK);
        setHeadingText(I18N.DISPLAY.collaborators());
        tool_help = new ToolButton(ToolButton.QUESTION);
        getHeader().addTool(tool_help);
        tool_help.addSelectHandler(new SelectHandler() {
            
            @Override
            public void onSelect(SelectEvent event) {
                ContextualHelpPopup popup = new ContextualHelpPopup();
                popup.add(new HTML(I18N.HELP.collaboratorsHelp()));
                popup.showAt(tool_help.getAbsoluteLeft(), tool_help.getAbsoluteTop() + 15);
            }
        });
        setPixelSize(450, 400);
        addOkButtonHandler();
        setHideOnButtonClick(true);
    }

    private void addOkButtonHandler() {
        addOkButtonSelectHandler(new SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                hide();
            }
        });
    }

    private ColumnModel<Collaborator> buildColumnModel() {

        List<ColumnConfig<Collaborator, ?>> configs = new ArrayList<ColumnConfig<Collaborator, ?>>();
        IdentityValueProvider<Collaborator> valueProvider = new IdentityValueProvider<Collaborator>();

        checkBoxModel = new CheckBoxSelectionModel<Collaborator>(valueProvider);

        ColumnConfig<Collaborator, Collaborator> colCheckBox = checkBoxModel.getColumn();
        configs.add(colCheckBox);

        ColumnConfig<Collaborator, Collaborator> name = new ColumnConfig<Collaborator, Collaborator>(
                valueProvider, 150);
        name.setHeader(I18N.DISPLAY.name());
        name.setCell(new AbstractCell<Collaborator>() {

            @Override
            public void render(com.google.gwt.cell.client.Cell.Context context, Collaborator value,
                    SafeHtmlBuilder sb) {
                StringBuilder builder = new StringBuilder();
                if (value.getFirstName() != null && !value.getFirstName().isEmpty()) {
                    builder.append(value.getFirstName());
                    if (value.getLastName() != null && !value.getLastName().isEmpty()) {
                        builder.append(" " + value.getLastName());
                    }
                    sb.appendEscaped(builder.toString());
                } else {
                    sb.appendEscaped(value.getUserName());
                }

            }
        });
        configs.add(name);

        ColumnConfig<Collaborator, String> ins = new ColumnConfig<Collaborator, String>(
                properties.institution(), 150);
        ins.setHeader(I18N.DISPLAY.institution());
        configs.add(ins);
        return new ColumnModel<Collaborator>(configs);

    }

    public List<Collaborator> getSelectedCollaborators() {
        return p.getSelectedCollaborators();
    }

}
