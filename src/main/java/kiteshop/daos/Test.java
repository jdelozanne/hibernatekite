/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.daos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import kiteshop.View.MenuAccounts;
import kiteshop.pojos.Account;
import kiteshop.pojos.Product;

/**
 *
 * @author julia
 */
public class Test {

    public static void main(String[] args) {
       Product a = new Product();
                
                a.setNaam("kite");
                a.setVoorraad(5);
                a.setPrijs(new BigDecimal(990.10));
                
        ProductDAOInterface m = new ProductDAOMongo();
        
        System.out.println(m.readProductByID(3));
        

    }

}
