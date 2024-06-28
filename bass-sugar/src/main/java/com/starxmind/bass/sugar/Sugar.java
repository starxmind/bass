package com.starxmind.bass.sugar;

/**
 * 其他语法糖
 *
 * @author Xpizza
 * @since 1.0
 */
public final class Sugar {
    /**
     * Close resources quietly
     *
     * @param autoCloseable Closeable resources
     */
    public static void closeQuietly(AutoCloseable autoCloseable) {
        if (autoCloseable == null) {
            return;
        }
        try {
            autoCloseable.close();
        } catch (Exception ignored) {
        }
    }
}
