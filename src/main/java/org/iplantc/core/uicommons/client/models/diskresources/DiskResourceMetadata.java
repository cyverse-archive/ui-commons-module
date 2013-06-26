package org.iplantc.core.uicommons.client.models.diskresources;

import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;

public interface DiskResourceMetadata {

    @PropertyName("attr")
    String getAttribute();

    @PropertyName("attr")
    void setAttribute(String attr);


    String getValue();

    void setValue(String value);

    String getUnit();

    void setUnit(String unit);
}
