/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.pojos;

import java.util.ArrayList;
import kiteshop.daos.AccountDAO;

/**
 *
 * @author julia
 */
public class Account {

    private String gebruikersnaam;
    private String wachtwoord;
    private AccountType type;

    public Account() {
    }
    
    public Account(String x, String y){
        this.gebruikersnaam = x;
        this.wachtwoord = y;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public void setGebruikersnaam(String s) {
        this.gebruikersnaam = s;
    }
    
    public String getGebruikersnaam(){
        return gebruikersnaam;
    }
    //eigenlijk zou je willen dat er asterix komen ipv tekens
    public void setWachtwoord(String s) {
        this.wachtwoord = s;
    }
    
    public String getWachtwoord(){
        return wachtwoord;
    }
}
