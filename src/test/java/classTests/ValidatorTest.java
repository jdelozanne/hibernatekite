 package classTests;

import static org.junit.Assert.*;

import org.apache.commons.validator.routines.RegexValidator;

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
        public final void testIsValidBigDecimal(){
            String testBigDecimal1 = "500034.566567";
            assertTrue(isValidBigDecimal(testBigDecimal1));
            
            String testBigDecimal2 = "adgfer";
            assertFalse(isValidBigDecimal(testBigDecimal2));
            
            String testBigDecimal3 = "5*20";
            assertFalse(isValidBigDecimal(testBigDecimal3));
            
            String testBigDecimal4 = "20000000034.566567";
            assertTrue(isValidBigDecimal(testBigDecimal4));
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
	public final void testIsValidTelefoonnummerInternet() {
		String testTelefoonnummer = "06-23546879";
		assertTrue(isValidTelefoonnummerInternet(testTelefoonnummer));
		
		String testTelefoonnummer2 = "026-3546879";
		assertTrue(isValidTelefoonnummerInternet(testTelefoonnummer2));
		
		String testTelefoonnummer3 = "+3126-3546879";
		assertTrue(isValidTelefoonnummerInternet(testTelefoonnummer3));
		
		String testTelefoonnummer4 = "0623459875";
		assertTrue(isValidTelefoonnummerInternet(testTelefoonnummer4));
		
		String testTelefoonnummer5 = "062345987";  //nummer te weinig
		assertFalse(isValidTelefoonnummerInternet(testTelefoonnummer5));
		
		String testTelefoonnummer6 = "06 23459875";
		assertTrue(isValidTelefoonnummerInternet(testTelefoonnummer6));
		
		String testTelefoonnummer8 = "026 3546879";
		assertTrue(isValidTelefoonnummerInternet(testTelefoonnummer8));
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
		
		String testTelefoonnummer6 = "+31 6 12345678";
		assertTrue(isValidTelefoonnummer(testTelefoonnummer6));
		
		String testTelefoonnummer8 = "026 3546879";
		assertTrue(isValidTelefoonnummer(testTelefoonnummer8));
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
		assertFalse(isValidPostcode(testPostCode4)); //lowercase mag niet, checkt ie goed
		
		
	}
	
	
	@Test
	public final void testIsValidHuisnummer(){

		String testHuisnummer = "4";
		assertTrue(isValidHuisnummer(testHuisnummer));
		String testHuisnummer2 = "K";
		assertFalse(isValidHuisnummer(testHuisnummer2));
	}
	
	@Test
	public final void testIsValidToevoeging(){

		String testToevoeging = "b";
		assertTrue(isValidToevoeging(testToevoeging));
		String testToevoeging2 = "zwart";
		assertTrue(isValidToevoeging(testToevoeging2));
	}
	
	@Test
	public final void testIsValidWachtwoord(){
		
		String wachtwoord = "aap79muis";
		assertTrue(isValidWachtwoord(wachtwoord));
	}

}
