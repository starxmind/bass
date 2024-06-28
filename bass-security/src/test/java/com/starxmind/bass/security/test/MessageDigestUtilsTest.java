package com.starxmind.bass.security.test;

import com.starxmind.bass.security.Base64Utils;
import com.starxmind.bass.security.MessageDigestUtils;
import org.junit.Test;

public class MessageDigestUtilsTest {

    @Test
    public void randomSaltTest() {
        byte[] saltBytes = MessageDigestUtils.generateSalt(); // 生成盐值
        String salt = Base64Utils.encrypt(saltBytes);
        System.out.println(salt);
    }

    @Test
    public void test() {
        String password = "abcd1234",// 用户输入的密码
                salt = "JM8VF4HNYcHNvyPCCI/fCw==";
        try {
            byte[] saltBytes = Base64Utils.decrypt(salt);
            String hashedPassword = MessageDigestUtils.makeHash(password, saltBytes); // 哈希加密密码
            System.out.println("Original Password: " + password);
            System.out.println("Hashed Password: " + hashedPassword); // +AgQcXbJ58Cdm3PYAht3bk4ee16i0e/5sLt5yDNeF4c=
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
