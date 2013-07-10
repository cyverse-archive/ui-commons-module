package org.iplantc.core.uicommons.client.info;

import org.iplantc.core.resources.client.IplantResources;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;

public class SuccessAnnouncementConfig extends IplantAnnouncementConfig {

    private final ImageResource okIcon = IplantResources.RESOURCES.tick();

    public SuccessAnnouncementConfig(String message) {
        super(message);
    }

    public SuccessAnnouncementConfig(String message, boolean closable) {
        super(message, closable);
    }

    public SuccessAnnouncementConfig(String message, boolean closable, int timeout_ms) {
        super(message, closable, timeout_ms);
    }

    /**
     * @return The given message as an HTML widget, for display by an IplantAnnouncement, proceeded by a
     *         success icon.
     */
    @Override
    public IsWidget getWidget() {
        ImageElement imgEl = Document.get().createImageElement();
        imgEl.setSrc(okIcon.getSafeUri().asString());

        SafeHtmlBuilder sb = new SafeHtmlBuilder();
        sb.appendHtmlConstant(imgEl.getString());
        sb.appendHtmlConstant("&nbsp;");
        sb.appendEscaped(message);

        return new HTML(sb.toSafeHtml());
    }

}
