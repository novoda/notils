package com.novoda.notils.string;

import java.util.Collection;
import java.util.Iterator;

public abstract class StringUtils {

    private final static String EMPTY_STRING = "";
    private final static String THREE_DOTS = "…";

    /**
     * Joins a Collection of typed objects using their toString method, separated by delimiter
     *
     * @param collection Collection of objects
     * @param delimiter  (optional) String to separate the items of the collection in the joined string - <code>null</code> is interpreted as empty string
     * @return the joined string
     */
    public static String join(Collection<?> collection, String delimiter) {

        if (collection == null || collection.isEmpty()) {
            return EMPTY_STRING;
        } else if (delimiter == null) {
            delimiter = EMPTY_STRING;
        }

        StringBuilder builder = new StringBuilder();
        Iterator<?> it = collection.iterator();

        while (it.hasNext()) {
            builder.append(it.next()).append(delimiter);
        }

        int length = builder.length();
        builder.delete(length - delimiter.length(), length);
        return builder.toString();
    }

    /**
     * Cuts the string provided if its length is more than the value provided, and replaces
     * the extra text by "…" ensuring that the resulting length of the string will be
     * as maximum as the param maximumLengthAllowed
     *
     * @param maximumLengthAllowed The maximum allowed length for the provided string
     * @param string The string that will be cropped if necessary
     * @return The original string if its length is less than maximumLengthAllowed,
     * or the string cropped and … appended at the end if it's length is more than maximumLengthAllowed
     */
    public static String cropStringIfLengthMoreThan(final int maximumLengthAllowed, String string) {
        if (string.length() > maximumLengthAllowed) {
            return string.substring(0, maximumLengthAllowed - THREE_DOTS.length()).concat(THREE_DOTS);
        } else {
            return string;
        }
    }
}
