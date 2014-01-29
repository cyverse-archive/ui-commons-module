package org.iplantc.core.uicommons.client.services.impl.models;

import org.iplantc.core.uicommons.client.models.diskresources.DiskResourceAutoBeanFactory;
import org.iplantc.core.uicommons.client.models.diskresources.DiskResourceExistMap;
import org.iplantc.core.uicommons.client.models.diskresources.DiskResourceStatMap;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory.Category;

@Category({DiskResourceExistMap.Category.class, DiskResourceStatMap.Category.class})
public interface DiskResourceServiceAutoBeanFactory extends DiskResourceAutoBeanFactory {

    AutoBean<DiskResourceMetadataBatchRequest> metadataBatchRequest();

}
