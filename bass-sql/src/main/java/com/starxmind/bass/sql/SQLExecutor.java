package com.starxmind.bass.sql;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * SQL Executor
 *
 * @author Xpizza
 * @since shire1.0
 */
public final class SQLExecutor {
    /**
     * execute statement in a datasource
     *
     * @param connection       datasource connection
     * @param executeStatement executable statement
     */
    public static void execute(Connection connection, String executeStatement) {
        execute(connection, executeStatement, null);
    }

    /**
     * execute statement in a datasource with params
     *
     * @param connection       datasource connection
     * @param executeStatement executable statement
     * @param params           parameters for statement
     */
    public static void execute(Connection connection, String executeStatement, Object[] params) {
        Statement statement = null;
        try {
            if (ArrayUtils.isEmpty(params)) {
                statement = connection.createStatement();
                statement.execute(executeStatement);
            } else {
                statement = JdbcUtils.prepareStatement(connection, executeStatement, params);
                ((PreparedStatement) statement).execute();
            }
        } catch (Throwable thr) {
            throw new RuntimeException("fatal: occur an error when execute statement", thr);
        } finally {
            JdbcUtils.release(statement, connection);
        }
    }

    /**
     * batch execute statements
     *
     * @param connection        datasource connection
     * @param executeStatements executable statements list
     * @throws Exception batch execute exception
     */
    public static void batchExecute(Connection connection, List<String> executeStatements) throws Exception {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            for (String executeStatement : executeStatements) {
                statement.addBatch(executeStatement);
            }
            statement.executeBatch();
        } catch (Throwable thr) {
            throw new Exception("fatal: occur an error when execute statements with batch", thr);
        } finally {
            JdbcUtils.release(statement, connection);
        }
    }

    /**
     * query for list
     *
     * @param connection     datasource connection
     * @param queryStatement query statement
     * @return data list
     */
    public static List<Map<String, Object>> queryForList(Connection connection, String queryStatement) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryStatement);
            return parseResultSet(resultSet);
        } catch (Throwable thr) {
            throw new RuntimeException("fatal: occur an error when querying for a result list", thr);
        } finally {
            JdbcUtils.release(statement, connection);
        }
    }

    /**
     * query for map
     *
     * @param connection     datasource connection
     * @param queryStatement query statement
     * @return one line map
     */
    public static Map<String, Object> queryForMap(Connection connection, String queryStatement) {
        List<Map<String, Object>> list = queryForList(connection, queryStatement);
        return first(list);
    }

    /**
     * Get only one line from list
     *
     * @param list list of elements
     * @param <T>  element type
     * @return Only one element
     */
    private static <T> T first(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
//        Sugar.BooleanAssert.isTrue(list.size() < 2, "fatal: more than one line result");
        return list.get(0);
    }
//
//    /**
//     * find list from datasource
//     *
//     * @param connection     datasource connection
//     * @param queryStatement query statement
//     * @param clazz          element's class
//     * @param <T>            element's type
//     * @return query result list
//     */
//    public static <T> List<T> queryForList(Connection connection, String queryStatement, Class<T> clazz) {
//        List<Map<String, Object>> list = queryForList(connection, queryStatement);
//        return ReflectionMeans.mapListToObjectList(list, clazz);
//    }
//
//    /**
//     * find unique line map from datasource
//     *
//     * @param connection     datasource connection
//     * @param queryStatement query statement
//     * @param clazz          result element's class
//     * @param <T>            result element's type
//     * @return unique line from datasource
//     */
//    public static <T> T queryForMap(Connection connection, String queryStatement, Class<T> clazz) {
//        List<T> list = queryForList(connection, queryStatement, clazz);
//        return first(list);
//    }

    /**
     * query for a value
     *
     * @param connection     datasource connection
     * @param queryStatement query statement
     * @return a value from query result
     */
    public static Object queryForValue(Connection connection, String queryStatement) {
        List<Map<String, Object>> list = queryForList(connection, queryStatement);
        return list.get(0).values().toArray()[0];
    }

    /**
     * parse sql result set into list
     *
     * @param resultSet result set
     * @return list data list
     * @throws SQLException SQL exception
     */
    private static List<Map<String, Object>> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Map<String, Object>> rows = Lists.newArrayList();
        ResultSetMetaData md = resultSet.getMetaData();
        int columnCount = md.getColumnCount();
        while (resultSet.next()) {
            Map<String, Object> row = Maps.newHashMap();
            for (int i = 1; i <= columnCount; i++) {
                String key = md.getColumnLabel(i);
                Object value = resultSet.getObject(i);
                row.put(key, value);
            }
            rows.add(row);
        }
        return rows;
    }
}