package org.iplantc.core.uicommons.client.views.panels;

import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.google.gwt.user.client.ui.Widget;

/**
 * Defines a base class for handling dialog panels.
 * 
 * @author amuir
 */
public abstract class IPlantDialogPanel {
    /**
     * Parent for all buttons contained in the bottom panel of the dialog.
     */
    protected ButtonBar parentButtons;

    /**
     * Store button bar for easy access.
     * 
     * @param buttons items to add to button bar.
     */
    public void setButtonBar(ButtonBar buttons) {
        parentButtons = buttons;
    }

    /**
     * Retrieve the widget that has focus by default.
     * 
     * @return widget to have focus on initial display.
     */
    public Widget getFocusWidget() {
        return null;
    }

    /**
     * Hook for handling when the user clicks the OK button.
     */
    public abstract void handleOkClick();

    /**
     * Retrieve widget to display as the body of the dialog.
     * 
     * @return a reference to the display widget
     */
    public abstract Widget getDisplayWidget();
}
