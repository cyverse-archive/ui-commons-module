package org.iplantc.core.uicommons.client.util;

import java.util.Comparator;

import com.extjs.gxt.ui.client.store.StoreSorter;
import com.extjs.gxt.ui.client.util.DefaultComparator;

/**
 * 
 * @author sriram
 * 
 */
@SuppressWarnings("rawtypes")
public class CommonStoreSorter extends StoreSorter {

    public static DefaultComparator<Object> COMMON_COMPARATOR = new CommonComparator();

    @SuppressWarnings("unchecked")
    public CommonStoreSorter(Comparator<Object> comparator) {
        super(comparator);
    }

    @SuppressWarnings("unchecked")
    public CommonStoreSorter() {
        super(COMMON_COMPARATOR);
    }

}
