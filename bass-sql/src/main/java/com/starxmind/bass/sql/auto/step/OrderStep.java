package com.starxmind.bass.sql.auto.step;

import com.starxmind.bass.sql.auto.clauses.OrderClause;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
public class OrderStep extends SQLStep {
    public OrderStep(WhereStep whereStep, List<OrderClause> orderClauses) {
        super.SQL = whereStep.getSQL();
        if (CollectionUtils.isNotEmpty(orderClauses)) {
            super.SQL += " order by ";
            List<String> statements = orderClauses.stream().map(
                    x -> x.getStatement()
            ).collect(Collectors.toList());
            super.SQL += StringUtils.join(statements, ",");
        }
    }

    public LimitStep limit(int limit) {
        return new LimitStep(this, limit);
    }
}
