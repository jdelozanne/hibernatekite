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
public class KlantDaoMongo implements KlantDaoInterface {

	DB database;
	DBCollection collection;
	BasicDBObject document;
	MongoClient mongo;

	public KlantDaoMongo() {
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
			bezoekadres.put("adres_type", (klant.getBezoekAdres().getAdresType()).toString());

			DBObject factuuradres = new BasicDBObject();
			factuuradres.put("straatnaam", klant.getFactuurAdres().getStraatnaam());
			factuuradres.put("huisnummer", klant.getFactuurAdres().getHuisnummer());
			factuuradres.put("toevoeging", klant.getFactuurAdres().getToevoeging());
			factuuradres.put("postcode", klant.getFactuurAdres().getPostcode());
			factuuradres.put("woonplaats", klant.getFactuurAdres().getWoonplaats());
			factuuradres.put("adres_type", (klant.getFactuurAdres().getAdresType()).toString());

			document.put("bezoekadres", bezoekadres);
			document.put("factuuradres", factuuradres);
			collection.insert(document);
		} catch (Exception ex) {
			Logger.getLogger(KlantDaoMongo.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@Override
	public void updateKlant(Klant klant) {
		int id = klant.getKlantID();
		DBObject query = new BasicDBObject();
		query.put("id", id);

		DBObject updateklant = new BasicDBObject();

		updateklant.put("voornaam", klant.getVoornaam());
		updateklant.put("tusenvoegsel", klant.getTussenvoegsel());
		updateklant.put("achternaam", klant.getAchternaam());
		updateklant.put("emailadres", klant.getEmail());
		updateklant.put("telefoon", klant.getTelefoonnummer());
		//adresID`, `klantIDadres`, `straatnaam`, `huisnummer`, `toevoeging`, `postcode`, `woonplaats`, `adres_type
		DBObject updatebezoekadres = new BasicDBObject();
		updatebezoekadres.put("straatnaam", klant.getBezoekAdres().getStraatnaam());
		updatebezoekadres.put("huisnummer", klant.getBezoekAdres().getHuisnummer());
		updatebezoekadres.put("toevoeging", klant.getBezoekAdres().getToevoeging());
		updatebezoekadres.put("postcode", klant.getBezoekAdres().getPostcode());
		updatebezoekadres.put("woonplaats", klant.getBezoekAdres().getWoonplaats());
		updatebezoekadres.put("adres_type", (klant.getBezoekAdres().getAdresType()).toString());

		DBObject updatefactuuradres = new BasicDBObject();
		updatefactuuradres.put("straatnaam", klant.getFactuurAdres().getStraatnaam());
		updatefactuuradres.put("huisnummer", klant.getFactuurAdres().getHuisnummer());
		updatefactuuradres.put("toevoeging", klant.getFactuurAdres().getToevoeging());
		updatefactuuradres.put("postcode", klant.getFactuurAdres().getPostcode());
		updatefactuuradres.put("woonplaats", klant.getFactuurAdres().getWoonplaats());
		updatefactuuradres.put("adres_type", (klant.getFactuurAdres().getAdresType()).toString());

		updateklant.put("bezoekadres", updatebezoekadres);
		updateklant.put("factuuradres", updatefactuuradres);

		DBObject updateObject = new BasicDBObject();
		updateObject.put("$set", updateklant);
		collection.update(query, updateObject);
	}

	@Override
	public void deleteKlant(Klant klant) {
		DBObject deletequery = new BasicDBObject();
		deletequery.put("id", klant.getKlantID());
		collection.remove(deletequery);
	}

	@Override
	public List<Klant> readAllKlanten() {
		List<Klant> klanten = new ArrayList<>();
		DBCursor cursor = collection.find();
		while (cursor.hasNext()) {
			DBObject object = cursor.next();
			BasicDBObject klantObj = (BasicDBObject) object;
			//hieronder maak je een extra  dbobject aan voor factuur en bezoekadres.
			//deze hebben namelijk embedded data, ofwel ze zijn te benaderen als een embedded document
			DBObject bezoek = (BasicDBObject) object.get("bezoekadres");
			DBObject factuur = (BasicDBObject) object.get("factuuradres");

			int id = klantObj.getInt("id");
			String voornaam = klantObj.getString("voornaam");
			String tussenvoegsel = klantObj.getString("tussenvoegsel");
			String achternaam = klantObj.getString("achternaam");
			String email = klantObj.getString("emailadres");
			String telefoon = klantObj.getString("telefoon");

			String straatnaam = (String) bezoek.get("straatnaam");
			int huisnummer = (int) bezoek.get("huisnummer");
			String toevoeging = (String) bezoek.get("toevoeging");
			String postcode = (String) bezoek.get("postcode");
			String woonplaats = (String) bezoek.get("woonplaats");
			String straatnaamf = (String) factuur.get("straatnaam");
			int huisnummerf = (int) factuur.get("huisnummer");
			String toevoegingf = (String) factuur.get("toevoeging");
			String postcodef = (String) factuur.get("postcode");
			String woonplaatsf = (String) factuur.get("woonplaats");

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
			b.setHuisnummer(huisnummer);
			b.setToevoeging(toevoeging);
			b.setPostcode(postcode);
			b.setWoonplaats(woonplaats);

			f.setStraatnaam(straatnaamf);
			f.setHuisnummer(huisnummerf);
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
	public List<Klant> readKlantByAchternaam(String a) {
		List<Klant> klanten = new ArrayList<>();
		DBObject query = new BasicDBObject();
		query.put("achternaam", a);
		DBCursor cursor = collection.find(query);

		while (cursor.hasNext()) {
			DBObject object = cursor.next();
			BasicDBObject klantObj = (BasicDBObject) object;
			//hieronder maak je een extra  dbobject aan voor factuur en bezoekadres.
			//deze hebben namelijk embedded data, ofwel ze zijn te benaderen als een embedded document
			DBObject bezoek = (BasicDBObject) object.get("bezoekadres");
			DBObject factuur = (BasicDBObject) object.get("factuuradres");

			int id = klantObj.getInt("id");
			String voornaam = klantObj.getString("voornaam");
			String tussenvoegsel = klantObj.getString("tussenvoegsel");
			String achternaam = klantObj.getString("achternaam");
			String email = klantObj.getString("emailadres");
			String telefoon = klantObj.getString("telefoon");

			String straatnaam = (String) bezoek.get("straatnaam");
			int huisnummer = (int) bezoek.get("huisnummer");
			String toevoeging = (String) bezoek.get("toevoeging");
			String postcode = (String) bezoek.get("postcode");
			String woonplaats = (String) bezoek.get("woonplaats");
			String straatnaamf = (String) factuur.get("straatnaam");
			int huisnummerf = (int) factuur.get("huisnummer");
			String toevoegingf = (String) factuur.get("toevoeging");
			String postcodef = (String) factuur.get("postcode");
			String woonplaatsf = (String) factuur.get("woonplaats");

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
			b.setHuisnummer(huisnummer);
			b.setToevoeging(toevoeging);
			b.setPostcode(postcode);
			b.setWoonplaats(woonplaats);

			f.setStraatnaam(straatnaamf);
			f.setHuisnummer(huisnummerf);
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
	public Klant readKlantById(int klantID) {
		Klant k = new Klant();
		Adres b = new Adres();
		Adres f = new Adres();
		
		DBObject query = new BasicDBObject();
		query.put("id", klantID);
		DBCursor cursor = collection.find(query);

		while (cursor.hasNext()) {
			DBObject object = cursor.next();
			BasicDBObject klantObj = (BasicDBObject) object;
			
			DBObject bezoek = (BasicDBObject) object.get("bezoekadres");
			DBObject factuur = (BasicDBObject) object.get("factuuradres");
		
			String voornaam = klantObj.getString("voornaam");
			String tussenvoegsel = klantObj.getString("tussenvoegsel");
			String achternaam = klantObj.getString("achternaam");
			String email = klantObj.getString("emailadres");
			String telefoon = klantObj.getString("telefoon");

			String straatnaam = (String) bezoek.get("straatnaam");
			int huisnummer = (int) bezoek.get("huisnummer");
			String toevoeging = (String) bezoek.get("toevoeging");
			String postcode = (String) bezoek.get("postcode");
			String woonplaats = (String) bezoek.get("woonplaats");
			String straatnaamf = (String) factuur.get("straatnaam");
			int huisnummerf = (int) factuur.get("huisnummer");
			String toevoegingf = (String) factuur.get("toevoeging");
			String postcodef = (String) factuur.get("postcode");
			String woonplaatsf = (String) factuur.get("woonplaats");

			k.setKlantID(klantID);
			k.setVoornaam(voornaam);
			k.setTussenvoegsel(tussenvoegsel);
			k.setAchternaam(achternaam);
			k.setEmail(email);
			k.setTelefoonnummer(telefoon);

			b.setStraatnaam(straatnaam);
			b.setHuisnummer(huisnummer);
			b.setToevoeging(toevoeging);
			b.setPostcode(postcode);
			b.setWoonplaats(woonplaats);

			f.setStraatnaam(straatnaamf);
			f.setHuisnummer(huisnummerf);
			f.setToevoeging(toevoegingf);
			f.setPostcode(postcodef);
			f.setWoonplaats(woonplaatsf);

			k.setFactuurAdres(f);
			k.setBezoekAdres(b);
		}
		return k;
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
