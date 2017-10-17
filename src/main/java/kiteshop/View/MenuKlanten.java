/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.View;

import kiteshop.controller.KlantenController;
import kiteshop.pojos.*;
import java.util.ArrayList;
import java.util.Scanner;
import static kiteshop.test.Validator.*;
/**
 *
 * @author julia fgf test tset test
 */
public class MenuKlanten {

    Scanner input = new Scanner(System.in);
    KlantenController controller = new KlantenController();

    public void start() {
        System.out.println("Kies wat je wilt doen:");
        System.out.println("Kies 1 voor Nieuwe klant maken");
        System.out.println("Kies 2 voor Klantgegevens wijzigen");
        System.out.println("Kies 3 voor Klantgegevens verwijderen");
        System.out.println("Kies 4 voor Overzicht klanten");
        System.out.println("Kies 5 voor Overzicht klanten, gezocht op achternaam");
        System.out.println("Kies 6 voor terug naar Startscherm");
        int keuze = input.nextInt();
input.nextLine();
        
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

        String email = null;
        while(!isValidEmail(email)){
        	System.out.println("geef emailadres: ");
        	email = input.nextLine();
        	if(isValidEmail(email)){
        		klant.setEmail(email);
        	} else{
        		System.out.println("Dit is geen geldig emailadres, probeer opnieuw");
        	}
        }


        System.out.println("geef emailadres: ");
        String email = input.nextLine();

        klant.setEmail(email);


        String telefoonnr = null;
        while(!isValidPnoneNumber(telefoonnr)){
        	System.out.println("geef telefoonnummer: ");
        	telefoonnr = input.nextLine();
        	if(isValidPnoneNumber(telefoonnr)){

        		klant.setTelefoonnummer(telefoonnr);
        	} else {
        		System.out.println("Dit is geen geldig telefoonnummer, probeer opnieuw");
        	}
        }
        System.out.println("Nu wordt het bezoekadres gevraagd");
        Adres bezoekadres = createAdres();
        bezoekadres.setAdresType(AdresType.BEZOEKADRES);
        klant.setBezoekAdres(bezoekadres);

        System.out.println("Wilt u een apart factuuradres toevoegen? J/N");
        if (input.next().equalsIgnoreCase("J")) {
            System.out.println("Nu wordt het factuuradres gevraagd");
            Adres factuurAdres = createAdres();
            factuurAdres.setAdresType(AdresType.FACTUURADRES);
            klant.setFactuurAdres(factuurAdres);
        }
        controller.createKlant(klant);
    }

    private Adres createAdres() {
        Adres adres = new Adres();
        System.out.println("geef plaats: ");
        String woonplaats = input.nextLine();
        adres.setWoonplaats(woonplaats);

        System.out.println("geef postcode: ");
        String postcode = input.nextLine();
        adres.setPostcode(postcode);

        System.out.println("geef straatnaam: ");
        String straatnaam = input.nextLine();
        adres.setStraatnaam(straatnaam);

        int huisnummer = geefHuisnummer();  // aparte methode van gemaakt om te checken voor int en evt te herhalen
        adres.setHuisnummer(huisnummer);

        System.out.println("geef toevoeging: ");
        String toevoeging = input.nextLine();
        adres.setToevoeging(toevoeging);
        return adres;
    }

    public void showKlanten() {
        for (Klant klant : controller.showAllKlanten()) {
            System.out.println(klant);
        }
    }

    public void readKlantenByAchternaam() {
        System.out.println("Geef alstublieft de achternaam van de klant die u wilt zien?");
        ArrayList<Klant> searchResult = controller.showKlantenAchternaam(input.nextLine());

        for (Klant klant : searchResult) {
            System.out.println(klant);
        }
    }

    private void klantWijzigenAchternaam() {
        System.out.println("Geef alstublieft de achternaam van de klant die u wilt wijzigen?");
        ArrayList<Klant> searchResult = controller.showKlantenAchternaam(input.nextLine());

        System.out.println("De volgende klanten zijn gevonden, geeft u alstublieft het nummer van de klant die u wil wijzigen");
        for (int i = 0; i < searchResult.size(); i++) {
            System.out.println(i + 1 + " " + searchResult.get(i));
        }
        Klant choosenKlant = searchResult.get(input.nextInt() - 1);

        System.out.println("Wat wilt u wijzigen van de klant? ");
        System.out.println("Kies 1 voor Naam");
        System.out.println("Kies 2 voor E-mail");
        System.out.println("Kies 3 voor telefoonnummer");
        System.out.println("Kies 4 voor bezoekadres");
        System.out.println("Kies 5 voor factuuradres");
        System.out.println("Kies 6 voor terug naar MenuKlanten");
        int keuze = input.nextInt();

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
                System.out.println("geef emailadres: ");
                String email = input.nextLine();
                choosenKlant.setEmail(email);
                break;
            case 3:
                System.out.println("geef telefoonnummer: ");
                String telefoonnr = input.nextLine();
                choosenKlant.setTelefoonnummer(telefoonnr);
                break;
            case 4:
                System.out.println("Nu wordt het bezoekadres gevraagd");
                Adres bezoekadres = createAdres();
                bezoekadres.setAdresType(AdresType.BEZOEKADRES);
                choosenKlant.setBezoekAdres(bezoekadres);
                System.out.println(choosenKlant);
                break;
            case 5:
                System.out.println("Nu wordt het factuuradres gevraagd");
                Adres factuurAdres = createAdres();
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

    private void klantVerwijderenAchterNaam() {
        System.out.println("Geef alstublieft de achternaam van de klant die u wilt verwijderen?");
        ArrayList<Klant> searchResult = controller.showKlantenAchternaam(input.nextLine());

        System.out.println("De volgende klanten zijn gevonden, geeft u alstublieft het nummer van de klant die u wil verwijderen");
        for (int i = 0; i < searchResult.size(); i++) {
            // arraylist begint op 0, maar ik laat de klant kiezen vanaf 1, moet later wel weer terugrekenen
            System.out.println(i + 1 + " " + searchResult.get(i));
        }
        Klant choosenKlant = searchResult.get(input.nextInt() - 1);
        System.out.println("Weet u zeker dat u de volgende klant wil verwijderen: " + choosenKlant + "J/N");
        if (input.next().equalsIgnoreCase("J")) {
            controller.deleteKlant(choosenKlant);
        } else {
            System.out.println("De klant is niet verwijderd");
        }
    }

    private int geefHuisnummer() {
        int huisnummer = 0;
        System.out.println("geef huisnummer: ");  // ik begreep uit een tutorial dat je next int en next niet doorelkaar moet gebruiken, tevens , next intwacht niet, vandaar deze oplossing
        String tempHuisnummer = input.nextLine();
        try {
            huisnummer = Integer.parseInt(tempHuisnummer);
        } catch (NumberFormatException e) {
            System.out.println("Je hebt geen nummer in getoetst, probeer opnieuw");
            geefHuisnummer();
        }
        return huisnummer;
    }

    public void printKlanten() {
        ArrayList<Klant> klantenlijst = new ArrayList<>();
        for (Klant element : klantenlijst) {
            System.out.println(element + "\n");
        }
    }

    public static void main(String args[]) {
        new MenuKlanten().start();
    }
}
