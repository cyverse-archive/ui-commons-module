package org.iplantc.core.uicommons.client.validators;

import com.sencha.gxt.widget.core.client.form.validator.MinNumberValidator;

public class MinIntegerValidator extends MinNumberValidator<Integer> {

    public MinIntegerValidator(Integer minNumber) {
        super(minNumber);
    }

}
