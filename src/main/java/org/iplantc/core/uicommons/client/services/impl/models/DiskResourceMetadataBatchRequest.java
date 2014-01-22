package org.iplantc.core.uicommons.client.services.impl.models;

import java.util.Set;

import org.iplantc.core.uicommons.client.models.diskresources.DiskResourceMetadata;

public interface DiskResourceMetadataBatchRequest {

    Set<DiskResourceMetadata> getAdd();

    void setAdd(Set<DiskResourceMetadata> add);

    Set<String> getDelete();

    void setDelete(Set<String> delete);

}
