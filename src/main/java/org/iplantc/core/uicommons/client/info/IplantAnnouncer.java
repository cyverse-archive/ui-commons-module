package org.iplantc.core.uicommons.client.info;

import java.util.LinkedList;
import java.util.Queue;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

/**
 * A queue for IplantAnnouncement popups that controls which one is displayed and its position.
 * 
 * Announcements are displayed by this class in the top center of the view port. The message may be a
 * String or a widget, allowing a user to interact with the message to obtain more information. By
 * default, an IplantAnnouncementConfig is used to determine the message timeout and if it is closable by
 * the user.
 * 
 * Only one message can be displayed at time. If a second message is scheduled, it will be shown once the
 * first one times out.
 */
public class IplantAnnouncer {

    private static IplantAnnouncer instance;

    protected final Queue<IplantAnnouncement> announcements = new LinkedList<IplantAnnouncement>();
    private final Timer timer;

    protected IplantAnnouncer() {
        timer = new CloseTimer();
        Window.addResizeHandler(new ResizeHandler() {
            @Override
            public void onResize(final ResizeEvent event) {
                positionAnnouncer();
            }
        });
    }

    public static IplantAnnouncer getInstance() {
        if (instance == null) {
            instance = new IplantAnnouncer();
        }

        return instance;
    }

    private void removeAnnouncement() {
        if (announcements.isEmpty()) {
            return;
        }

        IplantAnnouncement popup = announcements.poll();
        timer.cancel();
        popup.hide();

        showNextAnnouncement();
    }

    private void scheduleAnnouncement(final IplantAnnouncement newAnnouncement) {
        if (announcements.contains(newAnnouncement)) {
            return;
        }
        announcements.add(newAnnouncement);
        showNextAnnouncement();
    }

    private void showNextAnnouncement() {
        if (announcements.isEmpty()) {
            return;
        }

        IplantAnnouncement popup = announcements.peek();
        popup.show();
        positionAnnouncer();
        if (popup.getTimeOut() > 0) {
            timer.schedule(popup.getTimeOut());
        }

        if (announcements.size() > 1) {
            popup.indicateMore();
        } else {
            popup.indicateNoMore();
        }
    }

    private final class CloseHandler implements SelectHandler {
        @Override
        public void onSelect(final SelectEvent event) {
            removeAnnouncement();
        }
    }

    private final class CloseTimer extends Timer {
        @Override
        public void run() {
            removeAnnouncement();
        };
    }

    protected void positionAnnouncer() {
        if (announcements.isEmpty()) {
            return;
        }

        IplantAnnouncement popup = announcements.peek();

        int x = (Window.getClientWidth() - popup.getOffsetWidth()) / 2;
        int y = 0;
        popup.setPagePosition(x, y);
    }

    /**
     * Schedules a user closable announcement that will close automatically after 10 seconds.
     * 
     * @param message The plain text announcement message.
     */
    public void schedule(String message) {
        schedule(new HTML(message));
    }

    /**
     * Schedules an announcement using the given IplantAnnouncementConfig.
     * 
     * @param message The plain text announcement message.
     * @param config The announcement configuration.
     */
    public void schedule(String message, IplantAnnouncementConfig config) {
        schedule(new HTML(message), config);
    }

    /**
     * Schedules a user closable announcement that will close automatically after 10 seconds.
     * 
     * @param content A Widget containing the announcement message.
     */
    public void schedule(IsWidget content) {
        schedule(content, new IplantAnnouncementConfig());
    }

    /**
     * Schedules an announcement using the given IplantAnnouncementConfig.
     * 
     * @param content A Widget containing the announcement message.
     * @param config The announcement configuration.
     */
    public void schedule(IsWidget content, IplantAnnouncementConfig config) {
        IplantAnnouncement popup = new IplantAnnouncement(content, config);
        if (config.isClosable()) {
            popup.addCloseButtonHandler(new CloseHandler());
        }

        scheduleAnnouncement(popup);
    }
}
