package com.starxmind.bass.security;

import org.apache.commons.codec.binary.Base64;

import java.io.IOException;

/**
 * Base64加密解密
 *
 * @author pizzalord
 * @since 1.0
 */
public abstract class Base64Utils {
    /***
     * BASE64加密
     * @param origin 原始字符串
     * @return 加密后的字符串
     */
    public static String encrypt(byte[] origin) {
        return Base64.encodeBase64String(origin);
    }

    /***
     * BASE64解密
     * @param encrypted 已加密的字符串
     * @return 解密后的字符串
     */
    public static byte[] decrypt(String encrypted) {
        return Base64.decodeBase64(encrypted);
    }
}
