package com.starxmind.bass.files.converter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
public interface FilesConverter {
    void convert(String srcPath, String destPath) throws IOException;

    void convert(File scrFile, File destFile) throws IOException;

    void convert(InputStream in, OutputStream out) throws IOException;

    void convertWithPixelControl(String srcPath, String destPath, int desiredWidth, int desiredHeight, float quality) throws IOException;

    void convertWithPixelControl(File scrFile, File destFile, int desiredWidth, int desiredHeight, float quality) throws IOException;

    void convertWithPixelControl(InputStream in, OutputStream out, int desiredWidth, int desiredHeight, float quality) throws IOException;
}
