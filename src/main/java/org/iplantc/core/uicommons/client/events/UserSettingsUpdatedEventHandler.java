/**
 * 
 */
package org.iplantc.core.uicommons.client.events;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author sriram
 *
 */
public interface UserSettingsUpdatedEventHandler extends EventHandler {

    public void onUpdate(UserSettingsUpdatedEvent usue);
    
}
