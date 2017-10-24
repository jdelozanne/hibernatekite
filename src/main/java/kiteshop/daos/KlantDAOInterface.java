package kiteshop.daos;

import java.util.List;

import kiteshop.pojos.Klant;

public interface KlantDAOInterface {

    void createKlant(Klant klant);

    Klant readKlantById(int id);
    
    void updateKlant(Klant klant);

    void deleteKlant(Klant klant);

    List<Klant> readAllKlanten();

    List<Klant> readKlantByAchternaam(String a);
}
