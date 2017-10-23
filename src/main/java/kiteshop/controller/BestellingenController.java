package kiteshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import kiteshop.daos.BestelRegelDAO;
import kiteshop.daos.BestelRegelDAOInterface;
import kiteshop.daos.BestellingDAO;

import kiteshop.daos.BestellingDAOInterface;
import kiteshop.daos.KlantDAO;
import kiteshop.daos.ProductDAO;
import kiteshop.pojos.BestelRegel;
import kiteshop.pojos.Bestelling;
import kiteshop.pojos.Klant;
import kiteshop.pojos.Product;
import kiteshop.test.ProjectLog;

public class BestellingenController {

    private final Logger logger = ProjectLog.getLogger();

    BestellingDAOInterface bestellingDAO;
    BestelRegelDAOInterface bestelRegelDAO;

    public BestellingenController() {
        bestellingDAO = new BestellingDAO();
        bestelRegelDAO = new BestelRegelDAO();
    }

    public void createBestelling(Bestelling bestelling) {
        bestellingDAO.createBestelling(bestelling);
        createBestelRegels(bestelling);
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
        KlantDAO klantdao = new KlantDAO();
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
        BestelRegelDAO besteldao = new BestelRegelDAO();
        return besteldao.readBestelRegelsByBestelling(bestelling);
    }
}
