/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

import kiteshop.pojos.Account;
import kiteshop.pojos.Adres;
import kiteshop.pojos.AdresType;
import kiteshop.pojos.Klant;
import kiteshop.test.ProjectLog;

/**
 *
 * @author julia
 */
public class KlantDAO implements KlantDAOInterface {

	private final Logger logger = ProjectLog.getLogger();

	Connection connection;




	public KlantDAO() {
		this.connection = DBConnect.getConnection();
	}

	@Override
	public void createKlant(Klant klant) {
		try {
			String sql1 = "INSERT INTO klant" + "(KlantID, voornaam, tussenvoegsel, achternaam, "
					+ "emailadres, telefoonnummer)" 
					+ "values (?,?,?,?,?, ?)";

			PreparedStatement statement1 = connection.prepareStatement(sql1,Statement.RETURN_GENERATED_KEYS);

			statement1.setInt(1, 0);
			statement1.setString(2, klant.getVoornaam());
			statement1.setString(3, klant.getTussenvoegsel());
			statement1.setString(4, klant.getAchternaam());
			statement1.setString(5, klant.getEmail());
			statement1.setString(6, klant.getTelefoonnummer());
			statement1.execute();

			ResultSet result = statement1.getGeneratedKeys();
			int generatedkey = 0;
			if (result.isBeforeFirst()) {
				result.next();
				generatedkey = result.getInt(1);
				logger.info("Klant gegevens verwerkt, key gegenereerd: " +generatedkey);
			}

			//Creeeren van het bezoekadres
			String sql2 = "INSERT INTO `juliaworkshop`.`adres` (`adresID`, `klantIDadres`, `straatnaam`, `huisnummer`, `toevoeging`, `postcode`, `woonplaats`, `adres_type` ) "
					+ "VALUES (?,?,?,?, ?,?, ?, ?)";

			PreparedStatement statement2 = connection.prepareStatement(sql2);
			statement2.setInt(1, 0);
			statement2.setInt(2, generatedkey);
			statement2.setString(3, klant.getBezoekAdres().getStraatnaam());
			statement2.setInt(4, klant.getBezoekAdres().getHuisnummer());
			statement2.setString(5, klant.getBezoekAdres().getToevoeging());
			statement2.setString(6, klant.getBezoekAdres().getPostcode());
			statement2.setString(7, klant.getBezoekAdres().getWoonplaats());
			statement2.setString(8, klant.getBezoekAdres().getAdresType().toString());
			statement2.execute();

			//Creeeren van het factuuradres
			String sql3 = "INSERT INTO `juliaworkshop`.`adres` (`klantIDadres`, `straatnaam`, `huisnummer`, `toevoeging`, `postcode`, `woonplaats`, `adres_type`) "
					+ "VALUES (?,?,?,?,?,?, ?)";

			PreparedStatement statement3 = connection.prepareStatement(sql3);

			statement3.setInt(1, generatedkey);
			statement3.setString(2, klant.getFactuurAdres().getStraatnaam());
			statement3.setInt(3, klant.getFactuurAdres().getHuisnummer());
			statement3.setString(4, klant.getFactuurAdres().getToevoeging());
			statement3.setString(5, klant.getFactuurAdres().getPostcode());
			statement3.setString(6, klant.getFactuurAdres().getWoonplaats());
			statement3.setString(7, klant.getFactuurAdres().getAdresType().toString());
			statement3.execute();


		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	
	//Deze is volgens mij niet nodig wordt 100% afgedekt door ArrayList<Klant> readSelectedKlantenAchternaam(String achternaam) 
	@Override
	public Klant readKlant(String achternaam) {
		Klant klant = new Klant();
		Adres bezoekAdres = new Adres();
		Adres factuurAdres = new Adres();

		try {
			String query = "Select * from klant where achternaam = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, achternaam);

			ResultSet result = statement.executeQuery();

			while(result.next()){
				klant.setKlantID(result.getInt(1));
				klant.setVoornaam(result.getString(2));
				klant.setTussenvoegsel(result.getString(3));
				klant.setAchternaam(result.getString(4));
				klant.setEmail(result.getString(5));
				klant.setTelefoonnummer(result.getString(6));
				logger.info("In first loop");
			}

			String query2 = "Select * from adres where klantIDadres = ? ";
			PreparedStatement statement2 = connection.prepareStatement(query2);
			statement.setInt(1, klant.getKlantID());
			//statement.setString(1, klant.getBezoekAdres().getAdresType().toString());

			ResultSet result2 = statement2.executeQuery();

			while(result2.next()){
				logger.info("In while loop voor bezoekadres, "+ result2.getString(3));
				bezoekAdres.setStraatnaam(result2.getString(3));
				bezoekAdres.setHuisnummer(result2.getInt(4));
				bezoekAdres.setToevoeging(result2.getString(5));
				bezoekAdres.setPostcode(result2.getString(6));
				bezoekAdres.setWoonplaats(result2.getString(7));
				bezoekAdres.setAdresType(AdresType.BEZOEKADRES);
			}

			String query3 = "Select * from adres where klantIDadres = ? and adres_type = ?";
			PreparedStatement statement3 = connection.prepareStatement(query3);
			statement.setInt(1, klant.getKlantID());
			statement.setString(1, klant.getFactuurAdres().getAdresType().toString());

			ResultSet result3 = statement3.executeQuery();

			while(result3.next()){
				factuurAdres.setStraatnaam(result3.getString(3));
				factuurAdres.setHuisnummer(result3.getInt(4));
				factuurAdres.setToevoeging(result3.getString(5));
				factuurAdres.setPostcode(result3.getString(6));
				factuurAdres.setWoonplaats(result3.getString(7));
				factuurAdres.setAdresType(AdresType.FACTUURADRES);
			}

			logger.info("just before set besoekadres");
			klant.setBezoekAdres(bezoekAdres);
			klant.setFactuurAdres(factuurAdres);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return klant;
	}

	@Override
	public void updateKlant(Klant klant) {
		try {

			String sql ="UPDATE `juliaworkshop`.`klant` SET `voornaam`='"+klant.getVoornaam()+"', `tussenvoegsel`='d"+klant.getTussenvoegsel()+"', `achternaam`='"+klant.getAchternaam()+"', `emailadres`='"+klant.getEmail()+" ', `telefoonnummer`='"+klant.getTelefoonnummer()+"' WHERE `KlantID`='"+klant.getKlantID()+"';";
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);

			

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void deleteKlant(Klant klant) {
		try {
			//First delete 'children' dwz adres
			Statement statement1 = connection.createStatement();

			String sql1 = " DELETE FROM adres "
					+ " WHERE KlantIDadres = " + klant.getKlantID();
			statement1.executeUpdate(sql1);

			//Then delete 'mother' dwz klant
			Statement statement2 = connection.createStatement();

			logger.info("Deleting");
			String sql2 = " DELETE FROM klant "
					+ " WHERE KlantID = " + klant.getKlantID();

			statement2.executeUpdate(sql2);

		} catch (SQLException ex) {
			ex.printStackTrace();

		}
	}

	@Override   // nog afmaken
	public ArrayList<Klant> readSelectedKlantenAchternaam(String achternaam) {

		ArrayList<Klant> selectionKlanten = new ArrayList<Klant>();

		try {
			// De juiste klanten zoeken en ze in de arraylist stoppen
			String query = "Select * from klant where achternaam = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, achternaam);

			ResultSet result = statement.executeQuery();

			while(result.next()){
				Klant klant = new Klant();
				klant.setKlantID(result.getInt(1));
				klant.setVoornaam(result.getString(2));
				klant.setTussenvoegsel(result.getString(3));
				klant.setAchternaam(result.getString(4));
				klant.setEmail(result.getString(5));
				klant.setTelefoonnummer(result.getString(6));
				logger.info("In first loop");
				selectionKlanten.add(klant);
			}

			// Nu de volgestopte arraylist afgaan en per klant met het klant id het adres erbij (onder voorwaarde jusite type) zoeken en toevoegen
			for(int i = 0; i < selectionKlanten.size(); i++){

				Adres bezoekAdres = new Adres();
				Adres factuurAdres = new Adres();

				String query2 = "Select * from adres where klantIDadres = ? and adres_type = ?";
				PreparedStatement statement2 = connection.prepareStatement(query2);
				statement2.setInt(1, selectionKlanten.get(i).getKlantID());
				statement2.setString(2, "BEZOEKADRES");

				ResultSet result2 = statement2.executeQuery();

				while(result2.next()){
					logger.info("In while loop voor bezoekadres, "+ result2.getString(3));
					bezoekAdres.setStraatnaam(result2.getString(3));
					bezoekAdres.setHuisnummer(result2.getInt(4));
					bezoekAdres.setToevoeging(result2.getString(5));
					bezoekAdres.setPostcode(result2.getString(6));
					bezoekAdres.setWoonplaats(result2.getString(7));
					bezoekAdres.setAdresType(AdresType.BEZOEKADRES);
				}
				selectionKlanten.get(i).setBezoekAdres(bezoekAdres);


				String query3 = "Select * from adres where klantIDadres = ? and adres_type = ?";
				PreparedStatement statement3 = connection.prepareStatement(query3);
				statement3.setInt(1, selectionKlanten.get(i).getKlantID());
				statement3.setString(2, "FACTUURADRES");

				ResultSet result3 = statement3.executeQuery();

				while(result3.next()){
					factuurAdres.setStraatnaam(result3.getString(3));
					factuurAdres.setHuisnummer(result3.getInt(4));
					factuurAdres.setToevoeging(result3.getString(5));
					factuurAdres.setPostcode(result3.getString(6));
					factuurAdres.setWoonplaats(result3.getString(7));
					factuurAdres.setAdresType(AdresType.FACTUURADRES);
				}

				selectionKlanten.get(i).setFactuurAdres(factuurAdres);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return selectionKlanten;
	}

	@Override
	public void readAllKlanten(){
		ArrayList <Klant> allKlanten = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement("select * from klant");

			ResultSet result = statement.executeQuery();

			while(result.next()){
				Klant k = new Klant();
				k.setKlantID(result.getInt(1));
				k.setVoornaam(result.getString(2));
				k.setTussenvoegsel(result.getString(3));
				k.setAchternaam(result.getString(4));

				allKlanten.add(k);
			}

		}catch(SQLException ex){

		}
		displayKlanten(allKlanten);

	}

	public void displayKlanten(ArrayList <Klant> klanten){
		for(Klant k : klanten){
			System.out.println(k.toString());
		}
	}

	public static void main(String args[]) {
		new KlantDAO().readSelectedKlantenAchternaam("Lol2");
	}


}
