package org.iplantc.de.commons.client.util;

import java.util.Date;

/**
 * A common Date parser for creating date objects. This parser will assume a timestamp of 0 is not valid.
 * 
 * @author psarando
 * 
 */
public class DateParser {
    /**
     * Returns a Date object representing the given timestamp string. If the string could not be parsed
     * to a valid long value, or if parsed to 0, this method will return null instead.
     * 
     * @param timestamp
     * @return A Date representing the given timestamp string.
     */
    public static Date parseDate(String timestamp) {
        long parsedTimestamp;

        try {
            parsedTimestamp = Long.parseLong(timestamp);
        } catch (Exception e) {
            parsedTimestamp = 0;
        }

        return parseDate(parsedTimestamp);
    }

    /**
     * Returns a Date object representing the given timestamp. This method assumes a timestamp of 0 is
     * not valid and will return null instead.
     * 
     * @param timestamp
     * @return A Date representing the given timestamp, or null if given 0.
     */
    public static Date parseDate(long timestamp) {
        if (timestamp == 0) {
            return null;
        }

        return new Date(timestamp);
    }
}
