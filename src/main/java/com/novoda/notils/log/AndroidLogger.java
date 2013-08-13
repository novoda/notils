package com.novoda.notils.log;

 class AndroidLogger implements Logger {

    private final String tag;
    private final int stackDepth;
    private final int dotClass;

    AndroidLogger() {
        this("NoTils");
    }

    AndroidLogger(String tag) {
        this(tag, 5, 5);
    }

    AndroidLogger(String tag, int stackDepth, int dotClass) {
        this.tag = tag;
        this.stackDepth = stackDepth;
        this.dotClass = dotClass;
    }

    @Override
    public void v(String msg) {
        android.util.Log.v(tag, getDetailedLog(msg));
    }

    @Override
    public void v(String msg, Throwable t) {
        android.util.Log.v(tag, getDetailedLog(msg), t);
    }

    @Override
    public void i(String msg) {
        android.util.Log.i(tag, getDetailedLog(msg));
    }

    @Override
    public void i(String msg, Throwable t) {
        android.util.Log.i(tag, getDetailedLog(msg));
    }

    @Override
    public void d(String msg) {
        android.util.Log.d(tag, getDetailedLog(msg));
    }

    @Override
    public void d(String msg, Throwable t) {
        android.util.Log.d(tag, getDetailedLog(msg), t);
    }

    @Override
    public void w(String msg) {
        android.util.Log.w(tag, getDetailedLog(msg));
    }

    @Override
    public void w(String msg, Throwable t) {
        android.util.Log.w(tag, getDetailedLog(msg), t);
    }

    @Override
    public void e(String msg) {
        android.util.Log.e(tag, getDetailedLog(msg));
    }

    @Override
    public void e(String msg, Throwable t) {
        android.util.Log.e(tag, getDetailedLog(msg), t);
    }

    @Override
    public void wtf(String msg) {
        android.util.Log.wtf(tag, getDetailedLog(msg));
    }

    @Override
    public void wtf(String msg, Throwable t) {
        android.util.Log.wtf(tag, getDetailedLog(msg), t);
    }

    private String getDetailedLog(String msg) {
        return getDetailedLog(msg, stackDepth);
    }

    private String getDetailedLog(String msg, int depth) {
        Thread current = Thread.currentThread();
        final StackTraceElement trace = current.getStackTrace()[depth];
        final String filename = trace.getFileName();
        return "[" + current.getName() + "][" + filename.substring(0, filename.length() - dotClass) + "."
                + trace.getMethodName() + ":" + trace.getLineNumber() + "] " + msg;
    }
}