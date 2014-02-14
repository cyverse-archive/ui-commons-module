package org.iplantc.de.commons.client.models.toolrequests;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

/**
 * The factory for making tool request related auto beans.
 */
public interface ToolRequestFactory extends AutoBeanFactory {

    AutoBean<RequestedToolDetails> makeRequestedToolDetails();

    AutoBean<NewToolRequest> makeNewToolRequest();

}
