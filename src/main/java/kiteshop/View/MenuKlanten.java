/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.View;

import kiteshop.controller.KlantenController;
import kiteshop.pojos.*;
import kiteshop.test.ProjectLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import static kiteshop.View.Validator.*;
/**
 *
 * @author julia en steef
 */
public class MenuKlanten {
	private final Logger logger = ProjectLog.getLogger();
	private Scanner input = new Scanner(System.in);

	KlantenController controller;

	public MenuKlanten(KlantenController controller) {
		this.controller = controller;
	}

	public void start() {
		System.out.println("Kies wat je wilt doen:");
		System.out.println("Kies 1 voor Nieuwe klant maken");
		System.out.println("Kies 2 voor Klantgegevens wijzigen");
		System.out.println("Kies 3 voor Klantgegevens verwijderen");
		System.out.println("Kies 4 voor Overzicht klanten");
		System.out.println("Kies 5 voor Overzicht klanten, gezocht op achternaam");
		System.out.println("Kies 6 voor terug naar Startscherm");
		int keuze = vraagInteger();

		switch (keuze) {
		case 1:
			createKlant();
			System.out.println("Wilt u nog een nieuwe klant maken J/N");
			if (input.nextLine().equalsIgnoreCase("J")) {
				createKlant();
			} else {
				start();
			}
			break;
		case 2:
			klantWijzigenAchternaam();
			System.out.println("Wilt u nog een klant wijzigen J/N");
			if (input.nextLine().equalsIgnoreCase("J")) {
				klantWijzigenAchternaam();
			} else {
				start();
			}
			break;
		case 3:
			klantVerwijderenAchterNaam();
			System.out.println("Wilt u nog een een klant verwijderen J/N");
			if (input.nextLine().equalsIgnoreCase("J")) {
				klantVerwijderenAchterNaam();
				start();
			} else {
				start();
			}
			break;
		case 4:
			showKlanten();
			start();
			break;
		case 5:
			readKlantenByAchternaam();
			start();
			break;
		case 6:
			break;
			// hiermee komt de methode ten einde, en valt het programma vanzelf terug naar waar hij in het hoofdmenu gebleven was
		default:
			System.out.println("Probeer opnieuw");
			start();
		}
	}

	private void createKlant() {
		Klant klant = new Klant();

		System.out.println("geef voornaam: ");
		String voornaam = input.nextLine();
		klant.setVoornaam(voornaam);

		System.out.println("geef tussenvoegsel: ");
		String tussenvoegsel = input.nextLine();
		klant.setTussenvoegsel(tussenvoegsel);

		System.out.println("geef achternaam: ");
		String achternaam = input.nextLine();
		klant.setAchternaam(achternaam);

		String email = vraagEmail();
		klant.setEmail(email);

		String telefoonnummer = vraagTelefoonnummer();
		klant.setTelefoonnummer(telefoonnummer);

		System.out.println("Nu wordt het bezoekadres gevraagd");
		Adres bezoekadres = vraagAdres();
		bezoekadres.setAdresType(AdresType.BEZOEKADRES);
		klant.setBezoekAdres(bezoekadres);

		System.out.println("Wilt u een apart factuuradres toevoegen? J/N");
		if (input.next().equalsIgnoreCase("J")) {
			System.out.println("Nu wordt het factuuradres gevraagd");
			Adres factuurAdres = vraagAdres();
			factuurAdres.setAdresType(AdresType.FACTUURADRES);
			klant.setFactuurAdres(factuurAdres);
		}
		controller.createKlant(klant);
	}





	public void showKlanten() {
		for (Klant klant : controller.showAllKlanten()) {
			System.out.println(klant);
		}
	}

	public void readKlantenByAchternaam() {
		System.out.println("Geef alstublieft de achternaam van de klant die u wilt zien?");
		List<Klant> searchResult = controller.showKlantenAchternaam(input.nextLine());

		for (Klant klant : searchResult) {
			System.out.println(klant);
		}
	}

