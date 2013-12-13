package com.novoda.notils.disk;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.StatFs;

public class DiskUtils implements IDiskUtils {

    private static final long MEGA_BYTE = 1048576;
    private final IDiskUtils instance;

    public static DiskUtils newInstance() {
        return new DiskUtils(getInstance());
    }

    private DiskUtils(IDiskUtils diskUtils) {
        this.instance = diskUtils;
    }

    private static IDiskUtils getInstance() {
        return isAtLeastJellyBeanMR2() ? new JellyBeanMR2DiskUtils() : new DeprecatedDiskUtils();
    }

    private static boolean isAtLeastJellyBeanMR2() {
        return getApiLevel() >= Build.VERSION_CODES.JELLY_BEAN_MR2;
    }

    private static int getApiLevel() {
        return android.os.Build.VERSION.SDK_INT;
    }

    @Override
    public long totalSpace(DiskLocation location) {
        return instance.totalSpace(location);
    }

    @Override
    public long freeSpace(DiskLocation location) {
        return instance.freeSpace(location);
    }

    @Override
    public long busySpace(DiskLocation location) {
        return instance.busySpace(location);
    }

    private static class DeprecatedDiskUtils implements IDiskUtils {

        @Override
        public long totalSpace(DiskLocation location) {
            return toMb(getTotalBytes(location.getStats()));
        }

        @Override
        public long freeSpace(DiskLocation location) {
            return toMb(getFreeBytes(location.getStats()));
        }

        @Override
        public long busySpace(DiskLocation location) {
            StatFs statFs = location.getStats();
            return toMb((getTotalBytes(statFs) - getFreeBytes(statFs)));
        }

        private long getTotalBytes(StatFs statFs) {
            return statFs.getBlockCount() * (statFs.getBlockSize());
        }

        private long getFreeBytes(StatFs statFs) {
            return statFs.getAvailableBlocks() * statFs.getBlockSize();
        }

        private long toMb(long from) {
            return from / MEGA_BYTE;
        }

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    private static class JellyBeanMR2DiskUtils implements IDiskUtils {

        @Override
        public long totalSpace(DiskLocation location) {
            return toMb(getTotalBytes(location.getStats()));
        }

        @Override
        public long freeSpace(DiskLocation location) {
            return toMb(getFreeBytes(location.getStats()));
        }

        @Override
        public long busySpace(DiskLocation location) {
            StatFs statFs = location.getStats();
            return toMb((getTotalBytes(statFs) - getFreeBytes(statFs)));
        }

        private long getTotalBytes(StatFs statFs) {
            return statFs.getBlockCountLong() * (statFs.getBlockSizeLong());
        }

        private long getFreeBytes(StatFs statFs) {
            return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
        }

        private long toMb(long from) {
            return from / MEGA_BYTE;
        }
    }

}