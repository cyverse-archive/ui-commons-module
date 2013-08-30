package org.iplantc.core.uicommons.client.validators;

import org.iplantc.core.resources.client.messages.I18N;

import com.sencha.gxt.widget.core.client.form.validator.RegExValidator;

public class UrlValidator extends RegExValidator {
    private static final String URL_REGEX = "^(?:ftp|FTP|HTTPS?|https?)://[^/]+\\.[^/]+.*"; //$NON-NLS-1$

    public UrlValidator() {
        super(URL_REGEX, I18N.VALIDATION.invalidUrl());
    }
}
