package com.novoda.notils.disk;

import android.os.Environment;
import android.os.StatFs;

public enum DiskLocation {
    INTERNAL {
        @Override
        StatFs getStats() {
            return new StatFs(Environment.getRootDirectory().getAbsolutePath());
        }
    },
    EXTERNAL {
        @Override
        StatFs getStats() {
            return new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        }
    },
    CUSTOM {
        @Override
        StatFs getStats() {
            return new StatFs(this.customLocation);
        }
    };

    String customLocation;

    public void setCustomLocation(String customLocation) {
        this.customLocation = customLocation;
    }

    abstract StatFs getStats();

}
