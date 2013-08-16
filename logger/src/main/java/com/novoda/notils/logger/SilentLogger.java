package com.novoda.notils.logger;

class SilentLogger extends AbsLogger {

    @Override
    protected LogCommand getCommandForLevel(LogLevel level) {
        return NO_OP;
    }
}
