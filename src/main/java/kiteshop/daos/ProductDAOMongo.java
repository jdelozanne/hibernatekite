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
import static kiteshop.daos.AccountDAOMongo.getNextSequence;
import kiteshop.pojos.Account;
import kiteshop.pojos.Product;

/**
 *
 * @author julia
 */
public class ProductDAOMongo implements ProductDAOInterface {

    DB database;
    DBCollection collection;
    BasicDBObject document;
    MongoClient mongo;

    public ProductDAOMongo() {
        this.mongo = new MongoDBConnection().connect();
        this.database = mongo.getDB("kiteshop");
        this.collection = database.getCollection("product");
    }

    @Override
    public void createProduct(Product product) {
        document = new BasicDBObject();
        try {
            document.put("id", getNextSequence("productid"));
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
            Logger.getLogger(AccountDAOMongo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Product readProduct(String productnaam) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            BigDecimal prijs = new BigDecimal(productObj.getString("prijs"));

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
            BigDecimal prijs = new BigDecimal(productObj.getString("prijs"));
            p.setProductID(id);
            p.setNaam(productnaam);
            p.setVoorraad(voorraad);
            p.setPrijs(prijs);
        }
        return p;
    }

    public static Object getNextSequence(String name) throws Exception {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("kiteshop");
        DBCollection c = db.getCollection("countersProduct");
        BasicDBObject find = new BasicDBObject();
        find.put("_id", name);
        BasicDBObject update = new BasicDBObject();
        update.put("$inc", new BasicDBObject("seq", 1));
        DBObject obj = c.findAndModify(find, update);
        return obj.get("seq");
    }

}
