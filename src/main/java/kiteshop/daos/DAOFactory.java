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
    
private KlantDaoInterface klant;
private AccountDaoInterface account;
private ProductDaoInterface product;
private BestellingDaoInterface bestelling;
private BestelRegelDaoInterface bestelregel;

	
    
  public KlantDaoInterface createKlantDAO(String database){
		switch(database.toLowerCase()){
		case "mysql":
			this.klant = new KlantDaoSql();
                        break;
                case "mongodb":
                    this.klant = new KlantDaoMongo();
                    break;
		default:
		}
                return klant;
	}
  
  public AccountDaoInterface createAccountDAO(String database){
      switch(database.toLowerCase()){
		case "mysql":
			this.account = new AccountDaoSql();
                        break;
                case "mongodb":
                    this.account = new AccountDaoMongo();
                    break;
		default:
		}
                return account;
	}
  public ProductDaoInterface createProductDAO(String database){
      switch(database.toLowerCase()){
		case "mysql":
			this.product= new ProductDaoSql();
                        break;
                case "mongodb":
                    this.product = new ProductDaoMongo();
                    break;
		default:
		}
                return product;
	}
  
  public BestellingDaoInterface createBestellingDAO(String database){
      switch(database.toLowerCase()){
		case "mysql":
			this.bestelling= new BestellingDaoSql();
                        break;
                case "mongodb":
                    this.bestelling = new BestellingDaoMongo();
                    break;
		default:
		}
                return bestelling;
	}
  public BestelRegelDaoInterface createBestelRegelDAO(String database){
      switch(database.toLowerCase()){
		case "mysql":
			this.bestelregel= new BestelRegelDaoSql();
                        break;
                case "mongodb":
                    this.bestelregel = new BestelRegelDaoMongo();
                    break;
		default:
		}
                return bestelregel;
	}
  
      
  }
  
  

       
   

