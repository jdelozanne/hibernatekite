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

    public ArrayList<Product> showProducten() {
        // TODO Auto-generated method stub
        return null;
    }

    public void updateProduct() {

    }

    public void removeProduct(Product product) {
        logger.info("Product " + product + " wordt toegevoegd aan database");

    }

}
