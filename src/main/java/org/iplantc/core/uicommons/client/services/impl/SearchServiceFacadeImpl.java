package org.iplantc.core.uicommons.client.services.impl;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.google.web.bindery.autobean.shared.Splittable;
import com.google.web.bindery.autobean.shared.impl.StringQuoter;

import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfigBean;

import static org.iplantc.de.shared.services.BaseServiceCallWrapper.Type.GET;
import static org.iplantc.de.shared.services.BaseServiceCallWrapper.Type.POST;

import org.iplantc.core.uicommons.client.DEServiceFacade;
import org.iplantc.core.uicommons.client.models.DEProperties;
import org.iplantc.core.uicommons.client.models.UserInfo;
import org.iplantc.core.uicommons.client.models.diskresources.DiskResourceAutoBeanFactory;
import org.iplantc.core.uicommons.client.models.diskresources.File;
import org.iplantc.core.uicommons.client.models.diskresources.Folder;
import org.iplantc.core.uicommons.client.models.search.DiskResourceQueryTemplate;
import org.iplantc.core.uicommons.client.models.search.DiskResourceQueryTemplateList;
import org.iplantc.core.uicommons.client.models.search.SearchAutoBeanFactory;
import org.iplantc.core.uicommons.client.services.AsyncCallbackConverter;
import org.iplantc.core.uicommons.client.services.Endpoints;
import org.iplantc.core.uicommons.client.services.ReservedBuckets;
import org.iplantc.core.uicommons.client.services.SearchServiceFacade;
import org.iplantc.de.shared.services.ServiceCallWrapper;

import java.util.Collections;
import java.util.List;

public class SearchServiceFacadeImpl implements SearchServiceFacade {


    final class SubmitSearchCallbackConverter extends AsyncCallbackConverter<String, Folder> {
        private final DiskResourceQueryTemplate queryTemplate;
        private final UserInfo userInfo1;
        private final DiskResourceAutoBeanFactory factory;

        SubmitSearchCallbackConverter(AsyncCallback<Folder> callback, DiskResourceQueryTemplate queryTemplate, final UserInfo userInfo, final DiskResourceAutoBeanFactory factory) {
            super(callback);
            this.queryTemplate = queryTemplate;
            this.userInfo1 = userInfo;
            this.factory = factory;
        }

        @Override
        protected Folder convertFrom(String object) {
            // Clear previous collections from template
            queryTemplate.setFiles(Lists.<File> newArrayList());
            queryTemplate.setFolders(Lists.<Folder> newArrayList());

            Splittable split = StringQuoter.split(object);
            // Set the total returned on the query template
            queryTemplate.setTotal(Double.valueOf(split.get("total").asNumber()).intValue());
            if (split.get("matches").isIndexed()) {
                final int size = split.get("matches").size();
                for (int i = 0; i < size; i++) {
                    final Splittable child = split.get("matches").get(i);
                    final String asString = child.get("type").asString();
                    Splittable entity = child.get("entity");

                    reMapDateKeys(entity);
                    reMapPermissions(entity);
                    reMapPath(entity);

                    if (asString.equals("folder")) {
                        decodeFolderIntoQueryTemplate(entity, queryTemplate, factory);
                    } else if (asString.equals("file")) {
                        reMapFileSize(entity);
                        decodeFileIntoQueryTemplate(entity, queryTemplate, factory);
                    }
                }
            }
            return queryTemplate;
        }

        private void decodeFolderIntoQueryTemplate(Splittable entity, DiskResourceQueryTemplate queryTemplate, DiskResourceAutoBeanFactory factory) {
            final AutoBean<Folder> decodeFolder = AutoBeanCodex.decode(factory, Folder.class, entity);
            queryTemplate.getFolders().add(decodeFolder.as());
        }

        private void decodeFileIntoQueryTemplate(Splittable entity, DiskResourceQueryTemplate queryTemplate, DiskResourceAutoBeanFactory factory) {
            // Re-map JSON keys
            GWT.log("Re-mapping JSON keys!  'fileSize' -> 'file-size'");
            entity.get("fileSize").assign(entity, "file-size");
            final AutoBean<File> decodeFile = AutoBeanCodex.decode(factory, File.class, entity);
            queryTemplate.getFiles().add(decodeFile.as());
        }

