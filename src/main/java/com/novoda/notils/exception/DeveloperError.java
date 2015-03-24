package com.novoda.notils.exception;

/**
 * Use for fast feedback when developing, can be used in places where you expect
 * the code never to be executed & if it is then you need the app to crash so you can instantly
 * get feedback on the situation.
 * <b>You should never try catch a DeveloperError</b>
 */
public class DeveloperError extends Error {

    public DeveloperError(String detailMessage) {
        super(detailMessage);
    }

    public DeveloperError(String messageTemplate, Object... args) {
        super(String.format(messageTemplate, args));
    }

    public DeveloperError(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

}
