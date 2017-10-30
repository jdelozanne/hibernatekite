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
import java.util.logging.Logger;
import kiteshop.daos.BestelRegelDaoInterface;
import kiteshop.pojos.BestelRegel;
import kiteshop.pojos.Bestelling;
import kiteshop.pojos.Product;
import kiteshop.test.ProjectLog;

/**
 *
 * @author julia
 */
public class BestelRegelDaoSql implements BestelRegelDaoInterface {

    ConnectionFactory factory = new ConnectionFactory();
    private final Logger logger = ProjectLog.getLogger();
    String insertNew = "INSERT INTO Bestel_regel"
            + "(bestel_regelID, productID, aantal, bestellingID)"
            + "values (?,?,?,?)";
    String queryReadBestellingID = "Select * from bestel_regel where bestellingID = ?";
    String updateBestelRegel = "UPDATE bestel_regel SET bestel_regelID = ?, productID = ?, aantal = ?, bestellingID =? where bestellingID = ?";
    String queryReadAllByBestelling = "Select * from bestel_regel Where bestellingID = ?";

    public BestelRegelDaoSql() {
    }

    @Override
    public void createBestelRegel(BestelRegel regel) {

        try (Connection connection = factory.createConnection(factory.getConnectorType());
                PreparedStatement statement = connection.prepareStatement(this.insertNew)) {

            statement.setInt(1, 0);
            statement.setInt(2, regel.getProduct().getProductID());
            statement.setInt(3, regel.getAantal());
            statement.setInt(4, regel.getBestelling().getBestellingID());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void readBestelRegel(int bestellingID) {
        BestelRegel r = new BestelRegel();

        try (Connection connection = factory.createConnection(factory.getConnectorType());
                PreparedStatement statement = connection.prepareStatement(this.queryReadBestellingID);) {

            statement.setInt(1, bestellingID);
            try (ResultSet result = statement.executeQuery();) {
                while (result.next()) {
                    r.setBestelRegelID(result.getInt(1));
                    r.getProduct().setProductID(result.getInt(2));
                    r.setAantal(result.getInt(3));
                    r.getBestelling().setBestellingID(result.getInt(4));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateBestelRegel(BestelRegel regel) {

        try (Connection connection = factory.createConnection(factory.getConnectorType());
                PreparedStatement statement = connection.prepareStatement(this.updateBestelRegel)) {
            statement.setInt(1, regel.getBestelRegelID());
            statement.setInt(2, regel.getProduct().getProductID());
            statement.setInt(3, regel.getAantal());
            statement.setInt(4, regel.getBestellingID());
            statement.setInt(5, regel.getBestellingID());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteBestelRegel(BestelRegel regel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<BestelRegel> readBestelRegelsByBestelling(Bestelling bestelling) {
        List<BestelRegel> bestelregels = (ArrayList<BestelRegel>) bestelling.getBestelling();

        try (Connection connection = factory.createConnection(factory.getConnectorType());
                PreparedStatement statement = connection.prepareStatement(this.queryReadAllByBestelling)) {
            statement.setInt(1, bestelling.getBestellingID());
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    BestelRegel r = new BestelRegel();
                    r.setBestelRegelID(result.getInt(1));
                    r.getProduct().setProductID(result.getInt(2));
                    //r.setProduct(new ProductDaoSql().readProductByID(result.getInt(2)));
                    r.setAantal(result.getInt(3));
                    bestelregels.add(r);
                }
            }
            logger.info("reading from bestelregel with specific bestellingID");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bestelregels;
    }
}
