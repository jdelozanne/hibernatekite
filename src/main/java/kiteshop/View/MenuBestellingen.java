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
import kiteshop.daos.*;

import kiteshop.pojos.*;
import kiteshop.test.ProjectLog;

/**
 *
 * @author julia
 */
public class MenuBestellingen {

    /**
     *
     * @author julia
     */
    private final Logger logger = ProjectLog.getLogger();

    private Scanner input = new Scanner(System.in);

    BestellingenController controller = new BestellingenController();
    BestelRegelController controller2 = new BestelRegelController();

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
                break;
            case 2:
                System.out.println("Geef het bestelID van de bestelling die u wilt wijzigen");
                int id = input.nextInt();
                showSpecificBestelling(id);
                break;
            case 3:
                System.out.println("Geef het bestelID van de bestelling die u wilt wijzigen");
                int id1 = input.nextInt();
                deleteBestelling(id1);
                break;
            case 4:
                showBestellingen();
                break;
            case 5:
                new HoofdMenu().start();
                break;
            case 6:
                System.out.println("Van welke klant is de bestelling die u wilt wijzigen? Geef de achternaam");
                String klantnaam = input.nextLine();
                firstPickRightKlant(klantnaam);
        }
    }

    public void createBestelling(String achternaam) {

        KlantDAO k = new KlantDAO();
        Klant klant = k.readKlant(achternaam);
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
        Product p = new ProductDAO().readProduct(productnaam);

        System.out.println("Hoeveel stuks wilt u van dit specifieke product toevoegen?");
        int aantal = input.nextInt();
        input.nextLine();

        BestelRegel b = new BestelRegel(bestelling, p, aantal);

        return b;
    }
    
    public void deleteBestelling(int id){
        controller.deleteBestelling(id);
        System.out.println("bestelling verwijderd");
    }

    public void showSpecificBestelling(int id) {
        controller.showSpecificBestelling(id);
    }
    
    public void showBestellingen(){
        controller.showBestellingen();
    }
    
    public void firstPickRightKlant(String klantachternaam){
        ArrayList<Klant> klanten = controller.getKlantByAchternaam(klantachternaam);
        System.out.println("De volgende klanten zijn gevonden, geeft u alstublieft het nummer van de klant die u wil wijzigen");
		for(int i = 0; i < klanten.size(); i++ ){
			System.out.println(i+1 +" "+ klanten.get(i).toString());
		}
		Klant choosenKlant = klanten.get(input.nextInt()-1);
                input.nextLine();
                thenPickRightBestelling(choosenKlant);
    }
    public void thenPickRightBestelling(Klant choosenKlant){
        ArrayList<Bestelling> bestellingen = controller.getBestellingByKlantID(choosenKlant.getKlantID());
        System.out.println("De volgende bestellingen zijn gevonden van deze klant, geeft u alstublieft het nummer van de bestelling die u wil wijzigen");
		for(int i = 0; i < bestellingen.size(); i++ ){
			System.out.println(i+1 +" "+ bestellingen.get(i).bestellingToString());
		}
		Bestelling choosenBestelling = bestellingen.get(input.nextInt()-1);
                showBestelregels(choosenBestelling);
                
    }
    public void showBestelregels(Bestelling b){
        ArrayList<BestelRegel> bestelregels = controller.getBestelregelsByBestelling(b);
        System.out.println("De volgende bestelregels zijn gevonden,geeft u alstublieft het nummer van de bestelregel die u wil wijzigen");
		for(int i = 0; i < bestelregels.size(); i++ ){
			System.out.println(i+1 +" "+ bestelregels.get(i).toString());
		}
        }
    }
    


