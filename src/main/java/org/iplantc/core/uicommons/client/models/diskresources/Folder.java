package org.iplantc.core.uicommons.client.models.diskresources;

import java.util.List;

import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;

public interface Folder extends DiskResource {

    @PropertyName("hasSubDirs")
    boolean hasSubDirs();

    List<Folder> getFolders();

    void setFolders(List<Folder> folders);

    List<File> getFiles();
    
    void setFiles(List<File> files);
    
    @PropertyName("total")
    void setTotal(int total);
    
    int getTotal();
    
    @PropertyName("total_filtered")
    void setTotalFiltered(int total_filtered);

    @PropertyName("total_filtered")
    int getTotalFiltered();
}
