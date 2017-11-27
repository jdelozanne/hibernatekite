package hibernate;

import java.io.Serializable;

import javax.persistence.EntityManagerFactory;

import org.springframework.stereotype.Component;

import kiteshop.pojos.Account;
import kiteshop.pojos.Adres;
import kiteshop.pojos.Klant;
import kiteshop.pojos.Product;

@Component("AdresDao")
public class AdresDao<T extends Serializable> extends AbstractDao<T> {
	
	
	 public AdresDao() {
	        super((Class<T>) Adres.class);
	    }

}
