package com.starxmind.bass.random;

/**
 * String random utils
 *
 * @author pizzalord
 * @since 1.0
 */
public abstract class StringRandomUtils {
    /**
     * Generates a lowercase string by the input length
     *
     * @param length The length of the result string
     * @return
     */
    public static String genLowercase(int length) {
        String val = "";
        for (int i = 0; i < length; i++) {
            String str = NumberRandomUtils.RANDOM.nextInt(2) % 2 == 0 ? "num" : "char";
            if ("char".equalsIgnoreCase(str)) {
                // Generate a letter [65, 97]
                val += (char) (65 + NumberRandomUtils.RANDOM.nextInt(26));
            } else if ("num".equalsIgnoreCase(str)) {
                // Generate a number
                val += NumberRandomUtils.RANDOM.nextInt(10);
            }
        }
        return val;
    }

    /**
     * Generates a uppercase string by the input length
     *
     * @param length The length of the result string
     * @return
     */
    public static String genUppercase(int length) {
        return genLowercase(length).toUpperCase();
    }

    /**
     * 生成纯定长的数字
     *
     * @param length
     * @return
     */
    public static String genPureNumbers(int length) {
        String retStr = "";
        for (int i = 0; i < length; i++) {
            retStr += NumberRandomUtils.RANDOM.nextInt(10);
        }
        return retStr;
    }
}
