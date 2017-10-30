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
import kiteshop.daos.mysql.AccountDaoSql;
import kiteshop.pojos.Account;

/**
 *
 * @author julia
 */
public class InlogMenu {
    private final Logger logger = ProjectLog.getLogger();
    private Scanner input = new Scanner(System.in);

    AccountController controller;
    
    public InlogMenu(AccountController controller) {
		this.controller = controller;
	}    

    public boolean inloggen() {
        System.out.println("Geef uw gebruikersnaam: ");
        String user = input.nextLine();

        System.out.println("Geef uw wachtwoord: ");
        String ww = input.nextLine();

        logger.info("Gebruikersnaam en wachtwoord ingegeven");
        if (controller.checkLogin(user, ww)) {
            return true;
        } else {
            System.out.println("Onjuiste gegevens, probeer opnieuw");
            return false;
        }
    }




	
    
}
