package org.iplantc.core.uicommons.client.info;

import com.google.gwt.dom.client.Style.Float;
import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.widget.core.client.Popup;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.container.CssFloatLayoutContainer;
import com.sencha.gxt.widget.core.client.container.CssFloatLayoutContainer.CssFloatData;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

/**
 * A Popup for displaying a message by the IplantAnnouncer. Uses an IplantAnnouncementConfig to configure
 * if it's closable, timeout delay, and styles.
 * 
 * @author psarando
 * 
 */
public class IplantAnnouncement extends Popup {

    private final IplantAnnouncementConfig config;
    private ToolButton closeButton;

    /**
     * Constructs a user closable announcement that will close automatically after 10 seconds.
     * 
     * @param content the message widget
     */
    public IplantAnnouncement(final IsWidget content) {
        this(content, new IplantAnnouncementConfig());
    }

    /**
     * Constructs an announcement with a message content and a config.
     * 
     * @param content the message widget
     * @param config an IplantAnnouncementConfig that configures the style and behavior of this
     *            announcement.
     */
    public IplantAnnouncement(final IsWidget content, final IplantAnnouncementConfig config) {
        this.config = config;
        initPanel(content);
    }

    private void initPanel(final IsWidget content) {
        final SimpleContainer contentContainer = new SimpleContainer();
        contentContainer.setWidget(content);
        contentContainer.addStyleName(config.getContentStyle());

        final CssFloatLayoutContainer layout = new CssFloatLayoutContainer();
        layout.add(contentContainer, new CssFloatData(-1));

        if (config.isClosable()) {
            closeButton = new ToolButton(config.getCloseIconConfig());
            layout.add(closeButton, new CssFloatData(-1));
            closeButton.getElement().getStyle().setFloat(Float.RIGHT);
        }

        setWidget(layout);
        setAutoHide(false);
        addStyleName(config.getPanelStyle());
        setShadow(true);
    }

    public void addCloseButtonHandler(SelectHandler handler) {
        if (closeButton != null && handler != null) {
            closeButton.addSelectHandler(handler);
        }
    }

    public void indicateMore() {
        addStyleName(config.getPanelMultipleStyle());
    }

    public void indicateNoMore() {
        removeStyleName(config.getPanelMultipleStyle());
    }

    public int getTimeOut() {
        return config.getTimeOut();
    }
}
