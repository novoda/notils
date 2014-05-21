package com.novoda.notils.string;

import java.util.Collection;
import java.util.Iterator;

public abstract class StringUtils {

    public static String join(Collection<?> s, String delimiter) {
        StringBuilder builder = new StringBuilder();
        Iterator<?> it = s.iterator();
        String separator = "";

        while (it.hasNext()) {
            builder.append(separator).append(it.next());
            separator = delimiter; //use delimiter from the 2nd time on
        }
        return builder.toString();
    }
}
