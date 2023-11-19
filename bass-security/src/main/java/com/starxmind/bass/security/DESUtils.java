package com.starxmind.bass.security;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static com.starxmind.bass.security.SecurityConstants.SECURE_RANDOM;

/**
 * 标准数据加密
 *
 * @author pizzalord
 * @since 1.0
 */
public abstract class DESUtils {
    private static final String DES = "DES";
    /**
     * 密钥工厂
     */
    private static SecretKeyFactory KEY_FACTORY;

    private static Cipher CIPHER;

    static {
        try {
            KEY_FACTORY = SecretKeyFactory.getInstance(DES);
            CIPHER = Cipher.getInstance(DES);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用key加密
     *
     * @param origin 待加密数据
     * @param secret 密钥
     * @return 加密后的字符串
     */
    public static String encrypt(String origin, String secret) {
        byte[] bytes = encrypt(origin.getBytes(), secret.getBytes());
        return Base64Utils.encrypt(bytes);
    }

    /**
     * 使用 默认key 解密
     *
     * @param encrypted 加密数据
     * @param secret    密钥
     * @return 解密后的字符串
     */
    public static String decrypt(String encrypted, String secret) {
        if (encrypted == null) {
            return null;
        }
        try {
            byte[] buffer = Base64Utils.decrypt(encrypted);
            byte[] bytes = decrypt(buffer, secret.getBytes());
            return new String(bytes);
        } catch (Exception e) {
            throw new RuntimeException("解密失败...");
        }
    }

    /**
     * Description 根据键值进行加密
     *
     * @param data
     * @param key  加密键byte数组
     * @return 加密后的字符串
     */
    private static byte[] encrypt(byte[] data, byte[] key) {
        try {
            // 从原始密钥数据创建DESKeySpec对象
            DESKeySpec dks = new DESKeySpec(key);

            // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
            SecretKey secretkey = KEY_FACTORY.generateSecret(dks);

            // 用密钥初始化Cipher对象
            CIPHER.init(Cipher.ENCRYPT_MODE, secretkey, SECURE_RANDOM);

            return CIPHER.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("加密失败");
        }
    }

    /**
     * Description 根据键值进行解密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKey secretkey = KEY_FACTORY.generateSecret(dks);

        // 用密钥初始化Cipher对象
        CIPHER.init(Cipher.DECRYPT_MODE, secretkey, SECURE_RANDOM);

        return CIPHER.doFinal(data);
    }
}
