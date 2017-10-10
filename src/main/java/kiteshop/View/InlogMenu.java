/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.View;

import java.util.Scanner;
import java.util.logging.Logger;

import kiteshop.test.ProjectLog;
import kiteshop.test.ProjectLog.*;

import kiteshop.controller.*;
import kiteshop.daos.AccountDAO;
import kiteshop.pojos.Account;

/**
 *
 * @author julia
 */
public class InlogMenu {
	
	private final Logger logger = ProjectLog.getLogger();
	
	
	AccountController controller = new AccountController();
	private Scanner input = new Scanner(System.in);


//inloggegevens opvragen, invoeren en controleren vanaf database? Dus al direct connectie maken?
    public void inloggen() {
    	
        System.out.println("Geef uw gebruikersnaam: ");
        String user = input.nextLine();
        
        System.out.println("Geef uw wachtwoord: ");
        String ww = input.nextLine();
        
        logger.info("Gebruikersnaam en wachtwoord ingegeven");
             
      
            if(controller.checkLogin(user, ww)){
        	new HoofdMenu().start();
        } else {
        	System.out.println("Onjuiste gegevens, probeer opnieuw");
            inloggen();
        }
        
       

    }
    
    public static void main (String args[]){
    	new InlogMenu().inloggen();
    }
}
  