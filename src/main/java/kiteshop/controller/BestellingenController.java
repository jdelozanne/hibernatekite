package kiteshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import kiteshop.daos.mysql.*;
import kiteshop.pojos.*;
import kiteshop.test.ProjectLog;
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
        
    
    public Product showSpecificProduct(String naam) {
        Product p = productDAO.readProduct(naam);
        return p;
    }
    
    private void adjustVoorraad(Bestelling bestelling){
    	for(BestelRegel bestelregel : bestelling.getBestelling()){
    		Product betreffendeproduct = bestelregel.getProduct();
    		int aantalProductenbesteld = bestelregel.getAantal();
    		int productVoorraadInDatabase = productDAO.readProductByID(betreffendeproduct.getProductID()).getVoorraad(); //ik haal het product opnieuw op uit de database, om de actuele voorraad te weten, anders kan een een andere bestelregel die alvast hebben aangepast
    		int nieuweVoorraad = productVoorraadInDatabase - aantalProductenbesteld;
    		betreffendeproduct.setVoorraad(nieuweVoorraad);
    		productDAO.updateProduct(betreffendeproduct);
    		
    	}
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
