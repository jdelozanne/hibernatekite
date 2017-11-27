package hibernate;

import java.io.Serializable;

import javax.persistence.EntityManagerFactory;

import org.springframework.stereotype.Component;

import kiteshop.pojos.Account;
import kiteshop.pojos.Product;

@Component("ProductDao")
public class ProductDao<T extends Serializable> extends AbstractDao<T> {
	
	
	 public ProductDao() {
	        super((Class<T>) Product.class);
	    }

}
