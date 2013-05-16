package org.iplantc.core.uicommons.client.info;

public class ErrorAnnouncementConfig extends IplantAnnouncementConfig {

    public ErrorAnnouncementConfig() {
        super();
    }

    public ErrorAnnouncementConfig(boolean closable) {
        super(closable);
    }

    public ErrorAnnouncementConfig(boolean closable, int timeout_ms) {
        super(closable, timeout_ms);
    }

    @Override
    public String getPanelStyle() {
        return STYLE.panelError();
    }
}
