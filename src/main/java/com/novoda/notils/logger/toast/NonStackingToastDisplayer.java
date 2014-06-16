package com.novoda.notils.logger.toast;

import android.content.Context;
import android.widget.Toast;

import com.novoda.notils.logger.simple.Log;

import java.util.ArrayList;
import java.util.Collection;

public class NonStackingToastDisplayer implements ToastDisplayer {

    private final Context context;
    private final Collection<Toast> toasts;

    /**
     * @param context Application context should be passed
     * @param toasts an empty, modifiable Collection of Toasts
     */
    private NonStackingToastDisplayer(Context context, Collection<Toast> toasts) {
        this.context = context;
        this.toasts = toasts;
    }

    public static NonStackingToastDisplayer newInstance(Context context) {
        return new NonStackingToastDisplayer(context.getApplicationContext(), new ArrayList<Toast>());
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
     * Cancels all previous Toasts before displaying this one.
     *
     * @param message
     */
    @Override
    public void displayLong(String message) {
        display(message, Toast.LENGTH_LONG);
    }

    /**
     * {@inheritDoc}
     * Cancels all previous Toasts before displaying this one.
     *
     * @param stringResourceId
     */
    @Override
    public void displayLong(int stringResourceId) {
        display(stringResourceId, Toast.LENGTH_SHORT);
    }

    private void display(String message, int lengthMillis) {
        if (contextIsStillAlive()) {
            cancelAll();
            Toast toast = Toast.makeText(context, message, lengthMillis);
            display(toast);
        } else {
            Log.e("Couldn't toast, context has become null. Attempted message: " + message);
        }
    }

    private void display(int stringResourceId, int lengthMillis) {
        if (contextIsStillAlive()) {
            cancelAll();
            Toast toast = Toast.makeText(context, stringResourceId, lengthMillis);
            display(toast);
        } else {
            Log.e("Couldn't toast, context has become null.");
        }
    }

    private boolean contextIsStillAlive() {
        return context != null;
    }

    private void display(Toast toast) {
        toasts.add(toast);
        toast.show();
    }

    @Override
    public void cancelAll() {
        for (Toast toast : toasts) {
            toast.cancel();
        }
        toasts.clear();
    }

}
