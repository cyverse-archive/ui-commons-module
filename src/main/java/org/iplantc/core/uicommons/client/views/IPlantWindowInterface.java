package org.iplantc.core.uicommons.client.views;

import org.iplantc.core.uicommons.client.models.WindowConfig;

import com.extjs.gxt.ui.client.event.WindowListener;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.core.client.util.Point;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.event.ActivateEvent.HasActivateHandlers;
import com.sencha.gxt.widget.core.client.event.DeactivateEvent.HasDeactivateHandlers;
import com.sencha.gxt.widget.core.client.event.HideEvent.HasHideHandlers;
import com.sencha.gxt.widget.core.client.event.MinimizeEvent.HasMinimizeHandlers;
import com.sencha.gxt.widget.core.client.event.ShowEvent.HasShowHandlers;

/**
 * This interface is intended to be used by the {@link DEWindowManager} in place of the {@link IPlantWindow} class.
 * 
 * FIXME Rename this file to "IPlantWindow" and rename "IPlantWindow" -> "IPlantWindowImpl" JDS Ensure
 * that you use 'git mv' in order to preserve history.
 * 
 * @author jstroot
 * 
 */
public interface IPlantWindowInterface extends HasActivateHandlers<Window>,
        HasDeactivateHandlers<Window>, HasMinimizeHandlers,
 HasHideHandlers, HasShowHandlers, IsWidget {

    String getTag();

    void setId(String tag);

    void addWindowListener(WindowListener listener);

    void setPagePosition(int new_x, int new_y);

    void show();

    void toFront();

    void refresh();

    Point getPosition3(boolean b);

    <X> X getData(String key);

    void setWindowConfig(WindowConfig config);

    JSONObject getWindowState();

}
