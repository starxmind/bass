package com.starxmind.bass.sql.auto.clauses;

/**
 * Where从句
 *
 * @author pizzalord
 * @since 1.0
 */
public class WhereClause extends Clause {
    public WhereClause() {
    }

    public WhereClause(String statement) {
        super(statement);
    }

    public WhereClause and(WhereClause whereClause) {
        return join(whereClause, "and");

    }

    public WhereClause and(String statement) {
        return and(new WhereClause(statement));

    }

    public WhereClause or(WhereClause whereClause) {
        return join(whereClause, "or");
    }

    public WhereClause or(String statement) {
        return or(new WhereClause(statement));
    }

    private WhereClause join(WhereClause whereClause, String conjunction) {
        // 参数从句为空,返回自身
        if (whereClause == null || whereClause.isEmpty()) {
            return this;
        }
        // 自身为空,返回
        if (super.isEmpty()) {
            super.statement = whereClause.getStatement();
            return this;
        }
        super.statement = String.format("%s %s %s", this.getStatement(), conjunction, whereClause.getStatement());
        return this;
    }

    /**
     * 给语句加上小括号,看作一个整体
     *
     * @return 加上小括号的语句
     */
    public WhereClause parenthesize() {
        if (super.isEmpty()) {
            return this;
        }
        super.statement = "( " + super.statement + " )";
        return this;
    }

}
