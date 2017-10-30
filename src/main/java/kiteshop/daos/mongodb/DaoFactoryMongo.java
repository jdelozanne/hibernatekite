/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.daos.mongodb;

import kiteshop.daos.AccountDaoInterface;
import kiteshop.daos.BestelRegelDaoInterface;
import kiteshop.daos.BestellingDaoInterface;
import kiteshop.daos.DaoFactoryInterface;
import kiteshop.daos.KlantDaoInterface;
import kiteshop.daos.ProductDaoInterface;

/**
 *
 * @author Steef P
 */
public class DaoFactoryMongo implements DaoFactoryInterface {

	@Override
	public AccountDaoInterface createAccountDao() {
		return new AccountDaoMongo();
	}

	@Override
	public BestellingDaoInterface createBestellingDao() {

		return new BestellingDaoMongo();
	}

	@Override
	public BestelRegelDaoInterface createBestelregelDao() {
		return new BestelRegelDaoMongo();
	}

	@Override
	public KlantDaoInterface createKlantDao() {
		return new KlantDaoMongo();
	}

	@Override
	public ProductDaoInterface createProductDao() {
		return new ProductDaoMongo();
	}

	@Override
	public String toString() {
		return "Mongo Database";
	}

}
