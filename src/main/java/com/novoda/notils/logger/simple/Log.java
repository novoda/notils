package com.novoda.notils.logger.simple;

/**
 * Wrapper around Android Log that will print
 * <p/>
 * 'TAG [Thread Name][Class name][Method name:line number] message'
 */
public final class Log {

    /**
     * Flag to enable or disable logs
     * Recommended you set this to BuildConfig.DEBUG in your class that extends Application
     */
    public static boolean SHOW_LOGS = false;
    /**
     * Log tag to use - Default is 'NoTils'
     */
    public static String TAG = "NoTils";
    /**
     * How deep to log stack traces - Default is 5
     */
    public static int STACK_DEPTH = 5;

    private static final int DOT_CLASS = 5;

    private Log() {
    }

    public static void v(String msg) {
        try {
            if (SHOW_LOGS) {
                android.util.Log.v(TAG, getDetailedLog(msg));
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    public static void i(String msg) {
        try {
            if (SHOW_LOGS) {
                android.util.Log.i(TAG, getDetailedLog(msg));
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    public static void d(String msg) {
        try {
            if (SHOW_LOGS) {
                android.util.Log.d(TAG, getDetailedLog(msg));
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    public static void w(String msg) {
        try {
            if (SHOW_LOGS) {
                android.util.Log.w(TAG, getDetailedLog(msg));
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    public static void e(String msg) {
        try {
            if (SHOW_LOGS) {
                android.util.Log.e(TAG, getDetailedLog(msg));
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    public static void w(String msg, Throwable t) {
        try {
            if (SHOW_LOGS) {
                android.util.Log.w(TAG, getDetailedLog(msg), t);
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    public static void d(String msg, Throwable t) {
        try {
            if (SHOW_LOGS) {
                android.util.Log.d(TAG, getDetailedLog(msg), t);
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    public static void e(String msg, Throwable t) {
        try {
            if (SHOW_LOGS) {
                android.util.Log.e(TAG, getDetailedLog(msg), t);
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    public static void wtf(String msg, Throwable t) {
        try {
            if (SHOW_LOGS) {
                android.util.Log.wtf(TAG, getDetailedLog(msg), t);
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    private static String getDetailedLog(String msg) {
        return getDetailedLog(msg, STACK_DEPTH);
    }

    private static String getDetailedLog(String msg, int depth) {
        if (!SHOW_LOGS) {
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
}
