package org.iplantc.core.uicommons.client.services;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface Endpoints extends Constants {
    Endpoints INSTANCE = GWT.create(Endpoints.class);

    String buckets();

    String filesystemIndex();

    String filesystemIndexStatus();
}
