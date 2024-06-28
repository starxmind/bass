package com.starxmind.bass.security;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;

/**
 * MD5 encryption algorithm utils
 *
 * @author Xpizza
 * @since shire1.0
 */
public final class MD5Utils {
    /**
     * Hexadecimal numbers
     */
    private static final String[] HEX_DIGITS =
            {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * Transfer byte array to a string with hex numbers
     *
     * @param bytes Byte array
     * @return
     */
    private static String byteArrayToHexString(byte[] bytes) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            resultSb.append(byteToHexString(bytes[i]));
        }
        return resultSb.toString();
    }

    /**
     * Transfer a byte to hex string
     *
     * @param b byte
     * @return
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return HEX_DIGITS[d1] + HEX_DIGITS[d2];
    }

    /**
     * Encode a string by MD5 algorithm with a charset
     *
     * @param originString The origin sting
     * @return MD5 encoded result
     */
    public static String encode(String originString) {
        return encode(originString, null);
    }

    /**
     * Encode a string by MD5 algorithm with a charset
     *
     * @param originString The origin sting
     * @param charsetName  Charset
     * @return MD5 encoded result
     */
    public static String encode(String originString, String charsetName) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (StringUtils.isEmpty(charsetName)) {
                return byteArrayToHexString(md.digest(originString.getBytes()));
            }
            return byteArrayToHexString(md.digest(originString.getBytes(charsetName)));
        } catch (Throwable throwable) {
            throw new RuntimeException("Fatal: an error occurred when invoke MD5 algorithm", throwable);
        }
    }
}
