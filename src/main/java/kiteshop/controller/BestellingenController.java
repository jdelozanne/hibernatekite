package kiteshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;

import kiteshop.View.MenuProducten;
import kiteshop.pojos.*;
import kiteshop.utilities.ProjectLog;
import kiteshop.daos.*;
import hibernate.*;

public class BestellingenController {

    private final Logger logger = ProjectLog.getLogger();

    
    AbstractDao bestellingDAO;
    AbstractDao bestelRegelDAO;
    AbstractDao productDAO;
    AbstractDao klantDao; 
    String tableForNameSearch = "achternaam";
    String tableForNameProductSearch = "naam";
    String tableForIdSearch = "klant_klantID";

	public BestellingenController(EntityManagerFactory entityManagerFactory) {
		bestellingDAO = new ConcreteDao(Bestelling.class, entityManagerFactory);
		bestelRegelDAO = new ConcreteDao(BestelRegel.class, entityManagerFactory);
		productDAO = new ConcreteDao(Product.class, entityManagerFactory);
		klantDao = new ConcreteDao(Klant.class, entityManagerFactory);
	}

	
	

	//Bestelling functies
	public void createBestelling(Bestelling bestelling) {
		
		for(BestelRegel br: bestelling.getBestelling()){
			bestelRegelDAO.create(br);
		}
                bestellingDAO.create(bestelling);
		adjustVoorraad(bestelling);
        logger.info("nieuwe bestelling gemaakt");
    }

    public void updateBestelling(int id) {
        bestellingDAO.readById(id);
    }
//aanpassen
    public List<Bestelling> getBestellingByKlantID(int klantID) {
        return bestellingDAO.readByForeignkey(tableForIdSearch, klantID);
    }

    public List<Bestelling> showBestellingen() {
        List<Bestelling> bestellingen = bestellingDAO.readAll();
        
        return bestellingen;
    }

    public void deleteBestelling(int bestellingId) {
        bestellingDAO.delete(bestellingId);
    }

    public void displayBestelling(List<Bestelling> lijst) {
        for (Bestelling b : lijst) {
            System.out.println(b);
        }
    }

  //Bestelregel functies
   
    
    
    
    public void updateBestelregel(BestelRegel bestelregel){
    	bestelRegelDAO.update(bestelregel);
    }
    
    //NIET-bestellingdao functies
    public List<Klant> showKlantenAchternaam(String achternaam) {
        return klantDao.readByName(this.tableForNameSearch, achternaam);
    }
    public List<Product> showProductByName(String productnaam) {
        List<Product> producten = productDAO.readByName(this.tableForNameProductSearch, productnaam);
        return producten;
    }
    
    private void adjustVoorraad(Bestelling bestelling){
    	for(BestelRegel bestelregel : bestelling.getBestelling()){
    		Product betreffendeProduct = bestelregel.getProduct();
    		int aantalProductenBesteld = bestelregel.getAantal();
    		int productVoorraadInDatabase = ((Product) productDAO.readById(betreffendeProduct.getProductID())).getVoorraad(); //ik haal het product opnieuw op uit de database, om de actuele voorraad te weten, anders kan een een andere bestelregel die alvast hebben aangepast
    		int nieuweVoorraad = productVoorraadInDatabase - aantalProductenBesteld;
    		betreffendeProduct.setVoorraad(nieuweVoorraad);
    		productDAO.update(betreffendeProduct);
                if(aantalProductenBesteld > productVoorraadInDatabase){
                    System.out.println("LET OP: " + betreffendeProduct.getNaam() + " is momenteel niet voorradig in dit aantal. U kunt dit product bijbestellen."
                            + "\n Van dit product zijn maximaal " + productVoorraadInDatabase + " stuks direct leverbaar.");
                }else if(aantalProductenBesteld == productVoorraadInDatabase){
                    System.out.println("De voorraad van " + betreffendeProduct.getNaam() + " is nu nul.\n Bestel bij om de voorraad aan te vullen.");
                }
    		
    	}
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
