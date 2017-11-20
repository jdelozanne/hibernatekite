/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.pojos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 *
 * @author julia
 */

@Entity
public class Bestelling implements Serializable {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
    private int bestellingID;
	
	@ManyToOne
    private Klant klant;
	
	@JoinColumn
	@OneToMany
    private List<BestelRegel> bestelregels;
    
	private BigDecimal totaalprijs;
    
    public Bestelling() {
        bestelregels = new ArrayList<>();
    }

    public Bestelling(Klant klant) {
        bestelregels = new ArrayList<>();
        this.klant = klant;
    }

    public int getBestellingID() {
        return bestellingID;
    }

    public void setBestellingID(int bestellingID) {
        this.bestellingID = bestellingID;
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    public List<BestelRegel> getBestelling() {
        return bestelregels;
    }

    public void setBestelling(ArrayList<BestelRegel> bestelling) {
        this.bestelregels = bestelling;
    }

    public BigDecimal getTotaalprijs() {
        return totaalprijs;
    }

    public void setTotaalprijs(BigDecimal totaalprijs) {
        this.totaalprijs = totaalprijs;
    }

    public void addBestelRegel(BestelRegel b) {
        bestelregels.add(b);
    }

    

    public String bestellingToString() {
        String bestelling = "BestellingID = " + bestellingID + " KlantID = " + getKlant().getKlantID() + " Totaalbedrag = " + totaalprijs;
        return bestelling;
    }
    
    public BigDecimal calculatePrijs(List<BestelRegel> bestelregels) {
        totaalprijs = new BigDecimal(0);
        for (BestelRegel b : bestelregels) {
            BigDecimal temp = BigDecimal.valueOf(b.getAantal()).multiply(b.getProduct().getPrijs());
            totaalprijs = totaalprijs.add(temp);
        }
        return totaalprijs;
    }

	@Override
	public String toString() {
		return "Bestelling [bestellingID=" + bestellingID + ", klant=" + klant + ", bestelregels=" + bestelregels
				+ ", totaalprijs=" + totaalprijs + "]";
	}
}
