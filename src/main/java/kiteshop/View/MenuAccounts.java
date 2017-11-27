/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kiteshop.controller.AccountController;
import kiteshop.pojos.Account;
import kiteshop.pojos.Adres;
import kiteshop.pojos.AdresType;
import kiteshop.utilities.ProjectLog;

import static kiteshop.utilities.Validator.*;

/**
 *
 * @author julia en steef
 */

@Component
public class MenuAccounts {
	private final Logger logger = ProjectLog.getLogger();
	private Scanner input = new Scanner(System.in);
	
	@Autowired
	AccountController controller;

	public MenuAccounts(AccountController controller) {
		this.controller = controller;
	}

	public void start() {
		System.out.println("Kies wat u wilt doen:");
		System.out.println("Kies 1 voor nieuwe account maken");
		System.out.println("Kies 2 voor een overzicht van de bestaande accounts printen");
		System.out.println("Kies 3 voor een account wijzigen");
		System.out.println("Kies 4 voor Een account verwijderen");
		System.out.println("Kies 5 terug naar het hoofdmenu");
		int keuze = vraagInteger();
	
		switch (keuze) {
		case 1:
			maakNieuwAccount();
			start();
			
			break;
		case 2:
			printAllAccounts();
			System.out.println("U gaat terug naar het hoofdmenu");
			start();
			break;
		case 3:
			wijzigAccount();
			start();
			break;
		case 4:
			verwijderAccount();
			start();
			break;
		case 5:
			break;
		default:
			System.out.println("Probeer opnieuw");
			start();
		}
	}

	private void printAllAccounts() {
		List<Account> accounts = controller.readAllAccounts();
		for (Account account : accounts) {
			System.out.println(account);
		}
	}

	public void maakNieuwAccount() {
		Account account = new Account();
		System.out.println("Gebruikersnaam?");
		String gebruiker = input.nextLine();
		account.setGebruikersnaam(gebruiker);

		String wachtwoord = vraagWachtwoord();

		account.setWachtwoord(wachtwoord);

		controller.createAccount(account);
		System.out.println("Wilt u nog een account maken J/N");
		if (input.nextLine().equalsIgnoreCase("J")) {
			maakNieuwAccount();
		} 
	}



	public void maakNieuwWachtwoord(Account account) {
		System.out.println("Kies een wachtwoord minimaal 4 karakters en tenminste 1 cijfer");
		String ww = vraagWachtwoord();
		account.setWachtwoord(ww);
		System.out.println("Geef uw wachtwoord nogmaals en druk op enter");
		String wwControle = input.nextLine();
		if (wwControle.equals(account.getWachtwoord())) {
			System.out.println("Nieuw account succesvol aangemaakt");
		} else {
			System.out.println("Probeer opnieuw, de twee wachtwoorden komen niet overeen");
			maakNieuwWachtwoord(account);
		}
	}

	public void wijzigAccount() {
		System.out.println("Geef de gebruikersnaam van het account dat je wilt wijzigen?");
		String gebruikernaam = input.nextLine();
		Account account = controller.readAccountByGebruikersnaam(gebruikernaam);
		if (account.getAccountID() == 0) {
			System.out.println("Deze gebruikersnaam is niet bekend");
		} else {
			System.out.println("Het volgende account is gevonden: " + account);
			System.out.println("Wat wilt u doen");
			System.out.println("Kies 1 voor gebruikersnaam wijzigen");
			System.out.println("Kies 2 voor wachtwoord wijzigen");
			System.out.println("Kies 3 voor terug naar account menu");
			int keuze = input.nextInt();
			input.nextLine();

			switch (keuze) {
			case 1:
				System.out.println("geef nieuwe gebruikersnaam");
				String nieuwegebruikersnaam = input.nextLine();
				account.setGebruikersnaam(nieuwegebruikersnaam);
				controller.updateAccount(account);
				System.out.println("De gebruikersnaam is aangepast, het account is nu: " + account);
				break;
			case 2:
				String nieuwwachtwoord = vraagWachtwoord();
				account.setWachtwoord(nieuwwachtwoord);
				System.out.println("Het wachtwoord is aangepast, het account is nu: " + account);
				controller.updateAccount(account);
				break;
			case 3:
				start();
				break;
			default:
				System.out.println("Uw keuze was incorrect");
				wijzigAccount();
			}
		}
		System.out.println("Wilt u een ander account wijzigen J/N");
		if (input.nextLine().equalsIgnoreCase("J")) {
			wijzigAccount();
		}
	}

	public void verwijderAccount() {
		System.out.println("Geef de gebruikersnaam van het account dat je wilt verwijderen?");
		String gebruikernaam = input.nextLine();
		Account account = controller.readAccountByGebruikersnaam(gebruikernaam);
		if (account.getAccountID() == 0) {
			System.out.println("Deze gebruikersnaam is niet bekend");
		} else {
			System.out.println("Weet u zeker dat u de volgende klant wil verwijderen: " + account + " J/N");
			if (input.next().equalsIgnoreCase("J")) {
				controller.deleteAccount(account);
				System.out.println("De klant is verwijderd, u keert terug naar het menu accounts");
			} else {
				System.out.println("Het account is niet verwijderd, u keert terug naar het menu accounts");
			}
		}
	}
}
