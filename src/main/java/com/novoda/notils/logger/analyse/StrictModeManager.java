package com.novoda.notils.logger.analyse;

import android.os.Build;
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
        initializeStrictMode(newThreadPolicyBuilderWithDefaultViolations(), newVmPolicyBuilderWithDefaultViolations());
    }

    /**
     * Initializes StrictMode to close activity and log to console when a violation occurs.
     *
     * Allows specification of violations via parameters. Note, penaltyLog and penaltyDeath will be applied.
     */
    public static void initializeStrictMode(StrictMode.ThreadPolicy.Builder threadPolicyBuilder, StrictMode.VmPolicy.Builder vmPolicyBuilder) {
        if (ON) {
            StrictMode.setThreadPolicy(threadPolicyBuilder.penaltyLog().penaltyDeath().build());
            StrictMode.setVmPolicy(vmPolicyBuilder.penaltyLog().penaltyDeath().build());
        }
    }

    /**
     * Initializes StrictMode to log to console when a violation occurs.
     */
    public static void initializeStrictModeLogOnly() {
        initializeStrictModeWithPenaltyLog(newThreadPolicyBuilderWithDefaultViolations(), newVmPolicyBuilderWithDefaultViolations());
    }

    /**
     * Initializes StrictMode to log to console when a violation occurs.
     * <p/>
     * Allows specification of violations via parameters. Note, penaltyLog will be applied.
     */
    public static void initializeStrictModeWithPenaltyLog(StrictMode.ThreadPolicy.Builder threadPolicyBuilder, StrictMode.VmPolicy.Builder vmPolicyBuilder) {
        if (ON) {
            StrictMode.setThreadPolicy(threadPolicyBuilder.penaltyLog().build());
            StrictMode.setVmPolicy(vmPolicyBuilder.penaltyLog().build());
        }
    }

    private static StrictMode.VmPolicy.Builder newVmPolicyBuilderWithDefaultViolations() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            builder.detectLeakedRegistrationObjects();
        }
        return builder;
    }

    private static StrictMode.ThreadPolicy.Builder newThreadPolicyBuilderWithDefaultViolations() {
        return new StrictMode.ThreadPolicy.Builder()
                .detectCustomSlowCalls();
    }

}
