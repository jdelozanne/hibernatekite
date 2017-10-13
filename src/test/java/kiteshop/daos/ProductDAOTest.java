package kiteshop.daos;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import kiteshop.pojos.Product;

public class ProductDAOTest {

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
	public final void testCreateProduct() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testReadProductString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testShowProducten() {
		new DatabaseTest().populateDatabase();
		int productenindatabase = new ProductDAO().showProducten().size();
		assertTrue(productenindatabase == 3);
		

		//('2', 'Cabrinha Chaos', 8,  '719.00')
		Product expectedresult = new Product();
		expectedresult.setProductID(2);
		expectedresult.setNaam("Cabrinha Chaos");
		expectedresult.setVoorraad(8);
		expectedresult.setPrijs(new BigDecimal("719.00"));
		
		Product result = new ProductDAO().showProducten().get(1);
		System.out.println("Expeted product "+expectedresult);
		System.out.println("Result product "+result);
		assertEquals(expectedresult, result);
		
	}

	@Test
	public final void testUpdateProduct() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testDeleteProduct() {
		fail("Not yet implemented"); // TODO
	}

}
