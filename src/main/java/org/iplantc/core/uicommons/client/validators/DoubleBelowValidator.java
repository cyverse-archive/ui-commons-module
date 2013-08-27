package org.iplantc.core.uicommons.client.validators;

import java.util.List;

import org.iplantc.core.uicommons.client.I18N;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.sencha.gxt.widget.core.client.form.validator.AbstractValidator;

public class DoubleBelowValidator extends AbstractValidator<Double> {

    private final Double maxNumber;

    public DoubleBelowValidator(Double maxNumber) {
        this.maxNumber = maxNumber;
    }

    @Override
    public List<EditorError> validate(Editor<Double> editor, Double value) {
        if (value != null && (value >= maxNumber)) {
            return createError(editor, I18N.VALIDATION.notBelowValueMsg("", maxNumber), value);
        }

        return null;
    }

    public Double getMaxNumber() {
        return maxNumber;
    }
}
