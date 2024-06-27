package com.starxmind.bass.random;

import java.util.Random;

/**
 * Number random utils
 *
 * @author pizzalord
 * @since 1.0
 */
public abstract class RandomNumberUtils {
    public static final Random RANDOM = new Random();

    /**
     * 按长度随机生成整数
     *
     * @param length 整数长度
     * @return 定长的随机整数
     */
    public static long gen(int length) {
        return (long) ((Math.random() * 9 + 1) * Math.pow(10, length - 1));
    }
}
