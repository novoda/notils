package com.novoda.notils.devicedetection;

import android.os.Build;

public class AndroidVersion {

    private final int osApiLevel;
    private final String osVersionName;

    public static AndroidVersion newInstance() {
        return new AndroidVersion(Build.VERSION.SDK_INT, Build.VERSION.RELEASE);
    }

    AndroidVersion(int osApiLevel, String osVersionName) {
        this.osApiLevel = osApiLevel;
        this.osVersionName = osVersionName;
    }

    public boolean is16JellyBean() {
        return osApiLevel == Build.VERSION_CODES.JELLY_BEAN;
    }

    public boolean is16JellyBeanOrOver() {
        return osApiLevel >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public boolean is18JellyBeanOrOver() {
        return osApiLevel >= Build.VERSION_CODES.JELLY_BEAN_MR2;
    }

    public boolean is19KitKat() {
        return osApiLevel == Build.VERSION_CODES.KITKAT;
    }

    public boolean is19KitKatOrOver() {
        return osApiLevel >= Build.VERSION_CODES.KITKAT;
    }

    public boolean is21LollipopOrOver() {
        return osApiLevel >= Build.VERSION_CODES.LOLLIPOP;
    }

    public boolean isPre21Lollipop() {
        return !is21LollipopOrOver();
    }

    public boolean is23MarshmallowOrOver() {
        return osApiLevel >= Build.VERSION_CODES.M;
    }

    public boolean is24NougatOrOver() {
        return osApiLevel >= Build.VERSION_CODES.N;
    }

    public boolean is26OreoOrOver() {
        return osApiLevel >= Build.VERSION_CODES.O;
    }

    public int apiLevel() {
        return osApiLevel;
    }

    public String versionName() {
        return osVersionName;
    }
}
