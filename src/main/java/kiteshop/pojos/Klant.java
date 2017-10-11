/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

bla die bla
 */
package kiteshop.pojos;

public class Klant {

    
    private int klantID;
    private Adres adres;
    private String voornaam;
    private String tussenvoegsel;
    private String achternaam;
    
    public Klant() {
    }
    
    public int getKlantID() {
        return klantID;
    }
    
    public void setKlantID(int id){
        this.klantID = id;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    
    
    @Override
	public String toString() {
		return "KlantID = " + klantID + " voornaam = " + voornaam + " tussenvoegsel = "
				+ tussenvoegsel + " achternaam = " + achternaam;
	}

	//Benodigd voor het testen met JUnit
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((achternaam == null) ? 0 : achternaam.hashCode());
		result = prime * result + ((adres == null) ? 0 : adres.hashCode());
		result = prime * result + klantID;
		result = prime * result + ((tussenvoegsel == null) ? 0 : tussenvoegsel.hashCode());
		result = prime * result + ((voornaam == null) ? 0 : voornaam.hashCode());
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
		Klant other = (Klant) obj;
		if (achternaam == null) {
			if (other.achternaam != null)
				return false;
		} else if (!achternaam.equals(other.achternaam))
			return false;
		if (adres == null) {
			if (other.adres != null)
				return false;
		} else if (!adres.equals(other.adres))
			return false;
		if (klantID != other.klantID)
			return false;
		if (tussenvoegsel == null) {
			if (other.tussenvoegsel != null)
				return false;
		} else if (!tussenvoegsel.equals(other.tussenvoegsel))
			return false;
		if (voornaam == null) {
			if (other.voornaam != null)
				return false;
		} else if (!voornaam.equals(other.voornaam))
			return false;
		return true;
	}

    
    
}
