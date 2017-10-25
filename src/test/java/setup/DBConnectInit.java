/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package setup;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

import kiteshop.test.ProjectLog;

/**
 *
 * @author julia
 */
public class DBConnectInit {
	private final Logger logger = ProjectLog.getLogger(); 
    private Connection connection;
    private static final DBConnectInit INSTANCE = new DBConnectInit();
    
    public static String pathOfActivePropopertyFile = "src/test/java/setup/connectInit.properties";
    
    public static void setPathOfActivePropopertyFile (String path){
    	pathOfActivePropopertyFile = path;
      }

    private DBConnectInit() {
    }

    public void connect() {
        try {
            //load properties file s
            Properties props = new Properties();
            props.load(new FileInputStream(pathOfActivePropopertyFile));
            //read props
            String probsUser = props.getProperty("user");
            String probsWW = props.getProperty("password");
            String probsUrl = props.getProperty("dburl");
            //connect to database
            connection = DriverManager.getConnection(probsUrl, probsUser, probsWW);
            
            logger.info("Made new connection with "+ pathOfActivePropopertyFile);
            
            logger.info("Conectie met schema" +connection.getSchema());
        } catch (Exception ex) {
            System.out.println("geen connectie gemaakt:" + ex);
        }

    }

    public static Connection getConnection() {

        try {
            if (INSTANCE.connection == null) {
                INSTANCE.connect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return INSTANCE.connection;
    }
}
