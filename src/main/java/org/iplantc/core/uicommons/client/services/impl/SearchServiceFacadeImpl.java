package org.iplantc.core.uicommons.client.services.impl;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.Splittable;
import com.google.web.bindery.autobean.shared.impl.StringQuoter;

import static org.iplantc.de.shared.services.BaseServiceCallWrapper.Type.GET;

import org.iplantc.core.uicommons.client.DEServiceFacade;
import org.iplantc.core.uicommons.client.models.DEProperties;
import org.iplantc.core.uicommons.client.models.search.DiskResourceQueryTemplate;
import org.iplantc.core.uicommons.client.models.search.DiskResourceQueryTemplateList;
import org.iplantc.core.uicommons.client.models.search.SearchAutoBeanFactory;
import org.iplantc.core.uicommons.client.services.AsyncCallbackConverter;
import org.iplantc.core.uicommons.client.services.SearchServiceFacade;
import org.iplantc.de.shared.services.ServiceCallWrapper;

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

        String address = getUserDataEndpointAddress();
        ServiceCallWrapper wrapper = new ServiceCallWrapper(GET, address);
        deServiceFacade.getServiceData(wrapper, new QueryTemplateListCallbackConverter(callback));

    }

    @Override
    public void saveQueryTemplates(List<DiskResourceQueryTemplate> queryTemplates, AsyncCallback<String> callback) {
        // TODO Auto-generated method stub

    }
    
    @Override
    public void submitSearchFromQueryTemplate(DiskResourceQueryTemplate queryTemplate, AsyncCallback<String> callback) {
        // TODO Auto-generated method stub

    }

    private String getUserDataEndpointAddress(){
        return DEProperties.getInstance().getMuleServiceBaseUrl() + "user-data?key=" + QUERY_TEMPLATE_KEY;
    }

    @Override
    public String getUniqueId() {
        return Document.get().createUniqueId();
    }

}
