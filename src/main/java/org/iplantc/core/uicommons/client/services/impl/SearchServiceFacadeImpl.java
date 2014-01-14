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

import static org.iplantc.de.shared.services.BaseServiceCallWrapper.Type.GET;
import static org.iplantc.de.shared.services.BaseServiceCallWrapper.Type.POST;

import org.iplantc.core.jsonutil.JsonUtil;
import org.iplantc.core.uicommons.client.DEServiceFacade;
import org.iplantc.core.uicommons.client.models.UserInfo;
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
            // Expecting the string to be JSON list
            Splittable split = StringQuoter.createSplittable();
            StringQuoter.create(object).assign(split, DiskResourceQueryTemplateList.LIST_KEY);
            AutoBean<DiskResourceQueryTemplateList> decode = AutoBeanCodex.decode(searchAbFactory, DiskResourceQueryTemplateList.class, split);
            return decode.as().getQueryTemplateList();
        }
    }

    private final ReservedBuckets buckets;
    private final DEServiceFacade deServiceFacade;
    private final Endpoints endpoints;
    private final SearchAutoBeanFactory searchAbFactory;
    private final UserInfo userInfo;

    @Inject
    public SearchServiceFacadeImpl(final DEServiceFacade deServiceFacade, final SearchAutoBeanFactory searchAbFactory, final Endpoints endpoints, final ReservedBuckets buckets, final UserInfo userInfo) {
        this.deServiceFacade = deServiceFacade;
        this.searchAbFactory = searchAbFactory;
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
        String address = endpoints.buckets() + "/" + userInfo.getUsername() + "/" + buckets.queryTemplates();
        ServiceCallWrapper wrapper = new ServiceCallWrapper(GET, address);
        deServiceFacade.getServiceData(wrapper, new QueryTemplateListCallbackConverter(callback));
    }
    
    @Override
    public String getUniqueId() {
        return Document.get().createUniqueId();
    }

    @Override
    public void saveQueryTemplates(List<DiskResourceQueryTemplate> queryTemplates, AsyncCallback<Boolean> callback) {
        String address = endpoints.buckets() + "/" + userInfo.getUsername() + "/" + buckets.queryTemplates();

        Splittable body = StringQuoter.createSplittable();
        int index = 0;
        for (DiskResourceQueryTemplate qt : queryTemplates) {
            final Splittable encode = AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(qt));
            encode.assign(body, index++);
        }
        GWT.log("saveQueryTemplates body payload" + JsonUtil.prettyPrint(body));
        ServiceCallWrapper wrapper = new ServiceCallWrapper(POST, address, body.getPayload());
        deServiceFacade.getServiceData(wrapper, new BooleanCallbackConverter(callback));
    }

    @Override
    public void submitSearchFromQueryTemplate(final DiskResourceQueryTemplate queryTemplate, final FilterPagingLoadConfigBean loadConfig, final SearchType searchType,
            final AsyncCallback<Folder> callback) {
        String queryParameter = "q=" + new DataSearchQueryBuilder(queryTemplate).buildFullQuery();
        String limitParameter = "&limit=" + loadConfig.getLimit();
        String offsetParameter = "&offset=" + loadConfig.getOffset();
        String typeParameter = "&type=" + ((searchType == null) ? SearchType.ANY.toString() : searchType.toString());

        String address = endpoints.filesystemIndex() + "?" + queryParameter + limitParameter + offsetParameter + typeParameter;

        ServiceCallWrapper wrapper = new ServiceCallWrapper(GET, address);
        deServiceFacade.getServiceData(wrapper, new AsyncCallbackConverter<String, Folder>(callback) {

            @Override
            protected Folder convertFrom(String object) {
                return null;
            }
        });
    }

}
