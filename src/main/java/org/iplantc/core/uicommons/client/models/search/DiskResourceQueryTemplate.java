package org.iplantc.core.uicommons.client.models.search;

import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;

import org.iplantc.core.uicommons.client.models.diskresources.Folder;

/**
 * This object is used to collect the information required to build a search request for the endpoints
 * described <a href=
 * "https://github.com/iPlantCollaborativeOpenSource/Donkey/blob/dev/doc/endpoints/filesystem/search.md"
 * >here</a>
 * 
 * @author jstroot
 * 
 */
public interface DiskResourceQueryTemplate extends Folder {

    /**
     * creator.username:(here is the content)
     * 
     * @return
     */
    @PropertyName("creator.username")
    String getCreatedBy();

    DateInterval getCreatedWithin();

    String getFileQuery();

    FileSizeRange getFileSizeRange();

    String getMetadataQuery();

    DateInterval getModifiedWithin();

    String getNegatedFileQuery();

    String getNegatedMetadataQuery();

    String getSharedWith();

    @PropertyName("creator.username")
    void setCreatedBy(String createdBy);

    void setCreatedWithin(DateInterval createdWithin);

    void setFileQuery(String fileQuery);

    void setFileSizeRange(FileSizeRange fileSizeRange);

    void setMetadataQuery(String metadataQuery);

    void setModifiedWithin(DateInterval modifiedWithin);

    void setNegatedFileQuery(String negatedFileQuery);

    void setNegatedMetadataQuery(String negatedMetadataQuery);

    void setSharedWith(String sharedWith);
}