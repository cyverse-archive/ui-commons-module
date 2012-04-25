package org.iplantc.core.uicommons.client;

import org.iplantc.de.client.CommonDisplayStrings;

/** Display strings that are shared between applications */
public interface CommonUIDisplayStrings extends CommonDisplayStrings {
    
    /** Localized display text for integrators name
     * @return a string representing the localized text.
     */
    String integratedby();

    /**
     * Localized text for published dates.
     * 
     * @return string representing the text
     */
    String publishedOn();

    /**
     * Display text for No Apps to display.
     * 
     * @return a String representing the text.
     */
    String noApps();

    /**
     * Localized display text used as a filter field empty text string in the data window.
     * 
     * @return a string representing the localized text.
     */
    String filterDataList();
    

    /**
     * Localized text for forums
     * 
     * @return a string representing the localized text.
     */
    String forums();

    /**
     * Localized text for contact support
     * 
     * @return a string representing the localized text.
     */
    String contactSupport();

    /**
     * Display text for more actions button.
     * 
     * @return a string representing the text.
     */
    String moreActions();

    /**
     * Localized display text for displaying 'Rename'.
     * 
     * @return a string representing the localized text.
     */
    String rename();

    /**
     * Localized display text for an 'Integrated Tools' label.
     * 
     * @return a string representing the localized text.
     */
    String integratedTools();

    /**
     * Localized display text drag and drop promt
     * 
     * @return a string representing the localized text.
     */
    String dragAndDropPrompt();
}
