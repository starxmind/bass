package com.starxmind.bass.sugar.test;

import org.junit.Test;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
public class LangTest {
    @Test
    public void indexOfTest() {
        String s = "xxx.yyyy.zzz";
        String substring = s.substring(0, s.indexOf("."));
        System.out.println(substring);
    }
}
