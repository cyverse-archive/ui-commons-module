package org.iplantc.core.uicommons.client.info;

import org.iplantc.core.resources.client.AnnouncerStyle;
import org.iplantc.core.resources.client.IplantResources;

import com.sencha.gxt.widget.core.client.button.IconButton.IconConfig;

/**
 * A default config for an IplantAnnouncement that defines attributes such as style and timeout. By
 * default, the message is closable by the user and will close automatically after 10 seconds.
 * 
 * @author psarando
 * 
 */
public class IplantAnnouncementConfig {
    private static final int DEFAULT_TIMEOUT_ms = 10000;
    private static final AnnouncerStyle STYLE;
    private static final IconConfig CLOSER_CFG;

    static {
        STYLE = IplantResources.RESOURCES.getAnnouncerStyle();
        STYLE.ensureInjected();
        CLOSER_CFG = new IconConfig(STYLE.closeButton(), STYLE.closeButtonOver());
    }

    private final boolean closable;
    private final int timeout_ms;

    public IplantAnnouncementConfig() {
        this(true, DEFAULT_TIMEOUT_ms);
    }

    public IplantAnnouncementConfig(boolean closable) {
        this(closable, DEFAULT_TIMEOUT_ms);
    }

    /**
     * Constructs an announcement config. Setting a timeout of 0 or less will cause the message to not
     * close automatically.
     * 
     * If the closable flag is set to false, the message must close automatically. In this case, if the
     * provided timeout is 0 or less, the message will close automatically after 10 seconds.
     * 
     * @param closable
     * @param timeout_ms
     */
    public IplantAnnouncementConfig(boolean closable, int timeout_ms) {
        this.closable = closable;
        this.timeout_ms = timeout_ms;
    }

    public boolean isClosable() {
        return closable;
    }

    public int getTimeOut() {
        return (!closable && timeout_ms <= 0) ? DEFAULT_TIMEOUT_ms : timeout_ms;
    }

    public String getPanelStyle() {
        return STYLE.panel();
    }

    public String getContentStyle() {
        return STYLE.content();
    }

    public String getPanelMultipleStyle() {
        return STYLE.panelMultiple();
    }

    public IconConfig getCloseIconConfig() {
        return CLOSER_CFG;
    }
}
