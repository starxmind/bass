package com.starxmind.bass.sql.auto.step;


import com.starxmind.bass.sql.auto.clauses.WhereClause;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
public class FromStep extends SQLStep {
    public FromStep(SelectStep selectStep, String table) {
        super.SQL = selectStep.getSQL() + " from " + table;
    }

    public WhereStep where(WhereClause whereClause) {
        return new WhereStep(this, whereClause);
    }

    public LimitStep limit(int limit) {
        return new LimitStep(this, limit);
    }
}
