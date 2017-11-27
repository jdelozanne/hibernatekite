package hibernate;

import java.io.Serializable;

import javax.persistence.EntityManagerFactory;

import org.springframework.stereotype.Component;

import kiteshop.pojos.Account;
import kiteshop.pojos.Adres;
import kiteshop.pojos.BestelRegel;
import kiteshop.pojos.Bestelling;
import kiteshop.pojos.Klant;
import kiteshop.pojos.Product;

@Component("BestelRegelDao")
public class BestellingDao<T extends Serializable> extends AbstractDao<T> {
	
	
	 public BestellingDao() {
	        super((Class<T>) BestelRegel.class);
	    }

}
