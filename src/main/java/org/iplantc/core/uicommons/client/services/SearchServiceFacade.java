package org.iplantc.core.uicommons.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import org.iplantc.core.uicommons.client.models.search.DiskResourceQueryTemplate;
import org.iplantc.core.uicommons.client.models.search.util.DataSearchQueryBuilder;

public interface SearchServiceFacade {

    void saveFilter(DiskResourceQueryTemplate filter, AsyncCallback<String> callback);

    /**
     * Submits a search query build from the given filter.
     * 
     * Internally, this uses a {@link DataSearchQueryBuilder} to construct the query.
     * 
     * @param filter
     * @param callback
     */
    void submitSearchFromFilter(DiskResourceQueryTemplate filter, AsyncCallback<String> callback);

}
