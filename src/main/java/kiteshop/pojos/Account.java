/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.pojos;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.*;



/**
 *
 * @author julia
 */

@Entity
public class Account implements Serializable {

	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
    private int accountID;
	
    private String gebruikersnaam;
    private String wachtwoord;
    private String salt;
    


    public Account() {
    }

    public Account(int accountID, String gebruikersnaam, String wachtwoord) {
        this.accountID = accountID;
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
    }


    public void setGebruikersnaam(String s) {
        this.gebruikersnaam = s;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setWachtwoord(String s) {
        this.wachtwoord = s;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
    public String toString() {
        return "Account [accountID=" + accountID + ", gebruikersnaam=" + gebruikersnaam + ", wachtwoord=" + wachtwoord
                + ", type=" +  "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + accountID;
        result = prime * result + ((gebruikersnaam == null) ? 0 : gebruikersnaam.hashCode());
  
        result = prime * result + ((wachtwoord == null) ? 0 : wachtwoord.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Account other = (Account) obj;
        if (accountID != other.accountID) {
            return false;
        }
        if (gebruikersnaam == null) {
            if (other.gebruikersnaam != null) {
                return false;
            }
        } else if (!gebruikersnaam.equals(other.gebruikersnaam)) {
            return false;
        }
    
        if (wachtwoord == null) {
            if (other.wachtwoord != null) {
                return false;
            }
        } else if (!wachtwoord.equals(other.wachtwoord)) {
            return false;
        }
        return true;
    }
}
