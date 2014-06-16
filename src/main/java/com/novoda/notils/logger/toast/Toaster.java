package com.novoda.notils.logger.toast;

import android.content.Context;

/**
 * A Toast helper giving a short hand to show toasts.
 * Also checks for the validity of your context and Log's if it cannot toast.
 *
 * @deprecated {@link StackingToastDisplayer} subsumes the functionality of this Toaster
 */
@Deprecated
public class Toaster implements ToastDisplayer {

    private final StackingToastDisplayer toastDisplayer;

    /**
     * A helper in Toasting messages to the screen
     *
     * @param context
     * @deprecated use {@link Toaster#newInstance(Context)} instead
     */
    @Deprecated
    public Toaster(Context context) {
        this(StackingToastDisplayer.newInstance(context));
    }

    private Toaster(StackingToastDisplayer toastDisplayer) {
        this.toastDisplayer = toastDisplayer;
    }

    public static Toaster newInstance(Context context) {
        return new Toaster(StackingToastDisplayer.newInstance(context));
    }

    /**
     * Toast.LENGTH_SHORT
     *
     * @param stringResourceId
     * @deprecated use {@link #display(int)} instead
     */
    @Deprecated
    public void popToast(int stringResourceId) {
        display(stringResourceId);
    }

    /**
     * Toast.LENGTH_SHORT
     *
     * @param message
     * @deprecated use {@link #display(String)}
     */
    @Deprecated
    public void popToast(String message) {
        display(message);
    }

    /**
     * Toast.LENGTH_LONG
     *
     * @param stringResourceId
     * @deprecated use {@link #displayLong(int)} instead
     */
    @Deprecated
    public void popBurntToast(int stringResourceId) {
        displayLong(stringResourceId);
    }

    /**
     * Toast.LENGTH_LONG
     *
     * @param message
     * @deprecated use {@link #displayLong(String)} instead
     */
    @Deprecated
    public void popBurntToast(String message) {
        displayLong(message);
    }

    @Override
    public void display(String message) {
        toastDisplayer.display(message);
    }

    @Override
    public void display(int stringResourceId) {
        toastDisplayer.display(stringResourceId);
    }

    @Override
    public void displayLong(String message) {
        toastDisplayer.displayLong(message);
    }

    @Override
    public void displayLong(int stringResourceId) {
        toastDisplayer.displayLong(stringResourceId);
    }

    @Override
    public void cancelAll() {
        toastDisplayer.cancelAll();
    }

}
