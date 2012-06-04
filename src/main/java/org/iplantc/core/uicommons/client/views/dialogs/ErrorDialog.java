package org.iplantc.core.uicommons.client.views.dialogs;

import org.iplantc.core.uicommons.client.Constants;
import org.iplantc.core.uicommons.client.I18N;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;

/**
 * A dialog that is used to display RPC related failures with description
 * 
 * @author sriram
 * 
 */
public class ErrorDialog extends Dialog {
    private final String errorMsg;
    private final String description;

    /**
     * Instantiate from an error message and description.
     * 
     * @param errorMsg summary message to display.
     * @param description error description.
     */
    public ErrorDialog(String errorMsg, String description) {
        this.errorMsg = errorMsg;
        this.description = description;
        initDialog();
        initPanels();
    }

    private void initDialog() {
        setHeading(I18N.ERROR.error());
        setWidth(360);
        setAutoHeight(true);
        setModal(true);
        setShadow(false);
        setHideOnButtonClick(true);
        setResizable(false);
    }

    private void initPanels() {
        ContentPanel detailsPanel = buildDetailsPanel();
        ContentPanel msgPanel = buildMessagePanel(detailsPanel);
        VerticalPanel vp = new VerticalPanel();
        vp.add(msgPanel);
        vp.add(detailsPanel);
        add(vp);
        setButtonAlign(HorizontalAlignment.CENTER);
        setButtons(Dialog.OK);
    }

    private ContentPanel buildMessagePanel(final ContentPanel detailsPanel) {
        ContentPanel cp1 = new ContentPanel();
        cp1.setHeaderVisible(false);
        cp1.setFrame(true);
        cp1.setSize(350, 100);
        cp1.setAutoHeight(true);
        cp1.setLayout(new FitLayout());

        Image logo = new Image(Constants.CLIENT.iconError());
        HorizontalPanel hp = buildHorizonatalPanerl();
        hp.add(logo);
        HTML txt = new HTML(errorMsg);
        hp.add(txt);
        add(hp);

        Button b = buildDetailsButton(detailsPanel);
        cp1.addButton(b);

        return cp1;
    }

    private Button buildDetailsButton(final ContentPanel detailsPanel) {
        Button b = new ToggleButton(I18N.DISPLAY.details() + " &raquo;"); //$NON-NLS-1$
        b.setWidth(70);

        b.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                setDetailsPanelState(detailsPanel);

            }
        });
        return b;
    }

    private HorizontalPanel buildHorizonatalPanerl() {
        HorizontalPanel hp = new HorizontalPanel();
        hp.setLayout(new FitLayout());
        hp.setStyleAttribute("background-color", "#F1F1F1"); //$NON-NLS-1$ //$NON-NLS-2$
        hp.setHeight(70);
        hp.setSpacing(5);
        hp.setScrollMode(Scroll.AUTO);
        return hp;
    }

    private ContentPanel buildDetailsPanel() {
        ContentPanel cp2 = new ContentPanel();
        cp2.setWidth(350);
        cp2.setHeaderVisible(false);
        cp2.setHeight(150);
        cp2.collapse();
        cp2.setFooter(true);
        cp2.setFrame(true);
        TextArea descriptionArea = new TextArea();
        descriptionArea.setWidth(339);
        descriptionArea.setHeight(142);
        descriptionArea.setReadOnly(true);
        descriptionArea.setValue(description);
        cp2.add(descriptionArea);
        return cp2;
    }

    private void setDetailsPanelState(final ContentPanel detailsPanel) {
        if (detailsPanel.isCollapsed()) {
            detailsPanel.expand();
        } else if (detailsPanel.isExpanded()) {
            detailsPanel.collapse();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        // prevent button bar from receiving focus
        ButtonBar buttonbar = getButtonBar();
        buttonbar.getElement().setTabIndex(-1);
    }
}
