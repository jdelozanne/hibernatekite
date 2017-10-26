/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.test;

import java.util.List;
import kiteshop.controller.HoofdController;
import kiteshop.daos.AccountDaoSql;
import kiteshop.daos.BestelRegelDaoSql;
import kiteshop.daos.BestellingDaoSql;
import kiteshop.daos.DaoFactorySql;
import kiteshop.daos.KlantDaoSql;
import kiteshop.pojos.Account;
import kiteshop.pojos.BestelRegel;
import kiteshop.pojos.Bestelling;
import kiteshop.pojos.Klant;

/**
 *
 * @author julia
 */
public class CheckConnection {
    public static void main(String[] args) {
       
        
		//new HoofdController(new DaoFactorySql()).startMenuBestellingen();
                System.out.println(new BestellingDaoSql().readBestellingByBestellingID(4));
                /*List<BestelRegel> bestelregels = new BestelRegelDaoSql().readBestelRegelsByBestelling(b);
                for(Bestelling x : k){
                    System.out.println(x.toString());

                }*/
    }
}
