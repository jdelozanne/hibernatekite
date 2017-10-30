/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.daos.mysql;

import Connection.ConnectionFactory;
import Connection.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kiteshop.daos.BestellingDaoInterface;
import kiteshop.pojos.Bestelling;
import kiteshop.pojos.Klant;
import kiteshop.test.ProjectLog;

/**
 *
 * @author julia
 */
public class BestellingDaoSql implements BestellingDaoInterface {

    ConnectionFactory factory = new ConnectionFactory();
    private final Logger logger = ProjectLog.getLogger();

    public BestellingDaoSql() {

    }

    @Override
    public void createBestelling(Bestelling bestelling) {
        String sql = "INSERT INTO bestelling"
                + "(bestellingID, klantID, totaalprijs)"
                + "values (?,?,?)";
        try (Connection connection = factory.createConnection(factory.getConnectorType());
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            statement.setInt(1, 0);
            statement.setInt(2, bestelling.getKlant().getKlantID());
            statement.setBigDecimal(3, bestelling.getTotaalprijs());
            statement.execute();
            try (ResultSet result = statement.getGeneratedKeys();) {
                if (result.isBeforeFirst()) {
                    result.next();
                    bestelling.setBestellingID(result.getInt(1));
                    System.out.println("Nieuwe bestelling aangemaakt met bestelling id: " + bestelling.getBestellingID());
                }
            }
        } catch (SQLException ex) {
        }
    }

    @Override
    public void updateBestelling(Bestelling bestelling) {
        //deze methode is eigenlijk overbodig. Het enige wat je kunt aanpassen zijn de bestelregels. 
        //Of de klant aan wie de bestelling is gericht..
    }

    @Override
    public void deleteBestelling(int bestellingID) {
        try (Connection connection = factory.createConnection(factory.getConnectorType());
                Statement statement = connection.createStatement(); //Statement statement1 = connection.createStatement();
                ) {
            //First delete 'children' dwz bestelregel
            String deleteRegels = " DELETE FROM bestel_regel "
                    + " WHERE bestellingID =" + bestellingID;
            statement.executeUpdate(deleteRegels);
            //Then delete 'mother' dwz bestelling

            String delete = " DELETE FROM bestelling "
                    + " WHERE bestellingID = " + bestellingID;
            statement.executeUpdate(delete); //zelfde statement hergebruiken, kan dat?
            logger.info("Deleting");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Bestelling readBestellingByBestellingID(int bestellingID) {
        Bestelling b = new Bestelling();
        String query = "Select * from bestelling join klant "
                + "on bestelling.klantID = klant.klantID"
                + " where bestellingID = ?";
        try (Connection connection = factory.createConnection(factory.getConnectorType());
                PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setInt(1, bestellingID);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    b.setBestellingID(result.getInt(1));
                    b.setTotaalprijs(result.getBigDecimal(3));
                    Klant klant = new Klant();
                    klant.setKlantID(result.getInt(4));
                    klant.setVoornaam(result.getString(5));
                    klant.setTussenvoegsel(result.getString(6));
                    klant.setAchternaam(result.getString(7));
                    klant.setEmail(result.getString(8));
                    klant.setTelefoonnummer(result.getString(9));
                    b.setKlant(klant);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return b;
    }

    public List<Bestelling> readBestellingByKlantID(int klantID) {
        List<Bestelling> bestellingen = new ArrayList<>();
        String query = "Select * from bestelling Where klantID =" + klantID;
        Klant klant = new KlantDaoSql().readKlantById(klantID);
        try (Connection connection = factory.createConnection(factory.getConnectorType());
                Statement statement = connection.createStatement()) {
            try (ResultSet result = statement.executeQuery(query);) {
                while (result.next()) {
                    Bestelling b = new Bestelling();
                    b.setBestellingID(result.getInt(1));
                    b.setKlant(klant);
                    b.setTotaalprijs(result.getBigDecimal(3));
                    bestellingen.add(b);
                }
            }
            logger.info("reading from bestelling with specific klantID");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bestellingen;
    }

    public List<Bestelling> readAllBestelling() {
        List<Bestelling> bestellingen = new ArrayList<>();
        String readAll = "Select * from bestelling join klant "
                + "on bestelling.klantID = klant.klantID";
        
        try (Connection connection = factory.createConnection(factory.getConnectorType());
                Statement statement = connection.createStatement();) {

            try (ResultSet result = statement.executeQuery(readAll);) {
                while (result.next()) {
                    Bestelling b = new Bestelling();
                    b.setBestellingID(result.getInt(1));
                    //b.setKlantID(result.getInt(4));
                    b.setTotaalprijs(result.getBigDecimal(3));
                    
                    Klant klant = new Klant();
                    klant.setKlantID(result.getInt(4));
                    klant.setVoornaam(result.getString(5));
                    klant.setTussenvoegsel(result.getString(6));
                    klant.setAchternaam(result.getString(7));
                    klant.setEmail(result.getString(8));
                    klant.setTelefoonnummer(result.getString(9));
                    b.setKlant(klant);
                    bestellingen.add(b);
                }
            }
        logger.info("reading all bestelling");
    }
    catch (SQLException ex

    
        ) {
            ex.printStackTrace();
    }
    return bestellingen ;
}
}
