package kiteshop.daos;

import java.util.ArrayList;
import kiteshop.pojos.Product;

public interface ProductDAOInterface {

    void createProduct(Product product);

    Product readProduct(String productnaam);

    void updateProduct(Product product);

    void deleteProduct(Product product);

    public ArrayList<Product> showProducten();

    public Product readProductByID(int productID);

}
