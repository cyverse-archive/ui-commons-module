package org.iplantc.core.uicommons.client.models.diskresources;

import java.util.List;

import org.iplantc.core.uicommons.client.models.HasId;

import com.google.gwt.user.client.ui.HasName;
import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;

public interface MetadataTemplate extends HasId, HasName {

	@PropertyName("attributes")
	List<MetadataTemplateAttribute> getAttributes();
}
