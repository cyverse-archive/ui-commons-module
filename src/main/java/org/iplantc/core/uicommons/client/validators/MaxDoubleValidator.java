package org.iplantc.core.uicommons.client.validators;

import com.sencha.gxt.widget.core.client.form.validator.MaxNumberValidator;

public class MaxDoubleValidator extends MaxNumberValidator<Double> {

    public MaxDoubleValidator(Double maxNumber) {
        super(maxNumber);
    }

}
