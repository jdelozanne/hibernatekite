/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.daos;

import Connection.ConnectionFactory;
import Connection.HikariCP;
import Connection.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import kiteshop.pojos.Account;
import kiteshop.test.ProjectLog;

/**
 *
 * @author julia en steef
 */
public class AccountDaoSql implements AccountDaoInterface {

    private final Logger logger = ProjectLog.getLogger();
    ConnectionFactory factory = new ConnectionFactory();
    
 
    public AccountDaoSql() {
       
    }

    @Override
    public void createAccount(Account account) {
        String sql = "INSERT INTO account"
                + "(accountID, gebruikersnaam, wachtwoord, salt)"
                + "values (?,?,?, ?)";
        try (Connection connection = factory.createConnection(factory.getConnectorType()); 
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, 0);
            statement.setString(2, account.getGebruikersnaam());
            statement.setString(3, account.getWachtwoord());
            statement.setString(4, account.getSalt());
            statement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        logger.info("creating account :" + account);
    }

    @Override
    public Account readAccountByGebruikersnaam(String gebruikersnaam) {
        Account account = new Account();
        String sqlQuery = "SELECT * FROM account WHERE gebruikersnaam = ? ";
        try (Connection connection =factory.createConnection(factory.getConnectorType());
                PreparedStatement prepstat = connection.prepareStatement(sqlQuery)
                ) {
            prepstat.setString(1, gebruikersnaam);
            ResultSet result = prepstat.executeQuery();
            
            while (result.next()) {
                account.setAccountID(result.getInt(1));
                account.setGebruikersnaam(result.getString(2));
                account.setWachtwoord(result.getString(3));
                account.setSalt(result.getString(4));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        logger.info("Returning account :" + account);
        return account;
    }

    @Override
    public void updateAccount(Account account) {
    	String sql = "UPDATE account SET gebruikersnaam=?,wachtwoord=?, salt=?"
    			+ " WHERE accountID=?;";
    	try (Connection connection =factory.createConnection(factory.getConnectorType());
    			PreparedStatement statement = connection.prepareStatement(sql)) {
    		statement.setString(1, account.getGebruikersnaam());
    		statement.setString(2, account.getWachtwoord());
    		statement.setString(3, account.getSalt());
    		statement.setInt(4, account.getAccountID());
    		statement.execute();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	logger.info("Updating account :" + account);
    }

    @Override
    public void deleteAccount(Account account) {
    	String sql = " DELETE FROM account "
                + " WHERE accountID = " + account.getAccountID();
        try (Connection connection =factory.createConnection(factory.getConnectorType())) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Account> readAllAccounts() {
        List<Account> accounts = new ArrayList<Account>();
        String sqlQuery = "SELECT * FROM account";
        try (Connection connection =factory.createConnection(factory.getConnectorType());
                PreparedStatement prepstat = connection.prepareStatement(sqlQuery)
               ) { 
            ResultSet result = prepstat.executeQuery();
            while (result.next()) {
                Account account = new Account();
                account.setAccountID(result.getInt(1));
                account.setGebruikersnaam(result.getString(2));
                account.setWachtwoord(result.getString(3));
                account.setSalt(result.getString(4));
                accounts.add(account);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        logger.info("Returning all accounts :" + accounts);
        return accounts;
    }
}
