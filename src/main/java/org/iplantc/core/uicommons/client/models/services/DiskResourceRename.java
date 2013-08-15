package org.iplantc.core.uicommons.client.models.services;


/**
 * An AutoBean interface for DiskResource Rename requests and responses.
 * 
 * @author psarando
 * 
 */
public interface DiskResourceRename {

    String getSource();

    void setSource(String source);

    String getDest();

    void setDest(String dest);
}
