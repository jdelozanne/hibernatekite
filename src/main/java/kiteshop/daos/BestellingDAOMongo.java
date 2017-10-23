/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.daos;

import Connection.MongoDBConnection;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static kiteshop.daos.KlantDAOMongo.getNextSequence;
import kiteshop.pojos.BestelRegel;
import kiteshop.pojos.Bestelling;

/**
 *
 * @author julia
 */
public class BestellingDAOMongo implements BestellingDAOInterface {

    DB database;
    DBCollection collection;
    DBObject document;
    MongoClient mongo;

    public BestellingDAOMongo() {
        //create a connection with mongodb database
        this.mongo = new MongoDBConnection().connect();
        //this.mongo = new MongoClient("localhost", 27017);
        this.database = mongo.getDB("kiteshop");
        this.collection = database.getCollection("bestelling");
    }

    @Override
    public void createBestelling(Bestelling bestelling) {
        document = new BasicDBObject();
        try {
            document.put("id", getNextSequence("bestellingid", "countersBestelling"));
            document.put("klantID", bestelling.getKlant().getKlantID());
            document.put("totaalprijs", bestelling.getTotaalprijs().toString());
            collection.insert(document);
        } catch (Exception ex) {
            Logger.getLogger(BestellingDAOMongo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Bestelling readBestellingByBestellingID(int bestellingID) {
        Bestelling b = new Bestelling();
        try {
            DBObject query = new BasicDBObject();
            query.put("id", bestellingID);
            DBCursor cursor = collection.find(query);
            while (cursor.hasNext()) {
                DBObject object = cursor.next();
                BasicDBObject bestellingObj = (BasicDBObject) object;
                int bestelling = bestellingObj.getInt("id");
                int klantid = bestellingObj.getInt("klantID");
                BigDecimal totaalprijs = new BigDecimal(bestellingObj.getString("totaalprijs"));
                b.setBestellingID(bestellingID);
                b.setTotaalprijs(totaalprijs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public void updateBestelling(Bestelling bestelling) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteBestelling(int bestellingID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Bestelling> readBestellingByKlantID(int klantID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Bestelling> readAllBestelling() {
        List<Bestelling> bestellingen = new ArrayList<>();
        try {
            DBCursor cursor = collection.find();
            while (cursor.hasNext()) {
                DBObject object = cursor.next();
                BasicDBObject bestellingObj = (BasicDBObject) object;
                int bestellingid = bestellingObj.getInt("id");
                int klantid = bestellingObj.getInt("klantID");
                BigDecimal totaalprijs = new BigDecimal(bestellingObj.getString("totaalprijs"));

                Bestelling b = new Bestelling();
                b.setBestellingID(bestellingid);
                b.setTotaalprijs(totaalprijs);
                bestellingen.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bestellingen;
    }

}
