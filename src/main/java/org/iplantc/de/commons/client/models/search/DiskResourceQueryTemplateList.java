package org.iplantc.de.commons.client.models.search;

import java.util.List;

public interface DiskResourceQueryTemplateList {
    String LIST_KEY = "queryTemplateList";

    List<DiskResourceQueryTemplate> getQueryTemplateList();

}
