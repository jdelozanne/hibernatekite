package kiteshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import hibernate.*;
import kiteshop.pojos.Product;
import kiteshop.utilities.ProjectLog;

@Component
public class ProductenController {

    private final Logger logger = ProjectLog.getLogger();
    
    @Autowired @Qualifier("ProductDao")
    AbstractDao productDAO;
    String tableForNameSearch = "naam";
    
    

    public ProductenController() {
    
    }

    public void createProduct(Product product) {
             productDAO.create(product); 
    }

    public void showProducten() {
        List<Product> producten = productDAO.readAll();
        displayProducten(producten);
    }

    // Denk dat deze niet meer wordt gebruikt
    public List<Product> showProductByName(String productnaam) {
        List<Product> producten = productDAO.readByName(this.tableForNameSearch, productnaam);
        return producten;
    }
    
    public List<Product> showProductByNameLike(String productnaam) {
		 List<Product> producten = productDAO.readByNameLike(this.tableForNameSearch, productnaam);
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
