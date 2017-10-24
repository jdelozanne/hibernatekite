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
import com.mongodb.client.MongoDatabase;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static kiteshop.daos.KlantDaoMongo.getNextSequence;
import kiteshop.pojos.Account;
import kiteshop.pojos.Product;

/**
 *
 * @author julia
 */
public class ProductDaoMongo implements ProductDaoInterface {

    DB database;
    DBCollection collection;
    BasicDBObject document;
    MongoClient mongo;

    public ProductDaoMongo() {
        this.mongo = new MongoDBConnection().connect();
        this.database = mongo.getDB("kiteshop");
        this.collection = database.getCollection("product");
    }

    @Override
    public void createProduct(Product product) {
        document = new BasicDBObject();
        try {
            document.put("id", getNextSequence("productid", "countersProduct"));
            document.put("productnaam", product.getNaam());
            document.put("voorraad", product.getVoorraad());
            document.put("prijs", (product.getPrijs()).toString()); //dit moet nog bewerkt zodat er twee decimalen worden opgeslagen
            collection.insert(document);
            //show collection, print to console
            BasicDBObject read = new BasicDBObject();
            DBCursor cursor = collection.find(read);
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
        } catch (Exception ex) {
            Logger.getLogger(AccountDaoMongo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Product readProduct(String productnaam) {
        Product p = new Product();
        BasicDBObject query = new BasicDBObject();
        query.put("productnaam", productnaam);
        DBCursor cursor = collection.find(query);
        while (cursor.hasNext()) {
            DBObject object = cursor.next();
            BasicDBObject productObj = (BasicDBObject) object;
            int id = productObj.getInt("id");
            String naam = productObj.getString("productnaam");
            int voorraad = productObj.getInt("voorraad");
            BigDecimal prijs = new BigDecimal(productObj.getString("prijs")).setScale(2, BigDecimal.ROUND_HALF_UP);
            p.setProductID(id);
            p.setNaam(naam);
            p.setVoorraad(voorraad);
            p.setPrijs(prijs);
        }
        return p;
    }

    @Override
    public void updateProduct(Product product) {
        int id = product.getProductID();
        BasicDBObject query = new BasicDBObject();
        query.put("id", id);

        BasicDBObject newdoc = new BasicDBObject();
        newdoc.put("productnaam", product.getNaam());
        newdoc.put("voorraad", product.getVoorraad());
        newdoc.put("prijs", product.getPrijs().toString());
        BasicDBObject updateObject = new BasicDBObject();

        updateObject.put("$set", newdoc);
        collection.update(query, updateObject);
    }

    @Override
    public void deleteProduct(Product product) {
        BasicDBObject deletequery = new BasicDBObject();
        deletequery.put("id", product.getProductID());
        collection.remove(deletequery);
    }

    @Override
    public List<Product> readAllProducten() {
        List<Product> products = new ArrayList<>();
        DBCursor cursor = collection.find();
        while (cursor.hasNext()) {
            DBObject object = cursor.next();
            BasicDBObject productObj = (BasicDBObject) object;
            int id = productObj.getInt("id");
            String productnaam = productObj.getString("productnaam");
            int voorraad = productObj.getInt("voorraad");
            BigDecimal prijs = new BigDecimal(productObj.getString("prijs")).setScale(2, BigDecimal.ROUND_HALF_UP);
            Product p = new Product();
            p.setProductID(id);
            p.setNaam(productnaam);
            p.setVoorraad(voorraad);
            p.setPrijs(prijs);
            products.add(p);
        }
        return products;
    }

    @Override
    public Product readProductByID(int productID) {
        Product p = new Product();
        BasicDBObject query = new BasicDBObject();
        query.put("id", productID);
        DBCursor cursor = collection.find(query);

        while (cursor.hasNext()) {
            DBObject object = cursor.next();
            BasicDBObject productObj = (BasicDBObject) object;
            int id = productObj.getInt("id");
            String productnaam = productObj.getString("productnaam");
            int voorraad = productObj.getInt("voorraad");
            BigDecimal prijs = new BigDecimal(productObj.getString("prijs")).setScale(2, BigDecimal.ROUND_HALF_UP);
            p.setProductID(id);
            p.setNaam(productnaam);
            p.setVoorraad(voorraad);
            p.setPrijs(prijs);
        }
        return p;
    }

}
