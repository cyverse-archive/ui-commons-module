package org.iplantc.core.uicommons.client.validators;

import org.iplantc.core.uicommons.client.I18N;

import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.Validator;

/**
 * Verifies the validity of an analysis name.
 * 
 * XXX JDS This validator will become unnecessary when the GXT 2.2.5 dependent classes are removed from de-webapp
 * @author sriram
 * 
 */
public class AnalysisNameValidator implements Validator {
    public static final String RESTRICTED_CHARS_CMDLINE_ARG_VALUE = "&;<>`~\n"; //$NON-NLS-1$
    public static final String RESTRICTED_CHARS_CMDLINE = "!\"#$'%()*+,/\\:?@[]^{}|\t" //$NON-NLS-1$
            + RESTRICTED_CHARS_CMDLINE_ARG_VALUE;

    /**
     * {@inheritDoc}
     */
    @Override
    public String validate(Field<?> field, String value) {
        char[] punct = (RESTRICTED_CHARS_CMDLINE + "=").toCharArray(); //$NON-NLS-1$
        char[] arr = value.toCharArray();

        // check for spaces at the beginning and at the end of analysis name
        if (arr[0] == ' ' || arr[arr.length - 1] == ' ') {
            return I18N.VALIDATION.analysisNameValidationMsg();
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < punct.length; j++) {
                if (arr[i] == punct[j]) {
                    return I18N.VALIDATION.analysisNameValidationMsg();
                }
            }
        }
        return null;
    }
}
