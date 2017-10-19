package classTests;

import static org.junit.Assert.*;
import static kiteshop.View.Validator.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ValidatorTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testIsValidEmail() {

		//hier wordt de standaard email controle gebruikt, hij test blijkbaar niet of de provider echt bestaat, alleen op de @ en wel op de .nl/com ed
		String testEmail = "s.pelgrom@hotmail.com";
		assertTrue(isValidEmail(testEmail));
		
		String testEmail2 = "pelgrom@hotmail.com";
		assertTrue(isValidEmail(testEmail2));
		
		String testEmail3 = "pelgrom@noname.nl";
		assertTrue(isValidEmail(testEmail3));
		
		String testEmail4 = "grom@noname.nl.com"; //dit vindt ie blijkbaar neit erg
		assertTrue(isValidEmail(testEmail4));
		
		String testEmail5 = "grom@noname.nnn";  // nnn is niet ok, dus assert false, is idd false
		assertFalse(isValidEmail(testEmail5));


	}

	@Test
	public final void testIsValidTelefoonnummer() {
		String testTelefoonnummer = "06-23546879";
		assertTrue(isValidTelefoonnummer(testTelefoonnummer));
		
		String testTelefoonnummer2 = "026-3546879";
		assertTrue(isValidTelefoonnummer(testTelefoonnummer2));
		
		String testTelefoonnummer3 = "+3126-3546879";
		assertTrue(isValidTelefoonnummer(testTelefoonnummer3));
		
		String testTelefoonnummer4 = "0623459875";
		assertTrue(isValidTelefoonnummer(testTelefoonnummer4));
		
		String testTelefoonnummer5 = "062345987";  //nummer te weinig
		assertFalse(isValidTelefoonnummer(testTelefoonnummer5));
	}

	@Test
	public final void testIsValidPostcode() {
		String testPostCode = "5046NC";
		assertTrue(isValidPostcode(testPostCode));
		
		String testPostCode2 = "5046 NC";   // een spatie is volgens de huidige regex niet toegestaan, je zou het evt wel kunnen toestaan, en dan evt aan elkaar kunnen plakken om het goed in je DB te krijgen
		assertFalse(isValidPostcode(testPostCode2));
		
		String testPostCode3 = "504NC";   
		assertFalse(isValidPostcode(testPostCode3));
		
		String testPostCode4 = "5046nc";   
		assertFalse(isValidPostcode(testPostCode4)); //lowercase mag ook niet, checkt ie goed
		
		
	}

}
