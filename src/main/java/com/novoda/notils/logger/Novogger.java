package com.novoda.notils.logger;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;

public class Novogger {

    private static final String DEFAULT_TAG = "Novogger";

    private static Logger INSTANCE;

    static {
        enable();
    }

    private Novogger() {

    }

    public static void enable() {
        enable(DEFAULT_TAG);
    }

    public static void enable(Context context) {
        enable(appNameFromContext(context));
    }

    private static String appNameFromContext(Context context) {
        final PackageManager pm = context.getApplicationContext().getPackageManager();
        ApplicationInfo ai;
        try {
            ai = pm.getApplicationInfo(context.getPackageName(), 0);
        } catch (final PackageManager.NameNotFoundException e) {
            ai = null;
        }
        return (String) (ai != null ? pm.getApplicationLabel(ai) : DEFAULT_TAG);
    }

    public static void enable(String tag) {
        synchronized (Novogger.class) {
            switch (findPlatform()) {
                case ANDROID:
                    INSTANCE = new AndroidLogger(tag);
                    break;
                case JAVA:
                default:
                    INSTANCE = new JavaLogger();
                    break;
            }
        }
    }

    public static void disable() {
        synchronized (Novogger.class) {
            INSTANCE = new SilentLogger();
        }
    }

    private static Platform findPlatform() {
        try {
            Class.forName("android.os.Build");
            if (Build.VERSION.SDK_INT != 0) {
                return Platform.ANDROID;
            }
        } catch (ClassNotFoundException ignored) {
        }
        return Platform.JAVA;
    }

    public static void d(String msg) {
        INSTANCE.debug(msg);
    }

    public static void d(String msg, Throwable t) {
        INSTANCE.debug(msg, t);
    }

    public static void e(String msg) {
        INSTANCE.error(msg);
    }

    public static void e(String msg, Throwable t) {
        INSTANCE.error(msg, t);
    }

    public static void i(String msg) {
        INSTANCE.info(msg);
    }

    public static void i(String msg, Throwable t) {
        INSTANCE.info(msg, t);
    }

    public static void v(String msg) {
        INSTANCE.verbose(msg);
    }

    public static void v(String msg, Throwable t) {
        INSTANCE.verbose(msg, t);
    }

    public static void w(String msg) {
        INSTANCE.warn(msg);
    }

    public static void w(String msg, Throwable t) {
        INSTANCE.warn(msg, t);
    }

    public static void wtf(String msg) {
        INSTANCE.wtf(msg);
    }

    public static void wtf(Throwable t) {
        INSTANCE.wtf(t);
    }

    public static void wtf(String msg, Throwable t) {
        INSTANCE.wtf(msg, t);
    }
}
