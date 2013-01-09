package org.iplantc.core.uicommons.client.validators;

import com.sencha.gxt.widget.core.client.form.validator.MaxNumberValidator;

public class MaxIntegerValidator extends MaxNumberValidator<Integer> {

    public MaxIntegerValidator(Integer maxNumber) {
        super(maxNumber);
    }

}
