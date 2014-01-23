package org.iplantc.core.uicommons.client.services.impl;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;

import org.iplantc.core.uicommons.client.models.search.DiskResourceQueryTemplate;

/**
 * This class uses a builder pattern to construct a search query from a given query template.
 * 
 * If a field in the given query template is null or empty, the corresponding search term will be omitted
 * from
 * the final query.
 * 
 * @author jstroot
 * 
 */
public class DataSearchQueryBuilder {

    private final DiskResourceQueryTemplate dsf;
    private final StringBuilder sb;

    public DataSearchQueryBuilder(DiskResourceQueryTemplate dsf) {
        this.dsf = dsf;
        sb = new StringBuilder();
    }

    public String buildFullQuery() {
        ownedBy().createdWithin().file().fileSizeRange().metadataAttribute().metadataValue().modifiedWithin().negatedFile().sharedWith();
        return toString();
    }

    public DataSearchQueryBuilder ownedBy() {
        if (!Strings.isNullOrEmpty(dsf.getOwnedBy())) {
            String queryContent = dsf.getOwnedBy();
            // FIXME Consult with Tony on how to put this query together.
            // FIXME This query will change when this is all updated to a JSON format
            sb.append(createQuery("creator.username:", queryContent)).append(" ");
        }
        return this;
    }

    public DataSearchQueryBuilder createdWithin() {
        if ((dsf.getCreatedWithin() != null) && (dsf.getCreatedWithin().getFrom() != null) && (dsf.getCreatedWithin().getTo() != null)) {
            String query = createQuery("dateCreated:", "[" + dsf.getCreatedWithin().getFrom().toString() + " TO " + dsf.getCreatedWithin().getTo().toString() + "]");
            sb.append(query).append(" ");
        }
        return this;
    }

    /**
     * label:(some query)
     * FIXME File and negated file terms need to be combined
     * 
     * @return
     */
    public DataSearchQueryBuilder file() {
        if (!Strings.isNullOrEmpty(dsf.getFileQuery())) {
            String content = dsf.getFileQuery();
            sb.append(createQuery("label:", content)).append(" ");
        }
        return this;
    }

    public DataSearchQueryBuilder fileSizeRange() {
        if ((dsf.getFileSizeRange() != null) && (dsf.getFileSizeRange().getMin() != null) && (dsf.getFileSizeRange().getMax() != null)) {
            String content = "[" + dsf.getFileSizeRange().getMin() + " TO " + dsf.getFileSizeRange().getMax() + "]";
            sb.append(createQuery("fileSize:", content)).append(" ");
        }
        return this;
    }

    /**
     * @return the currently constructed query.
     */
    public String getQuery() {
        return toString();
    }

    /**
     * @return
     */
    public DataSearchQueryBuilder metadataAttribute() {
        if (!Strings.isNullOrEmpty(dsf.getMetadataAttributeQuery())) {
            String content = dsf.getMetadataAttributeQuery();
            // Search metadata.attribute for the given query
            // FIXME This will not work with the query string dsl. JSON format will need to be supported.
            sb.append(createQuery("metadata.\\*:", content)).append(" ");
        }
        return this;
    }

    public DataSearchQueryBuilder metadataValue() {
        if (!Strings.isNullOrEmpty(dsf.getMetadataValueQuery())) {
            String content = dsf.getMetadataValueQuery();
            // Search metadata.value for the given query
            // FIXME This will not work with the query string dsl. JSON format will need to be supported.
            sb.append(createQuery("metadata.\\*:", content)).append(" ");
        }
        return this;
    }

    public DataSearchQueryBuilder modifiedWithin() {
        if ((dsf.getModifiedWithin() != null) && (dsf.getModifiedWithin().getFrom() != null) && (dsf.getModifiedWithin().getTo() != null)) {
            String content = "[" + dsf.getModifiedWithin().getFrom().toString() + " TO " + dsf.getModifiedWithin().getTo().toString() + "]";
            sb.append(createQuery("dateModified:", content)).append(" ");
        }
        return this;
    }

    /**
     * label:(-some -query -fldjf)
     * 
     * FIXME File and negated file terms need to be combined
     * 
     * @return
     */
    public DataSearchQueryBuilder negatedFile() {
        if (!Strings.isNullOrEmpty(dsf.getNegatedFileQuery())) {
            // Split the query and reassemble with a "-" slapped onto the front.
            Iterable<String> split = Splitter.on(" ").split(dsf.getNegatedFileQuery());
            String content = "-" + Joiner.on(" -").join(split);
            sb.append(createQuery("label:", content)).append(" ");
        }
        return this;
    }

    public DataSearchQueryBuilder sharedWith() {
        if (!Strings.isNullOrEmpty(dsf.getSharedWith())) {
            String content = dsf.getSharedWith();
            sb.append(createQuery("sharedWith:", content)).append(" ");
        }
        return this;
    }

    /**
     * @return the currently constructed query.
     */
    @Override
    public String toString() {
        return sb.toString().trim();
    }

    private String createQuery(String queryField, String queryContent) {
        String query = queryField + queryContent;
        return query.trim();
    }
}
