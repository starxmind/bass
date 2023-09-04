package com.starxmind.bass.json.exceptions;

/**
 * 序列化异常
 */
public class SerializeException extends RuntimeException {

    private static final long serialVersionUID = 7882918268170154878L;

    public SerializeException() {
    }

    public SerializeException(String message) {
        super(message);
    }

    public SerializeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SerializeException(Throwable cause) {
        super(cause);
    }

    public SerializeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
