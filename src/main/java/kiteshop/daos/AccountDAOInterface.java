package kiteshop.daos;

import kiteshop.pojos.Account;

//test test

public interface AccountDAOInterface {

	void createAccount(Account account);

	String givePassword(String gebruiker);

}