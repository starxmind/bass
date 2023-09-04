package com.starxmind.bass.files.converter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
public class Png2JpgConverter implements FilesConverter {
    @Override
    public void convert(String srcPath, String destPath) throws IOException {
        convert(new File(srcPath), new File(destPath));
    }

    @Override
    public void convert(File scrFile, File destFile) throws IOException {
        convert(new FileInputStream(scrFile), new FileOutputStream(destFile));
    }

    @Override
    public void convert(InputStream in, OutputStream out) throws IOException {
        BufferedImage image = ImageIO.read(in);

        // Create a blank JPEG image with the same dimensions as the original PNG
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        // Draw the original PNG onto the new JPEG image
        newImage.createGraphics().drawImage(image, 0, 0, null);

        // Write the new JPEG image to the output file with the specified quality
        ImageIO.write(newImage, "jpg", out);
    }

    @Override
    public void convertWithPixelControl(String srcPath, String destPath, int desiredWidth, int desiredHeight, float quality) throws IOException {
        convertWithPixelControl(new File(srcPath), new File(destPath), desiredWidth, desiredHeight, quality);
    }

    @Override
    public void convertWithPixelControl(File scrFile, File destFile, int desiredWidth, int desiredHeight, float quality) throws IOException {
        convertWithPixelControl(new FileInputStream(scrFile), new FileOutputStream(destFile), desiredWidth, desiredHeight, quality);
    }

    @Override
    public void convertWithPixelControl(InputStream in, OutputStream out, int desiredWidth, int desiredHeight, float quality) throws IOException {
        BufferedImage originalImage = ImageIO.read(in);

        // Scale the image to the desired width and height
        Image scaledImage = originalImage.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
        BufferedImage newImage = new BufferedImage(desiredWidth, desiredHeight, BufferedImage.TYPE_INT_RGB);

        // Draw the scaled image onto the new image
        Graphics2D graphics = newImage.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        graphics.dispose();

        // Write the new image as JPEG with specified quality
        ImageIO.write(newImage, "jpg", out);
    }
}
