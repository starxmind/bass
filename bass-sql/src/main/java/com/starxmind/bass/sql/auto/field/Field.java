package com.starxmind.bass.sql.auto.field;

import com.starxmind.bass.sql.SQLUtils;
import com.starxmind.bass.sql.auto.SQL;
import com.starxmind.bass.sql.auto.clauses.OrderClause;
import com.starxmind.bass.sql.auto.clauses.OrderMethod;
import com.starxmind.bass.sql.auto.clauses.WhereClause;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 参与SQL拼写的字段工具类
 *
 * @author pizzalord
 * @since 1.0
 */
@Getter
public class Field {
    private String code;

    public Field(String code) {
        this.code = code;
    }

    // =
    public <V> WhereClause equalsTo(V value) {
        if (value == null) {
            return new WhereClause();
        }
        return condition("=", value);
    }

    // >
    public <V> WhereClause greaterThan(V value) {
        if (value == null) {
            return new WhereClause();
        }
        return condition(">", value);
    }

    // >=
    public <V> WhereClause greaterThanOrEqualsTo(V value) {
        if (value == null) {
            return new WhereClause();
        }
        return condition(">=", value);
    }

    // <
    public <V> WhereClause lessThan(V value) {
        if (value == null) {
            return new WhereClause();
        }
        return condition("<", value);
    }

    // <=
    public <V> WhereClause lessThanOrEqualsTo(V value) {
        if (value == null) {
            return new WhereClause();
        }
        return condition("<=", value);
    }

    // is null
    public WhereClause isNull() {
        return new WhereClause(this.code + " is null");
    }

    // is not null
    public WhereClause isNotNull() {
        return new WhereClause(this.code + " is not null");
    }

    // like
    public WhereClause like(String fuzzyWords) {
        if (StringUtils.isBlank(fuzzyWords)) {
            return new WhereClause();
        }
        fuzzyWords = fuzzyWords.replaceAll("'", "''");
        return new WhereClause(String.format("%s like '%s'", this.code, SQLUtils.like(fuzzyWords)));
    }

    // in (...)
    public <V> WhereClause in(List<V> values) {
        if (CollectionUtils.isEmpty(values)) {
            return new WhereClause();
        }
        // 如果值只有一个,则转为相等判断语句
        String statement;
        if (values.size() == 1) {
            return equalsTo(values.get(0));
        }
        if (isNumber(values.get(0))) {
            statement = String.format("%s in (%s)", this.code, StringUtils.join(values, ","));
        } else {
            List<String> varcharValues = values.stream().map(
                    x -> varcharValue(x)
            ).collect(Collectors.toList());
            statement = String.format("%s in (%s)", this.code, StringUtils.join(varcharValues, ","));
        }
        return new WhereClause(statement);
    }

    // jsonb contains
    public <V> WhereClause jsonbContains(List<V> values) {
        if (CollectionUtils.isEmpty(values)) {
            return new WhereClause();
        }
        String statement = String.format("%s @> Any (ARRAY [%s]::jsonb[])", this.code,
                isNumber(values.get(0)) ? jsonbArrayStringOfNumber(values) : jsonbArrayStringOfVarchar(values));
        return new WhereClause(statement);
    }

    public <V> WhereClause range(V start, V end) {
        if (start == null && end == null) {
            return new WhereClause();
        }
        if (start == null) {
            // <= end
            return SQL.field(this.code).lessThanOrEqualsTo(end);
        }
        if (end == null) {
            // >= start
            return SQL.field(this.code).greaterThanOrEqualsTo(start);
        }
        if (start.equals(end)) {
            return SQL.field(this.code).equalsTo(start);
        }
        // between start and end
        String statement = isNumber(start) ?
                String.format("%s between %s and %s", this.code, start, end) :
                String.format("%s between '%s' and '%s'", this.code, start, end);
        return new WhereClause(statement);
    }

    private static <V> boolean isNumber(V value) {
        return value instanceof Number;
    }

    // 'value1','value2'
    private <V> String jsonbArrayStringOfNumber(List<V> values) {
        String retVal = StringUtils.EMPTY;
        for (V value : values) {
            retVal += varcharValue(value) + ",";
        }
        return retVal.substring(0, retVal.length() - 1);
    }

    // '["value1"]','["value2"]'
    private <V> String jsonbArrayStringOfVarchar(List<V> values) {
        String retVal = StringUtils.EMPTY;
        for (V value : values) {
            retVal += String.format("'[\"%s\"]'", value) + ",";
        }
        return retVal.substring(0, retVal.length() - 1);
    }

    private <V> WhereClause condition(String operate, V value) {
        String statement = String.format("%s %s %s", this.code, operate, processValue(value));
        return new WhereClause(statement);
    }

    private <V> String processValue(V value) {
        if (value instanceof String) {
            return varcharValue(value);
        }
        return value.toString();
    }

    private <V> String varcharValue(V value) {
        return "'" + value + "'";
    }

    public OrderClause orderBy(OrderMethod method) {
        return new OrderClause(this.code, method);
    }
}
