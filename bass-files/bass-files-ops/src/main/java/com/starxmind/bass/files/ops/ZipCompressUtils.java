package com.starxmind.bass.files.ops;

import com.starxmind.bass.io.core.IOUtils;
import com.starxmind.bass.sugar.Sugar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
public final class ZipCompressUtils {
    public static void compressFile(String sourceFilePath, String compressedFilePath) throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        ZipOutputStream zipOut = null;
        try {
            File sourceFile = new File(sourceFilePath);
            fis = new FileInputStream(sourceFile);
            fos = new FileOutputStream(compressedFilePath);
            zipOut = new ZipOutputStream(fos);
            zipOut.putNextEntry(new ZipEntry(sourceFile.getName()));
            IOUtils.copy(fis, zipOut);
        } finally {
            Sugar.closeQuietly(fis);
            Sugar.closeQuietly(fos);
            Sugar.closeQuietly(zipOut);
        }
    }
}
