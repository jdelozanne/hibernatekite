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
public class DAOFactory {
    
private KlantDAOInterface klant;
private AccountDAOInterface account;
private ProductDAOInterface product;
private BestellingDAOInterface bestelling;
private BestelRegelDAOInterface bestelregel;

	
    
  public KlantDAOInterface createKlantDAO(String database){
		switch(database.toLowerCase()){
		case "mysql":
			this.klant = new KlantDAO();
                case "mongodb":
                    this.klant = new KlantDAOMongo();
		default:
		}
                return klant;
	}
  
  public AccountDAOInterface createAccountDAO(String database){
      switch(database.toLowerCase()){
		case "mysql":
			this.account = new AccountDAO();
                case "mongodb":
                    this.account = new AccountDAOMongo();
		default:
		}
                return account;
	}
  public ProductDAOInterface createProductDAO(String database){
      switch(database.toLowerCase()){
		case "mysql":
			this.product= new ProductDAO();
                case "mongodb":
                    this.product = new ProductDAOMongo();
		default:
		}
                return product;
	}
  
  public BestellingDAOInterface createBestellingDAO(String database){
      switch(database.toLowerCase()){
		case "mysql":
			this.bestelling= new BestellingDAO();
                case "mongodb":
                    this.bestelling = new BestellingDAOMongo();
		default:
		}
                return bestelling;
	}
  public BestelRegelDAOInterface createBestelRegelDAO(String database){
      switch(database.toLowerCase()){
		case "mysql":
			this.bestelregel= new BestelRegelDAO();
                case "mongodb":
                    this.bestelregel = new BestelRegelDAOMongo();
		default:
		}
                return bestelregel;
	}
  
      
  }
  
  

       
   

