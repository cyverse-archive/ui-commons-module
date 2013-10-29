package org.iplantc.core.uicommons.client.models.services;

import java.util.List;

import org.iplantc.core.uicommons.client.models.diskresources.Folder;

/**
 * An AutoBean interface for DiskResource Move requests and responses.
 * 
 * @author psarando
 * 
 */
public interface DiskResourceMove {

    String getDest();

    void setDest(String dest);

    Folder getDestination();

    void setDestination(Folder destination);

    List<String> getSources();

    void setSources(List<String> sources);
}
