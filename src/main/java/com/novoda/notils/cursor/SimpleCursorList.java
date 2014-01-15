package com.novoda.notils.cursor;

import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.net.Uri;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SimpleCursorList<T> implements CursorList<T> {

    protected Cursor cursor;
    protected final CursorMarshaller<T> marshaller;

    public SimpleCursorList(Cursor cursor, CursorMarshaller<T> marshaller) {
        this.cursor = cursor;
        this.marshaller = marshaller;
    }

    @Override
    public int getCount() {
        if (cursor == null) {
            return 0;
        }
        if (cursor.isClosed()) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override
    public int getPosition() {
        return cursor.getPosition();
    }

    @Override
    public boolean move(int offset) {
        return cursor.move(offset);
    }

    @Override
    public boolean moveToPosition(int position) {
        return cursor.moveToPosition(position);
    }

    @Override
    public boolean moveToFirst() {
        return cursor.moveToFirst();
    }

    @Override
    public boolean moveToLast() {
        return cursor.moveToLast();
    }

    @Override
    public boolean moveToNext() {
        return cursor.moveToNext();
    }

    @Override
    public boolean moveToPrevious() {
        return cursor.moveToPrevious();
    }

    @Override
    public boolean isFirst() {
        return cursor.isFirst();
    }

    @Override
    public boolean isLast() {
        return cursor.isLast();
    }

    @Override
    public boolean isBeforeFirst() {
        return cursor.isBeforeFirst();
    }

    @Override
    public boolean isAfterLast() {
        return cursor.isAfterLast();
    }

    @Override
    public int getColumnIndex(String columnName) {
        return cursor.getColumnIndex(columnName);
    }

    @Override
    public int getColumnIndexOrThrow(String columnName) throws IllegalArgumentException {
        return cursor.getColumnIndexOrThrow(columnName);
    }

    @Override
    public String getColumnName(int columnIndex) {
        return cursor.getColumnName(columnIndex);
    }

    @Override
    public String[] getColumnNames() {
        return cursor.getColumnNames();
    }

    @Override
    public int getColumnCount() {
        return cursor.getColumnCount();
    }

    @Override
    public byte[] getBlob(int columnIndex) {
        return cursor.getBlob(columnIndex);
    }

    @Override
    public String getString(int columnIndex) {
        return cursor.getString(columnIndex);
    }

    @Override
    public void copyStringToBuffer(int columnIndex, CharArrayBuffer buffer) {
        cursor.copyStringToBuffer(columnIndex, buffer);
    }

    @Override
    public short getShort(int columnIndex) {
        return cursor.getShort(columnIndex);
    }

    @Override
    public int getInt(int columnIndex) {
        return cursor.getInt(columnIndex);
    }

    @Override
    public long getLong(int columnIndex) {
        return cursor.getLong(columnIndex);
    }

    @Override
    public float getFloat(int columnIndex) {
        return cursor.getFloat(columnIndex);
    }

    @Override
    public double getDouble(int columnIndex) {
        return cursor.getDouble(columnIndex);
    }

    @Override
    public int getType(int columnIndex) {
        return cursor.getType(columnIndex);
    }

    @Override
    public boolean isNull(int columnIndex) {
        return cursor.isNull(columnIndex);
    }

    @Override
    public void deactivate() {
        cursor.deactivate();
    }

    @Override
    public boolean requery() {
        return cursor.requery();
    }

    @Override
    public void close() {
        cursor.close();
    }

    @Override
    public boolean isClosed() {
        return cursor.isClosed();
    }

    @Override
    public void registerContentObserver(ContentObserver observer) {
        cursor.registerContentObserver(observer);
    }

    @Override
    public void unregisterContentObserver(ContentObserver observer) {
        cursor.unregisterContentObserver(observer);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        cursor.registerDataSetObserver(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        cursor.unregisterDataSetObserver(observer);
    }

    @Override
    public void setNotificationUri(ContentResolver cr, Uri uri) {
        cursor.setNotificationUri(cr, uri);
    }

    @Override
    public boolean getWantsAllOnMoveCalls() {
        return cursor.getWantsAllOnMoveCalls();
    }

    @Override
    public Bundle getExtras() {
        return cursor.getExtras();
    }

    @Override
    public Bundle respond(Bundle extras) {
        return cursor.respond(extras);
    }

    @Override
    public int size() {
        return getCount();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return listIterator();
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        if (cursor == null) {
            return new EmptyListIterator<T>();
        }
        if (cursor.isClosed()) {
            return new EmptyListIterator<T>();
        }
        return new CursorListIterator<T>(cursor, marshaller, i);
    }

    @Override
    synchronized public T get(int index) {
        if (moveToPosition(index)) {
            return marshaller.marshall(cursor);
        } else {
            throw new CursorListException("CursorList tries to access data at index " + index + " while com.novoda.notils.cursor has size " + cursor.getCount());
        }
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c instanceof Cursor) {
            if (cursor.getCount() == 0) {
                cursor = (Cursor)c;
            } else {
                cursor = new MergeCursor(new Cursor[] {cursor, (Cursor)c});
            }
        } else {
            throw new UnsupportedOperationException();
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        cursor = new MatrixCursor(cursor.getColumnNames());
    }

    @Override
    public T set(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int i, int i1) {
        final int start = i;
        final int end = i1;
        //return subArrayList(start, end);
        return new SubCursorList<T>(cursor, marshaller, start, end);
    }

    @SuppressWarnings("unused")
    private List<T> subArrayList(int start, int end) {
        //List<T> list = new SimpleCursorList<T>(cursor, marshaller); add() did not be implemented
        List<T> list = new ArrayList<T>();
        if (start < end) {
            for (int m = start; m < end; m++) {
                T item = get(m);
                //logD("CursorList", "< end: " + item);
                //if (item != null)
                list.add(item);
            }
        } else {
            for (int m = end - 1; m >= start; m--) {
                T item = get(m);
                //logD("CursorList", "> end: " + item);
                //if (item != null)
                list.add(item);
            }
        }
        return list;
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

    @Override
    public Object[] toArray() {
        final int size = size();
        Object[] array = new Object[size];
        //for (int i = 0; i < size; i++) array[i] = get(i);
        //return array;
        return toArray(array);
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        final int size = ts.length;
        for (int i = 0; i < size; i++) ts[i] = (T) get(i);
        return ts;
    }

    public static class CursorListException extends RuntimeException {
        public CursorListException(String message) {
            super(message);
        }
    }
}
