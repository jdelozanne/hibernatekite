package kiteshop.daos;

import java.util.List;

import kiteshop.pojos.Account;


public interface AccountDAOInterface {

	void createAccount(Account account);
        
	Account readAccountByGebruikersnaam(String gebruikersnaam);
	
	List<Account> readAllAccounts();
	
	void updateAccount(Account account);
	
	void deleteAccount(Account account);

}