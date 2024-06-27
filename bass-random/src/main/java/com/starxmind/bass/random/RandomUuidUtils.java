package com.starxmind.bass.random;

import java.util.UUID;

/**
 * UUID random utils
 *
 * @author pizzalord
 * @since 1.0
 */
public abstract class RandomUuidUtils {
    /**
     * Generate a random UUID
     *
     * @return
     */
    public static String gen() {
        return UUID.randomUUID().toString();
    }

    /**
     * Generate a random UUID
     *
     * @return
     */
    public static String gen32() {
        return gen().replace("-", "");
    }

    /**
     * Generate a 32 length and uppercase UUID
     *
     * @return
     */
    public static String gen32UpperCase() {
        return gen32().toUpperCase();
    }

    /**
     * Generate a 32 length and lowercase UUID
     *
     * @return
     */
    public static String gen32Lowercase() {
        return gen32().toLowerCase();
    }
}
