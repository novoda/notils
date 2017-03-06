package com.novoda.notils.devicedetection;

public class AmazonDetector {

    //From: https://developer.amazon.com/appsandservices/solutions/devices/kindle-fire/specifications/01-device-and-feature-specifications
    private static final String AMAZON = "Amazon";

    private final String buildManufacturer;

    public static AmazonDetector newInstance() {
        String buildManufacturer = Build.MANUFACTURER;
        return new AmazonDetector(buildManufacturer);
    }

    AmazonDetector(String buildManufacturer) {
        this.buildManufacturer = buildManufacturer;
    }

    public boolean isAmazonDevice() {
        return AMAZON.equalsIgnoreCase(buildManufacturer);
    }
}
