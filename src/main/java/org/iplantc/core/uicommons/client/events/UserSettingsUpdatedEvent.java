/**
 * 
 */
package org.iplantc.core.uicommons.client.events;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author sriram
 *
 */
public class UserSettingsUpdatedEvent extends GwtEvent<UserSettingsUpdatedEventHandler> {

    public static final GwtEvent.Type<UserSettingsUpdatedEventHandler> TYPE = new GwtEvent.Type<UserSettingsUpdatedEventHandler>();
    
    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<UserSettingsUpdatedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(UserSettingsUpdatedEventHandler handler) {
        handler.onUpdate(this);
    }

}
