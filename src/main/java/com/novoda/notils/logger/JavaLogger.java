package com.novoda.notils.logger;

public class JavaLogger extends AbsLogger {

    static final LogCommand PRINT = new LogCommand() {
        @Override
        public void log(String message) {
            System.out.println(message);
        }
    };

    private final LogLevel enabledLevel;

    JavaLogger() {
        this(LogLevel.DEBUG);
    }

    JavaLogger(LogLevel enabledLevel) {
        this.enabledLevel = enabledLevel;
    }

    @Override
    protected LogCommand getCommandForLevel(LogLevel level) {
        if (level.isEnabledAt(enabledLevel)) {
            return PRINT;
        }
        return NO_OP;
    }
}
