package org.iplantc.de.commons.client.models.search;

import java.util.Date;

import org.iplantc.de.commons.client.models.HasLabel;

public interface DateInterval extends HasLabel {

    Date getFrom();

    Date getTo();

    void setFrom(Date from);

    void setTo(Date to);

}
