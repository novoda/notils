package com.novoda.notils.cursor;

import java.util.ListIterator;

public class CursorListIterator<T> implements ListIterator<T> {

    private final CursorList<T> cursorList;
    private int index;

    public CursorListIterator(CursorList<T> cursorList, int index) {
        this.cursorList = cursorList;
        this.index = index;
    }

    @Override
    public boolean hasNext() {
        return index < cursorList.getCount();
    }

    @Override
    public boolean hasPrevious() {
        return index > 0;
    }

    @Override
    public T next() {
        return cursorList.get(index++);
    }

    @Override
    public int nextIndex() {
        return index + 1;
    }

    @Override
    public T previous() {
        return cursorList.get(--index);
    }

    @Override
    public int previousIndex() {
        return index - 1;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(T t) {
        throw new UnsupportedOperationException();
    }
}
