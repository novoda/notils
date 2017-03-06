package com.novoda.notils.devicedetection;

import android.annotation.TargetApi;
import android.os.Build;

public class DeviceDetection {

    private final X86Detector x86Detector;
    private final AmazonDetector amazonDetector;

    public static DeviceDetection newInstance() {
        X86Detector x86Detector = getX86Detector();
        AmazonDetector amazonDetector = getAmazonDetector();

        return new DeviceDetection(x86Detector, amazonDetector);
    }

    private static X86Detector getX86Detector() {
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

    private static AmazonDetector getAmazonDetector() {
        String buildManufacturer = Build.MANUFACTURER;
        return new AmazonDetector(buildManufacturer);
    }

    DeviceDetection(X86Detector x86Detector, AmazonDetector amazonDetector) {
        this.x86Detector = x86Detector;
        this.amazonDetector = amazonDetector;
    }

    public boolean isX86() {
        return x86Detector.isX86();
    }

    public boolean isAmazon() {
        return amazonDetector.isAmazonDevice();
    }
}
