package com.starxmind.bass.sql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Database connection config
 *
 * @author pizzalord
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
public class ConnectionConfig {
    private String driver;
    private String url;
    private String username;
    private String password;
}