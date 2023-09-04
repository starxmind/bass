package com.starxmind.bass.sql.auto.clauses;

import lombok.Getter;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
@Getter
public class OrderClause extends Clause {
    private String field;
    private OrderMethod method;

    public OrderClause(String field, OrderMethod method) {
        this.field = field;
        this.method = method;
        super.statement = this.field + " " + this.method.getCode();
    }
}
