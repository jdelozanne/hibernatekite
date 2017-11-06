package kiteshop.daos;

import java.util.List;
import kiteshop.pojos.Product;

public interface ProductDaoInterface {

    void createProduct(Product product);

    List<Product> readProductByName(String productnaam);

    void updateProduct(Product product);

    void deleteProduct(Product product);

    public List<Product> readAllProducten();

    public Product readProductByID(int productID);

}
