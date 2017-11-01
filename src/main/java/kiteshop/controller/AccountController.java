package kiteshop.controller;

import java.util.List;
import java.util.logging.Logger;

import kiteshop.daos.mysql.AccountDaoSql;
import kiteshop.daos.mongodb.AccountDaoMongo;
import kiteshop.pojos.Account;
import kiteshop.test.PaswordHasher;
import kiteshop.test.ProjectLog;
import kiteshop.daos.AccountDaoInterface;

public class AccountController {

	private final Logger logger = ProjectLog.getLogger();
	AccountDaoInterface accountDAO;

	public AccountController(AccountDaoInterface accountDao) {
		this.accountDAO = accountDao;
	}

	public void createAccount(Account account) {
		/* 
		 * Het ingegeven nog niet gehashte password wordt in de hasher gestopt en daarna wordt het pasword overschreven 
		 * door het gehashte, en het orgineel bestaat dan dus niet meer
		 */
		
		String salthex = PaswordHasher.createSaltHex();
		String hashedwachtwoord = PaswordHasher.createHashedPassword(salthex, account.getWachtwoord());
		account.setSalt(salthex);
		account.setWachtwoord(hashedwachtwoord);

		accountDAO.createAccount(account);
	}

	public boolean checkLogin(String gebruikersnaam, String gegevenWachtwoord) {
		logger.info("Gebruikers naam :" + gebruikersnaam + " Wachtwoord :" + gegevenWachtwoord + "Juiste wachtwoord :" + accountDAO.readAccountByGebruikersnaam(gebruikersnaam).getWachtwoord());
		Account currentAccount = accountDAO.readAccountByGebruikersnaam(gebruikersnaam);
		String saltCurrentAccount = currentAccount.getSalt();
		String gegevenWachtwoordGehashd = PaswordHasher.createHashedPassword(saltCurrentAccount, gegevenWachtwoord);
				
		return gegevenWachtwoordGehashd.equals(currentAccount.getWachtwoord());
	}

	public Account readAccountByGebruikersnaam(String gebruikersnaam) {
		return accountDAO.readAccountByGebruikersnaam(gebruikersnaam);
	}

	public boolean accountExists(String gebruikersnaam){
		boolean exists = false;
		if(accountDAO.readAccountByGebruikersnaam(gebruikersnaam).getGebruikersnaam()!=null){
			exists=true;
		}
		return exists;
	}
	
	
	public void deleteAccount(Account account) {
		accountDAO.deleteAccount(account);
	}

	public void updateAccount(Account account) {
		accountDAO.updateAccount(account);
	}

	public List<Account> readAllAccounts() {
		return accountDAO.readAllAccounts();
	}

	public List<Account> readAllAccountsMongo() {
		return new AccountDaoMongo().readAllAccounts();
	}

}
