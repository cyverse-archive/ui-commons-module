package org.iplantc.core.uicommons.client.models.services;

import java.util.List;

/**
 * An AutoBean interface for DiskResource Move requests and responses.
 * 
 * @author psarando
 * 
 */
public interface DiskResourceMove {

    String getDest();

    void setDest(String dest);

    List<String> getSources();

    void setSources(List<String> sources);
}
