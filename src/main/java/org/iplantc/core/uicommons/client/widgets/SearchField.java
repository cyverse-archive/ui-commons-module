package org.iplantc.core.uicommons.client.widgets;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.util.DelayedTask;
import com.extjs.gxt.ui.client.widget.form.TriggerField;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.google.gwt.event.dom.client.KeyCodes;

/**
 * A TriggerField used for filtering a Grid. The trigger's style will toggle between a search icon when
 * the filter is not set, and a clear icon which allows the user to clear the filter when it is set.
 * 
 * @author psarando
 * 
 */
public class SearchField extends TriggerField<String> {
    public enum TriggerMode {
        SEARCH("x-form-search-trigger"), //$NON-NLS-1$
        CLEAR("x-form-clear-trigger"); //$NON-NLS-1$

        private String triggerStyle;

        TriggerMode(String triggerStyle) {
            this.triggerStyle = triggerStyle;
        }

        protected String getTriggerStyle() {
            return triggerStyle;
        }
    }

    private final StringFilter filter;
    private TriggerMode mode;
    private int minChars = 3;
    private int queryDelay = 500;
    private final DelayedTask dqTask;

    public SearchField(String dataIndex) {
        filter = new StringFilter(dataIndex);
        dqTask = new DelayedTask(new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                doQuery(getRawValue());
            }
        });

        setTriggerMode(TriggerMode.SEARCH);
    }

    /**
     * Sets the TriggerMode of this field, which also updates the trigger's icon style.
     * 
     * @param mode
     */
    public void setTriggerMode(TriggerMode mode) {
        this.mode = mode;
        setTriggerStyle(mode.getTriggerStyle());

        if (isRendered()) {
            // KLUDGE this should by handled by GXT in the setTriggerStyle method?
            trigger.dom.setClassName("x-form-trigger " + triggerStyle); //$NON-NLS-1$
        }
    }

    /**
     * @return
     */
    public StringFilter getFilter() {
        return filter;
    }

    /**
     * Returns the min characters used for activating filtering.
     * 
     * @return the minimum number of characters
     */
    public int getMinChars() {
        return minChars;
    }

    /**
     * Sets the minimum number of characters the user must type before filtering activates (defaults to
     * 3).
     * 
     * @param minChars
     */
    public void setMinChars(int minChars) {
        this.minChars = minChars;
    }

    /**
     * Returns the query delay.
     * 
     * @return the query delay
     */
    public int getQueryDelay() {
        return queryDelay;
    }

    /**
     * The length of time in milliseconds to delay between the start of typing and sending the query to
     * the StringFilter.
     * 
     * @param queryDelay the query delay
     */
    public void setQueryDelay(int queryDelay) {
        this.queryDelay = queryDelay;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onKeyUp(FieldEvent fe) {
        super.onKeyUp(fe);

        if (!isReadOnly() && isEditable()) {
            int keyCode = fe.getKeyCode();

            if (keyCode == KeyCodes.KEY_ENTER) {
                doQuery(getRawValue());
            } else if (!fe.isSpecialKey() || keyCode == KeyCodes.KEY_BACKSPACE
                    || keyCode == KeyCodes.KEY_DELETE) {
                // Delay setting the StringFilter.
                dqTask.delay(queryDelay);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onTriggerClick(ComponentEvent ce) {
        if (mode == TriggerMode.SEARCH) {
            doQuery(getRawValue());
        } else {
            clearSearchField();
        }
    }

    /**
     * Sets the given value in the search field, and in the StringFilter if the value meets the min-chars
     * limit. If the value is empty, this will clear the search field and StringFilter, and set the
     * TriggerMode to SEARCH. Otherwise it sets the TriggerMode to CLEAR.
     * 
     * @param filterValue
     */
    public void doQuery(String filterValue) {
        if (filterValue == null || filterValue.isEmpty()) {
            clearSearchField();
        } else {
            setTriggerMode(TriggerMode.CLEAR);

            if (filterValue.length() >= minChars) {
                filter.setValue(filterValue);
            }
        }
    }

    /**
     * Clears the value in the text box and the filter, and sets the TriggerMode to SEARCH.
     */
    protected void clearSearchField() {
        setTriggerMode(TriggerMode.SEARCH);
        setValue(null);
        clearFilter();
    }

    /**
     * Sets the StringFilter value to null.
     */
    protected void clearFilter() {
        filter.setValue(null);
    }
}
