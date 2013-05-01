package org.iplantc.core.uicommons.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * An abstract class which structures the conversion from one <code>AsyncCallback</code> type, to another
 * <code>AsyncCallback</code> type.
 * 
 * @author jstroot
 * 
 * @param <F> The class type to convert from
 * @param <T> The class type to convert to
 */
public abstract class AsyncCallbackConverter<F, T> implements AsyncCallback<F> {

    private final AsyncCallback<T> callback;

    public AsyncCallbackConverter(AsyncCallback<T> callback) {
        this.callback = callback;
    }

    @Override
    public void onSuccess(F result) {
        this.callback.onSuccess(convertFrom(result));
    }

    /**
     * Converts from the given object, to the object T.
     * 
     * @param object the object to be converted.
     * @return the converted object.
     */
    protected abstract T convertFrom(F object);

}
