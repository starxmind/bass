package com.starxmind.bass.sugar;

import com.starxmind.bass.sugar.beans.ExceptionLocation;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
public final class ExceptionUtils {
    /**
     * Get track for errors
     *
     * @param throwable Throwable error
     * @return
     */
    public static String track(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }

    /**
     * Get exception location info
     *
     * @param throwable Exceptions
     * @return Custom exception location
     */
    public static ExceptionLocation getCustomExceptionLocation(Throwable throwable) {
        String callerPackageName = StackTraceUtils.getCallerPackageName(3).getPackage().getName();
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (stackTraceElement.getClassName().startsWith(callerPackageName)) {
                return ExceptionLocation.builder()
                        .className(stackTraceElement.getClassName())
                        .methodName(stackTraceElement.getMethodName())
                        .lineNumber(stackTraceElement.getLineNumber())
                        .build();
            }
        }
        return null;
    }
}
