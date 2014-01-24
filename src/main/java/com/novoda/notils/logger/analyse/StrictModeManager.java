package com.novoda.notils.logger.analyse;

import android.os.StrictMode;

/**
 * Enables StrictMode with defaults to detect all wrong doing.
 * Default penalty is to close the activity and log to console.
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

    public static void initializeStrictMode() {
        if (ON) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }
    }
}
