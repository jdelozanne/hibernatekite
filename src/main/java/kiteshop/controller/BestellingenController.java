package kiteshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import kiteshop.View.MenuProducten;
import kiteshop.pojos.*;
import kiteshop.utilities.ProjectLog;
import kiteshop.daos.*;


public class BestellingenController {

    private final Logger logger = ProjectLog.getLogger();

    
    BestellingDaoInterface bestellingDAO;
    BestelRegelDaoInterface bestelRegelDAO;
    ProductDaoInterface productDAO;
    KlantDaoInterface klantDao;  

	public BestellingenController(BestellingDaoInterface bestellingDAO, BestelRegelDaoInterface bestelRegelDAO,
			ProductDaoInterface productDAO, KlantDaoInterface klantDao) {
		this.bestellingDAO = bestellingDAO;
		this.bestelRegelDAO = bestelRegelDAO;
		this.productDAO = productDAO;
		this.klantDao = klantDao;
	}

	
	//Bestelling functies
	public void createBestelling(Bestelling bestelling) {
        bestellingDAO.createBestelling(bestelling);
        createBestelRegels(bestelling);
        adjustVoorraad(bestelling);
        logger.info("nieuwe bestelling gemaakt");
    }

    public void updateBestelling(int id) {
        bestellingDAO.readBestellingByBestellingID(id);
    }

    public List<Bestelling> getBestellingByKlantID(int klantID) {
        return bestellingDAO.readBestellingByKlantID(klantID);
    }

    public List<Bestelling> showBestellingen() {
        List<Bestelling> bestellingen = bestellingDAO.readAllBestelling();
        for(Bestelling b:bestellingen){
        	ArrayList<BestelRegel> brList = (ArrayList<BestelRegel>) bestelRegelDAO.readBestelRegelsByBestelling(b);
        	b.setBestelling(brList);
        }
        return bestellingen;
    }

    public void deleteBestelling(int bestellingId) {
        bestellingDAO.deleteBestelling(bestellingId);
    }

    public void displayBestelling(List<Bestelling> lijst) {
        for (Bestelling b : lijst) {
            System.out.println(b);
        }
    }

  //Bestelregel functies
    public List<BestelRegel> getBestelregelsByBestelling(Bestelling bestelling) {
         return bestelRegelDAO.readBestelRegelsByBestelling(bestelling);
    }
    
    public void createBestelRegels(Bestelling bestelling) {
        List<BestelRegel> bestelregels = new ArrayList<>();
        bestelregels = bestelling.getBestelling();
        for (BestelRegel b : bestelregels) {
            bestelRegelDAO.createBestelRegel(b);
            logger.info("bestelregels gemaakt");
        }
    }
    
    public void updateBestelregel(BestelRegel bestelregel){
    	bestelRegelDAO.updateBestelRegel(bestelregel);
    }
    
    //NIET-bestellingdao functies
    public List<Klant> showKlantenAchternaam(String achternaam) {
        return klantDao.readKlantByAchternaam(achternaam);
    }
    public List<Product> showProductByName(String productnaam) {
        List<Product> producten = productDAO.readProductByName(productnaam);
        return producten;
    }
    
    private void adjustVoorraad(Bestelling bestelling){
    	for(BestelRegel bestelregel : bestelling.getBestelling()){
    		Product betreffendeProduct = bestelregel.getProduct();
    		int aantalProductenBesteld = bestelregel.getAantal();
    		int productVoorraadInDatabase = productDAO.readProductByID(betreffendeProduct.getProductID()).getVoorraad(); //ik haal het product opnieuw op uit de database, om de actuele voorraad te weten, anders kan een een andere bestelregel die alvast hebben aangepast
    		int nieuweVoorraad = productVoorraadInDatabase - aantalProductenBesteld;
    		betreffendeProduct.setVoorraad(nieuweVoorraad);
    		productDAO.updateProduct(betreffendeProduct);
                if(aantalProductenBesteld > productVoorraadInDatabase){
                    System.out.println("LET OP: " + betreffendeProduct.getNaam() + " is momenteel niet voorradig in dit aantal. U kunt dit product bijbestellen."
                            + "\n Van dit product zijn maximaal " + productVoorraadInDatabase + " stuks direct leverbaar.");
                }else if(aantalProductenBesteld == productVoorraadInDatabase){
                    System.out.println("De voorraad van " + betreffendeProduct.getNaam() + " is nu nul.\n Bestel bij om de voorraad aan te vullen.");
                }
    		
    	}
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
