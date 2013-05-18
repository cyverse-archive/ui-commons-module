package org.iplantc.core.uicommons.client.models;

import java.util.List;

public interface UserSession {
    List<WindowState> getWindowStates();

    void setWindowStates(List<WindowState> windowStates);

}