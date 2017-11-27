package kiteshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Component;

import hibernate.*;
import kiteshop.pojos.Product;
import kiteshop.utilities.ProjectLog;

@Component
public class ProductenController {

    private final Logger logger = ProjectLog.getLogger();
    
    AbstractDao productDAO;
    String tableForNameSearch = "naam";
    
    @PersistenceUnit
    public EntityManagerFactory entityManagerFactory;

    public ProductenController(EntityManagerFactory entityManagerFactory) {
    	productDAO = new ConcreteDao(Product.class,entityManagerFactory);
    }

    public void createProduct(Product product) {
             productDAO.create(product); 
    }

    public void showProducten() {
        List<Product> producten = productDAO.readAll();
        displayProducten(producten);
    }

    public List<Product> showProductByName(String productnaam) {
        List<Product> producten = productDAO.readByName(this.tableForNameSearch, productnaam);
        return producten;
    }

    public void displayProducten(List<Product> lijst) {
        for (Product p : lijst) {
            System.out.println(p);
        }
    }

    public void updateProduct(Product p) {
        productDAO.update(p);
    }

    public void deleteProduct(Product product) {
        productDAO.delete(product);
        logger.info("Product " + product + "wordt verwijderd");
    }
}
