package org.iplantc.core.uicommons.client.validators;

import org.iplantc.core.resources.client.messages.I18N;

import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.Validator;

/**
 * A validator to validate email address
 * 
 * @author sriram
 * @deprecated Class needs to be deleted or ported to GXT3
 */
@Deprecated
public class BasicEmailValidator implements Validator {

    @Override
    public String validate(Field<?> field, String value) {
        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"; //$NON-NLS-1$

        if (value == null || !value.matches(emailPattern)) {
            return I18N.VALIDATION.invalidEmail();
        }

        return null;
    }
}
