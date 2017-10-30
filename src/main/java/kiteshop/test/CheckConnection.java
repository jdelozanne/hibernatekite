/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.test;

import java.util.List;
import kiteshop.controller.HoofdController;
import kiteshop.daos.mongodb.DaoFactoryMongo;
import kiteshop.daos.mysql.AccountDaoSql;
import kiteshop.daos.mysql.BestelRegelDaoSql;
import kiteshop.daos.mysql.BestellingDaoSql;
import kiteshop.daos.mysql.DaoFactorySql;
import kiteshop.daos.mysql.KlantDaoSql;
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
       
        
	new HoofdController(new DaoFactoryMongo()).startMenuBestellingen();
                
               // Bestelling b = new BestellingDaoSql().readBestellingByBestellingID(5);
               // System.out.println(b.bestellingToString());
                //for(Bestelling x : be){
                //    System.out.println(x.bestellingToString());

                }
    }

