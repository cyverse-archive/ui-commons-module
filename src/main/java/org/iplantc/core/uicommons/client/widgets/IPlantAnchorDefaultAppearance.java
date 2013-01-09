/**
 * 
 */
package org.iplantc.core.uicommons.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.dom.XElement;

/**
 * @author sriram
 *
 */
public class IPlantAnchorDefaultAppearance implements IPlantAnchorAppearance {

    public interface Template extends XTemplates {
        @XTemplate(source = "IPlantAnchorDefaultAppearance.html")
        SafeHtml template(Style style);
    }

    public interface Style extends CssResource {

        String anchor();

        String anchorText();

        String anchorMouseOver();

        String anchorMouseOut();
    }

    private final Style style;
    private final Template template;

    public interface Resources extends ClientBundle {
        @Source("IPlantAnchorDefaultAppearance.css")
        Style style();
    }

    public IPlantAnchorDefaultAppearance() {
        Resources resources = ((Resources)GWT.create(Resources.class));
        this.style = resources.style();
        this.style.ensureInjected();

        this.template = GWT.create(Template.class);
    }

    /* (non-Javadoc)
     * @see org.iplantc.core.uicommons.client.widgets.IPlantAnchorAppearance#onMouseOver(com.sencha.gxt.core.client.dom.XElement)
     */
    @Override
    public void onMouseOver(XElement element) {
        element.selectNode("." + style.anchorText()).removeClassName(style.anchorMouseOut());
        element.selectNode("." + style.anchorText()).addClassName(style.anchorMouseOver());

    }

    /* (non-Javadoc)
     * @see org.iplantc.core.uicommons.client.widgets.IPlantAnchorAppearance#onMouseOut(com.sencha.gxt.core.client.dom.XElement)
     */
    @Override
    public void onMouseOut(XElement element) {
        element.selectNode("." + style.anchorText()).removeClassName(style.anchorMouseOver());
        element.selectNode("." + style.anchorText()).addClassName(style.anchorMouseOut());
    }

    /* (non-Javadoc)
     * @see org.iplantc.core.uicommons.client.widgets.IPlantAnchorAppearance#onUpdateText(com.sencha.gxt.core.client.dom.XElement, java.lang.String)
     */
    @Override
    public void onUpdateText(XElement parent, String text) {
        parent.selectNode("." + style.anchorText()).setInnerText(text);

    }

    @Override
    public void render(SafeHtmlBuilder sb) {
        sb.append(template.template(style));
    }

}
