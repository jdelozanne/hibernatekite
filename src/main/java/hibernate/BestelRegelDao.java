package hibernate;

import java.io.Serializable;

import javax.persistence.EntityManagerFactory;

import org.springframework.stereotype.Component;

import kiteshop.pojos.Account;
import kiteshop.pojos.Adres;
import kiteshop.pojos.Bestelling;
import kiteshop.pojos.Klant;
import kiteshop.pojos.Product;

@Component("BestellingDao")
public class BestelRegelDao<T extends Serializable> extends AbstractDao<T> {
	
	
	 public BestelRegelDao() {
	        super((Class<T>) Bestelling.class);
	    }

}
