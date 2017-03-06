package com.novoda.notils.devicedetection;

import android.annotation.TargetApi;
import android.os.Build;

import java.util.Arrays;

class X86Detector implements Detector {

    private static final String X86 = "x86";

    private final String[] supportedAbis;

    public static Detector newInstance() {
        AndroidVersion androidVersion = AndroidVersion.newInstance();
        if (androidVersion.is21LollipopOrOver()) {
            return new X86Detector(getSupportedAbis());
        } else {
            return new X86Detector(getLegacySupportedAbis());
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static String[] getSupportedAbis() {
        return Build.SUPPORTED_ABIS;
    }

    private static String[] getLegacySupportedAbis() {
        return new String[]{Build.CPU_ABI, Build.CPU_ABI2};
    }

    X86Detector(String... supportedAbis) {
        this.supportedAbis = Arrays.copyOf(supportedAbis, supportedAbis.length);
    }

    @Override
    public boolean detected() {
        for (String abi : supportedAbis) {
            if (abi.contains(X86)) {
                return true;
            }
        }
        return false;
    }
}
