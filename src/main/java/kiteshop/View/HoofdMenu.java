/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.View;

import static kiteshop.View.Validator.isValidInt;

import java.util.Scanner;
import java.util.logging.Logger;

import kiteshop.controller.HoofdController;
import kiteshop.pojos.Account;
import kiteshop.test.ProjectLog;

/**
 *
 * @author julia
 */
public class HoofdMenu {
	private final Logger logger = ProjectLog.getLogger();
	private Scanner input = new Scanner(System.in);
    

    
    HoofdController controller;
    
    public HoofdMenu(HoofdController hoofdController) {
		controller = hoofdController;
	}

	public void start() {
        System.out.println("Maak uw keuze:");
        System.out.println("Kies 1 Klanten");
        System.out.println("Kies 2 Producten");
        System.out.println("Kies 3 Bestellingen");
        System.out.println("Kies 4 Account");
        System.out.println("Kies 5 Wisselen Database");
        System.out.println("Kies 6 Uitloggen");
        int keuze = vraagInteger();

        
        switch (keuze) {
            case 1:
                System.out.println("naar menu klanten");
                controller.startMenuKlanten();
                System.out.println("U bent terug in het startmenu");
                start();
                break;
            case 2:
                System.out.println("naar menu producten");
                controller.startMenuProducten();
                System.out.println("U bent terug in het startmenu");
                start();
                break;
            case 3:
                System.out.println("naar menu bestellingen");
                controller.startMenuBestellingen();
                System.out.println("U bent terug in het startmenu");
                start();
                break;
            case 4:
                System.out.println("naar menu account");
                controller.startMenuAccounts();
                System.out.println("U bent terug in het startmenu");
                start();
                break;
            case 5:
                vraagWisselDatabase();
                
                start();
                break;
            case 6:
                System.out.println("uitloggen");
                this.uitloggen();
                break;
            default:
                System.out.println("Probeer opnieuw");
                start();
        }
    }

	private void vraagWisselDatabase() {
		System.out.println("Naar welke database wilt u veranderen");
		System.out.println("Kies 1 voor MySQL");
		System.out.println("Kies 2 voor Mongo");

		int keuze = vraagInteger();
		controller.setDatabase(keuze);
		System.out.println("De database is veranderd naar "+  controller.DaoFactory+ " u gaat nu terug naar het hoofdmenu");

	}

	public void uitloggen() {
        System.out.println("Weet u zeker dat u wilt afsluiten? J/N");
        if (input.nextLine().equalsIgnoreCase("j")) {
            System.exit(0);
        } else {
            start();
        }
    }
    
    

    private int vraagInteger() {
		String integer = null;
		while(!isValidInt(integer)){
			System.out.println("geef nummer: ");
			integer = input.nextLine();
			if(!isValidInt(integer)){
				System.out.println("Dit is geen nummer, probeer opnieuw");
			}
		}
		return Integer.parseInt(integer);
	}
  
}
