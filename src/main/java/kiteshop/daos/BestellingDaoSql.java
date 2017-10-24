/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import kiteshop.pojos.Bestelling;
import kiteshop.test.ProjectLog;

/**
 *
 * @author julia
 */
public class BestellingDaoSql implements BestellingDaoInterface {

    Connection connection;
    PreparedStatement statement;
    ResultSet result;
    private final Logger logger = ProjectLog.getLogger();

    public BestellingDaoSql() {
        connection = MySQLConnection.getConnection();
    }

    @Override
    public void createBestelling(Bestelling bestelling) {
        try {
            String sql = "INSERT INTO bestelling"
                    + "(bestellingID, klantID, totaalprijs)"
                    + "values (?,?,?)";
            this.statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, 0);
            statement.setInt(2, bestelling.getKlant().getKlantID());
            statement.setBigDecimal(3, bestelling.getTotaalprijs());
            statement.execute();
            result = statement.getGeneratedKeys();
            if (result.isBeforeFirst()) {
                result.next();
                bestelling.setBestellingID(result.getInt(1));
                System.out.println("Nieuwe bestelling aangemaakt met bestelling id: " + bestelling.getBestellingID());
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
        try {
            //First delete 'children' dwz bestelregel
            Statement statement = connection.createStatement();
            String deleteRegels = " DELETE FROM bestel_regel "
                    + " WHERE bestellingID =" + bestellingID;
            statement.executeUpdate(deleteRegels);
            //Then delete 'mother' dwz bestelling
            Statement statement1 = connection.createStatement();
            String delete = " DELETE FROM bestelling "
                    + " WHERE bestellingID = " + bestellingID;
            statement1.executeUpdate(delete);
            logger.info("Deleting");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Bestelling readBestellingByBestellingID(int bestellingID) {
        Bestelling b = new Bestelling();
        try {
            String query = "Select * from bestelling where bestellingID = ?";
            this.statement = this.connection.prepareStatement(query);
            statement.setInt(1, bestellingID);
            result = statement.executeQuery();
            while (result.next()) {
                b.setBestellingID(result.getInt(1));
                b.setKlant(new KlantDaoSql().readKlantById(result.getInt(2)));
                b.setTotaalprijs(result.getBigDecimal(3));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return b;
    }

    public List<Bestelling> readBestellingByKlantID(int klantID) {
        List<Bestelling> bestellingen = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "Select * from bestelling Where klantID =" + klantID;
            result = statement.executeQuery(query);
            while (result.next()) {
                Bestelling b = new Bestelling();
                b.setBestellingID(result.getInt(1));
                b.setKlant(new KlantDaoSql().readKlantById(result.getInt(2)));
                b.setTotaalprijs(result.getBigDecimal(3));
                bestellingen.add(b);
            }
            logger.info("reading from bestelling with specific klantID");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bestellingen;
    }

    public List<Bestelling> readAllBestelling() {
        List<Bestelling> bestellingen = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String readAll = "Select * from bestelling";
            result = statement.executeQuery(readAll);
            while (result.next()) {
                Bestelling b = new Bestelling();
                b.setBestellingID(result.getInt(1));
                b.setKlant(new KlantDaoSql().readKlantById(result.getInt(2)));
                b.setTotaalprijs(result.getBigDecimal(3));
                bestellingen.add(b);
            }
            logger.info("reading all bestelling");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bestellingen;
    }
}
