package org.iplantc.core.uicommons.client.models.autobeans;

import java.util.List;

public interface UserSession {
    List<WindowState> getWindowStates();

    void setWindowStates(List<WindowState> windowStates);

}