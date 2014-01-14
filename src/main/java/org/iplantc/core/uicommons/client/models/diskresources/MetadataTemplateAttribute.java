package org.iplantc.core.uicommons.client.models.diskresources;

import org.iplantc.core.uicommons.client.models.HasDescription;
import org.iplantc.core.uicommons.client.models.HasId;

import com.google.gwt.user.client.ui.HasName;

public interface MetadataTemplateAttribute extends HasId, HasName,
		HasDescription {

	String getType();

	void setType(String type);

	void setRequired(boolean required);

	boolean isRequired();

}
