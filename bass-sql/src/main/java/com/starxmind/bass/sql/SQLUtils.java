package com.starxmind.bass.sql;

import org.apache.commons.lang3.StringUtils;

/**
 * SQL Utils
 *
 * @author pizzalord
 * @since 1.0
 */
public abstract class SQLUtils {
    /**
     * Get fuzzy words
     * Returns null if origin words is null
     *
     * @param words origin words
     * @return Fuzzy words
     */
    public static String like(String words) {
        return StringUtils.isBlank(words) ? null : ("%" + words + "%");
    }

    /**
     * Get query offset by page params
     *
     * @param pageNum  page number
     * @param pageSize page size
     * @return Query offset
     */
    public static int getOffset(int pageNum, int pageSize) {
        return (pageNum - 1) * pageSize;
    }

    /**
     * Get result's pages by total count and page size
     *
     * @param total    total count
     * @param pageSize page size
     * @return result's pages
     */
    public static int getPages(long total, int pageSize) {
        if (pageSize == 0) {
            throw new RuntimeException("pageSize cannot be zero!");
        }
        long pages = total / pageSize;
        if (total % pageSize != 0) {
            pages++;
        }
        return (int) pages;
    }
}