        private void reMapFileSize(Splittable entity) {
            GWT.log("Re-mapping JSON keys!  'fileSize' -> 'file-size'");
            entity.get("fileSize").assign(entity, "file-size");
        }

        private void reMapDateKeys(Splittable entity) {
            // Re-map JSON keys
            GWT.log("Re-mapping JSON keys!  'dateModified' -> 'date-modified'");
            final long dateModifiedInSec = Double.valueOf(entity.get("dateModified").asNumber()).longValue();
            StringQuoter.create(dateModifiedInSec * 1000).assign(entity, "date-modified");
            // Re-map JSON keys
            GWT.log("Re-mapping JSON keys!  'dateCreated' -> 'date-created'");
            final long dateCreatedInSec = Double.valueOf(entity.get("dateCreated").asNumber()).longValue();
            StringQuoter.create(dateCreatedInSec * 1000).assign(entity, "date-created");
        }

        private void reMapPermissions(Splittable entity) {
            Splittable userPermissionsList = entity.get("userPermissions");
            for (int i = 0; i < userPermissionsList.size(); i++) {
                Splittable permission = userPermissionsList.get(i);
                final String permissionString = permission.get("permission").asString();
                final String userString = permission.get("user").asString();
                final Iterable<String> userStringSplit = Splitter.on("#").split(userString);
                Splittable newPermissionsSplit = StringQuoter.createSplittable();
                if (userStringSplit.iterator().next().equals(userInfo1.getUsername())) {
                    if (permissionString.equals("own")) {
                        StringQuoter.create(true).assign(newPermissionsSplit, "own");
                        StringQuoter.create(true).assign(newPermissionsSplit, "write");
                        StringQuoter.create(true).assign(newPermissionsSplit, "read");
                    } else if (permissionString.equals("write")) {
                        StringQuoter.create(false).assign(newPermissionsSplit, "own");
                        StringQuoter.create(true).assign(newPermissionsSplit, "write");
                        StringQuoter.create(true).assign(newPermissionsSplit, "read");
                    } else if (permissionString.equals("read")) {
                        StringQuoter.create(false).assign(newPermissionsSplit, "own");
                        StringQuoter.create(false).assign(newPermissionsSplit, "write");
                        StringQuoter.create(true).assign(newPermissionsSplit, "read");
                    }
                    newPermissionsSplit.assign(entity, "permissions");
                    break;
                }
            }
        }

        private void reMapPath(Splittable entity) {
            final String id = entity.get("id").asString();
            // StringQuoter.create(DiskResourceUtil.parseParent(id)).assign(entity, "path");
            StringQuoter.create(id).assign(entity, "path");
        }

    }

    public final class BooleanCallbackConverter extends AsyncCallbackConverter<String, Boolean> {
        public BooleanCallbackConverter(AsyncCallback<Boolean> callback) {
            super(callback);
        }

        @Override
        protected Boolean convertFrom(String object) {
            final Splittable split = StringQuoter.split(object);
            if (split.isUndefined("success")) {
                GWT.log("saveQueryTemplates callback return is missing \"success\" json key:\n\t" + split.getPayload());
                return null;
            }
            if (!split.get("success").isBoolean()) {
                GWT.log("saveQueryTemplates callback \"success\" json key is not a boolean but should be:\n\t" + split.getPayload());
                return null;
            }

            return split.get("success").asBoolean();
        }
    }

    public final class QueryTemplateListCallbackConverter extends AsyncCallbackConverter<String, List<DiskResourceQueryTemplate>> {
        public QueryTemplateListCallbackConverter(AsyncCallback<List<DiskResourceQueryTemplate>> callback) {
            super(callback);
        }

