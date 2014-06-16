package com.novoda.notils.logger.toast;

import android.content.Context;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Queue;

public class StackingToastDisplayer implements ToastDisplayer {

    private final Context context;
    private final Queue<Toast> toasts;

    public static StackingToastDisplayer newInstance(Context context) {
        return new StackingToastDisplayer(context, new LinkedList<Toast>());
    }

    private StackingToastDisplayer(Context context, Queue<Toast> toasts) {
        this.context = context;
        this.toasts = toasts;
    }

    @Override
    public Toast display(String message) {
        return display(message, Toast.LENGTH_SHORT);
    }

    @Override
    public Toast display(int resId) {
        return display(resId, Toast.LENGTH_SHORT);
    }

    @Override
    public Toast displayLong(String message) {
        return display(message, Toast.LENGTH_LONG);
    }

    @Override
    public Toast displayLong(int resId) {
        return display(resId, Toast.LENGTH_SHORT);
    }

    private Toast display(String message, int lengthMillis) {
        Toast toast = Toast.makeText(context, message, lengthMillis);
        toast.show();
        toasts.add(toast);
        return toast;
    }

    private Toast display(int resId, int lengthMillis) {
        Toast toast = Toast.makeText(context, resId, lengthMillis);
        toast.show();
        toasts.add(toast);
        return toast;
    }

    @Override
    public void cancelAll() {
        for (Toast toast : toasts) {
            toast.cancel();
        }
        toasts.clear();
    }

}
