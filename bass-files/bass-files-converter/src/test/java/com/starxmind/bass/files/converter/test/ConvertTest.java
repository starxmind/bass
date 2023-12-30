package com.starxmind.bass.files.converter.test;

import com.starxmind.bass.files.converter.Pdf2PngConverter;
import com.starxmind.bass.files.converter.Png2JpgConverter;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
public class ConvertTest {
    @Test
    public void testPng2JpgConverter() throws IOException {
        Png2JpgConverter png2JpgConverter = new Png2JpgConverter();
        png2JpgConverter.convertWithPixelControl(
                "source image path",
                "destination image path",
                801,
                396,
                0.9f
        );
    }

    @Test
    public void printDefaultPath() {
        System.out.println(new File("").getAbsolutePath());
    }

    //    @Test
    public void pdf2PngTest() throws IOException {
        Pdf2PngConverter pdf2PngConverter = new Pdf2PngConverter();
        pdf2PngConverter.convert("xxx.pdf", "");
    }
}
