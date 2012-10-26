package org.iplantc.core.uicommons.client.validators;

import java.util.List;

import org.iplantc.core.uicommons.client.I18N;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;
import com.sencha.gxt.widget.core.client.form.validator.AbstractValidator;

public class NameValidator3 extends AbstractValidator<String> {
    public static final String RESTRICTED_CHARS_CMDLINE_ARG_VALUE = "&;<>`~\n"; //$NON-NLS-1$
    public static final String RESTRICTED_CHARS_CMDLINE = "!\"#$'%()*+,/\\:?@[]^{}|\t" //$NON-NLS-1$
            + RESTRICTED_CHARS_CMDLINE_ARG_VALUE;

    @Override
    public List<EditorError> validate(Editor<String> editor, String value) {
        char[] punct = (RESTRICTED_CHARS_CMDLINE + "=").toCharArray(); //$NON-NLS-1$
        char[] arr = value.toCharArray();

        // check for spaces at the beginning and at the end of analysis name
        if (arr[0] == ' ' || arr[arr.length - 1] == ' ') {
            return createError(new DefaultEditorError(editor,
                    I18N.VALIDATION.analysisNameValidationMsg(),
                    value));// I18N.RULES.analysisNameValidationMsg();
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < punct.length; j++) {
                if (arr[i] == punct[j]) {
                    // return I18N.RULES.analysisNameValidationMsg();
                    return createError(new DefaultEditorError(editor,
                            I18N.VALIDATION.analysisNameValidationMsg(), value));// I18N.RULES.analysisNameValidationMsg();
                }
            }
        }
        return null;
    }
}
