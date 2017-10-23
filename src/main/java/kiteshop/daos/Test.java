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
import kiteshop.pojos.BestelRegel;
import kiteshop.pojos.Bestelling;
import kiteshop.pojos.Klant;
import kiteshop.pojos.Product;

/**
 *
 * @author julia
 */
public class Test {

    public static void main(String[] args) {

        BestellingDAOInterface m = new BestellingDAOMongo();

     /*   Klant klanttobecreated = new Klant();
        klanttobecreated.setKlantID(5);
        klanttobecreated.setVoornaam("Julia");
        klanttobecreated.setAchternaam("Lozanne");
        klanttobecreated.setEmail("jdl@hotmail.com");
        klanttobecreated.setTelefoonnummer("0676847965");
        Adres bezoekAdres = new Adres();
        bezoekAdres.setStraatnaam("wagenaarstraat");
        bezoekAdres.setHuisnummer(67);
        bezoekAdres.setToevoeging("g");
        bezoekAdres.setPostcode("1093EA");
        bezoekAdres.setWoonplaats("wageningen");
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
*/
    Klant k = new Klant();
    k.setKlantID(5);
     Bestelling b = new Bestelling();
     b.setBestellingID(6);
     b.setTotaalprijs(new BigDecimal(5000.99));
     b.setKlant(k);
     m.updateBestelling(b);
     
     
    // Bestelling x =  m.readBestellingByBestellingID(4);
    //    System.out.println(x.getBestellingID() + " " + x.getTotaalprijs());
    
   
        }
    
    }


