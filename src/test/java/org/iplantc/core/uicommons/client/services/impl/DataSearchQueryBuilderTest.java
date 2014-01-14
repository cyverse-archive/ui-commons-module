package org.iplantc.core.uicommons.client.services.impl;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.gwtmockito.GwtMockitoTestRunner;

import com.sencha.gxt.core.client.util.DateWrapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.iplantc.core.uicommons.client.models.search.DateInterval;
import org.iplantc.core.uicommons.client.models.search.DiskResourceQueryTemplate;
import org.iplantc.core.uicommons.client.models.search.FileSizeRange;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * TODO Verify that if a field in the given {@link DiskResourceQueryTemplate} is null or empty, that its
 * corresponding term will be ommitted.
 * 
 * @author jstroot
 * 
 */
@RunWith(GwtMockitoTestRunner.class)
public class DataSearchQueryBuilderTest {

    /*
     * These are the supported fields
     */
    static final String CREATED_BY_FIELD = "creator.username:";
    static final String DATE_CREATED_FIELD = "dateCreated:";
    static final String DATE_MODIFIED_FIELD = "dateModified:";
    static final String FILE_QUERY_FIELD = "label:";
    static final String FILE_SIZE_FIELD = "fileSize:";
    static final String METADATA_FIELD = "metadata.\\*:";
    static final String SHARED_WITH_FIELD = "sharedWith:";
    
    @Mock DiskResourceQueryTemplate dsf;

    @Before public void setUp() {
        
    } 

    @Test public void testBuildQuery() {
        final String expectedFileQuery = setFileQuery("some file query", dsf);
        final String expectedModifiedWithin = setModifiedWithin(new Date(), new DateWrapper().addDays(1).asDate(), dsf);
        final String expectedCreatedWithin = setCreatedWithin(new Date(), new DateWrapper().addMonths(1).asDate(), dsf);
        final String expectedNegatedFile = setNegatedFileQuery(Lists.newArrayList("term1", "term2", "term3"), dsf);
        final String expectedMetadataQuery = setMetadataQuery("some metadata query", dsf);
        final String expectedNegatedMetadata = setNegatedMetadata(Lists.newArrayList("negatedTerm1", "negatedTerm2", "negatedTerm3"), dsf);
        final String expectedCreatedBy = setCreatedBy("someUser", dsf);
        final String expectedFileSizeRange = setFileSizeRange(0.1, 100.78763, dsf);
        final String expectedSharedWith = setSharedWith("some users who were shared with", dsf);

        String result = new DataSearchQueryBuilder(dsf).buildFullQuery();
        
        assertTrue(result.contains(expectedFileQuery));
        assertTrue(result.contains(expectedModifiedWithin));
        assertTrue(result.contains(expectedCreatedWithin));
        assertTrue(result.contains(expectedNegatedFile));
        assertTrue(result.contains(expectedMetadataQuery));
        assertTrue(result.contains(expectedNegatedMetadata));
        assertTrue(result.contains(expectedCreatedBy));
        assertTrue(result.contains(expectedFileSizeRange));
        assertTrue(result.contains(expectedSharedWith));
    }

    @Test public void testCreatedBy() {
        final String expectedValue = setCreatedBy("someUser", dsf);

        String result = new DataSearchQueryBuilder(dsf).createdBy().toString();
        assertEquals(expectedValue, result);
    }

    @Test public void testCreatedWithin() {
        final String expectedValue = setCreatedWithin(new Date(), new DateWrapper().addDays(1).asDate(), dsf);

        String result = new DataSearchQueryBuilder(dsf).createdWithin().toString();
        assertEquals(expectedValue, result);
    }
    
    @Test public void testFile() {
        final String expectedValue = setFileQuery("some words in query", dsf);

        String result = new DataSearchQueryBuilder(dsf).file().toString();
        assertEquals(expectedValue, result);
    }

    @Test public void testFileSizeRange() {
        final String expectedValue = setFileSizeRange(0.01, 100.10101, dsf);

        String result = new DataSearchQueryBuilder(dsf).fileSizeRange().toString();
        assertEquals(expectedValue, result);
    }

    @Test public void testMetadata() {
        final String expectedValue = setMetadataQuery("some metadata to search for", dsf);

        String result = new DataSearchQueryBuilder(dsf).metadata().toString();
        assertEquals(expectedValue, result);
    }

    @Test public void testModifiedWithin() {
        final Date fromDate = new Date();
        final Date toDate = new DateWrapper(fromDate).addDays(2).asDate();
        final String expectedValue = setModifiedWithin(fromDate, toDate, dsf);

        String result = new DataSearchQueryBuilder(dsf).modifiedWithin().toString();
        assertEquals(expectedValue, result);
    }

