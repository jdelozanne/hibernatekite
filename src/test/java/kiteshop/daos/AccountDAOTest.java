package kiteshop.daos;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import kiteshop.pojos.Account;

public class AccountDAOTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		new DatabaseTest().initializeDatabase();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public final void testReadAllAccounts() {
		new DatabaseTest().populateDatabase();

		Account expectedResult = new Account(1, "Steef", "Cola");
		Account result = new AccountDAO().readAllAccounts().get(0);

		assertEquals(expectedResult, result);

	}

	@Test
	public final void testReadAccountByGebruikersnaam() {
		new DatabaseTest().populateDatabase();

		Account expectedResult = new Account(1, "Steef", "Cola");
		Account result = new AccountDAO().readAccountByGebruikersnaam("Steef");

		assertEquals(expectedResult, result);
	}

	@Test
	public final void testCreateAccount() {
		Account accounttobecreated = new Account(1, "Julia", "Moezel");
		new AccountDAO().createAccount(accounttobecreated);
		Account accountfromdatabase = new AccountDAO().readAccountByGebruikersnaam("Julia");

		assertEquals(accounttobecreated, accountfromdatabase);

	}

	@Test
	public final void testGivePassword() {
		new DatabaseTest().populateDatabase();
		String password = "Cola";
		String returnedpassword = new AccountDAO().givePassword("Steef");
		
		assertEquals(password, returnedpassword);
		
	}



	@Test
	public final void testUpdateAccount() {
		new DatabaseTest().populateDatabase();
		Account expectedResult = new Account(1, "Julia", "Chablis");
		new AccountDAO().updateAccount(expectedResult);
		Account result = new AccountDAO().readAccountByGebruikersnaam("Julia");
		
		assertEquals(expectedResult, result);
	}

	@Test
	public final void testDeleteAccount() {
		new DatabaseTest().populateDatabase();
		Account accounttobedeleted = new Account(1, "Steef", "Cola");
		new AccountDAO().deleteAccount(accounttobedeleted);
		int numberofaccounts = new AccountDAO().readAllAccounts().size();
		
		assertTrue(numberofaccounts == 0);
	}



}
