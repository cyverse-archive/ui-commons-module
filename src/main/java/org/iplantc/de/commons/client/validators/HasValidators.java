package org.iplantc.de.commons.client.validators;

import com.sencha.gxt.widget.core.client.form.Validator;

import java.util.List;

public interface HasValidators<T> {
    
    void addValidator(Validator<T> validator);
    
    void addValidators(List<Validator<T>> validators);

    void removeValidator(Validator<T> validator);

    List<Validator<T>> getValidators();
    
}
