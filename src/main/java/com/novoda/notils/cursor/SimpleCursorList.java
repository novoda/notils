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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class SimpleCursorList<T> implements CursorList<T> {

    protected Cursor cursor;
    protected final CursorMarshaller<T> marshaller;
    protected Map<T, Integer> indexMap;
    protected ArrayList<Integer> currentToOriginIndex;
    protected int[] originToCurrentIndex;

    public SimpleCursorList(Cursor cursor, CursorMarshaller<T> marshaller) {
        this.cursor = cursor;
        this.marshaller = marshaller;
        indexMap = new HashMap<T, Integer>();

        currentToOriginIndex = new ArrayList<Integer>(cursor.getCount() + 1);
        originToCurrentIndex = new int[cursor.getCount() + 1];
        for (int i = 0; i <= cursor.getCount(); i++) {
            currentToOriginIndex.add(i);
            originToCurrentIndex[i] = i;
        }
    }

    @Override
    public int getCount() {
        if (cursor == null) {
            return 0;
        }
        if (cursor.isClosed()) {
            return 0;
        }

        return currentToOriginIndex.size() - 1;
    }

    @Override
    public int getPosition() {
        return toCurrentIndex(cursor.getPosition());
    }

    @Override
    public boolean move(int offset) {
        return moveToPosition(getPosition() + offset);
    }

    @Override
    public boolean moveToPosition(int position) {
        return cursor.moveToPosition(toOriginIndex(position));
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
        return new CursorListIterator<T>(this, i);
    }

    @Override
    synchronized public T get(int index) {
        if (moveToPosition(index)) {
            T ret = marshaller.marshall(cursor);
            indexMap.put(ret, toOriginIndex(index));
            return ret;
        } else {
            throw new CursorListException("CursorList tries to access data at index "
                    + index + "(" + toOriginIndex(index) + ")"
                    + " while com.novoda.notils.cursor has size " + getCount()
                    + "(" + cursor.getCount() + ")");
        }
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index == -1) {
            return false;
        }

        remove(index);
        return true;
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

                currentToOriginIndex = new ArrayList<Integer>(cursor.getCount() + 1);
                originToCurrentIndex = new int[cursor.getCount() + 1];
                for (int i = 0; i <= cursor.getCount(); i++) {
                    currentToOriginIndex.add(i);
                    originToCurrentIndex[i] = i;
                }
            } else {
                int size = cursor.getCount();
                int addSize = ((Cursor) c).getCount();

                currentToOriginIndex.remove(currentToOriginIndex.size() - 1);
                originToCurrentIndex = Arrays.copyOf(originToCurrentIndex, size + addSize + 1);
                for (int i = size; i <= size + addSize; i++) {
                    currentToOriginIndex.add(i);
                    originToCurrentIndex[i] = i;
                }

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
        currentToOriginIndex = null;
        originToCurrentIndex = null;
    }

    @Override
    public T set(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, T element) {
        Integer orgIndex = indexMap.get(element);
        if (orgIndex == null) {
            throw new UnsupportedOperationException();
        }

        currentToOriginIndex.add(index, orgIndex);

        for (int i = 0; i < originToCurrentIndex.length; i++) {
            if (originToCurrentIndex[i] >= index) {
                originToCurrentIndex[i]++;
            }
        }
        originToCurrentIndex[orgIndex] = index;
    }

    @Override
    public boolean add(T element) {
        add(size(), element);
        return true;
    }

    @Override
    public T remove(int index) {
        T ret = get(index);
        int orgIndex = toOriginIndex(index);

        currentToOriginIndex.remove(index);

        originToCurrentIndex[orgIndex] = -1;
        for (int i = 0; i < originToCurrentIndex.length; i++) {
            if (originToCurrentIndex[i] > index) {
                originToCurrentIndex[i]--;
            }
        }

        return ret;
    }

    @Override
    public int indexOf(Object o) {
        Integer index = indexMap.get(o);

        if (index == null) {
            return -1;
        }

        return toCurrentIndex(index);
    }

    @Override
    public int lastIndexOf(Object o) {
        return indexOf(o);
    }

    @Override
    public boolean contains(Object o) {
        if (indexOf(o) != -1) {
            return true;
        }
        return false;
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

    protected int toCurrentIndex(int index) {
        return originToCurrentIndex[index];
    }

    protected int toOriginIndex(int index) {
        return currentToOriginIndex.get(index);
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
