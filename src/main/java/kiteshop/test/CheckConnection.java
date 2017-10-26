/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.test;

import java.util.List;
import kiteshop.controller.HoofdController;
import kiteshop.daos.AccountDaoSql;
import kiteshop.daos.DaoFactorySql;
import kiteshop.daos.KlantDaoSql;
import kiteshop.pojos.Account;
import kiteshop.pojos.Klant;

/**
 *
 * @author julia
 */
public class CheckConnection {
    public static void main(String[] args) {
       
        
		new HoofdController(new DaoFactorySql()).startMenuBestellingen();
    }
}
