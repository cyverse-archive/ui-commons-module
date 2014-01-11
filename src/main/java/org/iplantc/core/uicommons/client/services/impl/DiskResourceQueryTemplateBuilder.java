package org.iplantc.core.uicommons.client.services.impl;

import org.iplantc.core.uicommons.client.models.search.DiskResourceQueryTemplate;

import java.text.ParseException;

/**
 * Constructs a {@link DiskResourceQueryTemplate} from a given string.
 * 
 * @author jstroot
 * 
 */
public class DiskResourceQueryTemplateBuilder {

    private final String queryString;

    public DiskResourceQueryTemplateBuilder(String queryString) {
        this.queryString = queryString;
    }

    public DiskResourceQueryTemplate build() throws ParseException {
        if (queryString.startsWith("throw")) {
            throw new ParseException("Simulated parse error", 1);
        }
        // Start by creating a map of the query string field names to their contents
        // There will be a notion of "supported field names"
        return null;
    }
}
