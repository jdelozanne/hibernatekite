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
import kiteshop.pojos.Account;

/**
 *
 * @author julia en steef
 */
public class AccountDAO implements AccountDAOInterface {

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

}
