package com.novoda.notils.logger;

public interface Logger {

    void debug(String msg);
    void debug(String msg, Throwable tr);
    void error(String msg);
    void error(String msg, Throwable tr);
    void info(String msg);
    void info(String msg, Throwable tr);
    void verbose(String msg);
    void verbose(String msg, Throwable tr);
    void warn(String msg);
    void warn(String msg, Throwable tr);
    void wtf(String msg);
    void wtf(Throwable tr);
    void wtf(String msg, Throwable tr);
}
