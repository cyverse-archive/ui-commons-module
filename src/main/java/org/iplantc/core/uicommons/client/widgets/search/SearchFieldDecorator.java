package org.iplantc.core.uicommons.client.widgets.search;

import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.event.dom.client.KeyCodeEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.ui.HasText;

import com.sencha.gxt.core.client.util.DelayedTask;

import org.iplantc.core.uicommons.client.events.SubmitTextSearchEvent;
import org.iplantc.core.uicommons.client.events.SubmitTextSearchEvent.HasSubmitTextSearchEvents;
import org.iplantc.core.uicommons.client.events.SubmitTextSearchEvent.SubmitTextSearchEventHandler;

/**
 * A simple decorator class which fires {@link SubmitTextSearchEvent}s after a the given search field
 * enters over 3 characters.
 * 
 * @author jstroot
 * 
 * @param <F> the search field which must implement both {@link HasKeyUpHandlers} and {@link HasText}
 */
public class SearchFieldDecorator<F extends HasKeyUpHandlers & HasText> implements HasSubmitTextSearchEvents, KeyUpHandler, HasHandlers {

    final class SearchFieldDelayedTask extends DelayedTask {
        private final F dtSearchField;
        private final HasHandlers hasHandlers;
        private final int minChars1;

        public SearchFieldDelayedTask(final F searchField, final HasHandlers hasHandlers, final int minChars) {
            this.dtSearchField = searchField;
            this.hasHandlers = hasHandlers;
            this.minChars1 = minChars;
        }

        @Override
        public void onExecute() {
            final String text = dtSearchField.getText();
            if (text.length() >= minChars1) {
                hasHandlers.fireEvent(new SubmitTextSearchEvent(text));
            }
        }
    }

    final int queryDelay = 500;
    final int minChars = 3;
    private HandlerManager handlerManager;
    private final DelayedTask task;

    public SearchFieldDecorator(final F searchField) {
        task = new SearchFieldDelayedTask(searchField, this, minChars);
        searchField.addKeyUpHandler(this);
    }

    @Override
    public HandlerRegistration addSubmitTextSearchEventHandler(SubmitTextSearchEventHandler handler) {
        return ensureHandlers().addHandler(SubmitTextSearchEvent.TYPE, handler);
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
        if (handlerManager != null) {
            handlerManager.fireEvent(event);
        }
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        boolean isModifierKey = false;
        switch (event.getNativeKeyCode()) {
            case KeyCodes.KEY_ENTER:
                // Enter is special cased by TextInputCells, so it's handled by SearchFieldCell.
            case KeyCodes.KEY_ALT:
            case KeyCodes.KEY_CTRL:
            case KeyCodes.KEY_END:
            case KeyCodes.KEY_ESCAPE:
            case KeyCodes.KEY_HOME:
            case KeyCodes.KEY_PAGEDOWN:
            case KeyCodes.KEY_PAGEUP:
            case KeyCodes.KEY_SHIFT:
            case KeyCodes.KEY_TAB:
                isModifierKey = true;
            default:
                isModifierKey = KeyCodeEvent.isArrow(event.getNativeKeyCode());
        }

        if (!isModifierKey) {
            task.delay(queryDelay);
        }
    }

    HandlerManager createHandlerManager() {
        return new HandlerManager(this);
    }

    HandlerManager ensureHandlers() {
        return handlerManager == null ? handlerManager = createHandlerManager() : handlerManager;
    }

    HandlerManager getHandlerManager() {
        return handlerManager;
    }
}
