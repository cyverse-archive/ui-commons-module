package org.iplantc.core.uicommons.client.models.diskresources;



import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;

public interface File extends DiskResource {

    @PropertyName("file-size")
    long getSize();

    @PropertyName("file-size")
    void setSize(long size);

}
