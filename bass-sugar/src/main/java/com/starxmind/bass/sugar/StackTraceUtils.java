package com.starxmind.bass.sugar;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
public final class StackTraceUtils {
    /**
     * 获取调用方的类
     *
     * @param callerOffset 调用方离近栈的偏移量
     * @return
     */
    public static Class<?> getCallerPackageName(int callerOffset) {
        try {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            StackTraceElement caller = stackTrace[callerOffset];
            String callingClassName = caller.getClassName();
            Class<?> clazz = Class.forName(callingClassName);
            return clazz;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
