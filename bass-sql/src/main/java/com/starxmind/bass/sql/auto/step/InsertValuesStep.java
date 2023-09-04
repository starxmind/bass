package com.starxmind.bass.sql.auto.step;

import java.util.List;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
public class InsertValuesStep extends SQLStep {
    public InsertValuesStep(InsertColumnsStep insertColumnsStep, List<Object> values) {
        StringBuffer sb = new StringBuffer();
        sb.append(insertColumnsStep.getSQL()).append(" values ");
        sb.append(" (");
        for (Object value : values) {
            sb.append(getVal(value)).append(",");
        }
        sb.setLength(sb.length() - 1);
        sb.append(");");
        super.SQL = sb.toString();
    }

    public InsertValuesStep(InsertColumnsStep insertColumnsStep) {
        StringBuffer sb = new StringBuffer();
        sb.append(insertColumnsStep.getSQL()).append(" values ");
        sb.append(" (");
        for (int i = 0; i < insertColumnsStep.getColumns().size(); i++) {
            sb.append("?,");
        }
        sb.setLength(sb.length() - 1);
        sb.append(");");
        super.SQL = sb.toString();
    }

    private Object getVal(Object value) {
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return value;
    }
}
