package kiteshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import kiteshop.daos.mysql.KlantDaoSql;
import kiteshop.pojos.Klant;
import kiteshop.test.ProjectLog;
import kiteshop.daos.KlantDaoInterface;

public class KlantenController {

    private final Logger logger = ProjectLog.getLogger();
    private KlantDaoInterface klantDAO;

    public KlantenController(KlantDaoInterface klantDAO) {
        this.klantDAO = klantDAO;
    }

    public void createKlant(Klant klant) {
        logger.info("Klant " + klant + " wordt toegevoegd aan database");
        klantDAO.createKlant(klant);
    }

    public Klant readKlantenByID(int id) {
        return klantDAO.readKlantById(id);
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
