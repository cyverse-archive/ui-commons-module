package org.iplantc.core.uicommons.client.views.panels;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.ui.Widget;

/**
 * Provides a user interface for prompting for input.
 */
public abstract class IPlantPromptPanel extends IPlantDialogPanel {
    protected TextField<String> field;

    /**
     * Instantiate from just a caption.
     * 
     * @param caption caption to display.
     */
    protected IPlantPromptPanel(String caption) {
        initField(caption, -1, null);
    }

    /**
     * Instantiate from caption and maximum field length.
     * 
     * @param caption caption to display.
     * @param maxLength maximum number of characters the user may enter.
     */
    protected IPlantPromptPanel(String caption, int maxLength) {
        initField(caption, maxLength, null);
    }

    /**
     * Instantiate from caption, max length and validator.
     * 
     * @param caption caption to display.
     * @param maxLength maximum number of characters the user may enter.
     * @param validator validator for field validation.
     */
    protected IPlantPromptPanel(String caption, int maxLength, Validator validator) {
        initField(caption, maxLength, validator);
    }

    private void initField(String caption, int maxLength, Validator validator) {
        field = new TextField<String>() {

            @Override
            public void onKeyUp(FieldEvent fe) {
                super.onKeyUp(fe);

                setOkButtonState();
            }
        };

        field.setAllowBlank(false);
        field.setAutoValidate(true);
        field.setId("idPromptField"); //$NON-NLS-1$

        if (maxLength > 0) {
            field.setMaxLength(maxLength);
        }

        if (validator != null) {
            field.setValidator(validator);
        }

        addEnterKeyListener();

        field.setFieldLabel(caption);
        field.setWidth(60);
        field.setSelectOnFocus(true);
        field.focus();
    }

    private void addEnterKeyListener() {
        // if the user hits the enter key, treat it the same as if the user clicked the
        // login button
        field.addListener(Events.KeyPress, new Listener<FieldEvent>() {

            @Override
            public void handleEvent(FieldEvent be) {
                if (isValid() && be.getKeyCode() == KeyCodes.KEY_ENTER && parentButtons != null) {
                    // treat the enter key as if the ok button was clicked
                    Component btn = parentButtons.getItemByItemId(Dialog.OK);
                    btn.fireEvent(Events.Select);
                }

            }

        });
    }

    private void setOkButtonState() {
        parentButtons.getItemByItemId(Dialog.OK).setEnabled(isValid());
    }

    /**
     * @return True if the text field is valid and contains non-empty text (not all spaces).
     */
    public boolean isValid() {
        return field.isValid() && field.getValue() != null && !field.getValue().trim().isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Widget getDisplayWidget() {
        FormPanel panel = new FormPanel();
        panel.setBodyBorder(false);
        panel.setHeaderVisible(false);
        panel.setPadding(5);
        panel.add(field);

        return panel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void handleOkClick();
}