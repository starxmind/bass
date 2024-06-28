package com.starxmind.bass.sql;

import org.apache.commons.lang3.ArrayUtils;

import java.sql.*;

import static com.starxmind.bass.sugar.Sugar.closeQuietly;

/**
 * JDBC means
 *
 * @author pizzalord
 * @since 1.0
 */
public final class JdbcUtils {
    /**
     * Get a database connection
     *
     * @return database connection
     */
    public static Connection getConnection(ConnectionConfig connectionConfig) throws ClassNotFoundException, SQLException {
        Class.forName(connectionConfig.getDriver());
        return DriverManager.getConnection(connectionConfig.getUrl(), connectionConfig.getUsername(), connectionConfig.getPassword());
    }

    /**
     * Release a database connection
     *
     * @param resultSet  result set
     * @param statement  Sql statement
     * @param connection database connection
     */
    public static void release(ResultSet resultSet, Statement statement, Connection connection) {
        closeQuietly(resultSet);
        closeQuietly(statement);
        closeQuietly(connection);
    }

    /**
     * release resources
     *
     * @param statement  statement
     * @param connection connection
     */
    public static void release(Statement statement, Connection connection) {
        closeQuietly(statement);
        closeQuietly(connection);
    }

    /**
     * prepare statement
     *
     * @param connection connection
     * @param query      query
     * @param params     params
     * @return statement
     * @throws SQLException SQL exception
     */
    public static PreparedStatement prepareStatement(Connection connection, String query, Object[] params) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(query);
        setParams(stmt, params);
        return stmt;
    }

    /**
     * Set params
     *
     * @param preparedStatement prepared statement
     * @param params            params
     * @throws SQLException SQL exception
     */
    public static void setParams(PreparedStatement preparedStatement, Object[] params) throws SQLException {
        if (ArrayUtils.isEmpty(params)) {
            return;
        }
        // Param index starts from 1
        int index = 1;
        for (Object o : params) {
            preparedStatement.setObject(index, o);
            index++;
        }
    }
}