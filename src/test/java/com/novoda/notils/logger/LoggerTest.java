package com.novoda.notils.logger;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoggerTest {

    private final ValidatingLogCommand validatingLogCommand = new ValidatingLogCommand();

    @Test
    public void testDebugLogPrinting() {
        Logger logger = createLogger(LogLevel.DEBUG);
        String message = "debug";
        logger.debug(message);
        assertLastLogMessageEquals(message);
    }

    @Test
    public void testDisabledDebugLogPrinting() {
        Logger logger = createLogger(LogLevel.INFO);
        String message = "debug";
        logger.debug(message);
        assertNothingIsLogged();
    }

    @Test
    public void testInfoLogPrinting() {
        Logger logger = createLogger(LogLevel.DEBUG);
        String message = "info";
        logger.info(message);
        assertLastLogMessageEquals(message);
    }

    @Test
    public void testDisabledInfoLogPrinting() {
        Logger logger = createLogger(LogLevel.WARN);
        String message = "info";
        logger.info(message);
        assertNothingIsLogged();
    }

    @Test
    public void testWarnLogPrinting() {
        Logger logger = createLogger(LogLevel.DEBUG);
        String message = "warn";
        logger.warn(message);
        assertLastLogMessageEquals(message);
    }

    @Test
    public void testDisabledWarnLogPrinting() {
        Logger logger = createLogger(LogLevel.ERROR);
        String message = "warn";
        logger.warn(message);
        assertNothingIsLogged();
    }

    @Test
    public void testErrorLogPrinting() {
        Logger logger = createLogger(LogLevel.DEBUG);
        String message = "error";
        logger.error(message);
        assertLastLogMessageEquals(message);
    }

    @Test
    public void testWtfLogPrinting() {
        Logger logger = createLogger(LogLevel.DEBUG);
        String message = "wtf";
        logger.wtf(message);
        assertLastLogMessageEquals(message);
    }

    private Logger createLogger(LogLevel logLevel) {
        return new AbsLogger(logLevel) {
            @Override
            protected LogCommand getCommandForLevel(LogLevel level) {
                return validatingLogCommand;
            }
        };
    }

    private void assertLastLogMessageEquals(String value) {
        assertTrue("unexpected log message", validatingLogCommand.lastMessage.contains(value));
    }

    private void assertNothingIsLogged() {
        assertTrue("unexpected log message", validatingLogCommand.lastMessage == null);
    }

    private static final class ValidatingLogCommand implements LogCommand {
        public String lastMessage;

        @Override
        public void log(String message) {
            lastMessage = message;
        }
    }
}
