package org.iplantc.core.uicommons.client.widgets;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.sencha.gxt.widget.core.client.Popup;

public class ContextualHelpPopup extends Popup {

    interface PopupResources extends ClientBundle {
        @Source("PopupHelpCss.css")
        PopupHelpCssResources css();
    }

    public ContextualHelpPopup() {
        PopupResources res = GWT.create(PopupResources.class);
        res.css().ensureInjected();
        addStyleName(res.css().help());
        setAutoHide(true);
    }

}
