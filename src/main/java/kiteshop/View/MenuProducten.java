/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.View;

import static kiteshop.utilities.Validator.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

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
		System.out.println("Kies 1 voor Nieuw product toevoegen");
		System.out.println("Kies 2 voor Een product wijzigen");
		System.out.println("Kies 3 voor Een product verwijderen");
		System.out.println("Kies 4 voor Overzicht producten");
		System.out.println("Kies 5 voor Terug naar Hoofdmenu");
		int keuze = vraagInteger();
		switch (keuze) {
		case 1:
			createProduct();
			start();
			break;
		case 2:
			updateProduct();
			System.out.println("Wilt u nog een product wijzigen? J/N");
			if (input.nextLine().equalsIgnoreCase("J")) {
				updateProduct();
			} 
			start();
			break;
		case 3:
			deleteProduct();
			System.out.println("Wilt u nog een product verwijderen? J/N");
			if (input.nextLine().equalsIgnoreCase("J")) {
				deleteProduct();
				start();
			} 
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

		System.out.println("geef voorraad: ");
		int voorraad = vraagInteger();
		product.setVoorraad(voorraad);

		System.out.println("geef de prijs met de decimalen door een punt gescheiden:  ");
		BigDecimal prijs = vraagBigDecimal();
		product.setPrijs(prijs);

		controller.createProduct(product);
	}

	public void showProducten() {
		controller.showProducten();
	}

	public void updateProduct() {
		Product p = pickRightProduct();
		if (p != null) {
			System.out.println("Wat wilt u aanpassen aan dit product?");
			System.out.println("Kies 1 voor de productnaam");
			System.out.println("Kies 2 voor de voorraad");
			System.out.println("Kies 3 voor de prijs");
			int keuze = vraagInteger();

			switch (keuze) {
			case 1:
				System.out.println("Geef de nieuwe productnaam: ");
				p.setNaam(input.nextLine());
				controller.updateProduct(p);
				break;
			case 2:
				System.out.println("Geef de nieuwe voorraad: ");
				p.setVoorraad(vraagInteger());
				controller.updateProduct(p);
				break;
			case 3:
				System.out.println("Geef de nieuwe prijs: ");
				BigDecimal prijs = vraagBigDecimal();
				p.setPrijs(prijs);
				controller.updateProduct(p);
				break;
			}
		} 
	}


	public void deleteProduct() {
		Product p = pickRightProduct();
		if (p != null) {
			controller.deleteProduct(p);
		}
	}

	private Product pickRightProduct() {
		Scanner input = new Scanner(System.in);
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
			int choosenIndex = vraagIntegerMinMax(1,producten.size()+1);
			product = producten.get(choosenIndex -1);
			
		}
		return product;
	}
}
