package com.novoda.notils.logger;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class AndroidLogger extends AbsLogger {

    private final Map<LogLevel, LogCommand> commands = new HashMap<LogLevel, LogCommand>();

    private final String tag;
    private final LogLevel enabledLevel;

    AndroidLogger(String tag) {
        this(tag, LogLevel.DEBUG);
    }

    AndroidLogger(String tag, LogLevel enabledLevel) {
        this.tag = tag;
        this.enabledLevel = enabledLevel;
        initCommands();
    }

    private void initCommands() {
        commands.put(LogLevel.DEBUG, d());
        commands.put(LogLevel.INFO, i());
        commands.put(LogLevel.ERROR, e());
        commands.put(LogLevel.VERBOSE, v());
        commands.put(LogLevel.WARN, w());
        commands.put(LogLevel.ASSERT, wtf());
    }

    private LogCommand d() {
        return new LogCommand() {
            @Override
            public void log(String message) {
                Log.d(tag, message);
            }
        };
    }

    private LogCommand i() {
        return new LogCommand() {
            @Override
            public void log(String message) {
                Log.i(tag, message);
            }
        };
    }

    private LogCommand e() {
        return new LogCommand() {
            @Override
            public void log(String message) {
                Log.e(tag, message);
            }
        };
    }

    private LogCommand v() {
        return new LogCommand() {
            @Override
            public void log(String message) {
                Log.v(tag, message);
            }
        };
    }

    private LogCommand w() {
        return new LogCommand() {
            @Override
            public void log(String message) {
                Log.w(tag, message);
            }
        };
    }

    private LogCommand wtf() {
        return new LogCommand() {
            @Override
            public void log(String message) {
                Log.wtf(tag, message);
            }
        };
    }

    @Override
    protected LogCommand getCommandForLevel(LogLevel level) {
        if (hasEqualsOrMorePriority(level)) {
            return commands.get(level);
        }
        return NO_OP;
    }

    private boolean hasEqualsOrMorePriority(LogLevel level) {
        return level.ordinal() >= enabledLevel.ordinal()
    }
}
