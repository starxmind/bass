package com.starxmind.bass.http;

/**
 * Http exception
 *
 * @author pizzalord
 * @since 1.0
 */
public class StarxHttpException extends RuntimeException {
    private static final long serialVersionUID = 6404003864947145502L;

    public StarxHttpException() {
    }

    public StarxHttpException(String message) {
        super(message);
    }

    public StarxHttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public StarxHttpException(Throwable cause) {
        super(cause);
    }

    public StarxHttpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
