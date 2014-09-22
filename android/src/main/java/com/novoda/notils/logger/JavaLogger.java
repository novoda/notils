package com.novoda.notils.logger;

public class JavaLogger extends AbsLogger {

    static final LogCommand PRINT = new LogCommand() {
        @Override
        public void log(String message) {
            System.out.println(message);
        }
    };

    JavaLogger() {
        this(LogLevel.DEBUG);
    }

    JavaLogger(LogLevel enabledLevel) {
        super(enabledLevel);
    }

    @Override
    protected LogCommand getCommandForLevel(LogLevel level) {
        return PRINT;
    }
}
