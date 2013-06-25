package org.iplantc.core.uicommons.client.models.diskresources;

import java.util.Map;

import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;

public interface DiskResourceExistMap {

    @PropertyName("paths")
    Map<String, Boolean> getMap();

}
