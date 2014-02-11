package org.iplantc.de.commons.client.events.diskresources;

import org.iplantc.de.client.models.diskResources.DiskResource;
import org.iplantc.de.commons.client.events.diskresources.DiskResourceRefreshEvent.DiskResourceRefreshEventHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * FIXME this event and its handler should be renamed to FolderRefreshEvent.
 */
import java.util.List;

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
