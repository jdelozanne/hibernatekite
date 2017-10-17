package kiteshop.controller;

import java.util.List;
import java.util.logging.Logger;

import kiteshop.daos.AccountDAO;
import kiteshop.daos.AccountDAOInterface;
import kiteshop.pojos.Account;
import kiteshop.test.ProjectLog;

public class AccountController {

    private final Logger logger = ProjectLog.getLogger();
    AccountDAOInterface accountDAO;

    public AccountController() {
        accountDAO = new AccountDAO();
    }

    public void createAccount(Account account) {
        accountDAO.createAccount(account);
    }

    public boolean checkLogin(String gebruikersnaam, String wachtwoord) {
        logger.info("Gebruikers naam :" + gebruikersnaam + " Wachtwoord :" + wachtwoord + "Juiste wachtwoord :" + accountDAO.givePassword(gebruikersnaam));
        return accountDAO.givePassword(gebruikersnaam).equals(wachtwoord);
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
}
