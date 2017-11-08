/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author julia
 */
public class ConnectionFactory {

    private ConnectionInterface typeOfConnection;
    private Connection connection;
    private String connectorType;
    private static String pathOfActivePropertyFile = "src/main/resources/META-INF/connect.properties";

    public Connection createConnection(String connectorType) {
        switch (connectorType.toLowerCase()) {
            case "jdbc":
                typeOfConnection = new JDBC();
                connection = typeOfConnection.getConnection();
                break;
            case "hikaricp":
                typeOfConnection = new HikariCP();
                connection = typeOfConnection.getConnection();
                break;
        }
        return connection;
    }

    public String getConnectorType() {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream(pathOfActivePropertyFile));
            this.connectorType = props.getProperty("connectorType");
        } catch (Exception ex) {
            System.out.println("geen connectortype gevonden:" + ex);
        }
        return this.connectorType;
    }
}
