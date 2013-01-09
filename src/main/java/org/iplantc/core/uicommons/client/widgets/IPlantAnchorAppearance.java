/**
 * 
 */
package org.iplantc.core.uicommons.client.widgets;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.dom.XElement;

/**
 * @author sriram
 *
 */
public interface IPlantAnchorAppearance {

    void onMouseOver(XElement element);

    void onMouseOut(XElement element);

    void onUpdateText(XElement element, String text);

    void render(SafeHtmlBuilder sb);
}
