package com.novoda.notils.logger.simple;

/**
 * Wrapper around Android Log that will print
 * <p/>
 * 'TAG [Thread Name][Class name][Method name:line number] message'
 */
public final class Log {

    /**
     * <b>Deprecated</b>: Use getter <code>shouldShowLogs()</code> and setter <code>setShowLogs(bool)</code> instead<br/>
     * <br/>
     * Flag to enable or disable logs<br/>
     * Recommended you set this to <code>BuildConfig.DEBUG</code> in your class that extends Application
     */
    @Deprecated
    public static boolean SHOW_LOGS;

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
            if (SHOW_LOGS) {
                android.util.Log.v(TAG, getDetailedLog(formatString(msg)));
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    public static void i(Object... msg) {
        try {
            if (SHOW_LOGS) {
                android.util.Log.i(TAG, getDetailedLog(formatString(msg)));
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    public static void d(Object... msg) {
        try {
            if (SHOW_LOGS) {
                android.util.Log.d(TAG, getDetailedLog(formatString(msg)));
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    public static void w(Object... msg) {
        try {
            if (SHOW_LOGS) {
                android.util.Log.w(TAG, getDetailedLog(formatString(msg)));
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    public static void e(Object... msg) {
        try {
            if (SHOW_LOGS) {
                android.util.Log.e(TAG, getDetailedLog(formatString(msg)));
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    @Deprecated
    /**
     * @deprecated use (Throwable t, Object... msg) 
     */
    public static void w(String msg, Throwable t) {
        w(t, msg);
    }

    public static void w(Throwable t, Object... msg) {
        try {
            if (SHOW_LOGS) {
                android.util.Log.w(TAG, getDetailedLog(formatString(msg)), t);
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    @Deprecated
    /**
     * @deprecated use (Throwable t, Object... msg)
     */
    public static void d(String msg, Throwable t) {
        d(t, msg);
    }

    public static void d(Throwable t, Object... msg) {
        try {
            if (SHOW_LOGS) {
                android.util.Log.d(TAG, getDetailedLog(formatString(msg)), t);
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    @Deprecated
    /**
     * @deprecated use (Throwable t, Object... msg) 
     */
    public static void e(String msg, Throwable t) {
        e(t, msg);
    }

    public static void e(Throwable t, Object... msg) {
        try {
            if (SHOW_LOGS) {
                android.util.Log.e(TAG, getDetailedLog(formatString(msg)), t);
            }
        } catch (RuntimeException ignore) {
            logError(ignore);
        }
    }

    @Deprecated
    /**
     * @deprecated use (Throwable t, Object... msg)
     */
    public static void wtf(String msg, Throwable t) {
        wtf(t, msg);
    }

    public static void wtf(Throwable t, Object... msg) {
        try {
            if (SHOW_LOGS) {
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

    private static String formatString(Object... msg) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object o : msg) {
            stringBuilder.append(String.valueOf(o)).append(separator);
        }
        return stringBuilder.toString();
    }

    /**
     * Toggle if logs should be output or not. Recommended to bind against BuildConfig.DEBUG
     *
     * @param showLogs <code>true</code> for showing logs, <code>false</code> to deactivate
     */
    public static void setShowLogs(boolean showLogs) {
        Log.SHOW_LOGS = showLogs;
    }

    /**
     * Returns the active status of the log switch
     * @return <code>true</code> if logs are active, <code>false</code> if deactivated
     */
    public static boolean shouldShowLogs() {
        return SHOW_LOGS;
    }
}
