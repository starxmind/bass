package com.starxmind.bass.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static com.starxmind.bass.security.SecurityConstants.SECURE_RANDOM;

public final class MessageDigestUtils {

    private static final String ALGORITHM = "SHA-256";

    /**
     * 生成随机的盐值
     *
     * @return salt
     */
    public static byte[] generateSalt() {
        byte[] salt = new byte[16];
        SECURE_RANDOM.nextBytes(salt);
        return salt;
    }

    /**
     * 使用明文数据加盐生成哈希值,用于数据完整性验证、数字签名、密码存储和文件校验等场景,这个过程是不可逆的
     *
     * @param plainText 明文
     * @param salt      盐值
     * @return 哈希后的数据
     * @throws NoSuchAlgorithmException ex
     */
    public static String makeHash(String plainText, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
        messageDigest.update(salt);
        byte[] hashedPassword = messageDigest.digest(plainText.getBytes());
        return Base64.getEncoder().encodeToString(hashedPassword);
    }
}
