package com.novoda.notils.logger.simple;

import com.novoda.notils.exception.DeveloperError;

/**
 * Wrapper around Android Log that will print
 * <p/>
 * 'TAG [Thread Name][Class name][Method name:line number] message'
 */
public final class Log {

    /**
     * @deprecated Use getter {@link #shouldShowLogs()} and setter {@link #setShowLogs(boolean)} instead
     * <p/>
     * Flag to enable or disable logs
     * <p/>
     * Recommended you set this to {@code BuildConfig.DEBUG} in your class that extends Application
     */
    @Deprecated
    public static boolean SHOW_LOGS = false;

    private static boolean INITIALISED = false;

    /**
     * Log tag to use - Default is 'NoTils'
     */
    public static String TAG = "NoTils";
    /**
     * How deep to log stack traces - Default is 5
     */
    public static int STACK_DEPTH = 5;

    private static final int DOT_CLASS = 5;

    /**
     * Separator for the concatenation of the vaargs
     */
    private static String separator = " ";

    private Log() {
    }

    public static void setSeparator(String separator) {
        Log.separator = separator;
    }

    public static void v(Object... msg) {
        try {
            if (shouldShowLogs()) {
                android.util.Log.v(TAG, getDetailedLog(formatString(msg)));
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    public static void i(Object... msg) {
        try {
            if (shouldShowLogs()) {
                android.util.Log.i(TAG, getDetailedLog(formatString(msg)));
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    public static void d(Object... msg) {
        try {
            if (shouldShowLogs()) {
                android.util.Log.d(TAG, getDetailedLog(formatString(msg)));
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    public static void w(Object... msg) {
        try {
            if (shouldShowLogs()) {
                android.util.Log.w(TAG, getDetailedLog(formatString(msg)));
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    public static void e(Object... msg) {
        try {
            if (shouldShowLogs()) {
                android.util.Log.e(TAG, getDetailedLog(formatString(msg)));
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    /**
     * @deprecated use (Throwable t, Object... msg)
     */
    @Deprecated
    public static void w(String msg, Throwable t) {
        w(t, msg);
    }

    public static void w(Throwable t, Object... msg) {
        try {
            if (shouldShowLogs()) {
                android.util.Log.w(TAG, getDetailedLog(formatString(msg)), t);
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    /**
     * @deprecated use (Throwable t, Object... msg)
     */
    @Deprecated
    public static void d(String msg, Throwable t) {
        d(t, msg);
    }

    public static void d(Throwable t, Object... msg) {
        try {
            if (shouldShowLogs()) {
                android.util.Log.d(TAG, getDetailedLog(formatString(msg)), t);
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    /**
     * @deprecated use (Throwable t, Object... msg)
     */
    @Deprecated
    public static void e(String msg, Throwable t) {
        e(t, msg);
    }

    public static void e(Throwable t, Object... msg) {
        try {
            if (shouldShowLogs()) {
                android.util.Log.e(TAG, getDetailedLog(formatString(msg)), t);
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    /**
     * @deprecated use (Throwable t, Object... msg)
     */
    @Deprecated
    public static void wtf(String msg, Throwable t) {
        wtf(t, msg);
    }

    public static void wtf(Throwable t, Object... msg) {
        try {
            if (shouldShowLogs()) {
                android.util.Log.wtf(TAG, getDetailedLog(formatString(msg)), t);
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    private static String getDetailedLog(String msg) {
        return getDetailedLog(msg, STACK_DEPTH);
    }

    private static String getDetailedLog(String msg, int depth) {
        if (!shouldShowLogs()) {
            return msg;
        }
        Thread current = Thread.currentThread();
        final StackTraceElement trace = current.getStackTrace()[depth];
        final String filename = trace.getFileName();
        return "[" + current.getName() + "][" + filename.substring(0, filename.length() - DOT_CLASS) + "."
                + trace.getMethodName() + ":" + trace.getLineNumber() + "] " + msg;
    }

    private static void logError(Throwable ignore) {
        android.util.Log.e(TAG, "Error", ignore);
    }

    private static String formatString(Object... msg) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object o : msg) {
            stringBuilder.append(String.valueOf(o)).append(separator);
        }
        return stringBuilder.toString();
    }

    /**
     * Toggle if logs should be output or not. Recommended to bind against {@code BuildConfig.DEBUG}
     *
     * @param showLogs {@code true} for showing logs, {@code false} to deactivate them
     */
    public static void setShowLogs(boolean showLogs) {
        Log.INITIALISED = true;
        Log.SHOW_LOGS = showLogs;
    }

    /**
     * Returns the active status of the log switch
     *
     * @return {@code true} if logs are active, {@code false} if deactivated
     */
    public static boolean shouldShowLogs() {
        if (INITIALISED) {
            return SHOW_LOGS;
        } else {
            throw new DeveloperError("#rekt - To use simple logger you need to have called setShowLogs(boolean). "
                    + "The typical way is to use Log.setShowLogs(BuildConfig.DEBUG) "
                    + "in onCreate() of your class that extends Application."
                    + "(It's ok we've all been there.)");
        }
    }
}
