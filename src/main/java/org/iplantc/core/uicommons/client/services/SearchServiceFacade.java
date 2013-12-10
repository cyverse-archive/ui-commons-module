package org.iplantc.core.uicommons.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import org.iplantc.core.uicommons.client.models.search.DiskResourceQueryTemplate;
import org.iplantc.core.uicommons.client.models.search.util.DataSearchQueryBuilder;

import java.util.List;

/**
 * This service provides the ability to store, retrieve, and execute search queries.
 * 
 * <h2>Endpoints Used</h2>
 * <ul>
 * <li><a href=
 * "https://github.com/iPlantCollaborativeOpenSource/Donkey/blob/dev/doc/endpoints/filesystem/search.md#endpoints"
 * >/secured/filesystem/index</a></li>
 * <li><a href="">/secured/user-data</a></li>
 * </ul>
 * 
 * @author jstroot
 * 
 */
public interface SearchServiceFacade {

    String QUERY_TEMPLATE_KEY = "query_templates";

    /**
     * Saves the given query template to the {@link #QUERY_TEMPLATE_KEY}.
     * 
     * @param queryTemplate
     * @param callback
     */
    void saveQueryTemplate(DiskResourceQueryTemplate queryTemplate, AsyncCallback<String> callback);

    /**
     * Saves the given query templates to the {@link #QUERY_TEMPLATE_KEY}, on the user-data endpoint.
     * 
     * @param queryTemplates
     * @param callback
     */
    void saveQueryTemplates(List<DiskResourceQueryTemplate> queryTemplates, AsyncCallback<String> callback);

    /**
     * Retrieves the list of saved query templates using the {@link #QUERY_TEMPLATE_KEY}.
     * 
     * If there are no saved templates, then an empty list will be returned.
     * 
     * @param callback
     */
    void getSavedQueryTemplates(AsyncCallback<List<DiskResourceQueryTemplate>> callback);

    /**
     * Submits a search query build from the given filter.
     * 
     * Internally, this uses a {@link DataSearchQueryBuilder} to construct the query.
     * 
     * @param filter
     * @param callback
     */
    void submitSearchFromQueryTemplate(DiskResourceQueryTemplate queryTemplate, AsyncCallback<String> callback);

}
