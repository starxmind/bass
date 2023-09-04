package com.starxmind.bass.sql.auto.step;

import com.google.common.collect.Lists;
import com.starxmind.bass.sql.auto.clauses.OrderClause;
import com.starxmind.bass.sql.auto.clauses.WhereClause;

import java.util.List;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
public class WhereStep extends SQLStep {
    public WhereStep(FromStep fromStep, WhereClause whereClause) {
        super.SQL = fromStep.getSQL();
        if (whereClause != null && whereClause.isNotEmpty()) {
            super.SQL += " where " + whereClause.getStatement();
        }
    }

    public OrderStep orderBy(OrderClause... orderClauses) {
        return orderBy(Lists.newArrayList(orderClauses));
    }

    public OrderStep orderBy(List<OrderClause> orderClauses) {
        return new OrderStep(this, orderClauses);
    }

    public LimitStep limit(int limit) {
        return new LimitStep(this, limit);
    }
}
