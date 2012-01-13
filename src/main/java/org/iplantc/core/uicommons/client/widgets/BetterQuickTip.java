package org.iplantc.core.uicommons.client.widgets;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.tips.QuickTip;
import com.google.gwt.user.client.Element;

/**
 * A subclass of the GXT QuickTip class which fixes the "blank tool tip" problem where
 * a tool tip with no text in it is shown if the mouse pointer leaves the component
 * during the tool tip delay.
 * @author hariolf
 *
 */
public class BetterQuickTip extends QuickTip {
    private boolean showTip = true;
    
    public BetterQuickTip(Component component) {
        super(component);
    }
    
    @Override
    protected void onTargetOver(ComponentEvent ce) {
        Element target = ce.getTarget();
        showTip = hasTip(target);
        super.onTargetOver(ce);
    }
    
    private boolean hasTip(Element target) {
        return !isEmpty(target.getAttribute("qtip")) //$NON-NLS-1$
            || (isInterceptTitles() && !isEmpty(target.getAttribute("title"))); //$NON-NLS-1$
    }

    private boolean isEmpty(String v) {
        return v == null || v.equals(""); //$NON-NLS-1$
    }

    @Override
    public void show() {
        if (showTip) super.show();
    }
}
