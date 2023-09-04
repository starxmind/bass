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
public class Variable {
    private String name;
    private String defaultValue;

    public Variable(String name) {
        this.name = name;
    }
}
