
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.View;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;


import kiteshop.controller.BestelRegelController;
import kiteshop.controller.BestellingenController;
import kiteshop.daos.KlantDaoSql;
import kiteshop.daos.ProductDaoSql;
import kiteshop.pojos.*;
import kiteshop.test.ProjectLog;

/**
 *
 * @author julia
 */
public class MenuBestellingen {

    private final Logger logger = ProjectLog.getLogger();
    private Scanner input = new Scanner(System.in);
    
    BestellingenController controller = new BestellingenController();
    
    public MenuBestellingen(BestellingenController controller) {
		this.controller = controller;
	}

	public void start() {
        System.out.println("Kies wat je wilt doen:");
        System.out.println("Kies 1 voor een nieuwe bestelling maken");
        System.out.println("Kies 2 voor een bestelling wijzigen");
        System.out.println("Kies 3 voor een bestelling verwijderen");
        System.out.println("Kies 4 voor een overzicht van bestellingen");
        System.out.println("Kies 5 om terug te keren naar het hoofdmenu");
        System.out.println("Kies 6 voor bestelling wijzigen");
        int keuze = input.nextInt();
        input.nextLine();

        switch (keuze) {
            case 1:
                System.out.println("Voor welke klant is deze bestelling? Geef de achternaam");
                String achternaam = input.nextLine();
                createBestelling(achternaam);
                start();
                break;
            case 2:
                System.out.println("Van welke klant is de bestelling die u wilt wijzigen? Geef de achternaam");
                String klantnaam = input.nextLine();
                Klant choosenKlant = firstPickRightKlant(klantnaam);
                Bestelling choosenBestelling = pickRightBestelling(choosenKlant);
                showBestelregels(choosenBestelling);
                start();
                break;
            case 3:
                System.out.println("Van welke klant is de bestelling die u wilt wijzigen? Geef de achternaam");
                String naam = input.nextLine();
                Klant klantBestelling = firstPickRightKlant(naam);
                Bestelling bestellingToDelete = pickRightBestelling(klantBestelling);
                deleteBestelling(bestellingToDelete);
                start();
                break;
            case 4:
                showBestellingen();
                start();
                break;
            case 5:

                break;
            default:
                System.out.println("Probeer opnieuw");
                start();
        }
    }

    public void createBestelling(String achternaam) {
        KlantDaoSql k = new KlantDaoSql();
        Klant klant = k.readKlantByAchternaam(achternaam).get(0);
        Bestelling bestelling = new Bestelling(klant);

        System.out.println("Wilt u iets toevoegen aan de bestelling? J/N");

        String antwoord = input.nextLine();
        while (antwoord.equalsIgnoreCase("J")) {
            BestelRegel bestelRegelToBeAdded = createBestelRegel(bestelling);
            bestelling.addBestelRegel(bestelRegelToBeAdded);
            System.out.println("Wilt u iets toevoegen aan de bestelling? J/N");
            antwoord = input.nextLine();
        }
        System.out.println("totaalprijs van de bestelling: EURO " + bestelling.calculatePrijs(bestelling.getBestelling()));

        controller.createBestelling(bestelling);
        logger.info("bereken de prijs");
    }

    public BestelRegel createBestelRegel(Bestelling bestelling) {
        System.out.println("Welk product wilt u toevoegen aan de bestelling");
        String productnaam = input.nextLine();
        Product p = new ProductDaoSql().readProduct(productnaam);

        System.out.println("Hoeveel stuks wilt u van dit specifieke product toevoegen?");
        int aantal = input.nextInt();
        input.nextLine();

        BestelRegel b = new BestelRegel(bestelling, p, aantal);
        return b;
    }

    public void deleteBestelling(Bestelling b) {
        controller.deleteBestelling(b.getBestellingID());
        System.out.println("bestelling verwijderd");
    }

    public void showBestellingen() {
        controller.showBestellingen();
    }

    public Klant firstPickRightKlant(String klantachternaam) {
        List<Klant> klanten = controller.getKlantByAchternaam(klantachternaam);
        System.out.println("De volgende klanten zijn gevonden, geeft u alstublieft het nummer van de klant die u wil wijzigen");
        for (int i = 0; i < klanten.size(); i++) {
            System.out.println(i + 1 + " " + klanten.get(i).toString());
        }
        Klant choosenKlant = klanten.get(input.nextInt() - 1);
        input.nextLine();
        return choosenKlant;
    }

    public Bestelling pickRightBestelling(Klant choosenKlant) {
        List<Bestelling> bestellingen = controller.getBestellingByKlantID(choosenKlant.getKlantID());
        System.out.println("De volgende bestellingen zijn gevonden van deze klant, geeft u alstublieft het nummer van de bestelling die u wil wijzigen");
        for (int i = 0; i < bestellingen.size(); i++) {
            System.out.println(i + 1 + " " + bestellingen.get(i).bestellingToString());
        }
        Bestelling choosenBestelling = bestellingen.get(input.nextInt() - 1);
        return choosenBestelling;
    }

    public void showBestelregels(Bestelling b) {
        List<BestelRegel> bestelregels = controller.getBestelregelsByBestelling(b);
        System.out.println("De volgende bestelregels zijn gevonden,geeft u alstublieft het nummer van de bestelregel die u wil wijzigen");
        for (int i = 0; i < bestelregels.size(); i++) {
            System.out.println(i + 1 + " " + bestelregels.get(i).toString());
        }
        BestelRegel choosenBestelRegel = bestelregels.get(input.nextInt() - 1);
        updateBestelling(choosenBestelRegel, b);
    }

    public void updateBestelling(BestelRegel br, Bestelling b) {
        System.out.println("Wat wilt u veranderen, kies 1 voor product of kies 2 voor het aantal");
        int keuze = input.nextInt();
        input.nextLine();
        switch (keuze) {
            case 1:
                System.out.println("Geef de naam van het nieuwe product");
                String productnaam = input.nextLine();
                br.setProduct(new ProductDaoSql().readProduct(productnaam));
                break;
            case 2:
                System.out.println("Geef het nieuwe aantal");
                int aantal = input.nextInt();
                br.setAantal(aantal);
                break;
        }
        System.out.println("Wilt u nog een verandering doorvoeren binnen deze regel? Kies 1");
        System.out.println("Wilt u een andere regel veranderen? Kies 2");
        System.out.println("Bent u klaar? Kies 3");
        int keuzeVervolg = input.nextInt();
        
        switch (keuzeVervolg) {
            case 1:
                updateBestelling(br, b);
                break;
            case 2:
                showBestelregels(b);
                break;
            case 3:
                System.out.println("bestelling is geupdate, terug naar Hoofdmenu");
                break;
        }
    }
}
