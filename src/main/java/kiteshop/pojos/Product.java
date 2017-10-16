/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.pojos;

import java.math.BigDecimal;

/**
 *
 * @author julia
 */
public class Product {

    private int productID;
    private String naam;
    private BigDecimal prijs;
    private int voorraad;

    public Product() {
    }
    
    public Product(String naam) {
        this.naam = naam;
    }

    public int getProductID() {
        return productID;
    }
    
    public void setProductID(int productID){
        this.productID = productID;
    }   

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public void setPrijs(BigDecimal prijs) {
        this.prijs = prijs;
    }

    public int getVoorraad() {
        return voorraad;
    }

    public void setVoorraad(int voorraad) {
        this.voorraad = voorraad;
    }
    
    @Override
    public String toString() {
        String productDisplay ="ProductID: " + this.getProductID()+ " Product: " + this.getNaam() + " Prijs: " + this.getPrijs() + " Voorraad: " + this.getVoorraad();
        return productDisplay;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		result = prime * result + ((prijs == null) ? 0 : prijs.hashCode());
		result = prime * result + productID;
		result = prime * result + voorraad;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		if (prijs == null) {
			if (other.prijs != null)
				return false;
		} else if (!prijs.equals(other.prijs))
			return false;
		if (productID != other.productID)
			return false;
		if (voorraad != other.voorraad)
			return false;
		return true;
	}
}
