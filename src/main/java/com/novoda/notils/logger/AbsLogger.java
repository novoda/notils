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

    protected void log(String message, Throwable throwable, LogLevel level) {
        if (!level.isEnabledAt(minimumLogLevel)) {
            return;
        }
        String detailedMessage = getDetailedLog(message);
        if (throwable != null) {
            detailedMessage += "\n" + getStackTraceString(throwable);
        }
        getCommandForLevel(level).log(detailedMessage);
    }

    protected String getDetailedLog(String message) {
        Thread current = Thread.currentThread();
        final StackTraceElement trace = current.getStackTrace()[DEPTH];
        final String filename = trace.getFileName();
        return "[" + current.getName() + "][" + filename.substring(0, filename.length() - CLASS_SUFFIX) + "."
                + trace.getMethodName() + ":" + trace.getLineNumber() + "] " + message;
    }

    protected String getStackTraceString(Throwable throwable) {

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            throwable.printStackTrace(pw);
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
    public void debug(String msg, Throwable throwable) {
        log(msg, throwable, LogLevel.DEBUG);

    }

    @Override
    public void error(String msg) {
        log(msg, null, LogLevel.ERROR);
    }

    @Override
    public void error(String msg, Throwable throwable) {
        log(msg, throwable, LogLevel.ERROR);
    }

    @Override
    public void info(String msg) {
        log(msg, null, LogLevel.INFO);
    }

    @Override
    public void info(String msg, Throwable throwable) {
        log(msg, throwable, LogLevel.INFO);
    }

    @Override
    public void verbose(String msg) {
        log(msg, null, LogLevel.VERBOSE);
    }

    @Override
    public void verbose(String msg, Throwable throwable) {
        log(msg, throwable, LogLevel.VERBOSE);
    }

    @Override
    public void warn(String msg) {
        log(msg, null, LogLevel.WARN);
    }

    @Override
    public void warn(String msg, Throwable throwable) {
        log(msg, throwable, LogLevel.WARN);
    }

    @Override
    public void wtf(String msg) {
        log(msg, null, LogLevel.ASSERT);
    }

    @Override
    public void wtf(Throwable throwable) {
        log("", throwable, LogLevel.ASSERT);
    }

    @Override
    public void wtf(String msg, Throwable throwable) {
        log(msg, throwable, LogLevel.ASSERT);
    }
}
