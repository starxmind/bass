package com.starxmind.bass.sql.auto.step;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
public class OffsetStep extends SQLStep {
    public OffsetStep(LimitStep limitStep, int offset) {
        super.SQL = limitStep.getSQL() + " offset " + offset;
    }
}
