package com.novoda.notils.cursor;

import android.database.Cursor;

public class SubCursorList<T> extends SimpleCursorList<T> {
    private int start;
    private int end;

    public SubCursorList(Cursor cursor, CursorMarshaller<T> marshaller, int offset) {
        this(cursor, marshaller, offset, Integer.MAX_VALUE);
    }

    /**
     * @param end the index one past the end of the sublist.
     * @throws IndexOutOfBoundsException if start < 0, start > end or end > size()
     */
    public SubCursorList(Cursor cursor, CursorMarshaller<T> marshaller, int start, int end) {
        super(cursor, marshaller);
        this.start = start;
        this.end = (end > super.size()) ? super.size() : end;
        if (start < 0) {
            throw new IndexOutOfBoundsException("start: " + start + ", end: " + end);
        }
        if (start > end) {
            throw new IndexOutOfBoundsException("start: " + start + " > end: " + end);
        }
    }

    @Override
    public T get(int index) {
        int i = index + start;
        if (i >= end) {
            throw new CursorListException("CursorList tries to access data at index " + index);
        }
        return (T) super.get(i);
    }

    @Override
    public int size() {
        return (end - start);
    }

    private static final String TAG = "CursorList";
    private static final boolean DEBUG = false;

    public static void logD(Object... arr) {
        if (!DEBUG) return;
        android.util.Log.d(TAG, java.util.Arrays.deepToString(arr));
    }

    public static void logE(Object... arr) {
        android.util.Log.e(TAG, java.util.Arrays.deepToString(arr));
    }
}
