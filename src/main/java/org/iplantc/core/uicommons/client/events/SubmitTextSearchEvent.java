package org.iplantc.core.uicommons.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import org.iplantc.core.uicommons.client.events.SubmitTextSearchEvent.SubmitTextSearchEventHandler;

/**
 * This event is used to pass search strings.
 * 
 * @author jstroot
 * 
 */
public class SubmitTextSearchEvent extends GwtEvent<SubmitTextSearchEventHandler> {

    public interface SubmitTextSearchEventHandler extends EventHandler {
        void onSubmitTextSearch(SubmitTextSearchEvent event);
    }

    public static interface HasSubmitTextSearchEvents {
        HandlerRegistration addSubmitTextSearchEventHandler(SubmitTextSearchEventHandler handler);
    }

    public static final GwtEvent.Type<SubmitTextSearchEventHandler> TYPE = new GwtEvent.Type<SubmitTextSearchEventHandler>();
    private final String searchText;

    public SubmitTextSearchEvent(final String searchText) {
        this.searchText = searchText;
    }

    public String getSearchText() {
        return searchText;
    }

    @Override
    public GwtEvent.Type<SubmitTextSearchEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(SubmitTextSearchEventHandler handler) {
        handler.onSubmitTextSearch(this);
    }
}
