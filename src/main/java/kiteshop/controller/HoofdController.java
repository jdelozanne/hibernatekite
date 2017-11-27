/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.controller;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import kiteshop.View.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author julia en steef
 */


@Component
public class HoofdController {


	@Autowired InlogMenu inlogMenu;
	@Autowired MenuKlanten menuklanten;
	@Autowired MenuProducten menuProducten;
	@Autowired MenuAccounts menuAccounts;
	@Autowired MenuBestellingen menuBestellingen;
	@Autowired HoofdMenu hoofdMenu;
	

	@PersistenceUnit
	public EntityManagerFactory entityManagerFactory;

	public HoofdController(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public void start() {
	boolean inlogSuccesfull = inlogMenu.start();
		if (inlogSuccesfull) {
			hoofdMenu.start();
		} else {
			start();
		}
	}

	public void startMenuKlanten() {
		menuklanten.start();
	}

	public void startMenuProducten() {
		menuProducten.start();
	}

	public void startMenuBestellingen() {
		menuBestellingen.start();
	}

	public void startMenuAccounts() {
		menuAccounts.start();
	}

}
