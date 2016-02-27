package com.novoda.notils.java.caster;

public final class Classes {

    private Classes() {
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