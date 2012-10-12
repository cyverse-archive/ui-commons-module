package org.iplantc.core.uicommons.client.views.gxt3.dialogs;

import java.util.ArrayList;

import com.google.gwt.cell.client.Cell.Context;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

/**
 * A base class for GXT 3.x IPlant dialogs. All IPlant dialogs will be modal windows.
 * 
 * @author jstroot
 * 
 */
public class IPlantDialog extends Dialog {

    private final ArrayList<SelectHandler> okButtonSelectHandlers = new ArrayList<SelectHandler>();
    private final ArrayList<SelectHandler> cancelButtonSelectHandlers = new ArrayList<SelectHandler>();

    public IPlantDialog() {
        init();
    }

    private void init() {
        setPredefinedButtons(PredefinedButton.OK, PredefinedButton.CANCEL);
        setModal(true);
    }

    @Override
    protected void onButtonPressed(TextButton button) {
        super.onButtonPressed(button);
        if (button == getButtonBar().getItemByItemId(PredefinedButton.OK.name())) {
            callEventHandlers(okButtonSelectHandlers, button);
        } else if (button == getButtonBar().getItemByItemId(PredefinedButton.CANCEL.name())) {
            callEventHandlers(cancelButtonSelectHandlers, button);
        }
    }

    public void addOkButtonSelectHandler(final SelectHandler handler) {
        okButtonSelectHandlers.add(handler);
    }

    public void addCancleButtonSelectHandler(final SelectHandler handler) {
        cancelButtonSelectHandlers.add(handler);
    }

    private void callEventHandlers(final ArrayList<SelectHandler> handlers, final TextButton button) {
        for (SelectHandler handler : handlers) {
            handler.onSelect(new SelectEvent(new Context(0, 0, button)));
        }
    }

}
