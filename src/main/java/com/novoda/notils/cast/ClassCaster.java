package com.novoda.notils.cast;

import android.app.Activity;

public final class ClassCaster {

    private ClassCaster() {
        throw new IllegalAccessError("This class should never be instantiated");
    }

    @SuppressWarnings("unchecked")
    public static <T> T toListener(Activity activity) {
        try {
            return (T) activity;
        } catch (ClassCastException e) {
            throw new RuntimeException("The parent " + activity.toString() + " does not implement the wanted interface", e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T from(Object implementor) {
        try {
            return (T) implementor;
        } catch (ClassCastException e) {
            throw new RuntimeException("The parent " + implementor.toString() + " does not inherit / implement the wanted interface", e);
        }
    }

}