    @Test public void testNegatedFile() {
        final String term1 = "term1";
        final String term2 = "term2";
        final String term3 = "term3";
        final ArrayList<String> newArrayList = Lists.newArrayList(term1, term2, term3);
        final String expectedValue = setNegatedFileQuery(newArrayList, dsf);

        String result = new DataSearchQueryBuilder(dsf).negatedFile().toString();
        assertEquals(expectedValue, result);
    }

    @Test public void testNegatedMetadata() {
        final String term1 = "metadataTerm1";
        final String term2 = "metadataTerm2";
        final String term3 = "term3";
        final ArrayList<String> newArrayList = Lists.newArrayList(term1, term2, term3);

        final String expectedValue = setNegatedMetadata(newArrayList, dsf);

        String result = new DataSearchQueryBuilder(dsf).negatedMetadata().toString();
        assertEquals(expectedValue, result);
    }

    @Test public void testSharedWith() {
        final String retVal = "user that are shared with";
        final String expectedValue = setSharedWith(retVal, dsf);

        String result = new DataSearchQueryBuilder(dsf).sharedWith().toString();
        assertEquals(expectedValue, result);
    }

    /**
     * @param givenValue
     * @param drqt
     * @return the expected value
     */
    private String setCreatedBy(final String givenValue, final DiskResourceQueryTemplate drqt) {
        when(dsf.getCreatedBy()).thenReturn(givenValue);
        return CREATED_BY_FIELD + givenValue;
    }

    /**
     * @param fromDate
     * @param toDate
     * @param drqt
     * @return the expected value
     */
    private String setCreatedWithin(final Date fromDate, final Date toDate, final DiskResourceQueryTemplate drqt) {
        DateInterval di = mock(DateInterval.class);
        when(di.getFrom()).thenReturn(fromDate);
        when(di.getTo()).thenReturn(toDate);

        when(dsf.getCreatedWithin()).thenReturn(di);

        return DATE_CREATED_FIELD + "[" + fromDate.toString() + " TO " + toDate.toString() + "]";
    }

    /**
     * @param givenQuery
     * @param drqt
     * @return the expected value
     */
    private String setFileQuery(final String givenQuery, final DiskResourceQueryTemplate drqt) {
        when(dsf.getFileQuery()).thenReturn(givenQuery);
        return FILE_QUERY_FIELD + givenQuery;
    }

    /**
     * @param min
     * @param max
     * @param drqt
     * @return the expected value
     */
    private String setFileSizeRange(final Double min, final Double max, final DiskResourceQueryTemplate drqt) {
        FileSizeRange fsr = mock(FileSizeRange.class);
        when(fsr.getMin()).thenReturn(min);
        when(fsr.getMax()).thenReturn(max);

        when(dsf.getFileSizeRange()).thenReturn(fsr);
        return FILE_SIZE_FIELD + "[" + min + " TO " + max + "]";
    }

    /**
     * @param givenQuery
     * @param drqt
     * @return the expected value
     */
    private String setMetadataQuery(final String givenQuery, final DiskResourceQueryTemplate drqt) {
        when(dsf.getMetadataQuery()).thenReturn(givenQuery);
        return METADATA_FIELD + givenQuery;
    }

    /**
     * @param from
     * @param to
     * @param drqt
     * @return the expected value
     */
    private String setModifiedWithin(final Date from, final Date to, final DiskResourceQueryTemplate drqt) {
        DateInterval di = mock(DateInterval.class);
        when(di.getFrom()).thenReturn(from);
        when(di.getTo()).thenReturn(to);

        when(dsf.getModifiedWithin()).thenReturn(di);
        return DATE_MODIFIED_FIELD + "[" + from.toString() + " TO " + to.toString() + "]";
    }

    /**
     * @param givenSearchTerms
     * @param drqt
     * @return the expected value
     */
    private String setNegatedFileQuery(final List<String> givenSearchTerms, final DiskResourceQueryTemplate drqt) {
        when(dsf.getNegatedFileQuery()).thenReturn(Joiner.on(" ").join(givenSearchTerms));

        return FILE_QUERY_FIELD + "-" + Joiner.on(" -").join(givenSearchTerms);
    }

    /**
     * @param givenSearchTerms
     * @param drqt
     * @return the expected value
     */
    private String setNegatedMetadata(final List<String> givenSearchTerms, final DiskResourceQueryTemplate drqt) {
        when(dsf.getNegatedMetadataQuery()).thenReturn(Joiner.on(" ").join(givenSearchTerms));

        return METADATA_FIELD + "-" + Joiner.on(" -").join(givenSearchTerms);
    }

    /**
     * @param givenValue
     * @param drqt
     * @return the expected value
     */
    private String setSharedWith(final String givenValue, final DiskResourceQueryTemplate drqt) {
        when(dsf.getSharedWith()).thenReturn(givenValue);
        return SHARED_WITH_FIELD + givenValue;
    }

}
