package com.novoda.notils.android.caster;

public final class Fragments {

    private Fragments() {
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