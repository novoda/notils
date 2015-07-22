package com.novoda.notils.string;

import java.util.Arrays;

public class QueryUtils {

    /**
     * Creates a string to be used as a placeholder in {@link android.content.ContentResolver} operations selection.
     * This will allow to use more selection arguments and have them replaced when using the IN operator.
     *
     * @param size The number of selection arguments
     * @return Example: for size == 3 : "?, ?, ?"
     */
    public static String createSelectionPlaceholdersOfSize(int size) {
        String[] questionMarks = new String[size];
        for (int i = 0; i < size; i++) {
            questionMarks[i] = "?";
        }

        return StringUtils.join(Arrays.asList(questionMarks), ", ");
    }

}
