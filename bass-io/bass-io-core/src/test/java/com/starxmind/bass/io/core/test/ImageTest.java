package com.starxmind.bass.io.core.test;

import com.starxmind.bass.io.core.ImageUtils;
import org.junit.Test;

import java.io.IOException;

public class ImageTest {

    @Test
    public void compressTest2() throws IOException {
        String inputPath = "D:\\outputs\\images\\news.jpg";
        String outputPath = "D:\\outputs\\images\\news_compressed.jpg";
        ImageUtils.resizeAndCompress(inputPath, outputPath, "jpg", 0.8f, 300, 0);
    }
}
