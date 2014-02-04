package org.iplantc.de.commons.client.models.diskresources;

import org.iplantc.de.commons.client.models.HasDescription;
import org.iplantc.de.commons.client.models.HasId;

import com.google.gwt.user.client.ui.HasName;

public interface MetadataTemplateAttribute extends HasId, HasName,
		HasDescription {

	String getType();

	void setType(String type);

	void setRequired(boolean required);

	boolean isRequired();

}
