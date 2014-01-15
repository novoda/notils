package com.novoda.notils.cursor;

import java.util.List;

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
    public int getCount() {
        return (end - start);
    }

    @Override
    public int getPosition() {
        int position = super.getPosition() - start;
        if (position > getCount()) {
            moveToPosition(getCount());
            return getCount();
        }
        if (position < -1) {
            moveToPosition(-1);
            return -1;
        }
        return position;
    }

    @Override
    public boolean move(int offset) {
        return moveToPosition(getPosition() + offset);
    }

    @Override
    public boolean moveToPosition(int position) {
        boolean ret = true;
        if (position < 0) {
            position = -1;
            ret = false;
        }

        if (position >= getCount()) {
            position = getCount();
            ret = false;
        }

        cursor.moveToPosition(position + start);
        return ret;
    }

    @Override
    public boolean moveToFirst() {
        return moveToPosition(0);
    }

    @Override
    public boolean moveToLast() {
        return moveToPosition(getCount() - 1);
    }

    @Override
    public boolean moveToNext() {
        return move(1);
    }

    @Override
    public boolean moveToPrevious() {
        return move(-1);
    }

    @Override
    public boolean isFirst() {
        return getPosition() == 0 && getCount() != 0;
    }

    @Override
    public boolean isLast() {
        int cnt = getCount();
        return getPosition() == (cnt - 1) && cnt != 0;
    }

    @Override
    public boolean isBeforeFirst() {
        if (getCount() == 0) {
            return true;
        }
        return getPosition() == -1;
    }

    @Override
    public boolean isAfterLast() {
        if (getCount() == 0) {
            return true;
        }
        return getPosition() == getCount();
    }

    @Override
    public List<T> subList(int i, int i1) {
        final int start = i + this.start;
        final int end = i1 + this.start;
        return new SubCursorList<T>(cursor, marshaller, start, end);
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
