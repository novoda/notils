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

    /**
     * Default separator for the concatenation of the vaargs
     */
    private static final String DEFAULT_SEPARATOR = " ";

    private Log() {
    }

    public static void v(Object... msg) {
        v(DEFAULT_SEPARATOR, msg);
    }

    public static void v(String separator, Object... msg) {
        try {
            if (SHOW_LOGS) {
                android.util.Log.v(TAG, getDetailedLog(formatString(separator, msg)));
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    public static void i(Object... msg) {
        i(DEFAULT_SEPARATOR, msg);
    }

    public static void i(String separator, Object... msg) {
        try {
            if (SHOW_LOGS) {
                android.util.Log.i(TAG, getDetailedLog(formatString(separator, msg)));
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    public static void d(Object... msg) {
        d(DEFAULT_SEPARATOR, msg);
    }

    public static void d(String separator, Object... msg) {
        try {
            if (SHOW_LOGS) {
                android.util.Log.d(TAG, getDetailedLog(formatString(separator, msg)));
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    public static void w(Object... msg) {
        w(DEFAULT_SEPARATOR, msg);
    }

    public static void w(String separator, Object... msg) {
        try {
            if (SHOW_LOGS) {
                android.util.Log.w(TAG, getDetailedLog(formatString(separator, msg)));
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    public static void e(Object... msg) {
        e(DEFAULT_SEPARATOR, msg);
    }

    public static void e(String separator, Object... msg) {
        try {
            if (SHOW_LOGS) {
                android.util.Log.e(TAG, getDetailedLog(formatString(separator, msg)));
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    public static void w(String msg, Throwable t) {
        w(t,msg);
    }

    public static void w(Throwable t, Object... msg) {
        w(t, DEFAULT_SEPARATOR, msg);
    }

    public static void w(Throwable t, String separator, Object... msg) {
        try {
            if (SHOW_LOGS) {
                android.util.Log.w(TAG, getDetailedLog(formatString(separator, msg)), t);
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    public static void d(String msg, Throwable t) {
        d(t, msg);
    }

    public static void d(Throwable t, Object... msg) {
        d(t, DEFAULT_SEPARATOR, msg);
    }

    public static void d(Throwable t, String separator, Object... msg) {
        try {
            if (SHOW_LOGS) {
                android.util.Log.d(TAG, getDetailedLog(formatString(separator, msg)), t);
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    public static void e(String msg, Throwable t) {
        e(t, msg);
    }
    public static void e(Throwable t, Object... msg) {
        e(t, DEFAULT_SEPARATOR, msg);
    }

    public static void e(Throwable t, String separator, Object... msg) {
        try {
            if (SHOW_LOGS) {
                android.util.Log.e(TAG, getDetailedLog(formatString(separator, msg)), t);
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    public static void wtf(String msg, Throwable t) {
        wtf(t, msg);
    }
    public static void wtf(Throwable t, Object... msg) {
        wtf(t, DEFAULT_SEPARATOR, msg);
    }

    public static void wtf(Throwable t, String separator, Object... msg) {
        try {
            if (SHOW_LOGS) {
                android.util.Log.wtf(TAG, getDetailedLog(formatString(separator, msg)), t);
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

    private static String formatString(String sep, Object... msg) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object o : msg) {
            stringBuilder.append(String.valueOf(o)).append(sep);
        }
        return stringBuilder.toString();
    }
}
