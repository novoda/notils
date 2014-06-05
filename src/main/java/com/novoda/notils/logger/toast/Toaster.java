package com.novoda.notils.logger.toast;

import android.content.Context;
import android.widget.Toast;

import com.novoda.notils.logger.simple.Log;

/**
 * Toast helper providing a short hand to display Toast messages.
 * <p/>
 * If the Toast cannot be shown, it will display an error log.
 */
public class Toaster {

    private final Context context;
    private final boolean cancelCurrentBeforeShowingNewToast;

    private Toast toast;

    public static Toaster from(Context context) {
        return new Toaster(context);
    }

    public static Toaster nonStackingFrom(Context context) {
        return new Toaster(context, true);
    }

    /**
     * Returns new Toaster helper.
     *
     * @param context
     * @deprecated this ctor will be made private at the next major release.
     * Use Toaster.from(Context) or Toaster.nonStackingFrom(Context) instead.
     */
    @Deprecated
    public Toaster(Context context) {
        this.context = context;
        this.cancelCurrentBeforeShowingNewToast = false;
    }

    private Toaster(Context context, boolean cancelCurrentBeforeShowingNewToast) {
        this.context = context;
        this.cancelCurrentBeforeShowingNewToast = cancelCurrentBeforeShowingNewToast;
    }

    /**
     * Toast.LENGTH_SHORT
     *
     * @param msgId String resource id for message
     */
    public void popToast(int msgId) {
        popToast(msgId, Toast.LENGTH_SHORT);
    }

    /**
     * Toast.LENGTH_SHORT
     *
     * @param msg message to display
     */
    public void popToast(String msg) {
        popToast(msg, Toast.LENGTH_SHORT);
    }

    /**
     * Toast.LENGTH_LONG
     *
     * @param msgId String resource id for message
     */
    public void popBurntToast(int msgId) {
        popToast(msgId, Toast.LENGTH_LONG);
    }

    /**
     * Toast.LENGTH_LONG
     *
     * @param msg message to display
     */
    public void popBurntToast(String msg) {
        popToast(msg, Toast.LENGTH_LONG);
    }

    private void popToast(String msg, int length) {
        if (cancelCurrentBeforeShowingNewToast) {
            cancelOldToast();
        }

        if (context != null) {
            toast = Toast.makeText(context, msg, length);
            toast.show();
        } else {
            Log.e("Unable to toast message (context is null). Message: " + msg);
        }
    }

    private void popToast(int msgId, int length) {
        if (cancelCurrentBeforeShowingNewToast) {
            cancelOldToast();
        }

        if (context != null) {
            toast = Toast.makeText(context, msgId, length);
            toast.show();
        } else {
            Log.e("Unable to toast message (context is null).");
        }
    }

    private void cancelOldToast() {
        if (toast != null) {
            toast.cancel();
        }
    }

}
