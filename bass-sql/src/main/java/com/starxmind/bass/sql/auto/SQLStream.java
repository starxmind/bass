package com.starxmind.bass.sql.auto;


import com.starxmind.bass.sql.auto.step.InsertIntoStep;
import com.starxmind.bass.sql.auto.step.SelectStep;

import java.util.Collection;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
public class SQLStream {
    public SelectStep select(String... fields) {
        return new SelectStep(fields);
    }

    public SelectStep select(Collection<String> fields) {
        return select(fields.toArray(new String[fields.size()]));
    }

    public SelectStep selectCount() {
        return new SelectStep("count(0) as count");
    }

    public InsertIntoStep insertInto(String table) {
        return new InsertIntoStep(table);
    }
}
