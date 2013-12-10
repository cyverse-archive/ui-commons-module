package org.iplantc.core.uicommons.client.gin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import org.iplantc.core.uicommons.client.services.DiskResourceServiceFacade;
import org.iplantc.core.uicommons.client.services.SearchServiceFacade;
import org.iplantc.core.uicommons.client.services.ToolRequestProvider;
import org.iplantc.core.uicommons.client.services.impl.SearchServiceFacadeImpl;

final class ServicesModule extends AbstractGinModule {

    @Override
    protected void configure() {
        // TODO JDS May want to perform binding here instead of via deferred binding.
        // That will help ensure that only one DiskResource service is active in the app (no one can
        // GWT.create one with deferred binding).
        bind(DiskResourceServiceFacade.class).in(Singleton.class);
        bind(ToolRequestProvider.class).in(Singleton.class);

        bind(SearchServiceFacade.class).to(SearchServiceFacadeImpl.class);
    }

}
