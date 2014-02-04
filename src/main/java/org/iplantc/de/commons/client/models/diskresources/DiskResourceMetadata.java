package org.iplantc.de.commons.client.models.diskresources;

import org.iplantc.de.commons.client.models.HasId;

import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;

public interface DiskResourceMetadata extends HasId {

    @PropertyName("attr")
    String getAttribute();

    @PropertyName("attr")
    void setAttribute(String attr);


    String getValue();

    void setValue(String value);

    String getUnit();

    void setUnit(String unit);
    
    void setId(String id);
    
    
}
