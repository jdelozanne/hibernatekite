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
import java.util.logging.Level;
import java.util.logging.Logger;
import kiteshop.pojos.Bestelling;

/**
 *
 * @author julia
 */
public class BestellingDAO implements BestellingDAOInterface {

    Connection connection;
    PreparedStatement statement;
    ResultSet result;

    public BestellingDAO() {
        connection = DBConnect.getConnection();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
        public void deleteBestelling(Bestelling bestelling) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void readBestelling(int bestellingID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
