package com.starxmind.bass.io.core;

import java.io.*;

import static com.starxmind.bass.sugar.Sugar.closeQuietly;

/**
 * IO工具
 *
 * @author pizzalord
 * @since 1.0
 */
public final class IOUtils {
    /**
     * 1KB
     */
    public static final int ONE_KB = 1024;

    /**
     * 1MB
     */
    public static final int ONE_MB = ONE_KB * ONE_KB;

    /**
     * 1GB
     */
    public static final int ONE_GB = ONE_KB * ONE_MB;

    /**
     * Line break for windows
     */
    public static final String LINE_BREAK_WIN = "\r\n";

    /**
     * Line break for unix
     */
    public static final String LINE_BREAK_UNIX = "\n";

    /**
     * Line break for mac
     */
    public static final String LINE_BREAK_MAC = "\r";

    /**
     * End of file
     */
    public static final int EOF = -1;

    /**
     * 将输入流读成字节数组
     *
     * @param in 输入流
     * @return 字节数组
     * @throws IOException IO异常
     */
    public static byte[] readStreamAsByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        copy(in, out);
        return out.toByteArray();
    }

    /**
     * 将输入流读成文本
     *
     * @param in      输入流
     * @param charset 字符集
     * @return 文本
     * @throws IOException IO异常
     */
    public static String readStreamAsString(InputStream in, String charset) throws IOException {
        Reader reader = null;
        Writer writer = new StringWriter();
        String result;

        char[] buffer = new char[ONE_KB];
        try {
            int n;
            reader = new BufferedReader(new InputStreamReader(in, charset));
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
            result = writer.toString();
        } finally {
            closeQuietly(in);
            closeQuietly(reader);
            closeQuietly(writer);
        }
        return result;
    }

    /**
     * 将输入流读成文本,默认字符集为UTF-8
     *
     * @param in 输入流
     * @return 文本
     * @throws IOException IO异常
     */
    public static String readStreamAsString(InputStream in) throws IOException {
        return readStreamAsString(in, "UTF-8");
    }

    /**
     * 复制流
     *
     * @param in  输入流
     * @param out 输出流
     * @return 以缓冲区读取流的次数
     * @throws IOException IO异常
     */
    public static int copy(InputStream in, OutputStream out) throws IOException {
        long count = copyLarge(in, out);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }

    /**
     * 复制流
     *
     * @param in  输入流
     * @param out 输出流
     * @return 以缓冲区读取流的次数
     * @throws IOException IO异常
     */
    public static long copyLarge(InputStream in, OutputStream out)
            throws IOException {
        return copyLarge(in, out, new byte[ONE_KB * 4]);
    }

    /**
     * 复制流
     *
     * @param in     输入流
     * @param out    输出流
     * @param buffer 缓冲区
     * @return 以缓存区读取流的次数
     * @throws IOException IO异常
     */
    public static long copyLarge(InputStream in, OutputStream out, byte[] buffer)
            throws IOException {
        long count = 0;
        int n;
        while (EOF != (n = in.read(buffer))) {
            out.write(buffer, 0, n);
            count += n;
        }
        closeQuietly(in);
        closeQuietly(out);
        return count;
    }
}
