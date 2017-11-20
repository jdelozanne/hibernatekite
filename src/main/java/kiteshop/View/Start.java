/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.View;

import javax.persistence.Persistence;

import kiteshop.controller.HoofdController;


/**
 *
 * @author Steef P
 */
public class Start {
  
    public static void main(String[] args) {
		new HoofdController(Persistence.createEntityManagerFactory("Hibernate")).start();
                

    }
    
    
}