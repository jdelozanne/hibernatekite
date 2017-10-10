package kiteshop.daos;

import kiteshop.pojos.Product;

public interface ProductDAOInterface {

	void createProduct(Product product);
        
        Product readProduct(String productnaam);
        
        void updateProduct(Product product);
        
        void deleteProduct(Product product);
        

}