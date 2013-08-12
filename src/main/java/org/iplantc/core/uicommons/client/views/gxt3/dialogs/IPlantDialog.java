package org.iplantc.core.uicommons.client.views.gxt3.dialogs;

import java.util.ArrayList;

import org.iplantc.core.resources.client.IplantContextualHelpAccessStyle;
import org.iplantc.core.resources.client.IplantResources;
import org.iplantc.core.uicommons.client.widgets.ContextualHelpPopup;

import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;

/**
 * A base class for GXT 3.x IPlant dialogs. All IPlant dialogs will be modal windows.
 * 
 * @author jstroot
 * 
 */
public class IPlantDialog extends Dialog implements IsHideable {

    protected ToolButton help_tool;

    private final ArrayList<SelectHandler> okButtonSelectHandlers = new ArrayList<SelectHandler>();
    private final ArrayList<SelectHandler> cancelButtonSelectHandlers = new ArrayList<SelectHandler>();
    
    protected ContextualHelpPopup help_popup;
    private boolean isHelpVisible;

    public IPlantDialog() {
        // no contextual help tool icon by default
        this(false);
    }

    public IPlantDialog(boolean contextualHelpTool) {
        if (contextualHelpTool) {
            initHelpButton();
            initHelpPopup();
            
        }
        init();
    }

    private void initHelpPopup() {
        help_popup = new ContextualHelpPopup();
        help_tool.addSelectHandler(new SelectHandler() {
            
            @Override
            public void onSelect(SelectEvent event) {
                if(!isHelpVisible) {
                    help_popup.showAt(help_tool.getAbsoluteLeft(), help_tool.getAbsoluteTop() + 15);
                } else {
                    help_popup.hide();
                }
            }
        });
        
        help_popup.addShowHandler(new ShowHandler() {
            
            @Override
            public void onShow(ShowEvent event) {
                isHelpVisible = true;
            }
        });
        
        help_popup.addHideHandler(new HideHandler() {
            
            @Override
            public void onHide(HideEvent event) {
                isHelpVisible = false;
            }
        });
    }
    
    /**
     * Add help to dialog
     * 
     * @param c widget containing help text
     * 
     */
    public void addHelp(Widget c) {
        help_popup.add(c);
    }

    private void initHelpButton() {
        IplantContextualHelpAccessStyle style = IplantResources.RESOURCES.getContxtualHelpStyle();
        style.ensureInjected();
      
        help_tool = new ToolButton(style.contextualHelp());
        getHeader().addTool(help_tool);
    }

    protected void init() {
        setPredefinedButtons(PredefinedButton.OK, PredefinedButton.CANCEL);
        setModal(true);
        setResizable(false);
        setHideOnButtonClick(true);
    }

    @Override
    protected void onButtonPressed(TextButton button) {
        if (isHideOnButtonClick()) {
            hide(button);
        }
        if (button == getButtonBar().getItemByItemId(PredefinedButton.OK.name())) {
            onOkButtonClicked();
            callEventHandlers(okButtonSelectHandlers, button);
        } else if (button == getButtonBar().getItemByItemId(PredefinedButton.CANCEL.name())) {
            callEventHandlers(cancelButtonSelectHandlers, button);
        }
    }

    protected void onOkButtonClicked() {

    }

    public ToolButton gelHelpToolButton() {
        return help_tool;
    }

    protected TextButton getOkButton() {
        Widget okButton = getButtonBar().getItemByItemId(PredefinedButton.OK.name());
        if ((okButton != null) && (okButton instanceof TextButton)) {
            return (TextButton)okButton;
        }
        return null;
    }

    public void addOkButtonSelectHandler(final SelectHandler handler) {
        okButtonSelectHandlers.add(handler);
    }

    public void addCancelButtonSelectHandler(final SelectHandler handler) {
        cancelButtonSelectHandlers.add(handler);
    }

    private void callEventHandlers(final ArrayList<SelectHandler> handlers, final TextButton button) {
        for (SelectHandler handler : handlers) {
            handler.onSelect(new SelectEvent(new Context(0, 0, button)));
        }
    }

    public void setOkButtonText(String text) {
        getOkButton().setText(text);
    }

}
