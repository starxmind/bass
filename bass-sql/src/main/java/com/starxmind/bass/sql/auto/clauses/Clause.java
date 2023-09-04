package com.starxmind.bass.sql.auto.clauses;

import org.apache.commons.lang3.StringUtils;

/**
 * SQL从句
 *
 * @author pizzalord
 * @since 1.0
 */
public abstract class Clause {
    protected String statement;

    public String getStatement() {
        return statement;
    }

    public Clause() {
        this.statement = StringUtils.EMPTY;
    }

    public Clause(String statement) {
        this.statement = statement;
    }

    public boolean isEmpty() {
        return StringUtils.isBlank(this.statement);
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }
}
