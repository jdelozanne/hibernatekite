package kiteshop.daos;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import kiteshop.pojos.Adres;
import kiteshop.pojos.Klant;

public class KlantDAOTest {

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
	public final void testCreateKlant() {
		

		Klant klanttobecreated = new Klant();
		klanttobecreated.setKlantID(1);
		klanttobecreated.setVoornaam("Steef");
		klanttobecreated.setAchternaam("Pelgrom");
		klanttobecreated.setEmail("stevey@hotmail.com");
		klanttobecreated.setTelefoonnummer("06-56847965");
		klanttobecreated.setBezoekAdres(new Adres());
		klanttobecreated.setFactuurAdres(new Adres());
		
		new KlantDAO().createKlant(klanttobecreated);

		Klant result = new KlantDAO().readKlantByAchternaam("Pelgrom").get(0);

		assertEquals(klanttobecreated, result);
	}

	@Test
	public final void testUpdateKlant() {
		new DatabaseTest().populateDatabase();
		
		Klant klanttobeupdated = new Klant();
		klanttobeupdated.setKlantID(1);
		klanttobeupdated.setVoornaam("Julia");
		klanttobeupdated.setAchternaam("Lozanne");
		klanttobeupdated.setEmail("Julia@hotmail.com");
		klanttobeupdated.setTelefoonnummer("06-59648753");
		klanttobeupdated.setBezoekAdres(new Adres());
		klanttobeupdated.setFactuurAdres(new Adres());
		
		new KlantDAO().updateKlant(klanttobeupdated);
		
		Klant result = new KlantDAO().readKlantByAchternaam("Lozanne").get(0);
		
		int klantenwithachternaampelgrom = new KlantDAO().readKlantByAchternaam("Pelgrom").size();
	
		assertEquals(klanttobeupdated, result);
		
	}

	@Test
	public final void testDeleteKlant() {
		new DatabaseTest().populateDatabase();

		int klantenindatabase = new KlantDAO().readAllKlanten().size();
		Klant klanttobedeleted = new Klant();
		klanttobedeleted.setKlantID(1);

		new KlantDAO().deleteKlant(klanttobedeleted);
		int klantenindatabaseafterdelete = new KlantDAO().readAllKlanten().size();

		assertTrue(klantenindatabaseafterdelete == klantenindatabase -1);
	}

	@Test
	public final void testReadSelectedKlantenAchternaam() {
		new DatabaseTest().populateDatabase();

		Klant verwachtResultKlant = new Klant();
		verwachtResultKlant.setKlantID(1);
		verwachtResultKlant.setVoornaam("Steef");
		verwachtResultKlant.setAchternaam("Pelgrom");
		verwachtResultKlant.setEmail("stevey@hotmail.com");
		verwachtResultKlant.setTelefoonnummer("06-56847965");
		verwachtResultKlant.setBezoekAdres(new Adres());
		verwachtResultKlant.setFactuurAdres(new Adres());

		Klant result = new KlantDAO().readKlantByAchternaam("Pelgrom").get(0);

		assertEquals(verwachtResultKlant, result);
	}

	@Test
	public final void testReadAllKlanten() {
		new DatabaseTest().populateDatabase();

		Klant verwachtResultKlant = new Klant();
		verwachtResultKlant.setKlantID(1);
		verwachtResultKlant.setVoornaam("Steef");
		verwachtResultKlant.setAchternaam("Pelgrom");
		verwachtResultKlant.setEmail("stevey@hotmail.com");
		verwachtResultKlant.setTelefoonnummer("06-56847965");
		verwachtResultKlant.setBezoekAdres(new Adres());
		verwachtResultKlant.setFactuurAdres(new Adres());

		Klant result = new KlantDAO().readAllKlanten().get(0);

		assertEquals(verwachtResultKlant, result);
	}

}
