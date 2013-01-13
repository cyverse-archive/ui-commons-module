package org.iplantc.core.uicommons.client.validators;

import org.iplantc.core.uicommons.client.I18N;

import com.sencha.gxt.widget.core.client.form.validator.RegExValidator;

public class BasicEmailValidator3 extends RegExValidator {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"; //$NON-NLS-1$

    public BasicEmailValidator3() {
        super(EMAIL_PATTERN, I18N.VALIDATION.invalidEmail());
    }

}
