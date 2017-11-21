/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.View;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import kiteshop.controller.BestellingenController;
import kiteshop.controller.HoofdController;
import kiteshop.controller.KlantenController;
import kiteshop.controller.ProductenController;
import setupdatabase.PeopleFactory;


/**
 *
 * @author Steef P
 */
public class Start {
  
    public static void main(String[] args) {
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibertest");
    	
    	PeopleFactory.createPeople(20, emf);
    	PeopleFactory.createProducts(emf);
    	
    	//MenuBestellingen menuBestellingen = new MenuBestellingen(new BestellingenController(emf));
		//menuBestellingen.start();
    	//MenuKlanten menuklanten = new MenuKlanten(new KlantenController(Persistence.createEntityManagerFactory("hibertest")));
	//	menuklanten.start();
    	
    	//MenuProducten menuProducten = new MenuProducten(new ProductenController(Persistence.createEntityManagerFactory("hibertest")));
	//menuProducten.start();
	new HoofdController(emf).start();
                

    }
    
    
}