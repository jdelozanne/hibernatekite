/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.View;

import static kiteshop.View.Validator.isValidInt;
import static kiteshop.View.Validator.isValidWachtwoord;

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


	public boolean start() {
		System.out.println("Welkom bij de Kiteshop");
		System.out.println("Kies 1 als u een nieuwe account wil aanmaken");
		System.out.println("Kies 2 als u wilt inloggen");
		int keuze = vraagInteger();

		boolean inlogsucces =  false;

		if (keuze==1){
			maakNieuwAccount();
			System.out.println("U kunt nu inloggen met uw nieuwe account");
			inlogsucces = inloggen();
		} else if (keuze==2){
			inlogsucces = inloggen();
		} else {
			System.out.println("Dit is geen geldige keuze");
		}

		return inlogsucces;
	}

	public boolean inloggen() {

		System.out.println("Geef uw gebruikersnaam: ");
		String user = input.nextLine();

		System.out.println("Geef uw wachtwoord: ");
		String ww = input.nextLine();

		if (controller.accountExists(user) && controller.checkLogin(user, ww)) {
			return true;
		}
		else {
			System.out.println("Onjuiste gegevens, probeer opnieuw");
			return false;
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


	public void maakNieuwAccount() {
		Account account = new Account();
		
		System.out.println("Gebruikersnaam?");
		String gebruiker = input.nextLine();
		if(!controller.accountExists(gebruiker)){
			
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
		while(!isValidWachtwoord(wachtwoord)){
			System.out.println("Wachtwoord?");
			wachtwoord = input.nextLine();
			if(!isValidWachtwoord(wachtwoord)){
				System.out.println("Dit is geen geldig wachtwoord, een wachtwoord bestaat uit minimaal 4 tekens en bevat minstens 1 cijfer");	
			}
		} 
		return wachtwoord;
	}




}
