package com.novoda.notils.cursor;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.ViewGroup;

public abstract class TypedCursorAdapter<T> extends CursorAdapter {

    public TypedCursorAdapter(Context context, CursorList<T> c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    public TypedCursorAdapter(Context context, CursorList<T> c, int flags) {
        super(context, c, flags);
    }

    @Override
    @SuppressWarnings("unchecked")
    public final View newView(Context context, Cursor cursor, ViewGroup parent) {
        return newView(context, getElement((CursorList<T>) cursor), parent);
    }

    public abstract View newView(Context context, T element, ViewGroup parent);

    @Override
    @SuppressWarnings("unchecked")
    public final void bindView(View view, Context context, Cursor cursor) {
        bindView(view, context, getElement((CursorList<T>) cursor));
    }

    public abstract void bindView(View view, Context context, T element);

    private <T> T getElement(CursorList<T> cursorList) {
        return cursorList.get(cursorList.getPosition());
    }
}