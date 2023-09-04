package com.starxmind.bass.files.ops;

import com.starxmind.bass.io.core.IOUtils;

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
public abstract class ZipCompressUtils {
    public static void compressFile(String sourceFilePath, String compressedFilePath) throws IOException {
        File sourceFile = new File(sourceFilePath);
        FileInputStream fis = new FileInputStream(sourceFile);
        FileOutputStream fos = new FileOutputStream(compressedFilePath);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        zipOut.putNextEntry(new ZipEntry(sourceFile.getName()));
        IOUtils.copy(fis, zipOut);
        fis.close();
        fos.close();
        zipOut.close();
    }
}
