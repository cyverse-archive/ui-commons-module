package org.iplantc.core.uicommons.client.models.search;

import java.util.Date;

import org.iplantc.core.uicommons.client.models.HasLabel;

public interface DateInterval extends HasLabel {

    Date getFrom();

    Date getTo();

    void setFrom(Date from);

    void setTo(Date to);

}
