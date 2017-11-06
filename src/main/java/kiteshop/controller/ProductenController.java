package kiteshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import kiteshop.pojos.Product;
import kiteshop.utilities.ProjectLog;
import kiteshop.daos.ProductDaoInterface;

public class ProductenController {

    private final Logger logger = ProjectLog.getLogger();
    ProductDaoInterface productDAO;

    public ProductenController(ProductDaoInterface productDAO) {
      this.productDAO = productDAO;
    }

    public void createProduct(Product product) {
             productDAO.createProduct(product); 
    }

    public void showProducten() {
        List<Product> producten = productDAO.readAllProducten();
        displayProducten(producten);
    }

    public List<Product> showProductByName(String productnaam) {
        List<Product> producten = productDAO.readProductByName(productnaam);
        return producten;
    }

    public void displayProducten(List<Product> lijst) {
        for (Product p : lijst) {
            System.out.println(p);
        }
    }

    public void updateProduct(Product p) {
        productDAO.updateProduct(p);
    }

    public void deleteProduct(Product product) {
        productDAO.deleteProduct(product);
        logger.info("Product " + product + "wordt verwijderd");
    }
}
