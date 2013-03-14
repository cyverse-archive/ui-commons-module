/**
 * 
 */
package org.iplantc.core.uicommons.client.collaborators.util;

import org.iplantc.core.uicommons.client.collaborators.models.Collaborator;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.sencha.gxt.widget.core.client.form.ComboBox;

/**
 * @author sriram
 *
 */
public class UserSearchField implements IsWidget {

    private final UserSearchRPCProxy searchProxy;

    private ComboBox<Collaborator> combo;

    interface ExampleTemplate extends XTemplates {
        @XTemplate("<div class ='search-item'> {c.userName} </div")
        SafeHtml render(Collaborator c);
    }

    public UserSearchField() {
        this.searchProxy = new UserSearchRPCProxy();
        ListLoader<FilterPagingLoadConfig, ListLoadResult<Collaborator>> loader = new ListLoader<FilterPagingLoadConfig, ListLoadResult<Collaborator>>(
                searchProxy);
        // loader.add
    }

    @Override
    public Widget asWidget() {
        return combo;
    }

}
