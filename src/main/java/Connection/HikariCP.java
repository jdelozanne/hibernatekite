/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

/**
 *
 * @author julia
 */

 
public class HikariCP {
 

 
    private HikariDataSource hikari;
 

 
    private HikariDataSource connectToDatabase() {
 

 
        try {
 
            Properties props = new Properties();
 
            props.load(new FileInputStream("src/main/java/Connection/connect.properties"));
 
            HikariConfig config = new HikariConfig();
 
            config.setJdbcUrl(props.getProperty("dburl"));
 
            config.setUsername(props.getProperty("user"));
 
            config.setPassword(props.getProperty("password"));
 
            config.setMaximumPoolSize(10);
 
            config.setAutoCommit(true);
 
            hikari = new HikariDataSource(config);
 
        } catch (IOException ex) {
 
            Logger.getLogger(HikariCP.class.getName()).log(Level.SEVERE, null, ex);
 
        }
 
        return hikari;
 
    }
 

 
    public Connection getConnection() {
 
        Connection connection = null;
 
        try {
 
            if (hikari == null || hikari.isClosed()) {
 
                return connection = new HikariCP().connectToDatabase().getConnection();
 
            }
 
            return connection = hikari.getConnection();
 
        } catch (SQLException ex) {
 
            Logger.getLogger(HikariCP.class.getName()).log(Level.SEVERE, null, ex);
 
        }
 
        return null;
 
    }
 
}
 
}
