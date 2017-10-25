/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.View;

import static kiteshop.View.Validator.*;

import java.math.BigDecimal;
import java.util.Scanner;
import kiteshop.controller.HoofdController;

import kiteshop.controller.ProductenController;
import kiteshop.pojos.Product;

/**
 *
 * @author julia
 */
public class MenuProducten {
    Scanner input = new Scanner(System.in);
    ProductenController controller;

    public MenuProducten(ProductenController controller) {
		this.controller = controller;
	}
   

    public void start() {
        
        System.out.println("Kies wat je wilt doen:");
        System.out.println("Kies 1 voor Nieuwe product maken");
        System.out.println("Kies 2 voor Een product wijzigen");
        System.out.println("Kies 3 voor Een product verwijderen");
        System.out.println("Kies 4 voor Overzicht producten");
        System.out.println("Kies 5 voor terug naar Hoofdmenu");
        int keuze = input.nextInt();
        input.nextLine();
        switch (keuze) {
            case 1:
                createProduct();
                start();
                break;
            case 2:
                System.out.println("Geef alstublieft de naam van het product dat u wilt wijzigen: ");
                showSpecificProduct(input.nextLine());
                start();
                break;
            case 3:
                System.out.println("Geef alstublieft de naam van het product dat u wilt verwijderen: ");
                deleteProduct(input.nextLine());
                start();
                break;
            case 4:
                showProducten();
                start();
                break;
            case 5:
            	break;
            default:
                System.out.println("Probeer opnieuw");
                start();
        }
    }

    public void createProduct() {
        Product product = new Product();
        
        System.out.println("geef productnaam: ");
        
        String productnaam = input.nextLine();
        product.setNaam(productnaam);

        System.out.println("geef voorraad");
        int voorraad = vraagInteger();
        product.setVoorraad(voorraad);
        
        System.out.println("geef prijs ");
        BigDecimal prijs = input.nextBigDecimal();
        product.setPrijs(prijs);
        input.nextLine();

        
        controller.createProduct(product);
    }

    public void showProducten() {
        controller.showProducten();
    }

    public void showSpecificProduct(String productnaam) {
        Product p = controller.showSpecificProduct(productnaam);
        updateProduct(p);
    }

    public void updateProduct(Product p) {
        System.out.println("Wat wilt u aanpassen aan dit product?");
        System.out.println("Kies 1 voor de productnaam");
        System.out.println("Kies 2 voor de voorraad");
        System.out.println("Kies 3 voor de prijs");
        int keuze = input.nextInt();
        input.nextLine();
        
        switch (keuze) {
            case 1:
                System.out.println("Geef de nieuwe productnaam: ");
                p.setNaam(input.nextLine());
                break;
            case 2:
                System.out.println("Geef de nieuwe voorraad: ");
                p.setVoorraad(input.nextInt());
                input.nextLine();
                break;
            case 3:
                System.out.println("Geef de nieuwe prijs: ");
                p.setPrijs(input.nextBigDecimal());
                input.nextLine();
                break;
        }
        System.out.println(p.toString() + "\nWilt u nog iets aanpassen aan dit product? J/N");
        if (input.nextLine().equalsIgnoreCase("j")) {
            updateProduct(p);
        } else {
            controller.updateProduct(p);
        }
    }

    public void deleteProduct(String productnaam) {
        Product p = controller.showSpecificProduct(productnaam);
        controller.deleteProduct(p);
    }

	private int vraagInteger() {
		String integer = null;
		while(!isValidInt(integer)){
			integer = input.nextLine();
			if(!isValidInt(integer)){
				System.out.println("Dit is geen nummer, probeer opnieuw");
			}
		}
		return Integer.parseInt(integer);
	}
	
	private BigDecimal vraagBigDecimal() {
		String bigDecimal = null;
		while(!isValidBigDecimal(bigDecimal)){
			bigDecimal = input.nextLine();
			if(!isValidBigDecimal(bigDecimal)){
				System.out.println("Dit is geen geldige waarde voor een prijs, decimale getallen zijn toegestaan, met een punt als scheidingsteken, probeer opnieuw");
			}
		}
		return new BigDecimal(bigDecimal);
	}


	
}
