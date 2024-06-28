package com.starxmind.bass.security;

import org.apache.commons.codec.digest.HmacAlgorithms;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public final class HmacUtils {
    public static byte[] encrypt(String origin, String secret) {
        return encrypt(origin, secret, HmacAlgorithms.HMAC_SHA_1);
    }

    public static byte[] encrypt(String origin, String secret, HmacAlgorithms algorithm) {
        try {
            // 创建HMAC实例，并指定算法
            Mac mac = Mac.getInstance(algorithm.getName());
            // 创建密钥材料
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), algorithm.getName());
            // 初始化HMAC实例
            mac.init(secretKeySpec);
            // 计算HMAC值
            byte[] hmacBytes = mac.doFinal(origin.getBytes(StandardCharsets.UTF_8));
            return hmacBytes;
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("HMAC加密算法错误", e);
        }
    }

    public static String encryptAsHexString(String origin, String secret) {
        return encryptAsHexString(origin, secret, HmacAlgorithms.HMAC_SHA_1);
    }

    public static String encryptAsHexString(String origin, String secret, HmacAlgorithms algorithm) {
        byte[] hmacBytes = encrypt(origin, secret, algorithm);
        // 将HMAC值转为十六进制字符串
        StringBuilder hexString = new StringBuilder();
        for (byte b : hmacBytes) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        String hmac = hexString.toString();
        return hmac;
    }
}
