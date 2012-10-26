package org.iplantc.core.uicommons.client.errorHandling.models;

import org.iplantc.core.uicommons.client.errorHandling.ServiceErrorCode;

import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;

public interface ServiceError {

    @PropertyName("action")
    String getServiceName();

    @PropertyName("error_code")
    ServiceErrorCode getErrorCode();

    @PropertyName("status")
    String getStatus();
}
