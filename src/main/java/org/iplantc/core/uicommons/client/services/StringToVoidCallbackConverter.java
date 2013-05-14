package org.iplantc.core.uicommons.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class StringToVoidCallbackConverter extends AsyncCallbackConverter<String, Void> {

    public StringToVoidCallbackConverter(AsyncCallback<Void> callback) {
        super(callback);
    }

    @Override
    protected Void convertFrom(String object) {
        return null;
    }

}
