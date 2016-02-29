package com.novoda.notils.logger;

class SilentLogger extends AbsLogger {

    public SilentLogger() {
        super(LogLevel.ERROR);
    }

    @Override
    protected LogCommand getCommandForLevel(LogLevel level) {
        return NO_OP;
    }
}
