package kiteshop.controller;

import java.util.List;
import java.util.logging.Logger;

import kiteshop.daos.AccountDaoSql;
import kiteshop.daos.AccountDaoMongo;
import kiteshop.pojos.Account;
import kiteshop.test.ProjectLog;
import kiteshop.daos.AccountDaoInterface;

public class AccountController {

    private final Logger logger = ProjectLog.getLogger();
    AccountDaoInterface accountDAO;

    public AccountController() {
        accountDAO = new AccountDaoSql();
    }

    public void createAccount(Account account) {
        accountDAO.createAccount(account);
    }

    public boolean checkLogin(String gebruikersnaam, String wachtwoord) {
        logger.info("Gebruikers naam :" + gebruikersnaam + " Wachtwoord :" + wachtwoord + "Juiste wachtwoord :" + accountDAO.readAccountByGebruikersnaam(gebruikersnaam).getWachtwoord());
        return accountDAO.readAccountByGebruikersnaam(gebruikersnaam).getWachtwoord().equals(wachtwoord);
    }

    public Account readAccountByGebruikersnaam(String gebruikersnaam) {
        return accountDAO.readAccountByGebruikersnaam(gebruikersnaam);
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
