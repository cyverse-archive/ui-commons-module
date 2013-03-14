/**
 * 
 */
package org.iplantc.core.uicommons.client.collaborators.util;

import java.util.List;

import org.iplantc.core.uicommons.client.ErrorHandler;
import org.iplantc.core.uicommons.client.collaborators.models.Collaborator;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.loader.FilterConfig;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;

/**
 * @author sriram
 *
 */
public class UserSearchRPCProxy extends RpcProxy<FilterPagingLoadConfig, ListLoadResult<Collaborator>> {

    private String lastQueryText = ""; //$NON-NLS-1$

    public UserSearchRPCProxy() {
    }

    public String getLastQueryText() {
        return lastQueryText;
    }

    @Override
    public void load(FilterPagingLoadConfig loadConfig,
            final AsyncCallback<ListLoadResult<Collaborator>> callback) {

        List<FilterConfig> filters = loadConfig.getFilters();
        if (filters != null && filters.size() > 0) {
            lastQueryText = filters.get(0).getValue();
        }

        if (lastQueryText == null || lastQueryText.isEmpty()) {
            // nothing to search
            return;
        }

        CollaboratorsUtil.search(lastQueryText, new AsyncCallback<Void>() {
            @SuppressWarnings("serial")
            @Override
            public void onSuccess(Void result) {
                callback.onSuccess(new ListLoadResult<Collaborator>() {

                    @Override
                    public List<Collaborator> getData() {
                        return CollaboratorsUtil.getSearchResutls();
                    }
                });
            }

            @Override
            public void onFailure(Throwable caught) {
                ErrorHandler.post(caught);
                callback.onFailure(caught);
            }
        });

    }

}
