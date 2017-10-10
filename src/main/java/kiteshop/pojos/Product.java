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

    //productID hier of pas in DAO?
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
        
        String productDisplay = this.getNaam();
        return productDisplay;
    }
}
