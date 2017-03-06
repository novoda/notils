package com.novoda.notils.devicedetection;

import java.util.Arrays;

class X86Detector {

    private static final String X86 = "x86";

    private final String[] supportedAbis;

    X86Detector(String... supportedAbis) {
        this.supportedAbis = Arrays.copyOf(supportedAbis, supportedAbis.length);
    }

    public boolean isX86() {
        for (String abi : supportedAbis) {
            if (abi.contains(X86)) {
                return true;
            }
        }
        return false;
    }
}
