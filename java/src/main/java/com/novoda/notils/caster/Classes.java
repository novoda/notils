package com.novoda.notils.caster;

import com.novoda.notils.exception.DeveloperError;

public final class Classes {

    private Classes() {
        // static helper class
    }

    @SuppressWarnings("unchecked")
    public static <T> T from(Object implementor) {
        try {
            return (T) implementor;
        } catch (ClassCastException e) {
            throw new DeveloperError(implementor.toString() + " does not inherit / implement the wanted interface", e);
        }
    }

}
