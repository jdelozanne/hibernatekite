/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.View;

import java.util.Scanner;

import kiteshop.pojos.Account;

/**
 *
 * @author julia
 */
public class HoofdMenu {

    Scanner input = new Scanner(System.in);

    public void start() {
        System.out.println("Maak uw keuze:");
        System.out.println("Kies 1 Klanten");
        System.out.println("Kies 2 Producten");
        System.out.println("Kies 3 Bestellingen");
        System.out.println("Kies 4 Account");
        System.out.println("Kies 5 Uitloggen");
        int keuze = input.nextInt();
        input.nextLine();
        
        switch (keuze) {
            case 1:
                System.out.println("naar menu klanten");
                new MenuKlanten().start();
                System.out.println("U bent terug in het startmenu");
                start();
                break;
            case 2:
                System.out.println("naar menu producten");
                new MenuProducten().chooseDatabase();
                System.out.println("U bent terug in het startmenu");
                start();
                break;
            case 3:
                System.out.println("naar menu bestellingen");
                new MenuBestellingen().start();
                break;
            case 4:
                System.out.println("naar menu account");
                new MenuAccounts().start();
                break;
            case 5:
                System.out.println("uitloggen");
                this.uitloggen();
                break;
            default:
                System.out.println("Probeer opnieuw");
                start();
        }
    }

    public void uitloggen() {
        System.out.println("Weet u zeker dat u wilt afsluiten? J/N");
        if (input.nextLine().equalsIgnoreCase("j")) {
            System.exit(0);
        } else {
            start();
        }
    }

    public static void main(String[] args) {
        new HoofdMenu().start();
    }
}
