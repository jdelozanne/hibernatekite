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
import kiteshop.pojos.Adres;
import kiteshop.pojos.Klant;

/**
 *
 * @author julia
 */
public class KlantDAOMongo implements KlantDAOInterface {

    DB database;
    DBCollection collection;
    BasicDBObject document;
    MongoClient mongo;

    public KlantDAOMongo() {
        this.mongo = new MongoDBConnection().connect();
        this.database = mongo.getDB("kiteshop");
        this.collection = database.getCollection("klant");
    }

    @Override
    public void createKlant(Klant klant) {
        try {
            //KlantID, voornaam, tussenvoegsel, achternaam, emailadres, telefoonnummer)"
            document = new BasicDBObject();
            
            document.put("id", getNextSequence("klantid", "countersKlant"));
            document.put("voornaam", klant.getVoornaam());
            document.put("tusenvoegsel", klant.getTussenvoegsel());
            document.put("achternaam", klant.getAchternaam());
            document.put("emailadres", klant.getEmail());
            document.put("telefoon", klant.getTelefoonnummer());
            //adresID`, `klantIDadres`, `straatnaam`, `huisnummer`, `toevoeging`, `postcode`, `woonplaats`, `adres_type
            DBObject bezoekadres = new BasicDBObject();
            bezoekadres.put("straatnaam", klant.getBezoekAdres().getStraatnaam());
            bezoekadres.put("huisnummer", klant.getBezoekAdres().getHuisnummer());
            bezoekadres.put("toevoeging", klant.getBezoekAdres().getToevoeging());
            bezoekadres.put("postcode", klant.getBezoekAdres().getPostcode());
            bezoekadres.put("woonplaats", klant.getBezoekAdres().getWoonplaats());
            //bezoekadres.put("adres_type", (klant.getBezoekAdres().getAdresType()).toString());
            
            DBObject factuuradres = new BasicDBObject();
            factuuradres.put("straatnaam", klant.getFactuurAdres().getStraatnaam());
            factuuradres.put("huisnummer", klant.getFactuurAdres().getHuisnummer());
            factuuradres.put("toevoeging", klant.getFactuurAdres().getToevoeging());
            factuuradres.put("postcode", klant.getFactuurAdres().getPostcode());
            factuuradres.put("woonplaats", klant.getFactuurAdres().getWoonplaats());
            //factuuradres.put("adres_type", (klant.getFactuurAdres().getAdresType()).toString());
            
            document.put("bezoekadres", bezoekadres);
            document.put("factuuradres", factuuradres);
            collection.insert(document);
        } catch (Exception ex) {
            Logger.getLogger(KlantDAOMongo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public Klant readKlant(String achternaam) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateKlant(Klant klant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteKlant(Klant klant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Klant> readAllKlanten() {
        List<Klant> klanten = new ArrayList<>();
        DBCursor cursor = collection.find();
        while (cursor.hasNext()) {
            DBObject object = cursor.next();
            BasicDBObject klantObj = (BasicDBObject) object;
            int id = klantObj.getInt("id");
            String voornaam = klantObj.getString("voornaam");
            String tussenvoegsel = klantObj.getString("tussenvoegsel");
            String achternaam = klantObj.getString("achternaam");
            String email = klantObj.getString("emailadres");
            String telefoon = klantObj.getString("telefoon");
            String straatnaam = klantObj.getString("bezoekadres.straatnaam");
            //int huisnummer = klantObj.getInt("bezoekadres.huisnummer");
            String toevoeging = klantObj.getString("bezoekadres.toevoeging");
            String postcode = klantObj.getString("bezoekadres.postcode");
            String woonplaats = klantObj.getString("bezoekadres.woonplaats");
            String straatnaamf = klantObj.getString("factuuradres.straatnaam");
            //int huisnummerf = klantObj.getInt("factuuradres.huisnummer");
            String toevoegingf = klantObj.getString("factuuradres.toevoeging");
            String postcodef = klantObj.getString("factuuradres.postcode");
            String woonplaatsf = klantObj.getString("factuuradres.woonplaats");
           
            Klant k = new Klant();
            Adres b = new Adres();
            Adres f = new Adres();
            
        k.setKlantID(id);
        k.setVoornaam(voornaam);
        k.setTussenvoegsel(tussenvoegsel);
        k.setAchternaam(achternaam);
        k.setEmail(email);
        k.setTelefoonnummer(telefoon);
        
        
        b.setStraatnaam(straatnaam);
        b.setHuisnummer(2);
        b.setToevoeging(toevoeging);
        b.setPostcode(postcode);
        b.setWoonplaats(woonplaats);
        
        f.setStraatnaam(straatnaamf);
        f.setHuisnummer(3);
        f.setToevoeging(toevoegingf);
        f.setPostcode(postcodef);
        f.setWoonplaats(woonplaatsf);
        
        k.setFactuurAdres(f);
        k.setBezoekAdres(b);    
            
            klanten.add(k);
        }
        return klanten;
    }
    

    @Override
    public ArrayList<Klant> readKlantByAchternaam(String a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static Object getNextSequence(String name, String collection) throws Exception {
        //voor aparte id autoincrement,  invoern in shell
        // db.collection.insert{
        // _id: "klantid",
        //seq: 0
        //}
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("kiteshop");
        DBCollection c = db.getCollection(collection);
        BasicDBObject find = new BasicDBObject();
        find.put("_id", name);
        BasicDBObject update = new BasicDBObject();
        update.put("$inc", new BasicDBObject("seq", 1));
        DBObject obj = c.findAndModify(find, update);
        return obj.get("seq");
    }

}
