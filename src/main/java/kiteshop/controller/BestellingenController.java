package kiteshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import kiteshop.daos.mysql.BestelRegelDaoSql;
import kiteshop.daos.mysql.BestellingDaoSql;
import kiteshop.daos.mysql.KlantDaoSql;
import kiteshop.daos.mysql.ProductDaoSql;
import kiteshop.pojos.BestelRegel;
import kiteshop.pojos.Bestelling;
import kiteshop.pojos.Klant;
import kiteshop.pojos.Product;
import kiteshop.test.ProjectLog;
import kiteshop.daos.BestelRegelDaoInterface;
import kiteshop.daos.BestellingDaoInterface;
import kiteshop.daos.ProductDaoInterface;

public class BestellingenController {

    private final Logger logger = ProjectLog.getLogger();

    BestellingDaoInterface bestellingDAO;
    BestelRegelDaoInterface bestelRegelDAO;
    ProductDaoInterface productDAO;

    

      
	public BestellingenController(BestellingDaoInterface bestellingDAO, BestelRegelDaoInterface bestelRegelDAO,
			ProductDaoInterface productDAO) {
		this.bestellingDAO = bestellingDAO;
		this.bestelRegelDAO = bestelRegelDAO;
		this.productDAO = productDAO;
	}

	public void createBestelling(Bestelling bestelling) {
        bestellingDAO.createBestelling(bestelling);
        createBestelRegels(bestelling);
        adjustVoorraad(bestelling);
        logger.info("nieuwe bestelling gemaakt");
    }

    public void createBestelRegels(Bestelling bestelling) {
        List<BestelRegel> bestelregels = new ArrayList<>();
        bestelregels = bestelling.getBestelling();
        for (BestelRegel b : bestelregels) {
            bestelRegelDAO.createBestelRegel(b);
            logger.info("bestelregels gemaakt");
        }
    }

    public void updateBestelling(int id) {
        bestellingDAO.readBestellingByBestellingID(id);
    }

    public List<Klant> getKlantByAchternaam(String klantachternaam) {
        KlantDaoSql klantdao = new KlantDaoSql();
        return klantdao.readKlantByAchternaam(klantachternaam);
    }

    public List<Bestelling> getBestellingByKlantID(int klantID) {
        return bestellingDAO.readBestellingByKlantID(klantID);
    }

    public void showBestellingen() {
        List<Bestelling> bestellingen = bestellingDAO.readAllBestelling();
        displayBestelling(bestellingen);
    }

    public void deleteBestelling(int bestellingId) {
        bestellingDAO.deleteBestelling(bestellingId);
    }

    public void displayBestelling(List<Bestelling> lijst) {
        for (Bestelling b : lijst) {
            System.out.println(b.bestellingToString());
        }
    }

    public List<BestelRegel> getBestelregelsByBestelling(Bestelling bestelling) {
        BestelRegelDaoSql besteldao = new BestelRegelDaoSql();
        return besteldao.readBestelRegelsByBestelling(bestelling);
    }
    
    private void adjustVoorraad(Bestelling bestelling){
    	for(BestelRegel bestelregel : bestelling.getBestelling()){
    		Product betreffendeproduct = bestelregel.getProduct();
    		int aantalProductenbesteld = bestelregel.getAantal();
    		int productVoorraadInDatabase = productDAO.readProductByID(betreffendeproduct.getProductID()).getVoorraad(); //ik haal het product opnieuw uit de database, om de actuele voorraad te weten, anders kan een een andere bestelregel die alvast hebben aangepast
    		int nieuweVoorraad = productVoorraadInDatabase - aantalProductenbesteld;
    		betreffendeproduct.setVoorraad(nieuweVoorraad);
    		productDAO.updateProduct(betreffendeproduct);
    		
    	}
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
