package org.iplantc.core.uicommons.client.validators;

import java.util.List;

import org.iplantc.core.uicommons.client.I18N;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.sencha.gxt.widget.core.client.form.validator.AbstractValidator;

public class IntAboveValidator extends AbstractValidator<Integer> {

    private final Integer minNumber;

    public IntAboveValidator(Integer minNumber) {
        this.minNumber = minNumber;
    }

    @Override
    public List<EditorError> validate(Editor<Integer> editor, Integer value) {
        if (value != null && (value <= minNumber)) {
            return createError(editor, I18N.VALIDATION.notAboveValueMsg("", minNumber), value);
        }

        return null;
    }

    public Integer getMinNumber() {
        return minNumber;
    }
}
