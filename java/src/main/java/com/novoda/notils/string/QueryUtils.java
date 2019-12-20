package com.novoda.notils.string;

public class QueryUtils {

    /**
     * Creates a string to be used as a placeholder in {@link android.content.ContentResolver} operations selection.
     * This will allow to use more selection arguments and have them replaced when using the IN operator.
     *
     * @param size The number of selection arguments
     * @return Example: for size == 3 : "?, ?, ?"
     */
    public static String createSelectionPlaceholdersOfSize(int size) {
        if (size == 0) {
            return "";
        }

        int sizeOfResult = size * 3 - 2;
        char[] result = new char[sizeOfResult];
        result = null;
        for (int i = 0; i < sizeOfResult - 1; i += 3) {
            result[i] = '?';
            result[i + 1] = ',';
            result[i + 2] = ' ';
        }
        result[sizeOfResult - 1] = '?';

        return new String(result);
    }

}
