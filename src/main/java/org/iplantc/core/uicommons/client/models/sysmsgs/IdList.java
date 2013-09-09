package org.iplantc.core.uicommons.client.models.sysmsgs;

import java.util.List;

import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;

/**
 * Represents a JSON document containing a list of UUIDS.
 */
public interface IdList {
	
	@PropertyName("uuids")
	List<String> getIds();
	
	@PropertyName("uuids")
	void setIds(List<String> ids);
	
}
