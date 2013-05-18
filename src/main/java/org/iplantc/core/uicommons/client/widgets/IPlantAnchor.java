/**
 * 
 */
package org.iplantc.core.uicommons.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasMouseOutHandlers;
import com.google.gwt.event.dom.client.HasMouseOverHandlers;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Event;
import com.sencha.gxt.core.client.dom.XDOM;
import com.sencha.gxt.widget.core.client.Component;

/**
 * A widget to create html link like <a> tag
 * 
 * TODO JDS This class needs to transformed into a cell-backed widget.
 * @author sriram
 * 
 */
public class IPlantAnchor extends Component implements HasClickHandlers, HasMouseOverHandlers,
        HasMouseOutHandlers {

    private final IPlantAnchorAppearance appearance;
    /**
     * 
     * A widget to create <a> like hyperlinks
     * 
     * @param text text to display
     */
    public IPlantAnchor(String text, int width, IPlantAnchorAppearance appearance, ClickHandler handler) {
        this.appearance = appearance;
        SafeHtmlBuilder sb = new SafeHtmlBuilder();
        this.appearance.render(sb);
        setWidth(width);
        setElement(XDOM.create(sb.toSafeHtml()));
        setText(text);
        sinkEvents(Event.ONCLICK);
        sinkEvents(Event.ONMOUSEOVER);
        sinkEvents(Event.ONMOUSEOUT);
        addEventHandler(handler);
    }

    public IPlantAnchor(String text, int width, ClickHandler handler) {
        this(text, width, (IPlantAnchorAppearance)GWT.create(IPlantAnchorAppearance.class), handler);
    }


    private void addEventHandler(ClickHandler handler) {
        addClickHandler(handler);
        addMouseOutHandler(new MouseOutHandler() {

            @Override
            public void onMouseOut(MouseOutEvent event) {
                IPlantAnchor.this.appearance.onMouseOut(getElement());
            }
        });
        addMouseOverHandler(new MouseOverHandler() {
            
            @Override
            public void onMouseOver(MouseOverEvent event) {
                IPlantAnchor.this.appearance.onMouseOver(getElement());
            }
        });
    }


    public void setText(String text) {
        appearance.onUpdateText(getElement(), text);
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return addDomHandler(handler, ClickEvent.getType());
    }


    @Override
    public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
        return addDomHandler(handler, MouseOutEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
        return addDomHandler(handler, MouseOverEvent.getType());
    }

}
