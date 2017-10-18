/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.daos;

import java.util.ArrayList;
import java.util.List;
import kiteshop.pojos.Account;

/**
 *
 * @author julia
 */
public class Test {
    public static void main(String[] args) {
      AccountDAOMongo m = new AccountDAOMongo();
      Account account = new Account();
      account.setGebruikersnaam("user12");
      account.setWachtwoord("password2");
      m.createAccount(account);
      
       
    }
}
