/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.daos;

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

	Connection connection;
	PreparedStatement statement;
	ResultSet result;

	public AccountDAO() {
		this.connection = DBConnect.getConnection();
	}

	/* (non-Javadoc)
	 * @see kiteshop.daos.AccountDAOInterface#createAccount(kiteshop.pojos.Account)
	 */
	@Override
	public void createAccount(Account account) {
		try {
			String sql = "INSERT INTO account"
					+ "(accountID, gebruikersnaam, wachtwoord)"
					+ "values (?,?,?)";
			statement = connection.prepareStatement(sql);

			statement.setInt(1, 0);
			statement.setString(2, account.getGebruikersnaam());
			statement.setString(3, account.getWachtwoord());

			statement.execute();

		} catch (SQLException ex) {
			ex.printStackTrace();

		}

	}


	public String givePassword(String gebruiker){

		String wwCheck = null;
		try {
			String sqlQuery = "SELECT * FROM account WHERE gebruikersnaam = ? ";

			PreparedStatement prepstat = this.connection.prepareStatement(sqlQuery);
			prepstat.setString(1, gebruiker);

			ResultSet x = prepstat.executeQuery();

			while(x.next()){
				wwCheck = x.getString("wachtwoord");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return wwCheck;


	}

	@Override
	public Account readAccountByGebruikersnaam(String gebruikersnaam) {
		Account account = new Account();
		try {
			String sqlQuery = "SELECT * FROM account WHERE gebruikersnaam = ? ";

			PreparedStatement prepstat = connection.prepareStatement(sqlQuery);
			prepstat.setString(1, gebruikersnaam);

			ResultSet result = prepstat.executeQuery();

			while(result.next()){
				account.setAccountID(result.getInt(1));
				account.setGebruikersnaam(result.getString(2));
				account.setWachtwoord(result.getString(3));

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		logger.info("Returning account :"+account);   
		return account;
	}

	@Override
	public void updateAccount(Account account) {

		try {
			String sql ="UPDATE account SET gebruikersnaam=?,wachtwoord=?"
					+ " WHERE accountID=?;";

			PreparedStatement statement = connection.prepareStatement(sql);
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
		try {
			
			Statement statement = connection.createStatement();

			String sql = " DELETE FROM account "
					+ " WHERE accountID = " + account.getAccountID();

			statement.executeUpdate(sql);

		} catch (SQLException ex) {
			ex.printStackTrace();

		}

	}

	@Override
	public List<Account> readAllAccounts() {
		List<Account> accounts = new ArrayList<Account>();
		try {
			String sqlQuery = "SELECT * FROM account";

			PreparedStatement prepstat = connection.prepareStatement(sqlQuery);
			ResultSet result = prepstat.executeQuery();

			while(result.next()){
				Account account = new Account();
				account.setAccountID(result.getInt(1));
				account.setGebruikersnaam(result.getString(2));
				account.setWachtwoord(result.getString(3));
				accounts.add(account);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		logger.info("Returning accounts :"+accounts);   
		return accounts;
	}

}
