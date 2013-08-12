package com.novoda.notils.android;

public final class Log {

    private static final int STACK_DEPTH = 5;
    private static final int DOT_CLASS = 5;
    private static final String HTTPTAG = "noTilsHttp";

    private static boolean SHOW_LOGS = false;
    private static String TAG = "NoTilsLog";

    private Log() {
    }

    public static void enable(String tag) {
        SHOW_LOGS = true;
        TAG = tag;
    }

    public static void disable() {
        SHOW_LOGS = false;
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

    public static boolean isHttpLogEnabled() {
        return android.util.Log.isLoggable(HTTPTAG, android.util.Log.DEBUG);
    }
}