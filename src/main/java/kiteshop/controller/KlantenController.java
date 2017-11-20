package kiteshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;

import kiteshop.pojos.*;
import kiteshop.utilities.ProjectLog;
import kiteshop.daos.KlantDaoInterface;
import hibernate.*;

public class KlantenController {

    private final Logger logger = ProjectLog.getLogger();
    private AbstractDao klantDAO;
    private AbstractDao adresDAO;
    String tableForNameSearch = "achternaam";

    public KlantenController(EntityManagerFactory entityManagerFactory) {
    	this.klantDAO = new ConcreteDao(Klant.class, entityManagerFactory);
    	this.adresDAO = new ConcreteDao(Adres.class, entityManagerFactory);
    }

    public void createKlant(Klant klant) {
        logger.info("Klant " + klant + " wordt toegevoegd aan database");
        adresDAO.create(klant.getBezoekAdres());
        if(klant.getFactuurAdres()!=null){
        	 adresDAO.create(klant.getFactuurAdres());
        }
        
        klantDAO.create(klant);
    }

    public Klant readKlantenByID(int id) {
        return (Klant) klantDAO.readById(id);
    }

    public List<Klant> showKlantenAchternaam(String achternaam) {
        return klantDAO.readByName(this.tableForNameSearch,achternaam);
    }

    public void updateKlant(Klant klant) {
        logger.info("Updating " + klant);
        klantDAO.update(klant);
    }

    public void deleteKlant(Klant klant) {
        logger.info("Deleting " + klant);
        klantDAO.delete(klant);
    }

    public List<Klant> showAllKlanten() {
        return klantDAO.readAll();
    }
}
