package classTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import kiteshop.daos.AccountDaoSql;
import kiteshop.pojos.Account;
import setup.SetUpTestDatabase;

public class AccountDAOTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		new SetUpTestDatabase().initializeDatabase();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public final void testReadAllAccounts() {
		new SetUpTestDatabase().populateDatabase();

		Account expectedResult = new Account(1, "Steef", "Cola");
		Account result = new AccountDaoSql().readAllAccounts().get(0);

		assertEquals(expectedResult, result);

	}

	@Test
	public final void testReadAccountByGebruikersnaam() {
		new SetUpTestDatabase().populateDatabase();

		Account expectedResult = new Account(1, "Steef", "Cola");
		Account result = new AccountDaoSql().readAccountByGebruikersnaam("Steef");

		assertEquals(expectedResult, result);
	}

	@Test
	public final void testCreateAccount() {
		Account accounttobecreated = new Account(1, "Julia", "Moezel");
		new AccountDaoSql().createAccount(accounttobecreated);
		Account accountfromdatabase = new AccountDaoSql().readAccountByGebruikersnaam("Julia");

		assertEquals(accounttobecreated, accountfromdatabase);

	}

	



	@Test
	public final void testUpdateAccount() {
		new SetUpTestDatabase().populateDatabase();
		Account expectedResult = new Account(1, "Julia", "Chablis");
		new AccountDaoSql().updateAccount(expectedResult);
		Account result = new AccountDaoSql().readAccountByGebruikersnaam("Julia");
		
		assertEquals(expectedResult, result);
	}

	@Test
	public final void testDeleteAccount() {
		new SetUpTestDatabase().populateDatabase();
		Account accounttobedeleted = new Account(1, "Steef", "Cola");
		new AccountDaoSql().deleteAccount(accounttobedeleted);
		int numberofaccounts = new AccountDaoSql().readAllAccounts().size();
		
		assertTrue(numberofaccounts == 0);
	}



}
