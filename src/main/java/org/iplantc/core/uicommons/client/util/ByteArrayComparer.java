package org.iplantc.core.uicommons.client.util;

public class ByteArrayComparer {
    /**
     * 
     * A util method to compare if byte arrays are equal
     * 
     * @param arr1
     * @param arr2
     * @return boolean
     */
    public static boolean arraysEqual(byte[] arr1, byte[] arr2) {
        boolean retval = false;
        if (arr1 != null && arr2 != null && arr1.length == arr2.length) {
            retval = true;
            for (int i = 0; i < arr1.length; i++) {
                if (arr1[i] != arr2[i]) {
                    retval = false;
                    break;
                }
            }
        }
        return retval;
    }
}
