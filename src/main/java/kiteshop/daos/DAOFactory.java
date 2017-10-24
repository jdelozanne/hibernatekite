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
public abstract class DAOFactory {
    
	
	
    
  private void createDao(String database){
		switch(database.toLowerCase()){
		case "mysql":
			new SQLDAOFactory();
                case "mongodb":
		default:
			new MongoDAOFactory();
		}
	}

       
   }

