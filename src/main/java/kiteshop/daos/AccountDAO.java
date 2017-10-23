/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.daos;

import Connection.MySQLConnection;
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
public class AccountDAO implements AccountDAOInterface {

    private final Logger logger = ProjectLog.getLogger();

 
    public AccountDAO() {
       
    }

    @Override
    public void createAccount(Account account) {
        String sql = "INSERT INTO account"
                + "(accountID, gebruikersnaam, wachtwoord)"
                + "values (?,?,?)";
        try (Connection connection = MySQLConnection.getConnection(); 
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, 0);
            statement.setString(2, account.getGebruikersnaam());
            statement.setString(3, account.getWachtwoord());

            statement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Account readAccountByGebruikersnaam(String gebruikersnaam) {
        Account account = new Account();
        String sqlQuery = "SELECT * FROM account WHERE gebruikersnaam = ? ";
        try (Connection connection = MySQLConnection.getConnection();
                PreparedStatement prepstat = connection.prepareStatement(sqlQuery)
                ) {
            ResultSet result = prepstat.executeQuery();
            prepstat.setString(1, gebruikersnaam);
            while (result.next()) {
                account.setAccountID(result.getInt(1));
                account.setGebruikersnaam(result.getString(2));
                account.setWachtwoord(result.getString(3));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        logger.info("Returning account :" + account);
        return account;
    }

    @Override
    public void updateAccount(Account account) {
        String sql = "UPDATE account SET gebruikersnaam=?,wachtwoord=?"
                + " WHERE accountID=?;";
        try (Connection connection = MySQLConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, account.getGebruikersnaam());
            statement.setString(2, account.getWachtwoord());
            statement.setInt(3, account.getAccountID());
            statement.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAccount(Account account) {
        String sql = " DELETE FROM account "
                + " WHERE accountID = " + account.getAccountID();
        try (Connection connection = MySQLConnection.getConnection();
                Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Account> readAllAccounts() {
        List<Account> accounts = new ArrayList<Account>();
        String sqlQuery = "SELECT * FROM account";
        try (Connection connection = MySQLConnection.getConnection();
                PreparedStatement prepstat = connection.prepareStatement(sqlQuery);
               ) { 
            ResultSet result = prepstat.executeQuery();
            while (result.next()) {
                Account account = new Account();
                account.setAccountID(result.getInt(1));
                account.setGebruikersnaam(result.getString(2));
                account.setWachtwoord(result.getString(3));
                accounts.add(account);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        logger.info("Returning accounts :" + accounts);
        return accounts;
    }
}
