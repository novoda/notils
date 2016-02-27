package com.novoda.notils.android.toast;

import android.content.Context;
import android.widget.Toast;

public class NonStackingToastDisplayer implements ToastDisplayer {

    private final Context context;

    private Toast toast;

    /**
     * @param context Application context should be passed
     */
    NonStackingToastDisplayer(Context context) {
        this.context = context;
    }

    /**
     * {@inheritDoc}
     * Cancels all previous Toasts before displaying this one.
     *
     * @param message
     */
    @Override
    public void display(String message) {
        display(message, Toast.LENGTH_SHORT);
    }

    /**
     * {@inheritDoc}
     * Cancels all previous Toasts before displaying this one.
     *
     * @param stringResourceId
     */
    @Override
    public void display(int stringResourceId) {
        display(stringResourceId, Toast.LENGTH_SHORT);
    }

    /**
     * {@inheritDoc}
     * Cancels all pending Toasts before displaying this one.
     *
     * @param message
     */
    @Override
    public void displayLong(String message) {
        display(message, Toast.LENGTH_LONG);
    }

    /**
     * {@inheritDoc}
     * Cancels all pending Toasts before displaying this one.
     *
     * @param stringResourceId
     */
    @Override
    public void displayLong(int stringResourceId) {
        display(stringResourceId, Toast.LENGTH_LONG);
    }

    private void display(String message, int lengthMillis) {
        cancelAll();
        toast = Toast.makeText(context, message, lengthMillis);
        toast.show();
    }

    private void display(int stringResourceId, int lengthMillis) {
        cancelAll();
        toast = Toast.makeText(context, stringResourceId, lengthMillis);
        toast.show();
    }

    @Override
    public void cancelAll() {
        if (toast == null) {
            return;
        }
        toast.cancel();
    }

}
