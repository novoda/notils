package com.novoda.notils.logger.toast;

import android.content.Context;
import android.widget.Toast;

import com.novoda.notils.logger.simple.Log;

import java.util.LinkedList;
import java.util.Queue;

public class NonStackingToastDisplayer implements ToastDisplayer {

    private final Context context;
    private final Queue<Toast> toasts;

    public static NonStackingToastDisplayer newInstance(Context context) {
        return new NonStackingToastDisplayer(context, new LinkedList<Toast>());
    }

    private NonStackingToastDisplayer(Context context, Queue<Toast> toasts) {
        this.context = context;
        this.toasts = toasts;
    }

    @Override
    public void display(String message) {
        display(message, Toast.LENGTH_SHORT);
    }

    @Override
    public void display(int resId) {
        display(resId, Toast.LENGTH_SHORT);
    }

    @Override
    public void displayLong(String message) {
        display(message, Toast.LENGTH_LONG);
    }

    @Override
    public void displayLong(int resId) {
        display(resId, Toast.LENGTH_SHORT);
    }

    private void display(String message, int lengthMillis) {
        if (contextIsStillAlive()) {
            cancelAll();
            Toast toast = Toast.makeText(context, message, lengthMillis);
            toast.show();
            toasts.add(toast);
        } else {
            Log.e("Couldn't toast, context has become null. Attempted message: " + message);
        }
    }

    private void display(int resId, int lengthMillis) {
        if (contextIsStillAlive()) {
            cancelAll();
            Toast toast = Toast.makeText(context, resId, lengthMillis);
            toast.show();
            toasts.add(toast);
        } else {
            Log.e("Couldn't toast, context has become null.");
        }
    }

    private boolean contextIsStillAlive() {
        return context != null;
    }

    @Override
    public void cancelAll() {
        for (Toast toast : toasts) {
            toast.cancel();
        }
        toasts.clear();
    }

}
