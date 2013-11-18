package org.iplantc.core.uicommons.client.models.diskresources;

import java.util.Date;

import org.iplantc.core.uicommons.client.models.HasId;
import org.iplantc.core.uicommons.client.models.HasPath;

import com.google.gwt.user.client.ui.HasName;
import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;

public interface DiskResource extends HasId, HasName, HasPath {

    void setId(String id);

    void setPath(String path);

    @Override
    @PropertyName("label")
    String getName();

    @Override
    @PropertyName("label")
    void setName(String name);

    @PropertyName("date-created")
    Date getDateCreated();

    @PropertyName("date-modified")
    Date getLastModified();

    Permissions getPermissions();
    
    @PropertyName("filter")
    boolean isFilter();
    
    @PropertyName("filter")
    void setFilter(boolean filter);
}
