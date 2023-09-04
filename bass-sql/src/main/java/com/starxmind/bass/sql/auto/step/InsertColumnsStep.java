package com.starxmind.bass.sql.auto.step;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
public class InsertColumnsStep extends SQLStep {
    @Getter
    private List<String> columns;

    public InsertColumnsStep(InsertIntoStep insertIntoStep, List<String> columns) {
        this.columns = columns;
        super.SQL = insertIntoStep.getSQL() + "(" + StringUtils.join(columns, ",") + ")";
    }

    public InsertValuesStep valuesOfPlaceholder() {
        return new InsertValuesStep(this);
    }

    public InsertValuesStep values(List<Object> values) {
        return new InsertValuesStep(this, values);
    }

}
