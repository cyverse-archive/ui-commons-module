package org.iplantc.core.uicommons.client.models.diskresources;



import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;

public interface File extends DiskResource {

    @PropertyName("file-size")
    String getSize();

    @PropertyName("file-size")
    void setSize(String size);

}
