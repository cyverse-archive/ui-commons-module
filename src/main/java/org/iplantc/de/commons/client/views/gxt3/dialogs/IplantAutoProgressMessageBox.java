package org.iplantc.de.commons.client.views.gxt3.dialogs;

import com.sencha.gxt.widget.core.client.box.AutoProgressMessageBox;

public class IplantAutoProgressMessageBox extends AutoProgressMessageBox {

    public IplantAutoProgressMessageBox(String headingHtml, String messageHtml, String progressText) {
        super(headingHtml, messageHtml);
        setProgressText(progressText);
    }

}
