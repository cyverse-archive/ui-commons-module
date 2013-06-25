package org.iplantc.core.uicommons.client.gin;

import org.iplantc.core.uicommons.client.services.DiskResourceServiceFacade;

import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(ServicesModule.class)
public interface ServicesInjector extends Ginjector {

    static final ServicesInjector INSTANCE = GWT.create(ServicesInjector.class);

    DiskResourceServiceFacade getDiskResourceServiceFacade();

}
