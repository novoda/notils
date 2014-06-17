package com.novoda.notils.logger.toast;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

public class StackingToastDisplayer implements ToastDisplayer {

    private final Context context;
    private final Collection<Toast> toasts;

    /**
     * @param context Application context should be passed
     */
    StackingToastDisplayer(Context context) {
        this.context = context;
        this.toasts = new ArrayList<Toast>();
    }

    @Override
    public void display(String message) {
        display(message, Toast.LENGTH_SHORT);
    }

    @Override
    public void display(int stringResourceId) {
        display(stringResourceId, Toast.LENGTH_SHORT);
    }

    @Override
    public void displayLong(String message) {
        display(message, Toast.LENGTH_LONG);
    }

    @Override
    public void displayLong(int stringResourceId) {
        display(stringResourceId, Toast.LENGTH_SHORT);
    }

    private void display(String message, int lengthMillis) {
        Toast toast = Toast.makeText(context, message, lengthMillis);
        display(toast);
    }

    private void display(int stringResourceId, int lengthMillis) {
        Toast toast = Toast.makeText(context, stringResourceId, lengthMillis);
        display(toast);
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
