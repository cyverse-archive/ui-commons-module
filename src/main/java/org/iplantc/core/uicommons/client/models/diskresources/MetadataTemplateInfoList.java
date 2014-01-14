package org.iplantc.core.uicommons.client.models.diskresources;

import java.util.List;

import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;


public interface MetadataTemplateInfoList {

	@PropertyName("metadata_templates")
	List<MetadataTemplateInfo> getTemplates();


}
