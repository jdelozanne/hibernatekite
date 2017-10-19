package kiteshop.daos;

import java.util.List;
import kiteshop.pojos.Product;

public interface ProductDAOInterface {

    void createProduct(Product product);

    Product readProduct(String productnaam);

    void updateProduct(Product product);

    void deleteProduct(Product product);

    public List<Product> readAllProducten();

    public Product readProductByID(int productID);

}
