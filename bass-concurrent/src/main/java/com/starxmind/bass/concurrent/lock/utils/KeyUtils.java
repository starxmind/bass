package com.starxmind.bass.concurrent.lock.utils;

/**
 * Redis key
 *
 * @author pizzalord
 * @since 1.0
 */
public final class KeyUtils {
    public static String lockKey(String key) {
        return "lock:" + key;
    }
}
