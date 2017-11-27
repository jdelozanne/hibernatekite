package kiteshop.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.currentTimeMillis;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import hibernate.AbstractDao;
import kiteshop.View.InlogMenu;
import kiteshop.pojos.Account;
import kiteshop.pojos.Product;
import kiteshop.utilities.PaswordHasher;
import static kiteshop.utilities.PaswordHasher.createHashedToken;
import kiteshop.utilities.ProjectLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class AccountController {

    private static final long TIMELIMIT = 600000000;

    private final Logger logger = ProjectLog.getLogger();
    
    @Autowired @Qualifier("AccountDao")
    AbstractDao accountDAO;
    String tableForNameSearch = "gebruikersnaam";
  
    public AccountController() {
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

        accountDAO.create(account);
    }

    public boolean checkLogin(String gebruikersnaam, String gegevenWachtwoord) {
        logger.info("Gebruikers naam :" + gebruikersnaam 
                + " Wachtwoord :" + gegevenWachtwoord 
                + "Juiste wachtwoord :" 
                + ((Account) accountDAO.readOneByName(this.tableForNameSearch, gebruikersnaam)).getWachtwoord());
        Account currentAccount = (Account) accountDAO.readOneByName(this.tableForNameSearch, gebruikersnaam);
        String saltCurrentAccount = currentAccount.getSalt();
        String gegevenWachtwoordGehashd = PaswordHasher.createHashedPassword(saltCurrentAccount, gegevenWachtwoord);

        return gegevenWachtwoordGehashd.equals(currentAccount.getWachtwoord());
    }

    public boolean findToken() {
        boolean tokenIsFound = false;
        File file = new File("src/main/java/Connection/token.txt");
        String token = null;
        if (file.length() != 0) {
            try (Scanner inputFromFile = new Scanner(file)) {
                while (inputFromFile.hasNext()) {
                    token = inputFromFile.nextLine();
                }
                String loginTime = token.split(":")[1];
                String readablePartOfToken = token.split(":")[0] + token.split(":")[1];
                String hashedPartOfToken = token.split(":")[2];
                if ((getTime() - Long.valueOf(loginTime) < TIMELIMIT)
                        && (createHashedToken(readablePartOfToken).equals(hashedPartOfToken))) {
                    tokenIsFound = true;
                }
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
            }
        }
        return tokenIsFound;
    }

    public void writingFile(String token) {
        File file = new File("src/main/java/Connection/token.txt");
        if (file.exists()) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(InlogMenu.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        try (PrintWriter print = new PrintWriter(file)) {
            print.print(token);

        } catch (FileNotFoundException e) {

        }
    }

    public Account readAccountByGebruikersnaam(String gebruikersnaam) {
        return (Account) accountDAO.readOneByName(this.tableForNameSearch, gebruikersnaam);
    }

    public boolean accountExists(String gebruikersnaam) {
        boolean exists = false;
        if (((Account) accountDAO.readOneByName(this.tableForNameSearch, gebruikersnaam))!= null) {
            exists = true;
        }
        return exists;
    }

    public void deleteAccount(Account account) {
        accountDAO.delete(account);
    }

    public void updateAccount(Account account) {
        accountDAO.update(account);
    }

    public List<Account> readAllAccounts() {
        return accountDAO.readAll();
    }


    public static long getTime() {
        long time = currentTimeMillis();
        return time;
    }

}
