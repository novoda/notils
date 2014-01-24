package com.novoda.notils.logger.toast;

import android.content.Context;
import android.widget.Toast;

import com.novoda.notils.logger.simple.Log;

/**
 * A Toast helper giving a short hand to show toasts.
 * Also checks for the validity of your context and Log's if it cannot toast.
 */
public class Toaster {

    private final Context context;

    /**
     * A helper in Toasting messages to the screen
     *
     * @param context
     */
    public Toaster(Context context) {
        this.context = context;
    }

    /**
     * Toast.LENGTH_SHORT
     *
     * @param msgId
     */
    public void popToast(int msgId) {
        popToast(msgId, Toast.LENGTH_SHORT);
    }

    /**
     * Toast.LENGTH_SHORT
     *
     * @param msg
     */
    public void popToast(String msg) {
        popToast(msg, Toast.LENGTH_SHORT);
    }

    /**
     * Toast.LENGTH_LONG
     *
     * @param msgId
     */
    public void popBurntToast(int msgId) {
        popToast(msgId, Toast.LENGTH_LONG);
    }

    /**
     * Toast.LENGTH_LONG
     *
     * @param msg
     */
    public void popBurntToast(String msg) {
        popToast(msg, Toast.LENGTH_LONG);
    }

    private void popToast(String msg, int length) {
        if (context != null) {
            Toast.makeText(context, msg, length).show();
        } else {
            Log.e("Couldn't toast, context has become null. Attempted msg: " + msg);
        }
    }

    private void popToast(int msgId, int length) {
        if (context != null) {
            Toast.makeText(context, msgId, length).show();
        }
    }

}
