/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

bla die bla
 */
package kiteshop.pojos;

public class Klant {
    
    private int klantID;
    private Adres bezoekAdres;
    private Adres factuurAdres;
    private String voornaam;
    private String tussenvoegsel;
    private String achternaam;
    private String email;
    private String telefoonnummer;
    
    public Klant() {
    }
    
    public int getKlantID() {
        return klantID;
    }
    
    public void setKlantID(int id){
        this.klantID = id;
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
    
    public String getEmail() {
		return email;
	}

	public String getTelefoonnummer() {
		return telefoonnummer;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefoonnummer(String telefoonnummer) {
		this.telefoonnummer = telefoonnummer;
	}
	
	public Adres getBezoekAdres() {
		return bezoekAdres;
	}

	public Adres getFactuurAdres() {
		return factuurAdres;
	}

	public void setBezoekAdres(Adres bezoekAdres) {
		this.bezoekAdres = bezoekAdres;
	}

	public void setFactuurAdres(Adres factuurAdres) {
		this.factuurAdres = factuurAdres;
	}

	@Override
	public String toString() {
		return "Klant [klantID=" + klantID + ", bezoekAdres=" + bezoekAdres + ", factuurAdres=" + factuurAdres
				+ ", voornaam=" + voornaam + ", tussenvoegsel=" + tussenvoegsel + ", achternaam=" + achternaam
				+ ", email=" + email + ", telefoonnummer=" + telefoonnummer + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((achternaam == null) ? 0 : achternaam.hashCode());
		result = prime * result + ((bezoekAdres == null) ? 0 : bezoekAdres.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((factuurAdres == null) ? 0 : factuurAdres.hashCode());
		result = prime * result + klantID;
		result = prime * result + ((telefoonnummer == null) ? 0 : telefoonnummer.hashCode());
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
		if (bezoekAdres == null) {
			if (other.bezoekAdres != null)
				return false;
		} else if (!bezoekAdres.equals(other.bezoekAdres))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (factuurAdres == null) {
			if (other.factuurAdres != null)
				return false;
		} else if (!factuurAdres.equals(other.factuurAdres))
			return false;
		if (klantID != other.klantID)
			return false;
		if (telefoonnummer == null) {
			if (other.telefoonnummer != null)
				return false;
		} else if (!telefoonnummer.equals(other.telefoonnummer))
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
