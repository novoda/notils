package com.novoda.notils.devicedetection;

import android.os.Build;

class AmazonDetector implements Detector {

    //From: https://developer.amazon.com/appsandservices/solutions/devices/kindle-fire/specifications/01-device-and-feature-specifications
    private static final String AMAZON = "Amazon";

    private final String buildManufacturer;

    public static Detector newInstance() {
        String manufacturer = Build.MANUFACTURER;
        return new AmazonDetector(manufacturer);
    }

    AmazonDetector(String buildManufacturer) {
        this.buildManufacturer = buildManufacturer;
    }

    @Override
    public boolean detected() {
        return AMAZON.equalsIgnoreCase(buildManufacturer);
    }
}
