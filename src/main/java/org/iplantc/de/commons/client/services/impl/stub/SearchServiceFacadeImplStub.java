package org.iplantc.de.commons.client.services.impl.stub;

import org.iplantc.de.client.models.diskResources.DiskResource;
import org.iplantc.de.client.models.diskResources.DiskResourceAutoBeanFactory;
import org.iplantc.de.client.models.diskResources.File;
import org.iplantc.de.client.models.diskResources.Folder;
import org.iplantc.de.client.models.search.DiskResourceQueryTemplate;
import org.iplantc.de.client.models.search.SearchAutoBeanFactory;
import org.iplantc.de.commons.client.services.SearchServiceFacade;

import com.google.common.collect.Lists;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.google.web.bindery.autobean.shared.Splittable;
import com.google.web.bindery.autobean.shared.impl.StringQuoter;

import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfigBean;

import java.util.Collections;
import java.util.List;

public class SearchServiceFacadeImplStub implements SearchServiceFacade {

    private final List<DiskResourceQueryTemplate> stubbedSavedQueryTemplateList = Lists.newArrayList();
    private final SearchAutoBeanFactory searchAbFactory;

    @Inject
    public SearchServiceFacadeImplStub(final SearchAutoBeanFactory searchAbFactory) {
        this.searchAbFactory = searchAbFactory;
    }

    @Override
    public List<DiskResourceQueryTemplate> createFrozenList(List<DiskResourceQueryTemplate> queryTemplates) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void getSavedQueryTemplates(AsyncCallback<List<DiskResourceQueryTemplate>> callback) {
        getSavedQueryTemplatesStub(callback);
    }

    @Override
    public void saveQueryTemplates(List<DiskResourceQueryTemplate> queryTemplates, AsyncCallback<List<DiskResourceQueryTemplate>> callback) {
        saveQueryTemplateStub(queryTemplates, callback);
    }

    @Override
    public void submitSearchFromQueryTemplate(DiskResourceQueryTemplate queryTemplate, FilterPagingLoadConfigBean loadConfig, SearchType searchType, AsyncCallback<List<DiskResource>> callback) {
        submitSearchFromQueryTemplateStub(queryTemplate, loadConfig, searchType, callback);

    }

    private File createStubFile(DiskResourceAutoBeanFactory factory, String fileName, String path) {
        Splittable fileSplit = StringQuoter.createSplittable();
        Splittable permissions = StringQuoter.createSplittable();
        StringQuoter.create(true).assign(permissions, "own");
        StringQuoter.create(true).assign(permissions, "read");
        StringQuoter.create(true).assign(permissions, "write");
        permissions.assign(fileSplit, "permissions");

        File ret = AutoBeanCodex.decode(factory, File.class, fileSplit).as();
        ret.setId(fileName);
        ret.setName(fileName);
        ret.setPath(path);

        return ret;
    }

    void submitSearchFromQueryTemplateStub(final DiskResourceQueryTemplate queryTemplate, final FilterPagingLoadConfigBean loadConfig, final SearchType searchType,
            final AsyncCallback<List<DiskResource>> callback) {
        // Create stubbed folder to return
        DiskResourceAutoBeanFactory drFactory = GWT.create(DiskResourceAutoBeanFactory.class);
        File file1 = createStubFile(drFactory, "File_Result_1.txt", "/");
        File file2 = createStubFile(drFactory, "File_Result_2.txt", "/");
        File file3 = createStubFile(drFactory, "File_Result_3.txt", "/");

        queryTemplate.setFiles(Lists.newArrayList(file1, file2, file3));
        queryTemplate.setFolders(Collections.<Folder> emptyList());

        final List<DiskResource> files = Lists.newArrayList();
        files.addAll(queryTemplate.getFiles());
        callback.onSuccess(files);
    }

    void saveQueryTemplateStub(List<DiskResourceQueryTemplate> queryTemplates, AsyncCallback<List<DiskResourceQueryTemplate>> callback) {
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

    void getSavedQueryTemplatesStub(AsyncCallback<List<DiskResourceQueryTemplate>> callback) {
        callback.onSuccess(stubbedSavedQueryTemplateList);
    }

}
