package org.iplantc.core.uicommons.client.views.dialogs;

import org.iplantc.core.uicommons.client.views.panels.IPlantDialogPanel;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Layout;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

/**
 * Provides a common, uniformly styled dialog user interface.
 * 
 * @author amuir
 * 
 */
public class IPlantDialog extends Dialog {
    private final IPlantDialogPanel panel;
    private boolean disableOkBeforeLayout;

    /**
     * Constructs a uniform dialog used in the application.
     * 
     * @param caption a string to display as 'caption text.'
     * @param width the width of the dialog as an integer value.
     * @param panel the panel to display in the dialog form.
     */
    public IPlantDialog(String caption, int width, IPlantDialogPanel panel) {
        this.panel = panel;
        this.disableOkBeforeLayout = false;

        setButtons(Dialog.OKCANCEL);
        setButtonAlign(HorizontalAlignment.RIGHT);

        if (panel != null) {
            panel.setButtonBar(getButtonBar());
        }

        setLayout(new FitLayout());
        setResizable(false);
        setModal(true);
        setHideOnButtonClick(true);
        setHeading(caption);
        setWidth(width);
        setupEventHandlers();
    }

    /**
     * Configure and attach all event handers for this dialog.
     */
    private void setupEventHandlers() {
        // handle ok button
        getOkButton().addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (panel != null) {
                    panel.handleOkClick();
                }
            }
        });
    }

    /**
     * Indicate the OK button should appear disabled when initially presented to the user.
     */
    public void disableOkOnStartup() {
        disableOkBeforeLayout = true;
    }

    /**
     * Enable the OK button.
     */
    public void enableOkButton() { // law of demeter - don't let code dependent on you depend on another
                                   // object's
                                   // contract
        getOkButton().setEnabled(true);
    }

    /**
     * Disable the OK button.
     */
    public void disableOkButton() {
        getOkButton().setEnabled(false);
    }

    /**
     * Retrieve the OK button for the dialog.
     * 
     * If a more generic retrieval is needed, use the inherited getButtonById() method.
     * 
     * @return a reference to the OK button for the dialog.
     */
    public Button getOkButton() {
        return getButtonById(Dialog.OK);
    }

    /**
     * Set caption on the dialog.
     * 
     * This corresponds to the heading property of a GXT Dialog.
     * 
     * @param caption the text to be used as the caption, or heading.
     */
    public void setCaption(String caption) {
        setHeading(caption);
    }

    /**
     * Perform any necessary action prior to the execution of layout.
     * 
     * Specifically, we may want to include actions that must handle before render or layout. One such
     * example would be conditionally disabling the OK button for the dialog.
     * 
     * @param layout the scheme for rending widgets.
     */
    @Override
    protected void onBeforeLayoutExcecuted(Layout layout) {
        if (disableOkBeforeLayout) {
            getOkButton().setEnabled(false);
            // only do this once so things like resize events don't disable the button
            disableOkBeforeLayout = false;
        }
    }

    /**
     * Attach a click handler to the selection of the dialog's OK button.
     * 
     * @param handler the defined handler for a Dialog OK button selection event.
     */
    public void addOkClickHandler(SelectionListener<ButtonEvent> handler) {
        getOkButton().addSelectionListener(handler);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        if (panel != null) {
            Widget display = panel.getDisplayWidget();

            add(display);

            Widget focus = panel.getFocusWidget();

            // if we don't have a focus widget, let's set focus on our display widget
            setFocusWidget((focus != null) ? focus : display);
        }

        // prevent button bar from receiving focus
        ButtonBar buttonbar = getButtonBar();
        if (buttonbar != null) {
            buttonbar.getElement().setTabIndex(-1);
        }
    }

    /**
     * Get the dialog panel managed by this dialog.
     * 
     * This is only available to subclasses so that they may determine if the dialog panel can or should
     * be exposed when defining its' contract.
     * 
     * @return an instance of the dialog panel managed, or wrapped by this dialog.
     */
    protected IPlantDialogPanel getUnderlyingPanel() {
        return panel;
    }

}
