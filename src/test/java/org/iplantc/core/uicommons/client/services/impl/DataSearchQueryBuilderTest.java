package org.iplantc.core.uicommons.client.services.impl;

import org.iplantc.core.uicommons.client.models.search.DateInterval;
import org.iplantc.core.uicommons.client.models.search.DiskResourceQueryTemplate;
import org.iplantc.core.uicommons.client.models.search.FileSizeRange;
import org.iplantc.core.uicommons.client.services.impl.DataSearchQueryBuilder;
import org.junit.Test;
import org.junit.Ignore;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Questions in need of answering:
 * -- Can I put multiple field queries into one query string?
 * 
 * @author jstroot
 * 
 */
public class DataSearchQueryBuilderTest {

    @Test
    @Ignore
    public void testBuildQuery() {
        String expected = "";
        DiskResourceQueryTemplate dsf = mock(DiskResourceQueryTemplate.class);

        // TODO Set up mocked returns

        String result = new DataSearchQueryBuilder(dsf).createdBy().toString();
        assertEquals(expected, result);
        // here be one

    }

    @Test
    @Ignore
    public void testCreatedBy() {
        String expected = "";
        String mockedReturnVal = "";
        DiskResourceQueryTemplate dsf = mock(DiskResourceQueryTemplate.class);

        when(dsf.getCreatedBy()).thenReturn(mockedReturnVal);

        String result = new DataSearchQueryBuilder(dsf).createdBy().toString();
        assertEquals(expected, result);
        // here be two

    }

    @Test
    @Ignore
    public void testCreatedWithin() {
        String expected = "";
        DateInterval di = mock(DateInterval.class);
        when(di.getFrom()).thenReturn(new Date());
        when(di.getTo()).thenReturn(new Date());
        DiskResourceQueryTemplate dsf = mock(DiskResourceQueryTemplate.class);

        when(dsf.getCreatedWithin()).thenReturn(di);

        String result = new DataSearchQueryBuilder(dsf).createdWithin().toString();
        assertEquals(expected, result);
        // here be three

    }

    @Test
    @Ignore
    public void testFile() {
        String expected = "";
        String mockedReturnVal = "";
        DiskResourceQueryTemplate dsf = mock(DiskResourceQueryTemplate.class);

        when(dsf.getFileQuery()).thenReturn(mockedReturnVal);

        String result = new DataSearchQueryBuilder(dsf).file().toString();
        assertEquals(expected, result);

    }

    @Test
    @Ignore
    public void testFileSizeRange() {
        String expected = "";
        FileSizeRange fsr = mock(FileSizeRange.class);
        when(fsr.getMin()).thenReturn(0.0);
        when(fsr.getMax()).thenReturn(0.0);
        DiskResourceQueryTemplate dsf = mock(DiskResourceQueryTemplate.class);

        when(dsf.getFileSizeRange()).thenReturn(fsr);

        String result = new DataSearchQueryBuilder(dsf).fileSizeRange().toString();
        assertEquals(expected, result);

    }

    @Test
    @Ignore
    public void testMetadata() {
        String expected = "";
        String mockedReturnVal = "";
        DiskResourceQueryTemplate dsf = mock(DiskResourceQueryTemplate.class);

        when(dsf.getMetadataQuery()).thenReturn(mockedReturnVal);

        String result = new DataSearchQueryBuilder(dsf).metadata().toString();
        assertEquals(expected, result);

    }

    @Test
    @Ignore
    public void testModifiedWithin() {
        String expected = "";
        DateInterval di = mock(DateInterval.class);
        when(di.getFrom()).thenReturn(new Date());
        when(di.getTo()).thenReturn(new Date());
        DiskResourceQueryTemplate dsf = mock(DiskResourceQueryTemplate.class);

        when(dsf.getModifiedWithin()).thenReturn(di);

        String result = new DataSearchQueryBuilder(dsf).modifiedWithin().toString();
        assertEquals(expected, result);

    }

    @Test
    @Ignore
    public void testNegatedFile() {
        String expected = "";
        String mockedReturnVal = "";
        DiskResourceQueryTemplate dsf = mock(DiskResourceQueryTemplate.class);

        when(dsf.getNegatedFileQuery()).thenReturn(mockedReturnVal);

        String result = new DataSearchQueryBuilder(dsf).negatedFile().toString();
        assertEquals(expected, result);

    }

    @Test
    @Ignore
    public void testNegatedMetadata() {
        String expected = "";
        String mockedReturnVal = "";
        DiskResourceQueryTemplate dsf = mock(DiskResourceQueryTemplate.class);

        when(dsf.getNegatedMetadataQuery()).thenReturn(mockedReturnVal);

        String result = new DataSearchQueryBuilder(dsf).negatedMetadata().toString();
        assertEquals(expected, result);

    }

    @Test
    @Ignore
    public void testSharedWith() {
        String expected = "";
        String mockedReturnVal = "";
        DiskResourceQueryTemplate dsf = mock(DiskResourceQueryTemplate.class);

        when(dsf.getSharedWith()).thenReturn(mockedReturnVal);

        String result = new DataSearchQueryBuilder(dsf).sharedWith().toString();
        assertEquals(expected, result);

    }

}
