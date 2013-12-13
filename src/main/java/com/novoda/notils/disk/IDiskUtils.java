package com.novoda.notils.disk;

interface IDiskUtils {
    long totalSpace(DiskLocation location);
    long freeSpace(DiskLocation location);
    long busySpace(DiskLocation location);
}
