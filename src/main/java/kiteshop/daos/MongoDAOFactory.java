/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.daos;

/**
 *
 * @author julia
 */
public class MongoDAOFactory extends DAOFactory {
    
MongoDAOFactory(){
    
}
public DAOInterface createDAO(String daoName){
    
    switch(daoName.toLowerCase()){
        case "account": return new AccountDAOMongo();
        
        case "bestelregel": return new BestelRegelDAOMongo();
        
        case "bestelling": return new BestellingDAOMongo();
        
        case "klant": return new KlantDAOMongo();
        
        case "product": return new ProductDAOMongo();
    }
    return null;
}
    
    
}
