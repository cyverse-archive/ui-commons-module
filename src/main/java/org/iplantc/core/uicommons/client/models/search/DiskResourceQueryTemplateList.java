package org.iplantc.core.uicommons.client.models.search;

import java.util.List;

public interface DiskResourceQueryTemplateList {
    String LIST_KEY = "queryTemplateList";

    List<DiskResourceQueryTemplate> getQueryTemplateList();

}
