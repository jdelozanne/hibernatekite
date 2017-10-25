/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import com.zaxxer.hikari.HikariConfig;

import com.zaxxer.hikari.HikariDataSource;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.IOException;

import java.sql.Connection;

import java.sql.SQLException;

import java.util.Properties;

import java.util.logging.Level;

import java.util.logging.Logger;

import static sun.security.krb5.SCDynamicStoreConfig.getConfig;

/**
 *
 * @author julia
 */
public class HikariCP {

    private HikariDataSource hikari;
    private Connection connection;

    private HikariDataSource createHikariPool() {

        try {

            Properties props = new Properties();

            props.load(new FileInputStream("src/main/java/Connection/connect.properties"));

            HikariConfig config = new HikariConfig();
            //config.setDataSourceClassName("com.mysql.jdbc.Driver");
            config.setJdbcUrl(props.getProperty("dburl"));
            config.setUsername(props.getProperty("user"));
            config.setPassword(props.getProperty("password"));
            config.setMaximumPoolSize(10);
            config.setAutoCommit(true);
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

            hikari = new HikariDataSource(config);

        } catch (IOException ex) {

            Logger.getLogger(HikariCP.class.getName()).log(Level.SEVERE, null, ex);

        }

        return hikari;

    }

    public Connection getConnection() {

        try {
            if (hikari == null || hikari.isClosed()) {
                return connection = createHikariPool().getConnection();
            }
            return connection = hikari.getConnection();

        } catch (SQLException ex) {

            Logger.getLogger(HikariCP.class.getName()).log(Level.SEVERE, null, ex);

        }

        return null;

    }
}
