package com.starxmind.bass.io.excel;

import com.starxmind.bass.sugar.Sugar;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Excel reader
 *
 * @author pizzalord
 * @since 1.0
 */
public class ExcelReader implements AutoCloseable {
    /**
     * core object for excel
     */
    @Getter
    private Workbook workbook;

    /**
     * read excel by path and version
     *
     * @param filePath     file path
     * @param excelVersion excel version
     */
    public void read(String filePath, ExcelVersion excelVersion) {
        InputStream in = null;
        try {
            // close the previous
            close();
            // read a new file
            in = new FileInputStream(filePath);
            // init a workbook
            switch (excelVersion) {
                case V_2003:
                    this.workbook = new HSSFWorkbook(in);
                    break;
                case V_2007_ABOVE:
                    this.workbook = new XSSFWorkbook(in);
                    break;
                default:
                    throw new IllegalArgumentException("param: unsupported excel version");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("fatal: occur an error when reading the excel");
        } finally {
            Sugar.closeQuietly(in);
        }
    }

    /**
     * read cell value as string
     *
     * @param cell Excel cell
     * @return Cell value as string
     */
    public String getCellValue(Cell cell) {
        return getCellValue(cell, true);
    }

    /**
     * read cell value
     *
     * @param cell       Excel cell
     * @param treatAsStr Treat cell as a string or not
     * @return Cell value
     */
    public String getCellValue(Cell cell, boolean treatAsStr) {
        if (cell == null) {
            return "";
        }
        if (treatAsStr) { // Process it into a string
            cell.setCellType(CellType.STRING);
        }
        switch (cell.getCellType()) {
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            default:
                return String.valueOf(cell.getStringCellValue());
        }
    }

    /**
     * Determined excel cell is a date or not
     *
     * @param cell Excel cell
     * @return boolean result
     */
    public boolean isDate(Cell cell) {
        String dataFormatString = cell.getCellStyle().getDataFormatString();
        if (StringUtils.isEmpty(dataFormatString)) {
            return false;
        }
        return dataFormatString.contains("yyyy/mm;@") ||
                dataFormatString.contains("m/d/yy") ||
                dataFormatString.contains("yy/m/d") ||
                dataFormatString.contains("mm/dd/yy") ||
                dataFormatString.contains("dd-mmm-yy") ||
                dataFormatString.contains("yyyy/m/d");
    }

    /**
     * close reader
     *
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        Sugar.closeQuietly(workbook);
    }
}
