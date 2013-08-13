package com.novoda.notils.log;

public interface Logger {

    void v(String msg);

    void v(String msg, Throwable t);

    void i(String msg);

    void i(String msg, Throwable t);

    void d(String msg);

    void d(String msg, Throwable t);

    void w(String msg);

    void w(String msg, Throwable t);

    void e(String msg);

    void e(String msg, Throwable t);

    void wtf(String msg);

    void wtf(String msg, Throwable t);
}
