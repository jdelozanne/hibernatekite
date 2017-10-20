package kiteshop.controller;

import java.util.ArrayList;
import java.util.List;
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

    public List<Klant> showKlantenAchternaam(String achternaam) {
        return klantDAO.readKlantByAchternaam(achternaam);
    }

    public void updateKlant(Klant klant) {
        logger.info("Updating " + klant);
        klantDAO.updateKlant(klant);
    }

    public void deleteKlant(Klant klant) {
        logger.info("Deleting " + klant);
        klantDAO.deleteKlant(klant);
    }

    public List<Klant> showAllKlanten() {
        return klantDAO.readAllKlanten();
    }
}
