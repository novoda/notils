package com.novoda.notils.devicedetection;

import android.os.Build;

public class AndroidVersion {

    private final int androidVersion;

    public static AndroidVersion newInstance() {
        return new AndroidVersion(Build.VERSION.SDK_INT);
    }

    AndroidVersion(int androidVersion) {
        this.androidVersion = androidVersion;
    }

    public boolean is18JellyBeanOrOver() {
        return androidVersion >= Build.VERSION_CODES.JELLY_BEAN_MR2;
    }

    public boolean is19KitKatOrOver() {
        return androidVersion >= Build.VERSION_CODES.KITKAT;
    }

    public boolean is21LollipopOrOver() {
        return androidVersion >= Build.VERSION_CODES.LOLLIPOP;
    }

    public boolean isPre21Lollipop() {
        return !is21LollipopOrOver();
    }

    public boolean is23MarshmallowOrOver() {
        return androidVersion >= Build.VERSION_CODES.M;
    }

    public int getVersion() {
        return androidVersion;
    }
}
