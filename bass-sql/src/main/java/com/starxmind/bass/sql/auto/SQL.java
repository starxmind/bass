package com.starxmind.bass.sql.auto;


import com.starxmind.bass.sql.auto.field.Field;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
public class SQL {
    public static SQLStream stream() {
        return new SQLStream();
    }

    public static Field field(String code) {
        return new Field(code);
    }
}