        @Override
        protected List<DiskResourceQueryTemplate> convertFrom(String object) {
            if (Strings.isNullOrEmpty(object)) {
                return Collections.emptyList();
            }
            // Expecting the string to be JSON list
            Splittable split = StringQuoter.createSplittable();
            StringQuoter.split(object).assign(split, DiskResourceQueryTemplateList.LIST_KEY);
            AutoBean<DiskResourceQueryTemplateList> decode = AutoBeanCodex.decode(searchAbFactory, DiskResourceQueryTemplateList.class, split);
            final List<DiskResourceQueryTemplate> queryTemplateList = decode.as().getQueryTemplateList();
            for (DiskResourceQueryTemplate qt : queryTemplateList) {
                qt.setDirty(false);
                qt.setFiles(Lists.<File> newArrayList());
                qt.setFolders(Lists.<Folder> newArrayList());
            }

            return queryTemplateList;
        }
    }

    private final ReservedBuckets buckets;
    private final DEServiceFacade deServiceFacade;
    private final Endpoints endpoints;
    private final SearchAutoBeanFactory searchAbFactory;
    private final UserInfo userInfo;
    private final DiskResourceAutoBeanFactory drFactory;

    @Inject
    public SearchServiceFacadeImpl(final DEServiceFacade deServiceFacade, final SearchAutoBeanFactory searchAbFactory, final DiskResourceAutoBeanFactory drFactory, final Endpoints endpoints,
            final ReservedBuckets buckets, final UserInfo userInfo) {
        this.deServiceFacade = deServiceFacade;
        this.searchAbFactory = searchAbFactory;
        this.drFactory = drFactory;
        this.endpoints = endpoints;
        this.buckets = buckets;
        this.userInfo = userInfo;
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

    @Override
    public void getSavedQueryTemplates(AsyncCallback<List<DiskResourceQueryTemplate>> callback) {
        //String address = endpoints.buckets() + "/" + userInfo.getUsername() + "/" + buckets.queryTemplates();
        String address = DEProperties.getInstance().getMuleServiceBaseUrl() + "buckets/" + userInfo.getUsername() + "/reserved/" + buckets.queryTemplates();
        ServiceCallWrapper wrapper = new ServiceCallWrapper(GET, address);
        deServiceFacade.getServiceData(wrapper, new QueryTemplateListCallbackConverter(callback));
    }
    
    @Override
    public void saveQueryTemplates(List<DiskResourceQueryTemplate> queryTemplates, AsyncCallback<Boolean> callback) {
        String address = DEProperties.getInstance().getMuleServiceBaseUrl() + "buckets/" + userInfo.getUsername() + "/reserved/" + buckets.queryTemplates();

        // check to see if query templates all have names, and that they are unique.throw illegal
        // argument exception
        Splittable body = StringQuoter.createIndexed();
        int index = 0;
        for (DiskResourceQueryTemplate qt : queryTemplates) {
            final Splittable encode = AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(qt));
            encode.assign(body, index++);
        }
        ServiceCallWrapper wrapper = new ServiceCallWrapper(POST, address, body.getPayload());
        deServiceFacade.getServiceData(wrapper, new BooleanCallbackConverter(callback));
    }

    @Override
    public void submitSearchFromQueryTemplate(final DiskResourceQueryTemplate queryTemplate, final FilterPagingLoadConfigBean loadConfig, final SearchType searchType,
            final AsyncCallback<Folder> callback) {
        String queryParameter = "q=" + URL.encodePathSegment(new DataSearchQueryBuilder(queryTemplate).buildFullQuery());
        String limitParameter = "&limit=" + loadConfig.getLimit();
        String offsetParameter = "&offset=" + loadConfig.getOffset();
        String typeParameter = "&type=" + ((searchType == null) ? SearchType.ANY.toString() : searchType.toString());

        String address = DEProperties.getInstance().getDataMgmtBaseUrl() + "index?" + queryParameter + limitParameter + offsetParameter + typeParameter;

        ServiceCallWrapper wrapper = new ServiceCallWrapper(GET, address);
        deServiceFacade.getServiceData(wrapper, new SubmitSearchCallbackConverter(callback, queryTemplate, userInfo, drFactory));
    }

}
