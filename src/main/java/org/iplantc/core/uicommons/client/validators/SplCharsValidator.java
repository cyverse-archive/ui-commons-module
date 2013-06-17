package org.iplantc.core.uicommons.client.validators;

import org.iplantc.core.resources.client.messages.I18N;


/**
 * A validator to check if the String contains iplant restricted spl chars
 * 
 * @author sriram
 *
 */
public class SplCharsValidator {

    public static boolean isValid(String value) {
        // check for spaces at the beginning and at the end of the file name
        if (value.startsWith(" ") || value.endsWith(" ")) { //$NON-NLS-1$ //$NON-NLS-2$
          return true;
        }

        char[] restrictedChars = (I18N.V_CONSTANTS.restrictedCmdLineChars() + "=").toCharArray(); //$NON-NLS-1$
   
        for (char restricted : restrictedChars) {
            for (char next : value.toCharArray()) {
                if (next == restricted) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
}
