/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.controller;

import kiteshop.View.*;

import kiteshop.daos.*;

/**
 *
 * @author julia
 */
public class HoofdController {

     public DaoFactoryInterface DaoFactory;

	public HoofdController(DaoFactoryInterface daoFactory) {
		DaoFactory = daoFactory;
	}

	public void start() {
		InlogMenu inlogMenu = new InlogMenu(new AccountController(DaoFactory.createAccountDao()));
		boolean inlogSuccesfull = inlogMenu.inloggen();
		
		if(inlogSuccesfull){
			HoofdMenu hoofdMenu = new HoofdMenu(this);
			hoofdMenu.start();
		} else {
			start();
		}
	}

	public void startMenuKlanten() {
		MenuKlanten menuklanten = new MenuKlanten(new KlantenController(DaoFactory.createKlantDao()));
		menuklanten.start();
		
	}

	public void startMenuProducten() {
		MenuProducten menuProducten = new MenuProducten(new ProductenController(DaoFactory.createProductDao()));
		menuProducten.start();
	}

	public void startMenuBestellingen() {
		MenuBestellingen menuBestellingen = new MenuBestellingen(new BestellingenController(DaoFactory.createBestellingDao(), DaoFactory.createBestelregelDao()));
		menuBestellingen.start();
	}

	public void startMenuAccounts() {
		MenuAccounts menuAccounts = new MenuAccounts(new AccountController(DaoFactory.createAccountDao()));
		menuAccounts.start();
	}
    
}
