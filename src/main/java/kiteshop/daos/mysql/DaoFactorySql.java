/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiteshop.daos.mysql;

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
public class DaoFactorySql implements DaoFactoryInterface {

	@Override
	public AccountDaoInterface createAccountDao() {
		
		return new AccountDaoSql();
	}

	@Override
	public BestellingDaoInterface createBestellingDao() {
		// TODO Auto-generated method stub
		return new BestellingDaoSql();
	}

	@Override
	public BestelRegelDaoInterface createBestelregelDao() {
		// TODO Auto-generated method stub
		return new BestelRegelDaoSql();
	}

	@Override
	public KlantDaoInterface createKlantDao() {
		// TODO Auto-generated method stub
		return new KlantDaoSql();
	}

	@Override
	public ProductDaoInterface createProductDao() {
		// TODO Auto-generated method stub
		return new ProductDaoSql();
	}

	@Override
	public String toString() {
		return "Sql Database";
	}
	
	

}
