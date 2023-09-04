package com.starxmind.bass.sugar;

import java.io.InputStream;
import java.net.URL;

/**
 * 类加载器工具类
 *
 * @author pizzalord
 * @since 1.0
 */
public abstract class ClassLoaderUtils {
    /**
     * 切换当前线程的ClassLoader
     */
    public static void switchCurrentClassLoader(Class<?> clazz) {
        Thread.currentThread().setContextClassLoader(clazz.getClassLoader());
    }

    /**
     * 获取资源的URL
     *
     * @param clazz
     * @param fileName 文件名
     * @return
     */
    public static URL getResourceUrl(Class<?> clazz, String fileName) {
        return clazz.getClassLoader().getResource(fileName);
    }

    /**
     * 获取资源的输入流
     *
     * @param fileName
     * @return
     */
    public static InputStream getResourceStream(Class<?> clazz, String fileName) {
        return clazz.getClassLoader().getResourceAsStream(fileName);
    }

    /**
     * 判断资源存在
     *
     * @param clazz
     * @param fileName
     * @return
     */
    public static boolean resourceExists(Class<?> clazz, String fileName) {
        return getResourceUrl(clazz, fileName) != null;
    }

    /**
     * 判断资源不存在
     *
     * @param clazz
     * @param fileName
     * @return
     */
    public static boolean resourceNotExists(Class<?> clazz, String fileName) {
        return !resourceExists(clazz, fileName);
    }
}
