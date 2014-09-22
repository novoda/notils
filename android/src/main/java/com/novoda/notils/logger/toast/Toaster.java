package com.novoda.notils.logger.toast;

import android.content.Context;

/**
 * A Toast helper giving a short hand to show toasts.
 * Also checks for the validity of your context and Log's if it cannot toast.
 */
public class Toaster {

    private final StackingToastDisplayer toastDisplayer;

    /**
     * A helper in Toasting messages to the screen
     *
     * @param context
     * @deprecated this ctor will be made private, use {@link #newInstance(android.content.Context)}
     */
    @Deprecated
    public Toaster(Context context) {
        this(new StackingToastDisplayer(context.getApplicationContext()));
    }

    Toaster(StackingToastDisplayer toastDisplayer) {
        this.toastDisplayer = toastDisplayer;
    }

    public static Toaster newInstance(Context context) {
        return new Toaster(new StackingToastDisplayer(context.getApplicationContext()));
    }

    /**
     * Toast.LENGTH_SHORT
     *
     * @param stringResourceId
     */
    public void popToast(int stringResourceId) {
        toastDisplayer.display(stringResourceId);
    }

    /**
     * Toast.LENGTH_SHORT
     *
     * @param message
     */
    public void popToast(String message) {
        toastDisplayer.display(message);
    }

    /**
     * Toast.LENGTH_LONG
     *
     * @param stringResourceId
     */
    public void popBurntToast(int stringResourceId) {
        toastDisplayer.displayLong(stringResourceId);
    }

    /**
     * Toast.LENGTH_LONG
     *
     * @param message
     */
    public void popBurntToast(String message) {
        toastDisplayer.displayLong(message);
    }

    /**
     * Cancels all Toasts created by this Toaster.
     */
    public void dropInBath() {
        toastDisplayer.cancelAll();
    }

}
