package org.iplantc.core.uicommons.client.validators;

import java.util.List;

import com.sencha.gxt.widget.core.client.form.Validator;

public interface HasValidators<T> {
    
    void addValidator(Validator<T> validator);
    
    void removeValidator(Validator<T> validator);

    List<Validator<T>> getValidators();
    
}
