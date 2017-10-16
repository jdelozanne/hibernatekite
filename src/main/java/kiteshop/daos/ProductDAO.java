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
import kiteshop.pojos.Product;
import kiteshop.test.ProjectLog;

/**
 *
 * @author julia
 */
public class ProductDAO implements ProductDAOInterface {

    Connection connection;
    PreparedStatement statement;
    ResultSet result;
    private final Logger logger = ProjectLog.getLogger();

    public ProductDAO() {
        this.connection = DBConnect.getConnection();
    }

    @Override
    public void createProduct(Product product) {
        try {
            String sql = "INSERT INTO product"
                    + "(productID, productnaam, voorraad, prijs)"
                    + "values (?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, 0);
            statement.setString(2, product.getNaam());
            statement.setInt(3, product.getVoorraad());
            statement.setBigDecimal(4, product.getPrijs());
            statement.execute();
            System.out.println("Product " + product.getNaam() + "is succesvol teogevoegd");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Product readProduct(String productnaam) {
        Product p = new Product();
        try {
            String query = "Select * from product where productnaam = ?";
            this.statement = this.connection.prepareStatement(query);
            statement.setString(1, productnaam);
            result = statement.executeQuery();
            while (result.next()) {
                p.setProductID(result.getInt(1));
                p.setNaam(result.getString(2));
                p.setVoorraad(result.getInt(3));
                p.setPrijs(result.getBigDecimal(4));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }
//overloaded

    public String readProduct(int productID) {
        Product p = new Product();
        try {
            String query = "Select productnaam from product where productID = ?";
            statement = this.connection.prepareStatement(query);
            statement.setInt(1, productID);
            result = statement.executeQuery();
            while (result.next()) {
                p.setProductID(result.getInt(1));
                p.setNaam(result.getString(2));
                p.setVoorraad(result.getInt(3));
                p.setPrijs(result.getBigDecimal(4));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p.getNaam();
    }
    
    @Override
    public Product readProductByID(int productID) {
        Product p = new Product();
        try {
            String query = "Select * from product where productID = ?";
            statement = this.connection.prepareStatement(query);
           statement.setInt(1, productID);
            result = statement.executeQuery();
            while (result.next()) {
                p.setProductID(result.getInt(1));
                p.setNaam(result.getString(2));
                p.setVoorraad(result.getInt(3));
                p.setPrijs(result.getBigDecimal(4));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }
    
    @Override
    public ArrayList<Product> showProducten() {
        ArrayList<Product> producten = new ArrayList<>();
        try {
            String query = "Select * from product";
            Statement statement = this.connection.createStatement();
            result = statement.executeQuery(query);
            while (result.next()) {
                Product p = new Product();
                p.setProductID(result.getInt(1));
                p.setNaam(result.getString(2));
                p.setVoorraad(result.getInt(3));
                p.setPrijs(result.getBigDecimal(4));
                producten.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return producten;
    }

    @Override
    public void updateProduct(Product product) {
        System.out.println("updaten naar database");
        String update = "UPDATE product SET productnaam = ?, voorraad = ?, prijs = ? where productID = ?";
        try {
            statement = this.connection.prepareStatement(update);
            statement.setString(1, product.getNaam());
            statement.setInt(2, product.getVoorraad());
            statement.setBigDecimal(3, product.getPrijs());
            statement.setInt(4, product.getProductID());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(Product product) {
        try {
            //First delete 'children' dwz bestelregel
            Statement statement = connection.createStatement();
            String query = " DELETE FROM bestel_regel "
                    + " WHERE productID = " + product.getProductID();
            statement.executeUpdate(query);
            //Then delete 'mother' dwz product
            Statement statement1 = connection.createStatement();
            logger.info("Deleting");
            String query1 = " DELETE FROM product "
                    + " WHERE productID = " + product.getProductID();
            statement1.executeUpdate(query1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void displayProducten(ArrayList<Product> producten) {
        for (Product p : producten) {
            System.out.println(p.toString());
        }
    }
    public static void main(String[] args) {
        BestelRegel br = new BestelRegel();
        br.setProduct(new ProductDAO().readProductByID(13));
        br.toString();
    }
}
