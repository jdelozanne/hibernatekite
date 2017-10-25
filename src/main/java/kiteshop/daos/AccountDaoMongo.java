/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.daos;

import Connection.MongoDBConnection;
import Connection.JDBC;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kiteshop.pojos.Account;
import org.bson.Document;
import static kiteshop.daos.KlantDaoMongo.getNextSequence;

/**
 *
 * @author julia
 */
public class AccountDaoMongo implements AccountDaoInterface {

    //set db
    DB database;
    //set collection
    DBCollection collection;
    //set dbobject
    BasicDBObject document;
    //connection
    MongoClient mongo;

    public AccountDaoMongo() {
        //create a connection with mongodb database
        this.mongo = new MongoDBConnection().connect();
        this.database = mongo.getDB("kiteshop");
        this.collection = database.getCollection("account");
    }

    @Override
    public void createAccount(Account account) {
        document = new BasicDBObject();
        try {
            document.put("id", getNextSequence("userid", "countersAccount"));
            document.put("gebruikersnaam", account.getGebruikersnaam());
            document.put("wachtwoord", account.getWachtwoord());
            collection.insert(document);
        } catch (Exception ex) {
            Logger.getLogger(AccountDaoMongo.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    public Account readAccountByGebruikersnaam(String gebruikersnaam) {
        Account a = new Account();
        DBObject query = new BasicDBObject();
        query.put("gebruikersnaam", gebruikersnaam);
        DBCursor cursor = collection.find(query);
        while (cursor.hasNext()) {
            DBObject object = cursor.next();
            BasicDBObject accountObj = (BasicDBObject) object;
            int id = (int) accountObj.getInt("id");
            String user = (String)accountObj.get("gebruikersnaam");
            String ww = (String)accountObj.get("wachtwoord");

            a.setAccountID(id);
            a.setGebruikersnaam(user);
            a.setWachtwoord(ww);
        }
        return a;
    }

    public Account readAccountByID(int id) {
        Account a = new Account();
        DBObject whereQuery = new BasicDBObject();
        whereQuery.put("id", id);
        DBCursor cursor = collection.find(whereQuery);
        while (cursor.hasNext()) {
            DBObject object = cursor.next();
            BasicDBObject accountObj = (BasicDBObject) object;
            String user = accountObj.getString("gebruikersnaam");
            String ww = accountObj.getString("wachtwoord");

            a.setAccountID(id);
            a.setGebruikersnaam(user);
            a.setWachtwoord(ww);
        }
        return a;
    }

    @Override
    public void updateAccount(Account account) {
        int id = account.getAccountID();
        DBObject query = new BasicDBObject();
        query.put("id", id);

        DBObject newdoc = new BasicDBObject();
        newdoc.put("gebruikersnaam", account.getGebruikersnaam());
        newdoc.put("wachtwoord", account.getWachtwoord());
        DBObject updateObject = new BasicDBObject();

        updateObject.put("$set", newdoc);
        collection.update(query, updateObject);
    }

    @Override
    public void deleteAccount(Account account) {
        DBObject deletequery = new BasicDBObject();
        deletequery.put("_id", account.getAccountID());
        collection.remove(deletequery);
    }

    private Document convertAccountToDocument(Account account) {
        Document document = new Document();
        try {
            document.append("id", getNextSequence("userid", "countersAccount"));
            document.append("gebruikersnaam", account.getGebruikersnaam());
            document.append("prijs", account.getWachtwoord());
        } catch (Exception ex) {
            Logger.getLogger(AccountDaoMongo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return document;
    }

}
