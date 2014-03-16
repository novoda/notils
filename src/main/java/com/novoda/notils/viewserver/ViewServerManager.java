package com.novoda.notils.viewserver;

import android.app.Activity;

/**
 * Handles the calls to the ViewServer, allowing applications on non-rooted
 * devices to use HierarchyViewer.
 *
 * To use this, your application must specify the internet permission in the
 * Android Manifest. If your application does not require internet permissions
 * otherwise, specify this in a separate debug manifest; the ViewServer should
 * only be used during debug anyway.
 *
 * Ensure {@link #ON} is set to true if you want to use the ViewServer. This
 * is useful to allow switching off for debug builds.
 *
 * Create an instance of the ViewServerManager, and call {@link #onCreate()},
 * {@link #onResume()} and {@link #onDestroy()} in the corresponding methods
 * in Activity.
 */
public class ViewServerManager {

    public static boolean ON;

    private Activity activity;

    public ViewServerManager(Activity activity) {
        this.activity = activity;
    }

    public void onCreate() {
        if (ON) {
            ViewServer.get(activity).addWindow(activity);
        }
    }

    public void onResume() {
        if (ON) {
            ViewServer.get(activity).setFocusedWindow(activity);
        }
    }

    public void onDestroy() {
        if (ON) {
            ViewServer.get(activity).removeWindow(activity);
        }
    }

}
