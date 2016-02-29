package com.novoda.notils.logger.toast;

import android.content.Context;

/**
 * Factory for ToastDisplayer implementations
 */
public class ToastDisplayers {

    private ToastDisplayers() {
    }

    /**
     * Creates a new "classic" ToastDisplayer, which queues Toasts in the order they are requested.
     *
     * @param context
     * @return toastDisplayer
     */
    public static ToastDisplayer newInstance(Context context) {
        return new StackingToastDisplayer(context.getApplicationContext());
    }

    /**
     * Creates a new ToastDisplayer which cancels all pending Toasts before showing the requested one.
     * <p/>
     * Cancels pending Toasts requested using this ToastDisplayer.
     *
     * @param context
     * @return toastDisplayer
     */
    public static ToastDisplayer noPendingToastsToastDisplayer(Context context) {
        return new NonStackingToastDisplayer(context.getApplicationContext());
    }

}
