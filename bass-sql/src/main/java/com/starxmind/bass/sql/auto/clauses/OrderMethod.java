package com.starxmind.bass.sql.auto.clauses;

import lombok.Getter;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
@Getter
public enum OrderMethod {
    ASC("asc"),
    DESC("desc");

    private String code;

    OrderMethod(String code) {
        this.code = code;
    }
}
