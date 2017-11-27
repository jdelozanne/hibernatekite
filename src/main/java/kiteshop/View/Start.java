/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.View;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import kiteshop.controller.BestellingenController;
import kiteshop.controller.HoofdController;
import kiteshop.controller.KlantenController;
import kiteshop.controller.ProductenController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import setupdatabase.PeopleFactory;


/**
 *
 * @author Steef P
 */
@Configuration
@ComponentScan({"kiteshop.View", "kiteshop.controller", "kiteshop.utilities" })
public class Start {
  
   // @Autowired
    //HoofdController hoofdcontroller;
    
    public static void main(String[] args) {
    	
	//new HoofdController(emf).start();
        ApplicationContext context = new AnnotationConfigApplicationContext(Start.class);
        HoofdController hoofdcontroller = context.getBean(HoofdController.class);

       // Start start = context.getBean(Start.class);
        hoofdcontroller.start();        

    }
    
    
}