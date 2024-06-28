package com.starxmind.bass.security;

import javax.crypto.Cipher;
import java.security.*;

/**
 * RSA加密解密
 *
 * @author pizzalord
 * @since 1.0
 */
public final class RSAUtils {
    public static void genPairKeys() throws Exception {
        // 生成RSA密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom();
        keyPairGenerator.initialize(2048, secureRandom);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        System.out.println("公钥：" + publicKey);
        System.out.println("私钥：" + privateKey);

        // 明文
        String plainText = "Hello, RSA!";

        // 加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

        // 解密
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        // 解密后的明文
        String decryptedText = new String(decryptedBytes);
        // 输出结果
        System.out.println("明文: " + plainText);
        System.out.println("加密后: " + new String(encryptedBytes));
        System.out.println("解密后: " + decryptedText);
    }
}
