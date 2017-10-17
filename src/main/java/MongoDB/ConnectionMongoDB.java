/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MongoDB;

import com.mongodb.*;

/**
 *
 * @author julia
 */
public class ConnectionMongoDB {
    
    DB database;
    DBCollection collection;
    BasicDBObject document = new BasicDBObject();
    
    public ConnectionMongoDB() {
        try{
            Mongo mongo = new Mongo("localhost", 27017);
            database = mongo.getDB("test");
            collection = database.getCollection("todo");
            System.out.println("connected");
        }catch(Exception e){
           e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new ConnectionMongoDB();
    }
    
}
