package com.novoda.notils.cursor;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import java.util.List;

public class TypedResolver {

    private final ContentResolver resolver;

    public TypedResolver(ContentResolver resolver) {
        this.resolver = resolver;
    }

    public <T> T get(Uri uri, CursorMarshaller<T> type) {
        return get(uri, null, null, null, type);
    }

    public <T> T get(Uri uri, String[] projection, CursorMarshaller<T> type) {
        return get(uri, projection, null, null, type);
    }

    public <T> T get(Uri uri, String selection, String[] selectionArgs, CursorMarshaller<T> type) {
        return get(uri, null, selection, selectionArgs, type);
    }

    public <T> T get(Uri uri, String[] projection, String selection, String[] selectionArgs, CursorMarshaller<T> type) {
        Cursor cursor = resolver.query(uri, projection, selection, selectionArgs, null);
        return new CursorList<T>(cursor, type).get(0);
    }

    public <T> List<T> query(Uri uri, CursorMarshaller<T> type) {
        return query(uri, null, null, type);
    }

    public <T> List<T> query(Uri uri, String selection, String[] selectionArgs, CursorMarshaller<T> type) {
        return query(uri, null, selection, selectionArgs, null, type);
    }

    public <T> List<T> query(Uri uri, String[] projection, String selection, String[] selectionArgs, CursorMarshaller<T> type) {
        return query(uri, projection, selection, selectionArgs, null, type);
    }

    public <T> List<T> query(Uri uri, String selection, String[] selectionArgs, String sortOrder, CursorMarshaller<T> type) {
        return query(uri, null, selection, selectionArgs, sortOrder, type);
    }

    public <T> List<T> query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, CursorMarshaller<T> type) {
        Cursor cursor = resolver.query(uri, projection, selection, selectionArgs, sortOrder);
        return new CursorList<T>(cursor, type);
    }
}