package com.novoda.notils.devicedetection;

public class DeviceDetection {

    private final X86Detector x86Detector;
    private final AmazonDetector amazonDetector;

    public DeviceDetection(X86Detector x86Detector, AmazonDetector amazonDetector) {
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
