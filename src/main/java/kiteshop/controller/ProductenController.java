package kiteshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


import kiteshop.daos.mysql.ProductDaoSql;
import kiteshop.pojos.Product;
import kiteshop.test.ProjectLog;
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

    public Product showSpecificProduct(String naam) {
        Product p = productDAO.readProduct(naam);
        System.out.println(p.toString());
        return p;
    }

    public void showProducten() {
        List<Product> producten = productDAO.readAllProducten();
        displayProducten(producten);
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
