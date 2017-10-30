/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import com.mongodb.*;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author julia
 */
public class MongoDBConnection {

    //download mongodb en volg instructie op om te activeren
    //gebruik 3t als interface voor mongodb 
    //dat werkt ongveer zoals workbench met sql. 
    //allereerst wil je een database maken. een collection is wat een table is in sql.
    //om data toe te voegen aan mongodb moet je de data eerst in een BasicDBObject zetten. 
    //dat doe je door document.put("naam", "ww");
    //vervolgens kun je het document opslaan door collection.insert(document); 
    MongoClient mongo;
    private static String pathOfActivePropopertyFile = "src/main/java/Connection/connect.properties";

    public MongoClient connect() {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream(pathOfActivePropopertyFile));
            String hostname = props.getProperty("hostnameMongo");
            int portMongo = Integer.parseInt(props.getProperty("portMongo"));
            this.mongo = new MongoClient(hostname, portMongo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return mongo;
    }
}
