package com.novoda.notils.logger;

public enum LogLevel {
    VERBOSE,
    DEBUG,
    INFO,
    WARN,
    ERROR,
    ASSERT;

    public boolean isEnabledAt(LogLevel other) {
        return ordinal() >= other.ordinal();
    }
}
