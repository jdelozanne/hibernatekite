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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kiteshop.pojos.BestelRegel;
import static kiteshop.daos.KlantDaoMongo.getNextSequence;
import kiteshop.pojos.Bestelling;

/**
 *
 * @author julia
 */
public class BestelRegelDaoMongo implements BestelRegelDaoInterface {

    DB database;
    DBCollection collection;
    DBObject document;
    MongoClient mongo;

    public BestelRegelDaoMongo() {
        //create a connection with mongodb database
        this.mongo = new MongoDBConnection().connect();
        //this.mongo = new MongoClient("localhost", 27017);
        this.database = mongo.getDB("kiteshop");
        this.collection = database.getCollection("bestel_regel");
    }

    @Override
    public void createBestelRegel(BestelRegel regel) {
        document = new BasicDBObject();
        try {
            document.put("id", getNextSequence("regelid", "countersBestelregel"));
            document.put("productID", regel.getProduct().getProductID());
            document.put("aantal", regel.getAantal());
            document.put("bestellingID", regel.getBestellingID());
            collection.insert(document);
        } catch (Exception ex) {
            Logger.getLogger(BestelRegelDaoMongo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<BestelRegel> readBestelRegelsByBestelling(Bestelling bestelling) {
        List<BestelRegel> bestelregels = (ArrayList<BestelRegel>) bestelling.getBestelling();
        try {
            DBObject query = new BasicDBObject();
            query.put("bestellingID", bestelling.getBestellingID());
            DBCursor cursor = collection.find(query);
            while (cursor.hasNext()) {
                BestelRegel br = new BestelRegel();
                DBObject object = cursor.next();
                BasicDBObject bestelregelObj = (BasicDBObject) object;
                int bestelregel_id = bestelregelObj.getInt("id");
                int product_id = (int) bestelregelObj.get("productID");
                int aantal = (int) bestelregelObj.get("aantal");
                br.setBestelRegelID(bestelregel_id);
                br.setProduct(new ProductDaoMongo().readProductByID(product_id));
                br.setAantal(aantal);
                bestelregels.add(br);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bestelregels;
    }

    public void readBestelRegel(int bestellingID) {
        BestelRegel br = new BestelRegel();
        DBCursor cursor = collection.find();
        while (cursor.hasNext()) {
            DBObject object = cursor.next();
            BasicDBObject bestelregelObj = (BasicDBObject) object;
            int bestelregel_id = bestelregelObj.getInt("id");
            int product_id = (int) bestelregelObj.get("productID");
            int aantal = (int) bestelregelObj.get("aantal");
            br.setBestelRegelID(bestelregel_id);
            br.setProduct(new ProductDaoMongo().readProductByID(product_id));
            br.setAantal(aantal);
        }
        System.out.println(br.toString());
    }

    @Override
    public void updateBestelRegel(BestelRegel regel) {
        int id = regel.getBestelRegelID();
        DBObject query = new BasicDBObject();
        query.put("id", id);
        DBObject updatebestelregel = new BasicDBObject();
        updatebestelregel.put("productID", regel.getProduct().getProductID());
        updatebestelregel.put("aantal", regel.getAantal());
        updatebestelregel.put("bestellingID", regel.getBestellingID());
        DBObject updateObject = new BasicDBObject();
        updateObject.put("$set", updatebestelregel);
        collection.update(query, updateObject);
    }

    @Override
    public void deleteBestelRegel(BestelRegel regel) {
        DBObject deletequery = new BasicDBObject();
        deletequery.put("_id", regel.getBestelRegelID());
        collection.remove(deletequery);
    }
}
