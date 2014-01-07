package com.novoda.notils.logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

abstract class AbsLogger implements Logger {

    static final LogCommand NO_OP = new LogCommand() {
        @Override
        public void log(String message) {
        }
    };

    private static final int DEPTH = 5;
    private static final int CLASS_SUFFIX = 5;
    private final LogLevel minimumLogLevel;

    public AbsLogger(LogLevel minimumLogLevel) {
        this.minimumLogLevel = minimumLogLevel;
    }

    protected void log(String msg, Throwable tr, LogLevel level) {
        if (!level.isEnabledAt(minimumLogLevel)) {
            return;
        }
        String message = getDetailedLog(msg);
        if (tr != null) {
            message += "\n" + getStackTraceString(tr);
        }
        getCommandForLevel(level).log(message);
    }

    protected String getDetailedLog(String msg) {
        Thread current = Thread.currentThread();
        final StackTraceElement trace = current.getStackTrace()[DEPTH];
        final String filename = trace.getFileName();
        return "[" + current.getName() + "][" + filename.substring(0, filename.length() - CLASS_SUFFIX) + "."
                + trace.getMethodName() + ":" + trace.getLineNumber() + "] " + msg;
    }

    protected String getStackTraceString(Throwable tr) {

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            tr.printStackTrace(pw);
            return sw.toString().trim();
        } finally {
            try {
                pw.close();
                sw.close();
            } catch (IOException e) {
            }
        }
    }

    protected abstract LogCommand getCommandForLevel(LogLevel level);

    @Override
    public void debug(String msg) {
        log(msg, null, LogLevel.DEBUG);
    }

    @Override
    public void debug(String msg, Throwable tr) {
        log(msg, tr, LogLevel.DEBUG);

    }

    @Override
    public void error(String msg) {
        log(msg, null, LogLevel.ERROR);
    }

    @Override
    public void error(String msg, Throwable tr) {
        log(msg, tr, LogLevel.ERROR);
    }

    @Override
    public void info(String msg) {
        log(msg, null, LogLevel.INFO);
    }

    @Override
    public void info(String msg, Throwable tr) {
        log(msg, tr, LogLevel.INFO);
    }

    @Override
    public void verbose(String msg) {
        log(msg, null, LogLevel.VERBOSE);
    }

    @Override
    public void verbose(String msg, Throwable tr) {
        log(msg, tr, LogLevel.VERBOSE);
    }

    @Override
    public void warn(String msg) {
        log(msg, null, LogLevel.WARN);
    }

    @Override
    public void warn(String msg, Throwable tr) {
        log(msg, tr, LogLevel.WARN);
    }

    @Override
    public void wtf(String msg) {
        log(msg, null, LogLevel.ASSERT);
    }

    @Override
    public void wtf(Throwable tr) {
        log("", tr, LogLevel.ASSERT);
    }

    @Override
    public void wtf(String msg, Throwable tr) {
        log(msg, tr, LogLevel.ASSERT);
    }
}
