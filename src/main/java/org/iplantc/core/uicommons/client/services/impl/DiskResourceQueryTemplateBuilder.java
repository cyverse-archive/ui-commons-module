package org.iplantc.core.uicommons.client.services.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.Splittable;
import com.google.web.bindery.autobean.shared.impl.StringQuoter;

import org.iplantc.core.uicommons.client.models.search.DiskResourceQueryTemplate;
import org.iplantc.core.uicommons.client.models.search.SearchAutoBeanFactory;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Constructs a {@link DiskResourceQueryTemplate} from a given string.
 * 
 * FIXME To be implemented
 * 
 * @author jstroot
 * 
 */
public class DiskResourceQueryTemplateBuilder {

    private final String queryString;
    static final String CREATED_BY_FIELD = "creator.username:";
    static final String DATE_CREATED_FIELD = "dateCreated:";
    static final String DATE_MODIFIED_FIELD = "dateModified:";
    static final String FILE_QUERY_FIELD = "label:";
    static final String FILE_SIZE_FIELD = "fileSize:";
    static final String METADATA_FIELD = "metadata.\\*:";
    static final String SHARED_WITH_FIELD = "sharedWith:";
    final List<String> supportedFieldNames = Lists.newArrayList(CREATED_BY_FIELD,
                                                                DATE_CREATED_FIELD,
 DATE_MODIFIED_FIELD, FILE_QUERY_FIELD, FILE_SIZE_FIELD, METADATA_FIELD, SHARED_WITH_FIELD);

    public DiskResourceQueryTemplateBuilder(String queryString) {
        this.queryString = queryString;
    }

    public static DiskResourceQueryTemplate createDefaultFilter() {
        SearchAutoBeanFactory factory = GWT.create(SearchAutoBeanFactory.class);
        Splittable defFilter = StringQuoter.createSplittable();
        // Need to create full permissions by default in order to function as a "smart folder"
        Splittable permissions = StringQuoter.createSplittable();
        StringQuoter.create(true).assign(permissions, "own");
        StringQuoter.create(true).assign(permissions, "read");
        StringQuoter.create(true).assign(permissions, "write");
        permissions.assign(defFilter, "permissions");
        StringQuoter.create("/savedFilters/").assign(defFilter, "path");

        DiskResourceQueryTemplate dataSearchFilter = AutoBeanCodex.decode(factory, DiskResourceQueryTemplate.class, defFilter).as();
        dataSearchFilter.setCreatedWithin(factory.dateInterval().as());
        dataSearchFilter.setModifiedWithin(factory.dateInterval().as());
        dataSearchFilter.setFileSizeRange(factory.fileSizeRange().as());

        return dataSearchFilter;
    }

    public DiskResourceQueryTemplate build() throws ParseException {
        String ret = queryString.trim();
        
        // Some debugging related stuff
        if (queryString.startsWith("throw")) {
            throw new ParseException("Simulated parse error", 1);
        }

        final HashMap<String, List<String>> newHashMap = Maps.newHashMap();
        final Iterable<String> split = Splitter.on(" ").split(ret);
        String lastTerm = null;
        for (String term : split) {
            if (term.matches("^\\b\\w+:$")) {
                if (!isSupportedField(term)) {
                    // If field is not supported, do something
                }
                newHashMap.put(term, Lists.<String> newArrayList());
                lastTerm = term;
            } else if (lastTerm != null) {
                newHashMap.get(lastTerm).add(term);
            }
        }

        final Set<Entry<String, List<String>>> entrySet = newHashMap.entrySet();
        for (Entry<String, List<String>> entry : entrySet) {
            System.out.println("Key : " + entry.getKey() + " -> " + entry.getValue());
        }

        // Start by creating a map of the query string field names to their contents
        // There will be a notion of "supported field names"
        final DiskResourceQueryTemplate defaultFilter = createDefaultFilter();
        defaultFilter.setFileQuery(queryString);

        return defaultFilter;
    }


    /**
     * Determines if the given field is supported or not.
     * 
     * @param field
     * @return
     */
    private boolean isSupportedField(String field) {
        return supportedFieldNames.contains(field);
    }
}
