package com.novoda.notils.android;

import android.os.StrictMode;

/**
 * Enables StrictMode with defaults to detect all wrong doing.
 * <p/>
 * See http://developer.android.com/reference/android/os/StrictMode.html for more
 */
public final class StrictModeManager {

    /**
     * Flag to enable or disable StrictMode
     * Recommended you set this to BuildConfig.DEBUG in your class that extends Application
     */
    public static boolean ON = true;

    private StrictModeManager() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Initializes StrictMode to close activity and log to console when a violation occurs.
     */
    public static void initializeStrictMode() {
        if (ON) {
            StrictMode.setThreadPolicy(newThreadPolicyBuilderWithDefaults().penaltyDeath().build());
            StrictMode.setVmPolicy(newVmPolicyBuilderWithDefaults().penaltyDeath().build());
        }
    }

    /**
     * Initializes StrictMode to log to console when a violation occurs.
     */
    public static void initializeStrictModeLogOnly() {
        if (ON) {
            StrictMode.setThreadPolicy(newThreadPolicyBuilderWithDefaults().build());
            StrictMode.setVmPolicy(newVmPolicyBuilderWithDefaults().build());
        }
    }

    private static StrictMode.VmPolicy.Builder newVmPolicyBuilderWithDefaults() {
        return new StrictMode.VmPolicy.Builder().detectAll().penaltyLog();
    }

    private static StrictMode.ThreadPolicy.Builder newThreadPolicyBuilderWithDefaults() {
        return new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog();
    }

}