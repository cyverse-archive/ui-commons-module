package org.iplantc.core.uicommons.client.services.impl;

import com.google.common.collect.Lists;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.google.web.bindery.autobean.shared.Splittable;
import com.google.web.bindery.autobean.shared.impl.StringQuoter;

import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfigBean;

import org.iplantc.core.uicommons.client.DEServiceFacade;
import org.iplantc.core.uicommons.client.models.DEProperties;
import org.iplantc.core.uicommons.client.models.diskresources.DiskResourceAutoBeanFactory;
import org.iplantc.core.uicommons.client.models.diskresources.File;
import org.iplantc.core.uicommons.client.models.diskresources.Folder;
import org.iplantc.core.uicommons.client.models.search.DiskResourceQueryTemplate;
import org.iplantc.core.uicommons.client.models.search.DiskResourceQueryTemplateList;
import org.iplantc.core.uicommons.client.models.search.SearchAutoBeanFactory;
import org.iplantc.core.uicommons.client.services.AsyncCallbackConverter;
import org.iplantc.core.uicommons.client.services.SearchServiceFacade;

import java.util.Collections;
import java.util.List;

public class SearchServiceFacadeImpl implements SearchServiceFacade {


    public final class QueryTemplateListCallbackConverter extends AsyncCallbackConverter<String, List<DiskResourceQueryTemplate>> {
        public QueryTemplateListCallbackConverter(AsyncCallback<List<DiskResourceQueryTemplate>> callback) {
            super(callback);
        }

        @Override
        protected List<DiskResourceQueryTemplate> convertFrom(String object) {
            // Expecting the string to be JSON list
            Splittable split = StringQuoter.createSplittable();
            StringQuoter.create(object).assign(split, DiskResourceQueryTemplateList.LIST_KEY);
            AutoBean<DiskResourceQueryTemplateList> decode = AutoBeanCodex.decode(searchAbFactory, DiskResourceQueryTemplateList.class, split);
            return decode.as().getQueryTemplateList();
        }
    }

    private final SearchAutoBeanFactory searchAbFactory;
    private final DEServiceFacade deServiceFacade;

    @Inject
    public SearchServiceFacadeImpl(final DEServiceFacade deServiceFacade, final SearchAutoBeanFactory searchAbFactory) {
        this.deServiceFacade = deServiceFacade;
        this.searchAbFactory = searchAbFactory;
    }

    @Override
    public void getSavedQueryTemplates(AsyncCallback<List<DiskResourceQueryTemplate>> callback) {
        getSavedQueryTemplatesStub(callback);
        // String address = getUserDataEndpointAddress();
        // ServiceCallWrapper wrapper = new ServiceCallWrapper(GET, address);
        // deServiceFacade.getServiceData(wrapper, new QueryTemplateListCallbackConverter(callback));

    }

    public void getSavedQueryTemplatesStub(AsyncCallback<List<DiskResourceQueryTemplate>> callback) {
        callback.onSuccess(stubbedSavedQueryTemplateList);
    }

    @Override
    public void saveQueryTemplates(List<DiskResourceQueryTemplate> queryTemplates, AsyncCallback<List<DiskResourceQueryTemplate>> callback) {
        saveQueryTemplateStub(queryTemplates, callback);

    }
    
    private final List<DiskResourceQueryTemplate> stubbedSavedQueryTemplateList = Lists.newArrayList();

    private void saveQueryTemplateStub(List<DiskResourceQueryTemplate> queryTemplates, AsyncCallback<List<DiskResourceQueryTemplate>> callback) {
        List<DiskResourceQueryTemplate> returnList = Lists.newArrayList();
        for (DiskResourceQueryTemplate template : queryTemplates) {

            // Serialize, set the "saved" key set to true, and re-encode
            Splittable serialized = AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(template));

            StringQuoter.create(true).assign(serialized, "saved");
            DiskResourceQueryTemplate as = AutoBeanCodex.decode(searchAbFactory, DiskResourceQueryTemplate.class, serialized).as();
            returnList.add(as);
        }
        stubbedSavedQueryTemplateList.clear();
        stubbedSavedQueryTemplateList.addAll(returnList);
        callback.onSuccess(returnList);
    }

    @Override
    public void submitSearchFromQueryTemplate(final DiskResourceQueryTemplate queryTemplate, final FilterPagingLoadConfigBean loadConfig, final AsyncCallback<Folder> callback) {
        // TODO Auto-generated method stub
        // Stub out functionality until search service comes online

        // Construct query string from given template
        // DataSearchQueryBuilder queryBuilder = new DataSearchQueryBuilder(queryTemplate);
        // String fullQuery = queryBuilder.buildFullQuery();
        submitSearchFromQueryTemplateStub(queryTemplate, loadConfig, callback);

    }

    public void submitSearchFromQueryTemplateStub(final DiskResourceQueryTemplate queryTemplate, final FilterPagingLoadConfigBean loadConfig, final AsyncCallback<Folder> callback) {
        // Create stubbed folder to return
        DiskResourceAutoBeanFactory drFactory = GWT.create(DiskResourceAutoBeanFactory.class);
        File file1 = createStubFile(drFactory, getUniqueId(), "File_Result_1.txt", "/");
        File file2 = createStubFile(drFactory, getUniqueId(), "File_Result_2.txt", "/");
        File file3 = createStubFile(drFactory, getUniqueId(), "File_Result_3.txt", "/");

        queryTemplate.setFiles(Lists.newArrayList(file1, file2, file3));
        queryTemplate.setFolders(Collections.<Folder> emptyList());

        callback.onSuccess(queryTemplate);
    }

    private File createStubFile(DiskResourceAutoBeanFactory factory, String id, String fileName, String path) {
        Splittable fileSplit = StringQuoter.createSplittable();
        Splittable permissions = StringQuoter.createSplittable();
        StringQuoter.create(true).assign(permissions, "own");
        StringQuoter.create(true).assign(permissions, "read");
        StringQuoter.create(true).assign(permissions, "write");
        permissions.assign(fileSplit, "permissions");

        File ret = AutoBeanCodex.decode(factory, File.class, fileSplit).as();
        ret.setId(id);
        ret.setName(fileName);
        ret.setPath(path);

        return ret;
    }

    private String getUserDataEndpointAddress(){
        return DEProperties.getInstance().getMuleServiceBaseUrl() + "user-data?key=" + QUERY_TEMPLATE_KEY;
    }

    @Override
    public String getUniqueId() {
        return Document.get().createUniqueId();
    }

    @Override
    public List<DiskResourceQueryTemplate> createFrozenList(List<DiskResourceQueryTemplate> queryTemplates) {
        List<DiskResourceQueryTemplate> toSave = Lists.newArrayList();
        for (DiskResourceQueryTemplate qt : queryTemplates) {
            // Create copy of template
            Splittable qtSplittable = AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(qt));
            AutoBean<DiskResourceQueryTemplate> decode = AutoBeanCodex.decode(searchAbFactory, DiskResourceQueryTemplate.class, qtSplittable);

            // Freeze the autobean
            decode.setFrozen(true);
            toSave.add(decode.as());
        }
        return Collections.unmodifiableList(toSave);
    }

}
