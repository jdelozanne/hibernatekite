package hibernate;

import java.io.Serializable;

import javax.persistence.EntityManagerFactory;

import org.springframework.stereotype.Component;

import kiteshop.pojos.Account;

@Component("AccountDao")
public class AccountDao<T extends Serializable> extends AbstractDao<T> {
	
	
	 public AccountDao() {
	        super((Class<T>) Account.class);
	    }

}
