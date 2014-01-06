package com.novoda.notils.logger;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoggerTest {

    private static final class ValidatingLogCommand implements LogCommand {
        public String lastMessage;

        @Override
        public void log(String message) {
            lastMessage = message;
        }
    }

    private final ValidatingLogCommand validatingLogCommand = new ValidatingLogCommand();

    @Test
    public void testDebugLogPrinting() {
        Logger logger = createLogger(LogLevel.DEBUG);
        String debug = "debug";
        logger.debug(debug);
        assertLastMessage(debug);
    }

    @Test
    public void testDisabledDebugLogPrinting() {
        Logger logger = createLogger(LogLevel.INFO);
        String debug = "debug";
        logger.debug(debug);
        assertLastMessageIsNull();
    }


    @Test
    public void testInfoLogPrinting() {
        Logger logger = createLogger(LogLevel.DEBUG);
        String info = "info";
        logger.info(info);
        assertLastMessage(info);
    }
    @Test
    public void testDisabledInfoLogPrinting() {
        Logger logger = createLogger(LogLevel.WARN);
        String info = "info";
        logger.info(info);
        assertLastMessageIsNull();
    }

    @Test
    public void testWarnLogPrinting() {
        Logger logger = createLogger(LogLevel.DEBUG);
        String warn = "warn";
        logger.warn(warn);
        assertLastMessage(warn);
    }

    @Test
    public void testDisabledWarnLogPrinting() {
        Logger logger = createLogger(LogLevel.ERROR);
        String warn = "warn";
        logger.warn(warn);
        assertLastMessageIsNull();
    }

    @Test
    public void testErrorLogPrinting() {
        Logger logger = createLogger(LogLevel.DEBUG);
        String error = "error";
        logger.error(error);
        assertLastMessage(error);
    }

    @Test
    public void testWtfLogPrinting() {
        Logger logger = createLogger(LogLevel.DEBUG);
        String wtf = "wtf";
        logger.wtf(wtf);
        assertLastMessage(wtf);
    }

    private Logger createLogger(LogLevel logLevel) {
        return new AbsLogger(logLevel) {
            @Override
            protected LogCommand getCommandForLevel(LogLevel level) {
                return validatingLogCommand;
            }
        };
    }

    private void assertLastMessage(String value) {
        assertTrue("unexpected log message", validatingLogCommand.lastMessage.contains(value));
    }

    private void assertLastMessageIsNull() {
        assertTrue("unexpected log message", validatingLogCommand.lastMessage == null);
    }
}
