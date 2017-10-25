/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 *
 * @author julia
 */
public class JDBC implements ConnectionInterface{

    private Connection connection;
    private static final JDBC INSTANCE = new JDBC();
    private static String pathOfActivePropopertyFile = "src/main/java/Connection/connect.properties";

    public void createConnectionJDBC() {
        try {
            
            Properties props = new Properties();
            props.load(new FileInputStream(pathOfActivePropopertyFile));
            
            String probsUser = props.getProperty("user");
            String probsWW = props.getProperty("password");
            String probsUrl = props.getProperty("dburl");
            
            connection = DriverManager.getConnection(probsUrl, probsUser, probsWW);
        } catch (Exception ex) {
            System.out.println("geen connectie gemaakt:" + ex);
        }
    }

    @Override
    public Connection getConnection() {
        try {
            if (INSTANCE.connection == null|| INSTANCE.connection.isClosed()) {
                INSTANCE.createConnectionJDBC();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return INSTANCE.connection;
    }

    //Toegevoegd om voor het testen het pad te veranderen naar het de testdatabase, zodat de DAOs hier verbinding op maken
    public static void setPathOfActivePropopertyFiletoTest() {
        pathOfActivePropopertyFile = "src/main/java/Connection/connectTestDB.properties";
    }
}
