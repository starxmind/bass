package com.starxmind.bass.sql.auto.step;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
public class LimitStep extends SQLStep {
    public LimitStep(SQLStep step, int limit) {
        super.SQL = step.getSQL() + " limit " + limit;
    }

    public OffsetStep offset(int offset) {
        return new OffsetStep(this, offset);
    }
}
