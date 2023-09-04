package com.starxmind.bass.io.excel;

import com.starxmind.bass.io.core.FileUtils;
import com.starxmind.bass.sugar.Sugar;
import lombok.Getter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Excel写入器
 *
 * @author pizzalord
 * @since 1.0
 */
public class ExcelWriter implements AutoCloseable {
    /**
     * core object for excel
     */
    @Getter
    private Workbook workbook;

    /**
     * current sheet
     */
    @Getter
    private Sheet sheet;

    /**
     * current row
     */
    @Getter
    private Row currentRow;

    /**
     * row index
     */
    private AtomicInteger rowIndex = new AtomicInteger();

    /**
     * get current row index
     *
     * @return
     */
    public int currentRowIndex() {
        return this.rowIndex.get();
    }

    /**
     * construct a excel writer by default rowAccessWindowSize
     */
    public ExcelWriter() {
        this.workbook = new SXSSFWorkbook();
    }

    /**
     * construct a excel writer by the window for row access
     *
     * @param rowAccessWindowSize the window for access
     */
    public ExcelWriter(int rowAccessWindowSize) {
        this.workbook = new SXSSFWorkbook(rowAccessWindowSize);
    }

    /**
     * construct a excel writer by version
     *
     * @param excelVersion excel version
     */
    public ExcelWriter(ExcelVersion excelVersion) {
        switch (excelVersion) {
            case V_2003:
                this.workbook = new HSSFWorkbook();
                break;
            case V_2007_ABOVE:
                // streaming workbook
                this.workbook = new SXSSFWorkbook();
                break;
            default:
                throw new IllegalArgumentException("Unsupported excel version");
        }
    }

    /**
     * construct a excel writer by loading exists workbook
     *
     * @param workbook
     */
    public ExcelWriter(Workbook workbook) {
        this.workbook = workbook;
    }

    /**
     * create a new sheet with the default name
     */
    public void createSheet() {
        this.sheet = this.workbook.createSheet();
        this.rowIndex.set(0);
    }

    /**
     * create a new sheet with the name
     *
     * @param sheetName sheet name
     */
    public void createSheet(String sheetName) {
        this.sheet = this.workbook.createSheet(sheetName);
        this.rowIndex.set(0);
    }

    /**
     * open an exists sheet
     *
     * @param sheetName sheet name
     */
    public void openSheet(String sheetName) {
        this.sheet = this.workbook.getSheet(sheetName);
        this.rowIndex.set(this.sheet.getLastRowNum() + 1);
    }

    /**
     * 写头部,不带样式
     *
     * @param heads
     * @throws IOException
     */
    @Deprecated
    public void writeHeads(String[] heads) throws IOException {
        writeHeads(heads, null);
    }

    /**
     * 写头部,带样式
     *
     * @param heads
     * @param cellStyle
     * @throws IOException
     */
    public void writeHeads(String[] heads, CellStyle cellStyle) throws IOException {
        createRow();
        for (int columnIndex = 0; columnIndex < heads.length; columnIndex++) {
            createCell(this.currentRow, columnIndex, heads[columnIndex], null, cellStyle);
        }
    }

    /**
     * 创建一行
     */
    public void createRow() {
        this.currentRow = this.sheet.createRow(this.rowIndex.getAndIncrement());
    }

    /**
     * 创建默认单元格样式
     *
     * @return
     */
    public CellStyle defaultCellStyle() {
        return this.workbook.createCellStyle();
    }

    /**
     * 创建number单元格样式
     *
     * @param numberFormat 数字格式化
     * @return
     */
    public CellStyle createNumberCellStyle(String numberFormat) {
        CellStyle style = this.workbook.createCellStyle();
        DataFormat format = this.workbook.createDataFormat();
        style.setDataFormat(format.getFormat(numberFormat));
        return style;
    }

    /**
     * Write cell
     *
     * @param columnIndex column index
     * @param value       value
     * @param format      data format
     * @param cellStyle   Excel cell style
     */
    public void writeCell(int columnIndex, Object value, Format format, CellStyle cellStyle) {
        String resultValue = format != null ?
                format.format(value) :
                Objects.toString(value);
        createCell(this.currentRow, columnIndex, resultValue, format, cellStyle);
    }

    /**
     * 刷到文件输出流
     *
     * @param filePath file path
     * @throws IOException IO exception
     */
    public void flush(String filePath) throws IOException {
        FileOutputStream out = FileUtils.getFileOutputStream(filePath);
        this.workbook.write(out);
    }

    /**
     * close excel writer
     */
    @Override
    public void close() {
        Sugar.closeQuietly(this.workbook);
        this.sheet = null;
        this.currentRow = null;
    }

    /**
     * @param row       excel row
     * @param columnNo  column number
     * @param value     cell value
     * @param format    data format
     * @param cellStyle cell style
     */
    private void createCell(Row row,
                            int columnNo,
                            String value,
                            Format format,
                            CellStyle cellStyle) {
        Cell cell = row.createCell(columnNo);
        if (cellStyle != null) {
            cell.setCellStyle(cellStyle);
        }
        if (format instanceof DecimalFormat) {
            cell.setCellValue(Double.parseDouble(value));
        } else {
            cell.setCellValue(new XSSFRichTextString(value));
        }
    }
}
