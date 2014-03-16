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
 * Ensure {@link #enable(boolean)} is called with true if you want to use the
 * ViewServer. This is useful to allow switching off for debug builds.
 *
 * Create an instance of the ViewServerManager, and call {@link #onCreate()},
 * {@link #onResume()} and {@link #onDestroy()} in the corresponding methods
 * in Activity.
 */
public class ViewServerManager {

    private Activity activity;
    private boolean enabled;

    public ViewServerManager(Activity activity) {
        this.activity = activity;
    }

    public void enable(boolean enabled) {
        this.enabled = enabled;
    }

    public void onCreate() {
        if (enabled) {
            ViewServer.get(activity).addWindow(activity);
        }
    }

    public void onResume() {
        if (enabled) {
            ViewServer.get(activity).setFocusedWindow(activity);
        }
    }

    public void onDestroy() {
        if (enabled) {
            ViewServer.get(activity).removeWindow(activity);
        }
    }

}
