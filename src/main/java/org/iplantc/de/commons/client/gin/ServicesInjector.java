package org.iplantc.de.commons.client.gin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

import org.iplantc.de.commons.client.services.DiskResourceServiceFacade;
import org.iplantc.de.commons.client.services.SearchServiceFacade;
import org.iplantc.de.commons.client.services.ToolRequestProvider;

@GinModules(ServicesModule.class)
public interface ServicesInjector extends Ginjector {

    final ServicesInjector INSTANCE = GWT.create(ServicesInjector.class);

    DiskResourceServiceFacade getDiskResourceServiceFacade();

    ToolRequestProvider getToolRequestServiceProvider();

    SearchServiceFacade getSearchServiceFacade();

}
