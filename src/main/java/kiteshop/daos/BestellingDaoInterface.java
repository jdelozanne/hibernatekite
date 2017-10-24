package kiteshop.daos;

import java.util.ArrayList;
import java.util.List;
import kiteshop.pojos.Bestelling;

public interface BestellingDAOInterface {

    void createBestelling(Bestelling bestelling);

    Bestelling readBestellingByBestellingID(int bestellingID);

    void updateBestelling(Bestelling bestelling);

    void deleteBestelling(int bestellingID);

    List<Bestelling> readBestellingByKlantID(int klantID);

    List<Bestelling> readAllBestelling();
}
