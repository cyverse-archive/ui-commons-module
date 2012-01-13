/**
 * 
 */
package org.iplantc.core.uicommons.client.util;

import com.extjs.gxt.ui.client.util.DefaultComparator;

/**
 * @author sriram
 *
 */
public class CommonComparator extends DefaultComparator<Object> {

    @Override
    public int compare(Object o1, Object o2) {
        if (o1 != null && o2 != null) {
            if (o1 instanceof String && o2 instanceof String) {
                return compareStrings(o1.toString(), o2.toString());
            }
        }
        return super.compare(o1, o2);
    }
}
