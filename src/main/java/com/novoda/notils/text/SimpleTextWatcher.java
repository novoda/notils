package com.novoda.notils.text;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Simple implementation of the {@link android.text.TextWatcher} interface with stub implementations of each method.
 * Extend this if you do not intend to override every method of {@link android.text.TextWatcher}.
 */
public class SimpleTextWatcher implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // override if needed
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // override if needed
    }

    @Override
    public void afterTextChanged(Editable s) {
        // override if needed
    }
}
