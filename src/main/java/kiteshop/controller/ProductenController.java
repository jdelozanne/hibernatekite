package kiteshop.controller;

import java.util.ArrayList;
import java.util.logging.Logger;

import kiteshop.daos.ProductDAO;
import kiteshop.daos.ProductDAOInterface;
import kiteshop.pojos.Product;
import kiteshop.test.ProjectLog;

public class ProductenController {

    private final Logger logger = ProjectLog.getLogger();

    ProductDAOInterface productDAO;

    public ProductenController() {
        productDAO = new ProductDAO();
    }

    public void createProduct(Product product) {
        productDAO.createProduct(product);

    }
    
    public Product showSpecificProduct(String naam){
        return productDAO.readProduct(naam);
    }

    public void showProducten() {
        ArrayList<Product> producten =productDAO.showProducten();
        displayProducten(producten);
       
    }
    
    public void displayProducten(ArrayList<Product> lijst){
        for(Product p : lijst)
            System.out.printf("%s50\n", p.toString());
    }

    public void updateProduct(Product p) {
        productDAO.updateProduct(p);

    }


    public void removeProduct(Product product) {
        logger.info("Product " + product + " wordt toegevoegd aan database");

    }

}
