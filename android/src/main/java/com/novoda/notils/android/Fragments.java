package com.novoda.notils.android;

import android.support.v4.app.FragmentManager;

public final class Fragments {

    private Fragments() {
        throw new IllegalAccessError("This class should never be instantiated");
    }

    /**
     * Simpler version of {@link android.support.v4.app.FragmentManager#findFragmentById(int)}} which infers the target type.
     */
    @SuppressWarnings({"unchecked", "UnusedDeclaration"})
    public static <T> T findFragmentById(FragmentManager fragmentManager, int fragmentId) {
        return (T) fragmentManager.findFragmentById(fragmentId);
    }

    public static <T> T findFragmentByTag(FragmentManager fragmentManager, String fragmentTag) {
        return (T) fragmentManager.findFragmentByTag(fragmentTag);
    }

}