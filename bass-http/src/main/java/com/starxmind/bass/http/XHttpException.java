package com.starxmind.bass.http;

/**
 * Http exception
 *
 * @author pizzalord
 * @since 1.0
 */
public class XHttpException extends RuntimeException {
    private static final long serialVersionUID = 6404003864947145502L;

    public XHttpException() {
    }

    public XHttpException(String message) {
        super(message);
    }

    public XHttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public XHttpException(Throwable cause) {
        super(cause);
    }

    public XHttpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
