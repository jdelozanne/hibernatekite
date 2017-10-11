package kiteshop.controller;

import java.util.ArrayList;
import java.util.logging.Logger;

import kiteshop.daos.KlantDAO;
import kiteshop.daos.KlantDAOInterface;
import kiteshop.pojos.Klant;
import kiteshop.test.ProjectLog;

public class KlantenController {

    private final Logger logger = ProjectLog.getLogger();

    private KlantDAOInterface klantDAO;

    public KlantenController() {
        klantDAO = new KlantDAO();
    }

    public void createKlant(Klant klant) {
        logger.info("Klant " + klant + " wordt toegevoegd aan database");
        klantDAO.createKlant(klant);
    }

    public Klant readKlanten(String achternaam) {
        
        return klantDAO.readKlant(achternaam);
    }

    public ArrayList<Klant> showKlantenAchternaam(String achternaam) {
        return klantDAO.readSelectedKlantenAchternaam(achternaam);
    }

    public void updateKlant(Klant klant) {
        logger.info("Updating " + klant);
        klantDAO.updateKlant(klant);
    }

    public void deleteKlant(Klant klant) {
        logger.info("Deleting " + klant);
        klantDAO.deleteKlant(klant);
    }
    
    public ArrayList<Klant> showAllKlanten(){
    	
        return klantDAO.readAllKlanten();
    }

}
