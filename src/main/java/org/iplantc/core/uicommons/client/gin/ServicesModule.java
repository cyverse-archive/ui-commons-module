package org.iplantc.core.uicommons.client.gin;

import org.iplantc.core.uicommons.client.services.DiskResourceServiceFacade;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

final class ServicesModule extends AbstractGinModule {

    @Override
    protected void configure() {
        bind(DiskResourceServiceFacade.class).in(Singleton.class);
    }

}
