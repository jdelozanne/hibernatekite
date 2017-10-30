package classTests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import kiteshop.daos.mysql.ProductDaoSql;
import kiteshop.pojos.Product;
import setup.SetUpTestDatabase;

public class ProductDAOTest {

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
	public final void testCreateProduct() {

		Product expectedresult = new Product();
		expectedresult.setProductID(1);
		expectedresult.setNaam("GAASTRA PURE 2017");
		expectedresult.setVoorraad(10);
		expectedresult.setPrijs(new BigDecimal("999.00"));

		new ProductDaoSql().createProduct(expectedresult);

		Product result = new ProductDaoSql().readAllProducten().get(0);

		System.out.println("Expeted product "+expectedresult);
		System.out.println("Result product "+result);

		assertEquals(expectedresult, result);
	}

	@Test
	public final void testReadProductString() {
		new SetUpTestDatabase().populateDatabase();
		//form populate database ('2', 'Cabrinha Chaos', 8,  '719.00')
		Product expectedresult = new Product();
		expectedresult.setProductID(2);
		expectedresult.setNaam("Cabrinha Chaos");
		expectedresult.setVoorraad(8);
		expectedresult.setPrijs(new BigDecimal("719.00"));

		Product result = new ProductDaoSql().readProduct("Cabrinha Chaos");


		assertEquals(expectedresult, result);
	}

	@Test
	public final void testShowProducten() {
		new SetUpTestDatabase().populateDatabase();
		int productenindatabase = new ProductDaoSql().readAllProducten().size();
		assertTrue(productenindatabase == 3);


		//('2', 'Cabrinha Chaos', 8,  '719.00')
		Product expectedresult = new Product();
		expectedresult.setProductID(2);
		expectedresult.setNaam("Cabrinha Chaos");
		expectedresult.setVoorraad(8);
		expectedresult.setPrijs(new BigDecimal("719.00"));

		Product result = new ProductDaoSql().readAllProducten().get(1);

		assertEquals(expectedresult, result);

	}

	@Test
	public final void testUpdateProduct() {
	new SetUpTestDatabase().populateDatabase();
		Product resultbefore = new ProductDaoSql().readAllProducten().get(1); // ik pak hier de tweede (arraylist begint op 0 database op 1, omdat ik ook de tweede ga vervangen

		Product expectedresult = new Product();
		expectedresult.setProductID(2);
		expectedresult.setNaam("GAASTRA PURE 2017");
		expectedresult.setVoorraad(10);
		expectedresult.setPrijs(new BigDecimal("999.00"));

		new ProductDaoSql().updateProduct(expectedresult);

		Product result = new ProductDaoSql().readAllProducten().get(1);
		System.out.println("Expeted product "+expectedresult);
		System.out.println("Result product "+result);

		assertEquals(expectedresult, result);

	}

	@Test
	public final void testDeleteProduct() {
		new SetUpTestDatabase().populateDatabase();
		int productenindatabase = new ProductDaoSql().readAllProducten().size();
		assertTrue(productenindatabase == 3);
		Product producttobedeleted = new Product();
		producttobedeleted.setProductID(2);
		producttobedeleted.setNaam("Cabrinha Chaos");
		producttobedeleted.setVoorraad(8);
		producttobedeleted.setPrijs(new BigDecimal("719.00"));
		new ProductDaoSql().deleteProduct(producttobedeleted);
		int productenindatabaseafterdelete = new ProductDaoSql().readAllProducten().size();
		assertTrue(productenindatabaseafterdelete == 2);

	}

}
