package com.starxmind.bass.json.exceptions;

/**
 * 反序列化异常
 */
public class DeserializeException extends RuntimeException {

    private static final long serialVersionUID = 6648124997696257937L;

    public DeserializeException() {
    }

    public DeserializeException(String message) {
        super(message);
    }

    public DeserializeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeserializeException(Throwable cause) {
        super(cause);
    }

    public DeserializeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
