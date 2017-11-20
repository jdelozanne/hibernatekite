/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.pojos;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author julia
 */
@Entity
public class Adres implements Serializable{
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	int id;

    private String woonplaats;
    private String postcode;
    private String straatnaam;
    private int huisnummer;
    private String toevoeging;
       
    
    private AdresType adresType;

    public Adres() {
    }

   
    public AdresType getAdresType() {
		return adresType;
	}

    public void setAdresType(AdresType adresType) {
		this.adresType = adresType;
	}

	public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStraatnaam() {
        return straatnaam;
    }

    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }

    public int getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(int huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getToevoeging() {
        return toevoeging;
    }

    public void setToevoeging(String toevoeging) {
        this.toevoeging = toevoeging;
    }


	@Override
	public String toString() {
		return "Adres [woonplaats=" + woonplaats + ", postcode=" + postcode + ", straatnaam=" + straatnaam
				+ ", huisnummer=" + huisnummer + ", toevoeging=" + toevoeging + ", adresType="  + "]";
	}

	//Benodigd voor het testen
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		//result = prime * result + ((adresType == null) ? 0 : adresType.hashCode());
		result = prime * result + huisnummer;
		result = prime * result + ((postcode == null) ? 0 : postcode.hashCode());
		result = prime * result + ((straatnaam == null) ? 0 : straatnaam.hashCode());
		result = prime * result + ((toevoeging == null) ? 0 : toevoeging.hashCode());
		result = prime * result + ((woonplaats == null) ? 0 : woonplaats.hashCode());
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
		Adres other = (Adres) obj;
		if (adresType != other.adresType)
			return false;
			
		if (huisnummer != other.huisnummer)
			return false;
		if (postcode == null) {
			if (other.postcode != null)
				return false;
		} else if (!postcode.equals(other.postcode))
			return false;
		if (straatnaam == null) {
			if (other.straatnaam != null)
				return false;
		} else if (!straatnaam.equals(other.straatnaam))
			return false;
		if (toevoeging == null) {
			if (other.toevoeging != null)
				return false;
		} else if (!toevoeging.equals(other.toevoeging))
			return false;
		if (woonplaats == null) {
			if (other.woonplaats != null)
				return false;
		} else if (!woonplaats.equals(other.woonplaats))
			return false;
		return true;
	}




	
	

    
}
