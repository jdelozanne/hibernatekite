/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.View;

import static java.lang.System.currentTimeMillis;
import java.util.Calendar;
import java.util.Date;
import static kiteshop.View.Validator.isValidInt;
import static kiteshop.View.Validator.isValidWachtwoord;

import java.util.Scanner;
import java.util.TimeZone;
import java.util.UUID;
import java.util.logging.Logger;
import static kiteshop.View.Validator.vraagInteger;
import static kiteshop.View.Validator.vraagWachtwoord;

import kiteshop.test.ProjectLog;
import kiteshop.test.ProjectLog.*;

import kiteshop.controller.*;
import kiteshop.daos.mysql.AccountDaoSql;
import kiteshop.pojos.Account;
import static kiteshop.test.PaswordHasher.createHashedPassword;
import static kiteshop.test.PaswordHasher.createHashedToken;
import static kiteshop.test.PaswordHasher.createSaltHex;

/**
 *
 * @author julia
 */
public class InlogMenu {

    private final Logger logger = ProjectLog.getLogger();
    private Scanner input = new Scanner(System.in);
    AccountController controller;
    private static String token;
    private static final long timeLimit = 3600000;

    public InlogMenu(AccountController controller) {
        this.controller = controller;
    }

    public boolean start() {
        System.out.println("Welkom bij de Kiteshop");
        System.out.println("Kies 1 als u een nieuwe account wil aanmaken");
        System.out.println("Kies 2 als u wilt inloggen");
        System.out.println("Kies 3 als u wilt afsluiten");
        int keuze = vraagInteger();

        boolean inlogsucces = false;

        if (keuze == 1) {
            maakNieuwAccount();
            System.out.println("U kunt nu inloggen met uw nieuwe account");
            inlogsucces = inloggen();
        } else if (keuze == 2) {
            inlogsucces = inloggen();
        } else if (keuze == 3) {
            System.out.println("Afsluiten..");
            System.exit(0);
        } else {
            System.out.println("Dit is geen geldige keuze");
        }

        return inlogsucces;
    }

    public boolean inloggen() {

        System.out.println("Geef uw gebruikersnaam: ");
        String user = input.nextLine();

        //tijdlimiet controleren
        if (getToken() != null) {
            String timeLogin = token.split(":")[0];
            if (getTime() - Long.valueOf(timeLogin) < timeLimit) {
                if (createHashedToken(user).equals(getToken().split(":")[1])) {
                    return true;
                }
            }
        }

        System.out.println("Geef uw wachtwoord: ");
        String ww = input.nextLine();

        if (controller.accountExists(user) && controller.checkLogin(user, ww)) {
            //maak een token nadat de gegevens zijn gecontroleerd
            saveToken(user);
            return true;
        } else {
            System.out.println("Onjuiste gegevens, probeer opnieuw");
            return false;
        }

    }

    public void maakNieuwAccount() {
        Account account = new Account();

        System.out.println("Gebruikersnaam?");
        String gebruiker = input.nextLine();
        if (!controller.accountExists(gebruiker)) {

            account.setGebruikersnaam(gebruiker);

            String wachtwoord = vraagWachtwoord();

            account.setWachtwoord(wachtwoord);

            controller.createAccount(account);
        } else {
            System.out.println("Deze gebruiker bestaat al, probeer opnieuw");
            maakNieuwAccount();
        }

    }

    private String vraagWachtwoord() {
        String wachtwoord = null;
        while (!isValidWachtwoord(wachtwoord)) {
            System.out.println("Wachtwoord?");
            wachtwoord = input.nextLine();
            if (!isValidWachtwoord(wachtwoord)) {
                System.out.println("Dit is geen geldig wachtwoord, een wachtwoord bestaat uit minimaal 4 tekens en bevat minstens 1 cijfer");
            }
        }
        return wachtwoord;
    }

    public String getToken() {
        return token;
    }

    public void saveToken(String username) {
        //creeer een hash van de gebruikersnaam, plak erna de huidige tijd in milliseconde ervoor.
        //de tijd wordt gebruikt om een tijdlimiet te controleren.
        String hashedToken = createHashedToken(username);
        token = getTime() + ":" + hashedToken;
    }

    public static long getTime() {
        long time = currentTimeMillis();
        return time;

    }

}
