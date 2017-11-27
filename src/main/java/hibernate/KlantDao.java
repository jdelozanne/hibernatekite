package hibernate;

import java.io.Serializable;

import javax.persistence.EntityManagerFactory;

import org.springframework.stereotype.Component;

import kiteshop.pojos.Account;
import kiteshop.pojos.Klant;
import kiteshop.pojos.Product;

@Component("KlantDao")
public class KlantDao<T extends Serializable> extends AbstractDao<T> {
	
	
	 public KlantDao() {
	        super((Class<T>) Klant.class);
	    }

}
