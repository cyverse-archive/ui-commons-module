package org.iplantc.core.uicommons.client.services;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.autobean.shared.AutoBean;

import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfigBean;

import org.iplantc.core.uicommons.client.models.diskresources.Folder;
import org.iplantc.core.uicommons.client.models.search.DiskResourceQueryTemplate;
import org.iplantc.core.uicommons.client.services.impl.DataSearchQueryBuilder;

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
     * Saves the given query templates to the {@link #QUERY_TEMPLATE_KEY}, on the user-data endpoint.
     * 
     * This method will ensure that the saved queries all have full permissions set before persisting the
     * given queryTemplate.
     * 
     * @param queryTemplates
     * @param callback returns the set of persisted templates on success. These templates will have their
     *            {@link DiskResourceQueryTemplate#isSaved()} flag set to true and the
     *            {@link DiskResourceQueryTemplate#isDirty()} flags set to false.
     */
    void saveQueryTemplates(List<DiskResourceQueryTemplate> queryTemplates, AsyncCallback<List<DiskResourceQueryTemplate>> callback);

    /**
     * Retrieves the list of saved query templates using the {@link #QUERY_TEMPLATE_KEY}.
     * 
     * If there are no saved templates, then an empty list will be returned.
     * 
     * @param callback returns the set of persisted templates on success. These templates will have their
     *            {@link DiskResourceQueryTemplate#isSaved()} flag set to true and the
     *            {@link DiskResourceQueryTemplate#isDirty()} flags set to false.
     */
    void getSavedQueryTemplates(AsyncCallback<List<DiskResourceQueryTemplate>> callback);

    /**
     * Submits a search query build from the given filter.
     * 
     * Internally, this uses a {@link DataSearchQueryBuilder} to construct the query.
     * 
     * @param queryTemplate the template used to construct the query string.
     * @param loadConfig the load config which defines the offset and limit for the paged request
     * @param callback executed when RPC call completes.
     */
    void submitSearchFromQueryTemplate(final DiskResourceQueryTemplate queryTemplate, final FilterPagingLoadConfigBean loadConfig, final AsyncCallback<Folder> callback);

    /**
     * 
     * @return provides an string id which is unique within the app document.
     * @see Document#createUniqueId()
     */
    String getUniqueId();

    /**
     * @param queryTemplates the list to be copied
     * @return a list of frozen query templates
     * @see AutoBean#setFrozen(boolean)
     */
    List<DiskResourceQueryTemplate> createFrozenList(List<DiskResourceQueryTemplate> queryTemplates);

}
