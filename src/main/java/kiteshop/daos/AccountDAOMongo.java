/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.daos;

import Connection.MongoDBConnection;
import Connection.MySQLConnection;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kiteshop.pojos.Account;
import org.bson.Document;

/**
 *
 * @author julia
 */
public class AccountDAOMongo implements AccountDAOInterface {

    //set db
    DB database;
    //set collection
    DBCollection collection;
    //set dbobject
    BasicDBObject document;
    //connection
    MongoClient mongo;

    public AccountDAOMongo() {
        //create a connection with mongodb database
        mongo = new MongoClient("localhost", 27017);
        database = mongo.getDB("kiteshop");
        collection = database.getCollection("account");
    }

    public static Object getNextSequence(String name) throws Exception {

        MongoClient mongoClient = new MongoClient("localhost", 27017);
        //Now connect to your databases
        DB db = mongoClient.getDB("kiteshop");
        DBCollection c = db.getCollection("counters");
        BasicDBObject find = new BasicDBObject();
        find.put("_id", name);
        BasicDBObject update = new BasicDBObject();
        update.put("$inc", new BasicDBObject("seq", 1));
        DBObject obj = c.findAndModify(find, update);
        return obj.get("seq");
    }

    @Override
    public void createAccount(Account account) {

        document = new BasicDBObject();
        try {
            document.put("id", getNextSequence("userid"));
        } catch (Exception ex) {
            Logger.getLogger(AccountDAOMongo.class.getName()).log(Level.SEVERE, null, ex);
        }
        document.put("gebruikersnaam", account.getGebruikersnaam());
        document.put("wachtwoord", account.getWachtwoord());
        collection.insert(document);

        //show collection, print to console
        BasicDBObject read = new BasicDBObject();
        DBCursor cursor = collection.find(read);
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    @Override
    public String givePassword(String gebruiker) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Account readAccountByGebruikersnaam(String gebruikersnaam) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Account> readAllAccounts() {
        List<Account> accounts = new ArrayList<>();

        DBCursor cursor = collection.find();
        while (cursor.hasNext()) {
            DBObject object = cursor.next();

            BasicDBObject accountObj = (BasicDBObject) object;
            int id = accountObj.getInt("id");
            String user = accountObj.getString("gebruikersnaam");
            String ww = accountObj.getString("wachtwoord");

            Account a = new Account();
            a.setAccountID(id);
            a.setGebruikersnaam(user);
            a.setWachtwoord(ww);

            accounts.add(a);
        }

        return accounts;
    }

    @Override
    public void updateAccount(Account account) {
        int id = account.getAccountID();
        BasicDBObject query = new BasicDBObject();
        query.put("_id", id);

        BasicDBObject newdoc = new BasicDBObject();
        newdoc.put("gebruikersnaam", account.getGebruikersnaam());

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newdoc);

        collection.update(query, updateObject);
    }

    @Override
    public void deleteAccount(Account account) {
        BasicDBObject deletequery = new BasicDBObject();
        deletequery.put("_id", account.getAccountID());
        collection.remove(deletequery);

    }

}
