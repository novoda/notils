package com.novoda.notils.string;

import java.util.Collection;
import java.util.Iterator;

public abstract class StringUtils {

    private final static String EMPTY_STRING = "";

    /**
     * Joins a Collection of typed objects using their toString method, separated by delimiter
     * @param collection Collection of objects
     * @param delimiter (optional) String to separate the items of the collection in the joined string - <code>null</code> is interpreted as empty string
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

        int length = builder.toString().length();
        builder.delete(length - delimiter.length(), length);
        return builder.toString();
    }
}
