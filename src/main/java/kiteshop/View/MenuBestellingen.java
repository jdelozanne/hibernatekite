
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
import static kiteshop.utilities.Validator.vraagInteger;
import static kiteshop.utilities.Validator.vraagIntegerMinMax;

import kiteshop.controller.BestellingenController;
import kiteshop.daos.mysql.KlantDaoSql;
import kiteshop.daos.mysql.ProductDaoSql;
import kiteshop.pojos.*;
import kiteshop.utilities.ProjectLog;

/**
 *
 * @author julia
 */
public class MenuBestellingen {

	private final Logger logger = ProjectLog.getLogger();
	private Scanner input = new Scanner(System.in);

	BestellingenController controller;

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
		int keuze = vraagInteger();

		switch (keuze) {
		case 1:
			Klant Klant = firstPickRightKlant();
			if(Klant!=null){
				createBestelling( Klant);
			}
			start();
			break;
		case 2:
			Klant choosenKlant = firstPickRightKlant();
			if(choosenKlant!=null){
				Bestelling choosenBestelling = pickRightBestelling(choosenKlant);
				showBestelregels(choosenBestelling);
			}
			start();
			break;
		case 3:
			Klant klantBestelling = firstPickRightKlant();
			if(klantBestelling!=null){
			Bestelling bestellingToDelete = pickRightBestelling(klantBestelling);
			deleteBestelling(bestellingToDelete);
			}
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

	public void createBestelling(Klant klant ) {
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
		Product p = pickRightProduct();

		System.out.println("Hoeveel stuks wilt u van dit specifieke product toevoegen?");
		int aantal = vraagInteger();

		BestelRegel b = new BestelRegel(bestelling, p, aantal);
		return b;
	}

	public void deleteBestelling(Bestelling b) {
		controller.deleteBestelling(b.getBestellingID());
		System.out.println("bestelling verwijderd");
	}

	public void showBestellingen() {
		List<Bestelling> lijst = controller.showBestellingen();
		for (Bestelling b : lijst) {
			System.out.println("Bestelling ID :"+b.getBestellingID()+ " voor klant "+b.getKlant().getVoornaam()+" "+b.getKlant().getAchternaam());
			System.out.println("Met de volgende bestelregels: ");
			for(BestelRegel br : b.getBestelling()){
				System.out.println("    -Productnaam "+br.getProduct().getNaam()+ " Aantal: "+br.getAantal());
			}
			System.out.println("");
		}

	}

	public Klant firstPickRightKlant() {
		Klant klant = null;

		System.out.println("Geef alstublieft de achternaam van de betreffende klant");
		List<Klant> searchResult = controller.showKlantenAchternaam(input.nextLine());
		if(searchResult.size()==0){
			System.out.println("Er zijn geen klanten gevonden, u gaat terug naar het bestellingen menu");
		} else if(searchResult.size()==1){
			klant= searchResult.get(0);
			System.out.println("Betreffende klant: "+ klant.getVoornaam() + " "+ klant.getAchternaam());
		} else {
			System.out.println("De volgende klanten zijn gevonden, geeft u alstublieft het nummer van de betreffende klant");
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

	public Bestelling pickRightBestelling(Klant choosenKlant) {
		List<Bestelling> bestellingen = controller.getBestellingByKlantID(choosenKlant.getKlantID());
		System.out.println("De volgende bestellingen zijn gevonden van deze klant, geeft u alstublieft het nummer van de bestelling die u wil wijzigen");
		for (int i = 0; i < bestellingen.size(); i++) {
			System.out.println(i + 1 + " " + bestellingen.get(i).bestellingToString());
		}
		Bestelling choosenBestelling = bestellingen.get(vraagInteger() - 1);
		return choosenBestelling;
	}

	public void showBestelregels(Bestelling b) {
		List<BestelRegel> bestelregels = controller.getBestelregelsByBestelling(b);
		System.out.println("De volgende bestelregels zijn gevonden,geeft u alstublieft het nummer van de bestelregel die u wil wijzigen");
		for (int i = 0; i < bestelregels.size(); i++) {
			System.out.println(i + 1 + " " + bestelregels.get(i).toString());
		}
		BestelRegel choosenBestelRegel = bestelregels.get(vraagInteger() - 1);
		updateBestelling(choosenBestelRegel, b);
	}

	public void updateBestelling(BestelRegel br, Bestelling b) {
		
		br.setBestelling(b);
		System.out.println("Wat wilt u veranderen, kies 1 voor product of kies 2 voor het aantal");
		int keuze = input.nextInt();
		input.nextLine();
		switch (keuze) {
		case 1:
			System.out.println("Geef de naam van het nieuwe product");
			String productnaam = input.nextLine();
			br.setProduct(pickRightProduct());
			controller.updateBestelregel(br);
			break;
		case 2:
			System.out.println("Geef het nieuwe aantal");
			int aantal = vraagInteger();
			br.setAantal(aantal);
			controller.updateBestelregel(br);
			break;
		}
		System.out.println("Wilt u nog een verandering doorvoeren binnen deze regel? Kies 1");
		System.out.println("Wilt u een andere regel veranderen? Kies 2");
		System.out.println("Bent u klaar? Kies 3");
		int keuzeVervolg = vraagInteger();

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
        private Product pickRightProduct() {
        Product product = null;
        System.out.println("Geef alstublieft de productnaam of type ");
        List<Product> producten = controller.showProductByName(input.nextLine());
        if (producten.size() == 0) {
            System.out.println("Er zijn geen producten gevonden, u gaat terug naar het productenmenu");
        } else {
            System.out.println("De volgende producten zijn gevonden, geeft u alstublieft het nummer van het correcte product");
            for (int i = 0; i < producten.size(); i++) {
                System.out.println(i + 1 + " " + producten.get(i));
            }
            int choosenIndex = vraagInteger();
            if ((producten.size() <= choosenIndex) && (0 < choosenIndex)) {
                product = producten.get(choosenIndex -1);
            } else {
                System.out.println("Dit gekozen nummer is onjuist, probeer opnieuw");
                pickRightProduct();
            }
        }
        return product;
    }
}

	

