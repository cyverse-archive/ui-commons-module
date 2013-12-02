package org.iplantc.core.uicommons.client.models.search;

import java.util.Date;

public interface DateInterval {

    Date getFrom();

    Date getTo();

    void setFrom(Date from);

    void setTo(Date to);

}
