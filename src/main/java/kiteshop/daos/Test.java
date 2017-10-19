/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.daos;

import java.util.ArrayList;
import java.util.List;
import kiteshop.View.MenuAccounts;
import kiteshop.pojos.Account;

/**
 *
 * @author julia
 */
public class Test {

    public static void main(String[] args) {
        AccountDAOMongo m = new AccountDAOMongo();
        System.out.println((m.readAccountByID(7)).toString());
        System.out.println((m.readAccountByGebruikersnaam("user1")).toString());

    }

}
