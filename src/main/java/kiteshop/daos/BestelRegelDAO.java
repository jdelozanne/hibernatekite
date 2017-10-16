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
import java.util.List;
import java.util.logging.Logger;
import kiteshop.pojos.BestelRegel;
import kiteshop.pojos.Bestelling;


import kiteshop.pojos.Product;
import kiteshop.test.ProjectLog;

/**
 *
 * @author julia
 */
public class BestelRegelDAO implements BestelRegelDAOInterface {
    
    Connection connection;
    PreparedStatement statement;
    ResultSet result;
private final Logger logger = ProjectLog.getLogger();
    
    public BestelRegelDAO(){
        connection = DBConnect.getConnection();
    }

    @Override
    public void createBestelRegel(BestelRegel regel) {
       try {
            String sql = "INSERT INTO Bestel_regel"
                    + "(bestel_regelID, productID, aantal, bestellingID)"
                    + "values (?,?,?,?)";
            this.statement = connection.prepareStatement(sql);
            

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
        try {
            String query = "Select * from bestel_regel where bestellingID = ?";
            this.statement = connection.prepareStatement(query);
            statement.setInt(1, bestellingID);

            result = statement.executeQuery();
            
            while(result.next()){
            
            r.setBestelRegelID(result.getInt(1));
            r.getProduct().setProductID(result.getInt(2));
            r.setAantal(result.getInt(3));
            r.getBestelling().setBestellingID(result.getInt(4));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
      
    }

    @Override
    public void updateBestelRegel(BestelRegel regel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteBestelRegel(BestelRegel regel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public ArrayList<BestelRegel> readBestelRegelsByBestelling(Bestelling bestelling){
        
    List<BestelRegel> bestelregels = bestelling.getBestelling();
        //bestelregels opvragen met een bepaalde bestellingID 
        //arraylist teruggeven met alle bestelregels die deze id hebben
        try {

            Statement statement = connection.createStatement();

            String query = "Select * from bestel_regel Where bestellingID =" + bestelling.getBestellingID();

            statement.executeQuery(query);

            while (result.next()) {
             BestelRegel r = new BestelRegel();
             r.setBestelRegelID(result.getInt(1));
             r.setProductID(result.getInt(2));
             r.setAantal(result.getInt(3));
             bestelregels.add(r);
            }
            logger.info("reading from bestelregel with specific bestellingID");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (ArrayList<BestelRegel>) bestelregels;
    }
  
    
      
}
