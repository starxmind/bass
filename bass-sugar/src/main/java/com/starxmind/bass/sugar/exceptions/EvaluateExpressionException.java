package com.starxmind.bass.sugar.exceptions;

/**
 * 表达式求值异常
 *
 * @author pizzalord
 * @since 1.0
 */
public class EvaluateExpressionException extends RuntimeException{
    private static final long serialVersionUID = -5193128393079736934L;

    public EvaluateExpressionException() {
    }

    public EvaluateExpressionException(String message) {
        super(message);
    }

    public EvaluateExpressionException(String message, Throwable cause) {
        super(message, cause);
    }

    public EvaluateExpressionException(Throwable cause) {
        super(cause);
    }

    public EvaluateExpressionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
