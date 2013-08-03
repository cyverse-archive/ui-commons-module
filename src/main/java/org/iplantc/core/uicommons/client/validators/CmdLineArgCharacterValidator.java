package org.iplantc.core.uicommons.client.validators;

import java.util.List;

import org.iplantc.core.resources.client.messages.I18N;
import org.iplantc.core.uicommons.client.util.RegExp;

import com.extjs.gxt.ui.client.util.Format;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;
import com.sencha.gxt.widget.core.client.form.validator.AbstractValidator;

public class CmdLineArgCharacterValidator extends AbstractValidator<String> {

    @Override
    public List<EditorError> validate(Editor<String> editor, String value) {
        String regex = Format.substitute("[{0}]", RegExp.escapeCharacterClassSet(I18N.V_CONSTANTS.restrictedCmdLineArgChars()));
        if (value.matches(".*" + regex + ".*")) {
            // We have an error
            char[] restrictedChars = (I18N.V_CONSTANTS.restrictedCmdLineArgChars() + "=").toCharArray(); //$NON-NLS-1$
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
                String errorMsg = I18N.RULES.unsupportedChars(I18N.V_CONSTANTS.restrictedCmdLineArgChars()) + " " + I18N.RULES.invalidChars(restrictedFound.toString());
                return createError(new DefaultEditorError(editor, errorMsg, value));
            }
        }

        return null;
    }

}