	private void klantWijzigenAchternaam() {
		Klant choosenKlant = bepaalJuisteKlant();

		if(choosenKlant!= null){

			System.out.println("Wat wilt u wijzigen van de klant? ");
			System.out.println("Kies 1 voor Naam");
			System.out.println("Kies 2 voor E-mail");
			System.out.println("Kies 3 voor telefoonnummer");
			System.out.println("Kies 4 voor bezoekadres");
			System.out.println("Kies 5 voor factuuradres");
			System.out.println("Kies 6 voor terug naar MenuKlanten");
			int keuze = vraagInteger();

			switch (keuze) {
			case 1:
				System.out.println("geef voornaam: ");
				String voornaam = input.nextLine();
				choosenKlant.setVoornaam(voornaam);

				System.out.println("geef tussenvoegsel: ");
				String tussenvoegsel = input.nextLine();
				choosenKlant.setTussenvoegsel(tussenvoegsel);

				System.out.println("geef achternaam: ");
				String achternaam = input.nextLine();
				choosenKlant.setAchternaam(achternaam);
				System.out.println("De naam van de klant is aangepast, klant heeft nu de volgende gegevens: " + choosenKlant);
				break;
			case 2:
				String email = vraagEmail();
				choosenKlant.setEmail(email);
				break;
			case 3:

				String telefoonnr = vraagTelefoonnummer();
				choosenKlant.setTelefoonnummer(telefoonnr);
				break;
			case 4:
				System.out.println("Nu wordt het bezoekadres gevraagd");
				Adres bezoekadres = vraagAdres();
				bezoekadres.setAdresType(AdresType.BEZOEKADRES);
				choosenKlant.setBezoekAdres(bezoekadres);
				System.out.println(choosenKlant);
				break;
			case 5:
				System.out.println("Nu wordt het factuuradres gevraagd");
				Adres factuurAdres = vraagAdres();
				factuurAdres.setAdresType(AdresType.FACTUURADRES);
				choosenKlant.setFactuurAdres(factuurAdres);
				break;
			case 6:
				start();
				break;
			default:
				System.out.println("Uw keuze was incorrect");
				klantWijzigenAchternaam();
			}
			controller.updateKlant(choosenKlant);
		}
	}

	private void klantVerwijderenAchterNaam() {
		Klant choosenKlant = bepaalJuisteKlant();

		if(choosenKlant!= null){
			System.out.println("Weet u zeker dat u de volgende klant wil verwijderen: " + choosenKlant + "J/N");
			if (input.next().equalsIgnoreCase("J")) {
				controller.deleteKlant(choosenKlant);
			} else {
				System.out.println("De klant is niet verwijderd");
			}
		}
	}


	private Klant bepaalJuisteKlant(){
		Klant klant = null;

		System.out.println("Geef alstublieft de achternaam van de betreffende klant?");
		List<Klant> searchResult = controller.showKlantenAchternaam(input.nextLine());
		if(searchResult.size()==0){
			System.out.println("Er zijn geen klanten gevonden, u gaat terug naar het klantenmenu");
		} else {
			System.out.println("De volgende klanten zijn gevonden, geeft u alstublieft het nummer van de klant die u wil verwijderen");
			for (int i = 0; i < searchResult.size(); i++) {
				System.out.println(i + 1 + " " + searchResult.get(i));
			}
			int minimumKeuzewaarde = 1;
			int maximumKeuzewaarde = searchResult.size();
			int choosenIndex = vraagIntegerMinMax(minimumKeuzewaarde,maximumKeuzewaarde)-1;
			klant = searchResult.get(choosenIndex);
		}
		return klant;
	}

	public void printKlanten() {
		ArrayList<Klant> klantenlijst = new ArrayList<>();
		for (Klant element : klantenlijst) {
			System.out.println(element + "\n");
		}
	}
}
