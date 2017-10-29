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
import static kiteshop.daos.KlantDaoMongo.getNextSequence;
import kiteshop.pojos.BestelRegel;
import kiteshop.pojos.Bestelling;

/**
 *
 * @author julia
 */
public class BestellingDaoMongo implements BestellingDaoInterface {

    DB database;
    DBCollection collection;
    DBObject document;
    MongoClient mongo;

    public BestellingDaoMongo() {
        //create a connection with mongodb database
        this.mongo = new MongoDBConnection().connect();
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
            Logger.getLogger(BestellingDaoMongo.class.getName()).log(Level.SEVERE, null, ex);
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
                b.setKlant(new KlantDaoMongo().readKlantById(klantid));
                b.setTotaalprijs(totaalprijs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public void updateBestelling(Bestelling bestelling) {
        int id = bestelling.getBestellingID();
        BasicDBObject query = new BasicDBObject();
        query.put("id", id);

        DBObject newdoc = new BasicDBObject();
        newdoc.put("klantID", bestelling.getKlant().getKlantID());
        newdoc.put("totaalprijs", bestelling.getTotaalprijs().toString());
        BasicDBObject updateObject = new BasicDBObject();

        updateObject.put("$set", newdoc);
        collection.update(query, updateObject);
    }


@Override
        public void deleteBestelling(int bestellingID) {
        DBObject deletequery = new BasicDBObject();
        deletequery.put("id", bestellingID);
        collection.remove(deletequery);
    }

    @Override
        public List<Bestelling> readBestellingByKlantID(int klantID) {
        List<Bestelling> bestellingen = new ArrayList<>();
        try {
            DBObject query = new BasicDBObject();
            query.put("klantID", klantID);
            DBCursor cursor = collection.find(query);
            while (cursor.hasNext()) {
                DBObject object = cursor.next();
                BasicDBObject bestellingObj = (BasicDBObject) object;
                int bestellingid = bestellingObj.getInt("id");
                int klantid = bestellingObj.getInt("klantID");
                BigDecimal totaalprijs = new BigDecimal(bestellingObj.getString("totaalprijs")).setScale(2, BigDecimal.ROUND_HALF_UP);
                Bestelling b = new Bestelling();
                b.setBestellingID(bestellingid);
                b.setKlant(new KlantDaoMongo().readKlantById(klantid));
                b.setTotaalprijs(totaalprijs);
                bestellingen.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bestellingen;
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
                BigDecimal totaalprijs = new BigDecimal(bestellingObj.getString("totaalprijs")).setScale(2, BigDecimal.ROUND_HALF_UP);
                Bestelling b = new Bestelling();
                b.setBestellingID(bestellingid);
                b.setKlant(new KlantDaoMongo().readKlantById(klantid));
                b.setTotaalprijs(totaalprijs);
                bestellingen.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bestellingen;
    }

}
