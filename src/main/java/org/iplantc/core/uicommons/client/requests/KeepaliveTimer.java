package org.iplantc.core.uicommons.client.requests;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Timer;
import java.util.Arrays;
import java.util.List;
import org.iplantc.core.uicommons.client.ErrorHandler;

import static org.iplantc.core.uicommons.client.I18N.*;

/**
 * Periodically sends requests to a URL in order to keep the user's session alive.
 * 
 * @author Dennis Roberts
 */
public class KeepaliveTimer {

    /**
     * The status codes that we treat as being okay.
     */
    private static final List<Integer> OKAY_STATUS_CODES = Arrays.asList(301, 302, 200, 0);

    /**
     * The number of milliseconds in a minute.
     */
    public static final int MILLISECONDS_PER_MINUTE = 60000;

    /**
     * The single instance of this class.
     */
    private static KeepaliveTimer instance;

    /**
     * The actual timer used to send requests.
     */
    private PingTimer timer;

    /**
     * The default constructor.
     */
    private KeepaliveTimer() {
    }

    /**
     * @return the single instance of this class.
     */
    public static KeepaliveTimer getInstance() {
        if (instance == null) {
            instance = new KeepaliveTimer();
        }
        return instance;
    }

    public void start(String url, int interval) {
        clearTimer();
        initTimer(url, interval);
    }

    /**
     * Stops the timer.
     */
    public void stop() {
        clearTimer();
    }

    /**
     * Initializes the timer.
     * 
     * @param url the URL to ping.
     * @param interval the number of minutes between pings.
     */
    private void initTimer(String url, int interval) {
        timer = new PingTimer(url);
        timer.scheduleRepeating(interval * MILLISECONDS_PER_MINUTE);
    }

    /**
     * Clears the current timer.
     */
    private void clearTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    /**
     * The class used to time the ping requests.
     */
    private class PingTimer extends Timer {

        /**
         * The builder used to build requests to the server.
         */
        private RequestBuilder requestBuilder;

        /**
         * @param url the URL to send keepalive requests to.
         */
        public PingTimer(String url) {
            requestBuilder = new RequestBuilder(RequestBuilder.GET, url);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void run() {
            sendRequest(requestBuilder);
        }

        /**
         * Sends a request using the given request builder, following redirects if there are any.
         * 
         * @param builder the request builder. 
         */
        private void sendRequest(RequestBuilder builder) {
            try {
                builder.sendRequest(null, new RequestCallback() {

                    @Override
                    public void onResponseReceived(Request request, Response response) {
                        int status = response.getStatusCode();
                        if (!OKAY_STATUS_CODES.contains(status)) {
                            ErrorHandler.post(ERROR.keepaliveRequestFailed());
                        }
                    }

                    @Override
                    public void onError(Request request, Throwable exception) {
                        ErrorHandler.post(ERROR.keepaliveRequestFailed(), exception);
                    }
                });
            }
            catch (RequestException ignore) {
            }
        }
    }
}
