package org.iplantc.core.uicommons.client.events.diskresources;

import org.iplantc.core.uicommons.client.events.diskresources.DiskResourceRefreshEvent.DiskResourceRefreshEventHandler;
import org.iplantc.core.uicommons.client.models.diskresources.Folder;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * FIXME this event and its handler should be renamed to FolderRefreshEvent.
 */
public class DiskResourceRefreshEvent extends GwtEvent<DiskResourceRefreshEventHandler> {

    public interface DiskResourceRefreshEventHandler extends EventHandler {
        void onRefresh(DiskResourceRefreshEvent event);
    }

    public static final GwtEvent.Type<DiskResourceRefreshEventHandler> TYPE = new GwtEvent.Type<DiskResourceRefreshEventHandler>();

    private final Folder folder;

    public DiskResourceRefreshEvent(Folder folder) {
        this.folder = folder;
    }

    @Override
    protected void dispatch(DiskResourceRefreshEventHandler handler) {
        handler.onRefresh(this);
    }

    @Override
    public GwtEvent.Type<DiskResourceRefreshEventHandler> getAssociatedType() {
        return TYPE;
    }

    public Folder getFolder() {
        return folder;
    }
}
