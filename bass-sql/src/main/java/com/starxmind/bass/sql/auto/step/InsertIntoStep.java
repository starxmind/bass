package com.starxmind.bass.sql.auto.step;

import java.util.List;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
public class InsertIntoStep extends SQLStep {
    public InsertIntoStep(String table) {
        super.SQL = "insert into " + table;
    }

    public InsertColumnsStep columns(List<String> columns) {
        return new InsertColumnsStep(this, columns);
    }
}
