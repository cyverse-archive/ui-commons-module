package org.iplantc.core.uicommons.client.util;

import com.extjs.gxt.ui.client.util.Format;

/**
 * A utility class with methods for regular expressions in Java.
 * 
 * @author psarando
 * 
 */
public class RegExp {

    /**
     * Escapes a string containing backslashes and brackets for use in a regular expression character
     * class. For example, the string "[]\\" will be escaped for use in an expression like [\[\]\\].
     * 
     * @param charSet A string containing backslashes or brackets that needs to be used in a regex
     *            character class expression.
     * @return A string with characters escaped for use in a regex character class.
     */
    public static String escapeCharacterClassSet(String charSet) {
        // Java's replaceAll needs some additional escaping of backslashes and brackets.
        String backSlash = Format.escape("\\"); //$NON-NLS-1$
        String regexEscapedBackslash = Format.escape("\\\\"); //$NON-NLS-1$
        String openBracket = "\\["; //$NON-NLS-1$
        String escapedOpenBracket = Format.escape("\\["); //$NON-NLS-1$
        String closeBracket = "\\]"; //$NON-NLS-1$
        String escapedCloseBracket = Format.escape("\\]"); //$NON-NLS-1$

        return charSet.replaceAll(backSlash, regexEscapedBackslash)
                .replaceAll(openBracket, escapedOpenBracket)
                .replaceAll(closeBracket, escapedCloseBracket);
    }
}
