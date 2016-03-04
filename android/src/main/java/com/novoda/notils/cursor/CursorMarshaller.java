package com.novoda.notils.cursor;

import android.database.Cursor;

public interface CursorMarshaller<T> {
    T marshall(Cursor cursor);
}
