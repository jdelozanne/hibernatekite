/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. lala
 */
package kiteshop.daos;

import Connection.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import kiteshop.pojos.Account;
import kiteshop.pojos.Adres;
import kiteshop.pojos.AdresType;
import kiteshop.pojos.Klant;
import kiteshop.test.ProjectLog;

/**
 *
 * @author julia ne steef fg
 */
public class KlantDaoSql implements KlantDaoInterface {

    private final Logger logger = ProjectLog.getLogger();
    Connection connection;

    public KlantDaoSql() {
        this.connection = MySQLConnection.getConnection();
    }

    @Override
    public void createKlant(Klant klant) {
        try {
            String sql1 = "INSERT INTO klant" + "(KlantID, voornaam, tussenvoegsel, achternaam, "
                    + "emailadres, telefoonnummer)"
                    + "values (?,?,?,?,?,?)";
            PreparedStatement statement1 = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
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
                logger.info("Klant gegevens verwerkt, key gegenereerd: " + generatedkey);
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

    @Override
    public void updateKlant(Klant klant) {
        try {
            String sql = "UPDATE klant SET voornaam=?,tussenvoegsel=?,achternaam=?,emailadres=?,"
                    + "telefoonnummer=? WHERE KlantID=?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, klant.getVoornaam());
            statement.setString(2, klant.getTussenvoegsel());
            statement.setString(3, klant.getAchternaam());
            statement.setString(4, klant.getEmail());
            statement.setString(5, klant.getTelefoonnummer());
            statement.setInt(6, klant.getKlantID());
            statement.execute();
            String sql2 = "UPDATE adres SET straatnaam=?, huisnummer=?, toevoeging=?, postcode=?,"
                    + "woonplaats = ? WHERE KlantIDadres=? and adres_type = ?";
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement2.setString(1, klant.getBezoekAdres().getStraatnaam());
            statement2.setInt(2, klant.getBezoekAdres().getHuisnummer());
            statement2.setString(3, klant.getBezoekAdres().getToevoeging());
            statement2.setString(4, klant.getBezoekAdres().getPostcode());
            statement2.setString(5, klant.getBezoekAdres().getWoonplaats());
            statement2.setInt(6, klant.getKlantID());
            statement2.setString(7, "BEZOEKADRES");
            statement2.execute();
            String sql3 = "UPDATE adres SET straatnaam=?, huisnummer=?, toevoeging=?, postcode=?,"
                    + "woonplaats = ? WHERE KlantIDadres=? and adres_type = ?";
            PreparedStatement statement3 = connection.prepareStatement(sql3);
            statement3.setString(1, klant.getFactuurAdres().getStraatnaam());
            statement3.setInt(2, klant.getFactuurAdres().getHuisnummer());
            statement3.setString(3, klant.getFactuurAdres().getToevoeging());
            statement3.setString(4, klant.getFactuurAdres().getPostcode());
            statement3.setString(5, klant.getFactuurAdres().getWoonplaats());
            statement3.setInt(6, klant.getKlantID());
            statement3.setString(7, "FACTUURADRES");
            statement3.execute();
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

    @Override
    public List<Klant> readKlantByAchternaam(String achternaam) {
        ArrayList<Klant> selectionKlanten = new ArrayList<Klant>();
        try {
            String query = "Select * from klant where achternaam = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, achternaam);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
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
            for (int i = 0; i < selectionKlanten.size(); i++) {
                Adres bezoekAdres = new Adres();
                Adres factuurAdres = new Adres();
                String query2 = "Select * from adres where klantIDadres = ? and adres_type = ?";
                PreparedStatement statement2 = connection.prepareStatement(query2);
                statement2.setInt(1, selectionKlanten.get(i).getKlantID());
                statement2.setString(2, "BEZOEKADRES");
                ResultSet result2 = statement2.executeQuery();
                while (result2.next()) {
                    logger.info("In while loop voor bezoekadres, " + result2.getString(3));
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
                while (result3.next()) {
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
    public List<Klant> readAllKlanten() {
        ArrayList<Klant> allKlanten = new ArrayList<Klant>();
        try {
            String query = "Select * from klant";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Klant klant = new Klant();
                klant.setKlantID(result.getInt(1));
                klant.setVoornaam(result.getString(2));
                klant.setTussenvoegsel(result.getString(3));
                klant.setAchternaam(result.getString(4));
                klant.setEmail(result.getString(5));
                klant.setTelefoonnummer(result.getString(6));
                allKlanten.add(klant);
            }
            for (int i = 0; i < allKlanten.size(); i++) {
                Adres bezoekAdres = new Adres();
                Adres factuurAdres = new Adres();
                String query2 = "Select * from adres where klantIDadres = ? and adres_type = ?";
                PreparedStatement statement2 = connection.prepareStatement(query2);
                statement2.setInt(1, allKlanten.get(i).getKlantID());
                statement2.setString(2, "BEZOEKADRES");
                ResultSet result2 = statement2.executeQuery();
                while (result2.next()) {
                    logger.info("In while loop voor bezoekadres, " + result2.getString(3));
                    bezoekAdres.setStraatnaam(result2.getString(3));
                    bezoekAdres.setHuisnummer(result2.getInt(4));
                    bezoekAdres.setToevoeging(result2.getString(5));
                    bezoekAdres.setPostcode(result2.getString(6));
                    bezoekAdres.setWoonplaats(result2.getString(7));
                    bezoekAdres.setAdresType(AdresType.BEZOEKADRES);
                }
                allKlanten.get(i).setBezoekAdres(bezoekAdres);
                PreparedStatement statement3 = connection.prepareStatement(query2);
                statement3.setInt(1, allKlanten.get(i).getKlantID());
                statement3.setString(2, "FACTUURADRES");
                ResultSet result3 = statement3.executeQuery();
                while (result3.next()) {
                    factuurAdres.setStraatnaam(result3.getString(3));
                    factuurAdres.setHuisnummer(result3.getInt(4));
                    factuurAdres.setToevoeging(result3.getString(5));
                    factuurAdres.setPostcode(result3.getString(6));
                    factuurAdres.setWoonplaats(result3.getString(7));
                    factuurAdres.setAdresType(AdresType.FACTUURADRES);
                }
                allKlanten.get(i).setFactuurAdres(factuurAdres);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return allKlanten;
    }

    public void displayKlanten(ArrayList<Klant> klanten) {
        for (Klant k : klanten) {
            System.out.println(k.toString());
        }
    }

    @Override
    public Klant readKlantById(int id) {
        Klant k = new Klant();
        Adres bezoekAdres = new Adres();
        Adres factuurAdres = new Adres();
        String query = "Select * from klant where klantID = ?";
        String query2 = "Select * from adres where klantIDadres = ? and adres_type = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Klant klant = new Klant();
                klant.setKlantID(result.getInt(1));
                klant.setVoornaam(result.getString(2));
                klant.setTussenvoegsel(result.getString(3));
                klant.setAchternaam(result.getString(4));
                klant.setEmail(result.getString(5));
                klant.setTelefoonnummer(result.getString(6));
                logger.info("In first loop");
            }
            PreparedStatement statement2 = connection.prepareStatement(query2);
            statement2.setInt(1, id);
            statement2.setString(2, "BEZOEKADRES");
            ResultSet result2 = statement2.executeQuery();
            while (result2.next()) {
                logger.info("In while loop voor bezoekadres, " + result2.getString(3));
                bezoekAdres.setStraatnaam(result2.getString(3));
                bezoekAdres.setHuisnummer(result2.getInt(4));
                bezoekAdres.setToevoeging(result2.getString(5));
                bezoekAdres.setPostcode(result2.getString(6));
                bezoekAdres.setWoonplaats(result2.getString(7));
                bezoekAdres.setAdresType(AdresType.BEZOEKADRES);
            }
            PreparedStatement statement3 = connection.prepareStatement(query2);
                statement3.setInt(1, id);
                statement3.setString(2, "FACTUURADRES");
                ResultSet result3 = statement3.executeQuery();
                while (result3.next()) {
                    factuurAdres.setStraatnaam(result3.getString(3));
                    factuurAdres.setHuisnummer(result3.getInt(4));
                    factuurAdres.setToevoeging(result3.getString(5));
                    factuurAdres.setPostcode(result3.getString(6));
                    factuurAdres.setWoonplaats(result3.getString(7));
                    factuurAdres.setAdresType(AdresType.FACTUURADRES);
                }
        } catch (SQLException ex) {
            Logger.getLogger(KlantDaoSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return k;
    }
}
