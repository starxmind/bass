package com.starxmind.bass.security.test;

import com.starxmind.bass.security.Base64Utils;
import com.starxmind.bass.security.PasswordEncryption;
import org.junit.Test;

public class PasswordEncryptionTest {

    @Test
    public void randomSaltTest() {
        byte[] saltBytes = PasswordEncryption.generateSalt(); // 生成盐值
        String salt = Base64Utils.encrypt(saltBytes);
        System.out.println(salt);
    }

    @Test
    public void test() {
        String password = "abcd1234",// 用户输入的密码
                salt = "JM8VF4HNYcHNvyPCCI/fCw==";
        try {
            byte[] saltBytes = Base64Utils.decrypt(salt);
            String hashedPassword = PasswordEncryption.hashPassword(password, saltBytes); // 哈希加密密码
            System.out.println("Original Password: " + password);
            System.out.println("Hashed Password: " + hashedPassword.length()); // +AgQcXbJ58Cdm3PYAht3bk4ee16i0e/5sLt5yDNeF4c=
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
