package org.iplantc.core.uicommons.client.validators;

import java.util.List;

import org.iplantc.core.uicommons.client.I18N;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;
import com.sencha.gxt.widget.core.client.form.validator.AbstractValidator;

/**
 * Validates a file or folder name.
 * 
 * @author psarando
 * 
 */
public class NameValidator3 extends AbstractValidator<String> {
    public static final String RESTRICTED_CHARS_CMDLINE_ARG_VALUE = "&;<>`~\n"; //$NON-NLS-1$
    public static final String RESTRICTED_CHARS_CMDLINE = "!\"#$'%()*+,/\\:?@[]^{}|\t" //$NON-NLS-1$
            + RESTRICTED_CHARS_CMDLINE_ARG_VALUE;

    @Override
    public List<EditorError> validate(Editor<String> editor, String value) {
        if (value == null) {
            return null;
        }

        // check for spaces at the beginning and at the end of the file name
        if (value.startsWith(" ") || value.endsWith(" ")) { //$NON-NLS-1$ //$NON-NLS-2$
            return createError(new DefaultEditorError(editor,
                    I18N.VALIDATION.analysisNameValidationMsg(), value));
        }

        char[] restrictedChars = (RESTRICTED_CHARS_CMDLINE + "=").toCharArray(); //$NON-NLS-1$
        StringBuilder restrictedFound = new StringBuilder();

        for (char restricted : restrictedChars) {
            for (char next : value.toCharArray()) {
                if (next == restricted) {
                    restrictedFound.append(restricted);
                    break;
                }
            }
        }

        if (restrictedFound.length() > 0) {
            String errorMsg = I18N.VALIDATION.analysisNameValidationMsg() + " " //$NON-NLS-1$
                    + I18N.VALIDATION.analysisNameInvalidChars(restrictedFound.toString());

            return createError(new DefaultEditorError(editor, errorMsg, value));
        }

        return null;
    }
}