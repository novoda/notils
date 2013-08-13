package com.novoda.notils.log;

import android.os.Build;

public class Log {

    private static Logger INSTANCE;

    static {
        enable();
    }

    private Log() {

    }

    public static void enable(String tag) {
        synchronized (Log.class) {
            switch (findPlatform()) {
                case ANDROID: INSTANCE = new AndroidLogger(tag); break;
                case JAVA: default: INSTANCE = new JavaLogger(); break;
            }
        }
    }

    public static void enable() {
        synchronized (Log.class) {
            switch (findPlatform()) {
                case ANDROID: INSTANCE = new AndroidLogger(); break;
                case JAVA: default: INSTANCE = new JavaLogger(); break;
            }
        }
    }

    public static void disable() {
        synchronized (Log.class) {
            INSTANCE = new InvalidLogger();
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

    public static void v(String msg) {
        INSTANCE.v(msg);
    }

    public static void v(String msg, Throwable t) {
        INSTANCE.v(msg, t);
    }

    public static void i(String msg) {
        INSTANCE.i(msg);
    }

    public static void i(String msg, Throwable t) {
        INSTANCE.i(msg, t);
    }

    public static void d(String msg) {
        INSTANCE.d(msg);
    }

    public static void d(String msg, Throwable t) {
        INSTANCE.d(msg, t);
    }

    public static void w(String msg) {
        INSTANCE.w(msg);
    }

    public static void w(String msg, Throwable t) {
        INSTANCE.w(msg, t);
    }

    public static void e(String msg) {
        INSTANCE.e(msg);
    }

    public static void e(String msg, Throwable t) {
        INSTANCE.e(msg, t);
    }

    public static void wtf(String msg) {
        INSTANCE.wtf(msg);
    }

    public static void wtf(String msg, Throwable t) {
        INSTANCE.wtf(msg, t);
    }
}
