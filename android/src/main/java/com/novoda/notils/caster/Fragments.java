package com.novoda.notils.caster;

import androidx.fragment.app.FragmentManager;

public final class Fragments {

    private Fragments() {
    }

    /**
     * Simpler version of {@link FragmentManager#findFragmentById(int)}} which infers the target type.
     */
    @SuppressWarnings("unchecked")
    public static <T> T findFragmentById(FragmentManager fragmentManager, int fragmentId) {
        return (T) fragmentManager.findFragmentById(fragmentId);
    }

    @SuppressWarnings("unchecked")
    public static <T> T findFragmentByTag(FragmentManager fragmentManager, String fragmentTag) {
        return (T) fragmentManager.findFragmentByTag(fragmentTag);
    }

    @SuppressWarnings("unchecked")
    public static <T> T findFragmentById(android.app.FragmentManager fragmentManager, int fragmentId) {
        return (T) fragmentManager.findFragmentById(fragmentId);
    }

    @SuppressWarnings("unchecked")
    public static <T> T findFragmentByTag(android.app.FragmentManager fragmentManager, String fragmentTag) {
        return (T) fragmentManager.findFragmentByTag(fragmentTag);
    }

}
