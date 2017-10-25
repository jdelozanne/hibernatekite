/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.Connection;

/**
 *
 * @author julia
 */
public class ConnectionFactory {
    
    HikariCP hikariConnection = new HikariCP();
    JDBC jdbcConnection = new JDBC();
    Connection connection;
    String connectorType = "jdbc";
    
    public Connection createConnection(String connectorType){
        switch(connectorType.toLowerCase()){
            case "jdbc":
                connection = jdbcConnection.getConnection();
                break;
            case "hikaricp":
                connection = hikariConnection.getConnection();
                break;
        }
        return connection;
    }
    
    public String getConnectorType(){
        return this.connectorType;
    }

   
  
}
