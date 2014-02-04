package org.iplantc.de.commons.client.models.diskresources;

import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;

public interface Permissions {

    @PropertyName("own")
    boolean isOwner();

    @PropertyName("read")
    boolean isReadable();

    @PropertyName("write")
    boolean isWritable();

}