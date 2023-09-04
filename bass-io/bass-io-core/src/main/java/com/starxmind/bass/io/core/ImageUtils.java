package com.starxmind.bass.io.core;

import com.starxmind.bass.sugar.Sugar;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;

/**
 * 图像处理工具
 *
 * @author pizzalord
 * @since 1.0
 */
public abstract class ImageUtils {
    /**
     * 将图像读到字节数组
     *
     * @param image  图像
     * @param format 图像格式
     * @return base64
     * @throws IOException IO异常
     */
    public static String readImageAsBase64(Image image, String format) throws IOException {
        byte[] bytes = readImageAsBytes(image, format);
        return readBytesAsBase64(bytes, format);
    }

    /**
     * 将图像读到字节数组
     *
     * @param image  图像
     * @param format 图像格式
     * @return 字节数组
     * @throws IOException IO异常
     */
    public static byte[] readImageAsBytes(Image image, String format) throws IOException {
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            readImageAsStream(image, format, out);
            return out.toByteArray();
        } finally {
            Sugar.closeQuietly(out);
        }
    }

    /**
     * 将图像读到流中
     *
     * @param image  图像
     * @param format 图像格式
     * @param out    输出流
     * @throws IOException IO异常
     */
    public static void readImageAsStream(Image image, String format, OutputStream out) throws IOException {
        BufferedImage bufferedImage = toBufferedImage(image);
        ImageIO.write(bufferedImage, format, out);
    }

    /**
     * 将{@link Image}类型图像转成{@link BufferedImage}类型
     *
     * @param image 原始图像
     * @return 转换之后的图像
     */
    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }

        // 创建具有透明度的缓冲图像
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // 将图像绘制到缓冲图像上
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.drawImage(image, 0, 0, null);
        graphics2D.dispose();

        return bufferedImage;
    }

    /**
     * 字节数组转base64
     *
     * @param bytes  字节数组
     * @param format 图片格式
     * @return 图片base64形式
     */
    public static String readBytesAsBase64(byte[] bytes, String format) {
        return String.format("data:image/%s;base64,", format) +
                Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 输入流转base64
     *
     * @param in     输入流
     * @param format 图片格式
     * @return 图片base64形式
     */
    public static String readStreamAsBase64(InputStream in, String format) {
        try {
            byte[] bytes = IOUtils.readStreamAsByteArray(in);
            return readBytesAsBase64(bytes, format);
        } catch (Exception e) {
            throw new RuntimeException("读取文件转Base64失败", e);
        } finally {
            Sugar.closeQuietly(in);
        }
    }
}
