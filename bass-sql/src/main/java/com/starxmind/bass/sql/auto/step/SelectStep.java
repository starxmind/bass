package com.starxmind.bass.sql.auto.step;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
public class SelectStep extends SQLStep {
    public SelectStep(String... fields) {
        if (ArrayUtils.isEmpty(fields)) {
            super.SQL = "select 0";
        } else {
            super.SQL = "select " + StringUtils.join(fields, ",");
        }
    }

    public FromStep from(String table) {
        return new FromStep(this, table);
    }
}
