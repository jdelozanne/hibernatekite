/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.daos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import kiteshop.View.MenuAccounts;
import kiteshop.pojos.Account;
import kiteshop.pojos.Adres;
import kiteshop.pojos.AdresType;
import kiteshop.pojos.Klant;
import kiteshop.pojos.Product;

/**
 *
 * @author julia
 */
public class Test {

    public static void main(String[] args) {

        KlantDAOInterface m = new KlantDAOMongo();

        Klant klanttobecreated = new Klant();
        klanttobecreated.setKlantID(5);
        klanttobecreated.setVoornaam("Julia");
        klanttobecreated.setAchternaam("Pelgrom");
        klanttobecreated.setEmail("stevey@hotmail.com");
        klanttobecreated.setTelefoonnummer("06-56847965");
        Adres bezoekAdres = new Adres();
        bezoekAdres.setStraatnaam("wagenaarstraat");
        bezoekAdres.setHuisnummer(67);
        bezoekAdres.setToevoeging("g");
        bezoekAdres.setPostcode("1093EA");
        bezoekAdres.setWoonplaats("amsterdam");
        bezoekAdres.setAdresType(AdresType.BEZOEKADRES);
        Adres factuurAdres = new Adres();
        factuurAdres.setStraatnaam("wagenaarstraat");
        factuurAdres.setHuisnummer(10);
        factuurAdres.setToevoeging("a");
        factuurAdres.setPostcode("1054AP");
        factuurAdres.setWoonplaats("Groningen");
        factuurAdres.setAdresType(AdresType.FACTUURADRES);
        klanttobecreated.setBezoekAdres(bezoekAdres);
        klanttobecreated.setFactuurAdres(factuurAdres);

        m.createKlant(klanttobecreated);

        List<Klant> klanten = m.readAllKlanten();
        for (Klant k : klanten) {
            System.out.println(k.toString());
        }
    }

}
