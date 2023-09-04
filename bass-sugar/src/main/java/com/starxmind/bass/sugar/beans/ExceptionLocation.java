package com.starxmind.bass.sugar.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
public class ExceptionLocation {
    private String className;
    private String methodName;
    private int lineNumber;
}
