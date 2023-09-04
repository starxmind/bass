package com.starxmind.bass.io.excel;

/**
 * Excel版本
 *
 * @author pizzalord
 * @since 1.0
 */
public enum ExcelVersion {
    V_2003("xls"),
    V_2007_ABOVE("xlsx");

    private String extension;

    ExcelVersion(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
