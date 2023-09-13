package com.starxmind.bass.files.converter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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
public class Pdf2PngConverter implements FilesConverter {
    @Override
    public void convert(String srcPath, String destPath) throws IOException {
        try {
            PDDocument document = PDDocument.load(new File(srcPath));
            PDFRenderer pdfRenderer = new PDFRenderer(document);

            // 创建输出文件夹
            File outputFolder = new File(destPath);
            outputFolder.mkdirs();

            for (int page = 0; page < document.getNumberOfPages(); ++page) {
                BufferedImage image = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);

                // 指定图像文件名
                String imageFileName = destPath + "/page_" + (page + 1) + ".png";

                // 保存图像
                ImageIO.write(image, "PNG", new File(imageFileName));
            }

            document.close();
            System.out.println("PDF转换完成。");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void convert(File scrFile, File destFile) throws IOException {

    }

    @Override
    public void convert(InputStream in, OutputStream out) throws IOException {

    }

    @Override
    public void convertWithPixelControl(String srcPath, String destPath, int desiredWidth, int desiredHeight, float quality) throws IOException {

    }

    @Override
    public void convertWithPixelControl(File scrFile, File destFile, int desiredWidth, int desiredHeight, float quality) throws IOException {

    }

    @Override
    public void convertWithPixelControl(InputStream in, OutputStream out, int desiredWidth, int desiredHeight, float quality) throws IOException {

    }
}
