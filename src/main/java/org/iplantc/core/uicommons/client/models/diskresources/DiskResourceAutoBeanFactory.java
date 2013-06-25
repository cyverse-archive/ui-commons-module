package org.iplantc.core.uicommons.client.models.diskresources;

import org.iplantc.core.uicommons.client.models.HasPaths;
import org.iplantc.core.uicommons.client.models.diskresources.RestoreResponse.RestoredResource;

import com.google.gwt.core.shared.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

public interface DiskResourceAutoBeanFactory extends AutoBeanFactory {

    static final DiskResourceAutoBeanFactory INSTANCE = GWT.create(DiskResourceAutoBeanFactory.class);

    AutoBean<Folder> folder();

    AutoBean<Folder> folder(Folder toWrap);

    AutoBean<DiskResource> diskResource();

    AutoBean<File> file();

    AutoBean<Permissions> permissions();

    AutoBean<RootFolders> rootFolders();

    AutoBean<DiskResourceMetadata> metadata();

    AutoBean<DiskResourceMetadataList> metadataList();

    AutoBean<DiskResourceInfo> info();

    AutoBean<HasPaths> pathsList();

    AutoBean<RestoreResponse> restoreResponse();

    AutoBean<RestoredResource> partialRestoreResponse();

    AutoBean<DiskResourceExistMap> diskResourceExistMap();

}
