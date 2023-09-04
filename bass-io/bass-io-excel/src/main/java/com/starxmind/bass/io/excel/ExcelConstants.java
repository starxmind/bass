package com.starxmind.bass.io.excel;

/**
 * Define constants for excel
 *
 * @author pizzalord
 * @since 1.0
 */

import com.starxmind.bass.sugar.Asserts;

/**
 * Define constants for excel
 */
public abstract class ExcelConstants {
    /**
     * Get number format for digits
     *
     * @param digits
     * @return
     */
    public static String numberFormatDigits(short digits) {
        Asserts.isTrue(digits > 0, "param: digits must be more than 0");
        String digitZeros = "";
        for (int i = 0; i < digits; i++) {
            digitZeros += "0";
        }
        return String.format("#,##0.%s;-#,##0.%s", digitZeros, digitZeros);
    }
}
