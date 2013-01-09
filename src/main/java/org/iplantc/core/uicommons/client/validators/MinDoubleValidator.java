package org.iplantc.core.uicommons.client.validators;

import com.sencha.gxt.widget.core.client.form.validator.MinNumberValidator;

public class MinDoubleValidator extends MinNumberValidator<Double> {

    public MinDoubleValidator(Double minNumber) {
        super(minNumber);
    }

}
