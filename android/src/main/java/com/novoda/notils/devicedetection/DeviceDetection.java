package com.novoda.notils.devicedetection;

import android.util.SparseArray;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

public class DeviceDetection {

    public static final int INTEL = 0;
    public static final int AMAZON = 1;

    @IntDef({INTEL, AMAZON})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DeviceType { }

    private final SparseArray<Detector> deviceDetector;

    public static DeviceDetection newInstance() {
        SparseArray<Detector> deviceDetector = new SparseArray<>(2);
        deviceDetector.put(INTEL, X86Detector.newInstance());
        deviceDetector.put(AMAZON, AmazonDetector.newInstance());

        return new DeviceDetection(deviceDetector);
    }

    DeviceDetection(SparseArray<Detector> deviceDetector) {
        this.deviceDetector = deviceDetector;
    }

    public boolean is(@DeviceType int deviceType) {
        Detector detector = deviceDetector.get(deviceType);
        return detector != null && detector.detected();
    }
}
