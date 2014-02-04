package org.iplantc.de.commons.client.services.impl.models;

import java.util.Set;

import org.iplantc.de.commons.client.models.diskresources.DiskResourceMetadata;

public interface DiskResourceMetadataBatchRequest {

    Set<DiskResourceMetadata> getAdd();

    void setAdd(Set<DiskResourceMetadata> add);

    Set<String> getDelete();

    void setDelete(Set<String> delete);

}
