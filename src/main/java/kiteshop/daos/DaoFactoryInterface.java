/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.daos;

/**
 *
 * @author Steef P
 */
public interface DaoFactoryInterface {
    AccountDaoInterface createAccountDao();
	
	BestellingDaoInterface createBestellingDao();
	
	BestelRegelDaoInterface createBestelregelDao();
	
	KlantDaoInterface createKlantDao();
	
	ProductDaoInterface createProductDao();
}
