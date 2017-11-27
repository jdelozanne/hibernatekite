/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.controller;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import kiteshop.View.*;
import org.springframework.stereotype.Component;

/**
 *
 * @author julia en steef
 */
@Component
public class HoofdController {

    @PersistenceContext(unitName = "hibertest")
    public EntityManagerFactory entityManagerFactory;

    //public HoofdController(EntityManagerFactory entityManagerFactory) {
    //    this.entityManagerFactory = entityManagerFactory;
   // }

    public void start() {
        InlogMenu inlogMenu = new InlogMenu(new AccountController(entityManagerFactory));
        boolean inlogSuccesfull = inlogMenu.start();

        if (inlogSuccesfull) {
            HoofdMenu hoofdMenu = new HoofdMenu(this);
            hoofdMenu.start();
        } else {
            start();
        }
    }

    public void startMenuKlanten() {
        MenuKlanten menuklanten = new MenuKlanten(new KlantenController(entityManagerFactory));
        menuklanten.start();

    }

    public void startMenuProducten() {
        MenuProducten menuProducten = new MenuProducten(new ProductenController(entityManagerFactory));
        menuProducten.start();
    }

    public void startMenuBestellingen() {
        MenuBestellingen menuBestellingen = new MenuBestellingen(new BestellingenController(entityManagerFactory));
        menuBestellingen.start();
    }

    public void startMenuAccounts() {
        MenuAccounts menuAccounts = new MenuAccounts(new AccountController(entityManagerFactory));
        menuAccounts.start();
    }

}
