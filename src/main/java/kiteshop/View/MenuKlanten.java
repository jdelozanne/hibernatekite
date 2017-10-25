/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.View;

import kiteshop.controller.KlantenController;
import kiteshop.pojos.*;
import kiteshop.test.ProjectLog;

import static kiteshop.View.Validator.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
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
			/* geimplenteerd nu voor zoeken op achternaam (wellicht in de toekomst meer opties) omdat 
			 * er meerdere klanten kunnen zijn met dezelfde achternaam
			 */
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


	private Adres vraagAdres() {  //Simpele user Input die geen validatie betreft doet hij zelf, als er wel validatie is, is er een hulp methode gemaakt
		Adres adres = new Adres();
		System.out.println("geef plaats: ");
		String woonplaats = input.nextLine();
		adres.setWoonplaats(woonplaats);

		String postcode = vraagPostcode();
		adres.setPostcode(postcode);

		System.out.println("geef straatnaam: ");
		String straatnaam = input.nextLine();
		adres.setStraatnaam(straatnaam);

		int huisnummer = vraagHuisnummer();
		adres.setHuisnummer(huisnummer);

		String toevoeging = vraagToevoeging();
		adres.setToevoeging(toevoeging);

		return adres;
	}

	private String vraagPostcode() {
		String postcode = null;
		while(!isValidPostcode(postcode)){
			System.out.println("geef postcode: ");
			postcode = input.nextLine();
			if(!isValidPostcode(postcode)){
				System.out.println("Dit is geen geldige postcode, probeer opnieuw");
			}
		}
		return postcode;
	}

	private String vraagToevoeging() {
		String toevoeging = null;
		while(!isValidToevoeging(toevoeging)){
			System.out.println("geef toevoeging: ");
			toevoeging = input.nextLine();
			if(!isValidToevoeging(toevoeging)){
				System.out.println("Dit is geen geldige toevoeging, probeer opnieuw");
			}
		}
		return toevoeging;
	}

	private String vraagTelefoonnummer() {
		String telefoonnr = null;
		while(!isValidTelefoonnummer(telefoonnr)){
			System.out.println("geef telefoonnummer: ");
			telefoonnr = input.nextLine();
			if(!isValidTelefoonnummer(telefoonnr)){
				System.out.println("Dit is geen geldig telefoonnummer, probeer opnieuw");
			}
		}
		return telefoonnr;
	}

	private int vraagInteger() {
		String integer = null;
		while(!isValidInteger(integer)){
			System.out.println("geef nummer: ");
			integer = input.nextLine();
			if(!isValidInteger(integer)){
				System.out.println("Dit is geen nummer, probeer opnieuw");
			}
		}
		return Integer.parseInt(integer);
	}

	private int vraagIntegerMinMax(int min, int max) {
		String integer = null;
		while(!isValidInteger(integer)|| !isValidValue(integer, min,max)){
			System.out.println("geef nummer: ");
			integer = input.nextLine();
			if(!isValidInteger(integer)){
				System.out.println("Dit is geen nummer, probeer opnieuw");
			} else if(!isValidValue(integer, min,max)){
				System.out.println("Dit nummer behoort niet tot de mogelijkheden, probeer opnieuw");
			}
		}
		return Integer.parseInt(integer);
	}

	private String vraagEmail() {
		String email = null;
		while(!isValidEmail(email)){
			System.out.println("geef emailadres: ");
			email = input.nextLine();
			if(!isValidEmail(email)){
				System.out.println("Dit is geen geldig emailadres, probeer opnieuw");
			}
		}
		return email;
	}

	private int vraagHuisnummer() {
		int huisnummer = 0;
		System.out.println("geef huisnummer: ");  // ik begreep uit een tutorial dat je next int en next niet doorelkaar moet gebruiken, tevens , next intwacht niet, vandaar deze oplossing
		String tempHuisnummer = input.nextLine();
		try {
			huisnummer = Integer.parseInt(tempHuisnummer);
		} catch (NumberFormatException e) {
			System.out.println("Je hebt geen nummer in getoetst, probeer opnieuw");
			vraagHuisnummer();
		}
		return huisnummer;
	}

	public void printKlanten() {
		ArrayList<Klant> klantenlijst = new ArrayList<>();
		for (Klant element : klantenlijst) {
			System.out.println(element + "\n");
		}
	}

}
