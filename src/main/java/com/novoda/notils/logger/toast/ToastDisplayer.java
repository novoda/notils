package com.novoda.notils.logger.toast;

/**
 * Displays Android Toast notifications
 */
public interface ToastDisplayer {

    /**
     * Displays Toast for the standard Toast.LENGTH_SHORT period
     *
     * @param message
     */
    void display(String message);

    /**
     * Displays Toast for the standard Toast.LENGTH_SHORT period
     *
     * @param resId
     */
    void display(int resId);

    /**
     * Displays Toast for the longer Toast.LENGTH_LONG period
     *
     * @param message
     */
    void displayLong(String message);

    /**
     * Displays Toast for the longer Toast.LENGTH_LONG period
     *
     * @param resId
     */
    void displayLong(int resId);

    /**
     * Cancels any Toast that is currently showing, or is waiting to be shown.
     */
    void cancelAll();

}
