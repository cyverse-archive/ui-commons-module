package org.iplantc.core.uicommons.client.services;

import com.google.common.collect.Lists;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwtmockito.GxtMockitoTestRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.iplantc.core.uicommons.client.DEServiceFacade;
import org.iplantc.core.uicommons.client.models.diskresources.Permissions;
import org.iplantc.core.uicommons.client.models.search.DiskResourceQueryTemplate;
import org.iplantc.core.uicommons.client.models.search.SearchAutoBeanFactory;
import org.iplantc.core.uicommons.client.services.impl.SearchServiceFacadeImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.List;

@RunWith(GxtMockitoTestRunner.class)
public class SearchServiceFacadeTest {

    @Mock SearchAutoBeanFactory searchAbFactory;

    @Mock DEServiceFacade deServiceFacade;

    private SearchServiceFacade searchService;

    @Before public void setUp() {
        searchService = new SearchServiceFacadeImpl(deServiceFacade, searchAbFactory);
    }

    /**
     * Verifies the following when the {@link SearchServiceFacade#saveQueryTemplates()} method is
     * invoked:<br/>
     * 
     * <ol>
     * <li>full permissions are added before persisting the templates</li>
     * </ol>
     * 
     */
    @Ignore
    @Test public void testSaveQueryTemplates() {

        Permissions incorrectPermissions = mock(Permissions.class);
        when(incorrectPermissions.isOwner()).thenReturn(false);
        when(incorrectPermissions.isReadable()).thenReturn(true);
        when(incorrectPermissions.isWritable()).thenReturn(true);
        Permissions correctPermissions = mock(Permissions.class);
        when(incorrectPermissions.isOwner()).thenReturn(true);
        when(incorrectPermissions.isReadable()).thenReturn(true);
        when(incorrectPermissions.isWritable()).thenReturn(true);


        DiskResourceQueryTemplate qt1 = mock(DiskResourceQueryTemplate.class);
        DiskResourceQueryTemplate qt2 = mock(DiskResourceQueryTemplate.class);
        DiskResourceQueryTemplate qt3 = mock(DiskResourceQueryTemplate.class);
        
        // qt1 will return null for call to getPermissions. Mockito default behavior.
        when(qt2.getPermissions()).thenReturn(incorrectPermissions);
        when(qt3.getPermissions()).thenReturn(correctPermissions);
        
        List<DiskResourceQueryTemplate> qtList = Lists.newArrayList(qt1, qt2, qt3);

        searchService.saveQueryTemplates(qtList, mock(AsyncCallback.class));

        // Verify that full permissions are added when queryTemplate permissions are null

        // Verify that full permissions are added when queryTemplate permissions are not null, but
        // incorrect
    }

    @Test public void testGetSavedQueryTemplates() {
        // fail("Not yet implemented");
    }

    @Test public void testSubmitSearchFromQueryTemplate() {
        // fail("Not yet implemented");
    }

}
