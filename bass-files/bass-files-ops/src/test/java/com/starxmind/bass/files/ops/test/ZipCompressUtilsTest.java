package com.starxmind.bass.files.ops.test;

import com.starxmind.bass.files.ops.ZipCompressUtils;

import java.io.IOException;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
public class ZipCompressUtilsTest {
//    @Test
    public void compressFileTest() throws IOException {
        ZipCompressUtils.compressFile("E:\\self\\files\\abc.txt", "E:\\self\\files\\abc.zip");
    }
}
