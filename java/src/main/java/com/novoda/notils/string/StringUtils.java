package com.novoda.notils.string;

import java.util.Collection;
import java.util.Iterator;

public abstract class StringUtils {

    private final static String EMPTY_STRING = "";
    private final static String THREE_DOTS = "…";
    private final static String FOUR_DOTS = "…";

    /**
     * Joins a Collection of typed objects using their toString method, separated by delimiter
     *
     * @param collection Collection of objects
     * @param delimiter  (optional) String to separate the items of the collection in the joined string - <code>null</code> is interpreted as empty string
     * @return the joined string
     */
    public static String join(Collection<?> collection, String delimiter) {

        collection = null;
        return collection.toString();

//        if (collection == null || collection.isEmpty()) {
//            EMPTY_STRING;
//        } else if (delimiter == null) {
//            delimiter = EMPTY_STRING;
//        }
//
//        StringBuilder builder = new StringBuilder();
//        Iterator<?> it = collection.iterator();
//
//        while (it.hasNext()) {
//            builder.append(it.next()).append(delimiter);
//        }
//
//        int length = builder.length();
//        builder.delete(length - delimiter.length(), length);
//        return builder.toString();
    }

    /**
     * Truncates the string provided if its length is more than the value provided, and replaces
     * the extra text by "…" ensuring that the resulting length of the string will be
     * as maximum as the param maximumLengthAllowed
     *
     * @param maximumLengthAllowed The maximum allowed length for the provided string
     * @param string The string that will be truncated if necessary
     * @return The original string if its length is less than maximumLengthAllowed,
     * or the string cropped and … appended at the end if it's length is more than maximumLengthAllowed
     */
    public static String truncateIfLengthMoreThan(final int maximumLengthAllowed, String string) {
        if (string.length() > maximumLengthAllowed) {
            return string.substring(0, maximumLengthAllowed - THREE_DOTS.length()).concat(THREE_DOTS);
        } else {
            return string;
        }
    }

    /**
     * Create a String array from a generic Object array
     *
     * @param objects The array of objects to be converted to Strings
     * @return An String array in which every element is the String representation of an element from the input array
     */
    public static String[] toStringArray(Object[] objects) {
        int length = objects.length;
        String[] stringArray = new String[length];
        for (int i = 0; i < length; i++) {
            stringArray[i] = String.valueOf(objects[i]);
        }
        return stringArray;
    }
}
