package kiteshop.View;

import java.math.BigDecimal;
import java.util.Scanner;
import kiteshop.pojos.Adres;
import org.apache.commons.validator.routines.BigDecimalValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;

public class Validator {

	public static int vraagInteger( ) {
		String integer = null;
		Scanner input = new Scanner(System.in);
		while(!isValidInt(integer)){
			integer = input.nextLine();
			if(!isValidInt(integer)){
				System.out.println("Dit is geen nummer, probeer opnieuw.\n Gebruik getal(len) zonder punt of komma");
			}
		}
		return Integer.parseInt(integer);
	}

	public static BigDecimal vraagBigDecimal() {
		String bigDecimal = null;
		Scanner input = new Scanner(System.in);
		while(!isValidBigDecimal(bigDecimal)){
			bigDecimal = input.nextLine();
			if(!isValidBigDecimal(bigDecimal)){
				System.out.println("Dit is geen geldige waarde voor een prijs.\n Decimale getallen zijn toegestaan, met een punt als scheidingsteken, probeer opnieuw");
			}
		}
		return new BigDecimal(bigDecimal);
	}
	public static Adres vraagAdres() {  
		Adres adres = new Adres();
		Scanner input = new Scanner(System.in);
		System.out.println("geef plaats: ");
		String woonplaats = input.nextLine();
		adres.setWoonplaats(woonplaats);

		String postcode = vraagPostcode();
		adres.setPostcode(postcode);

		System.out.println("geef straatnaam: ");
		String straatnaam = input.nextLine();
		adres.setStraatnaam(straatnaam);

		int huisnummer = vraagHuisnummer();
		adres.setHuisnummer(huisnummer);

		String toevoeging = vraagToevoeging();
		adres.setToevoeging(toevoeging);

		return adres;
	}

	public static String vraagPostcode() {
		String postcode = null;
		Scanner input = new Scanner(System.in);
		while(!isValidPostcode(postcode)){
			System.out.println("geef postcode: ");
			postcode = input.nextLine();
			if(!isValidPostcode(postcode)){
				System.out.println("Dit is geen geldige postcode, probeer opnieuw. \n de postcode bestaat uit 4 cijfers en 2 letters aan elkaar");
			}
		}
		return postcode;
	}

	public static String vraagToevoeging() {
		String toevoeging = null;
		Scanner input = new Scanner(System.in);
		while(!isValidToevoeging(toevoeging)){
			System.out.println("geef toevoeging: ");
			toevoeging = input.nextLine();
			if(!isValidToevoeging(toevoeging)){
				System.out.println("Dit is geen geldige toevoeging, probeer opnieuw. \n Werk met Romeinse cijfers of letters.");
			}
		}
		return toevoeging;
	}

	public static String vraagTelefoonnummer() {
		String telefoonnr = null;
		Scanner input = new Scanner(System.in);
		while(!isValidTelefoonnummer(telefoonnr)){
			System.out.println("geef telefoonnummer: ");
			telefoonnr = input.nextLine();
			if(!isValidTelefoonnummer(telefoonnr)){
				System.out.println("Dit is geen geldig telefoonnummer, probeer opnieuw.\n Gebruik geen haakjes");
			}
		}
		return telefoonnr;
	}

	public static int vraagIntegerMinMax(int min, int max) {
		String integer = null;
		Scanner input = new Scanner(System.in);
		while(!isValidInt(integer)|| !isValidValue(integer, min,max)){
			integer = input.nextLine();
			if(!isValidInt(integer)){
				System.out.println("Dit is geen nummer, probeer opnieuw");
			} else if(!isValidValue(integer, min,max)){
				System.out.println("Dit nummer behoort niet tot de mogelijkheden, probeer opnieuw");
			}
		}
		return Integer.parseInt(integer);
	}

	public static String vraagEmail() {
		String email = null;
		Scanner input = new Scanner(System.in);
		while(!isValidEmail(email)){
			System.out.println("geef emailadres: ");
			email = input.nextLine();
			if(!isValidEmail(email)){
				System.out.println("Dit is geen geldig emailadres, probeer opnieuw.\n vb: naam@provider.nl");
			}
		}
		return email;
	}

	public static int vraagHuisnummer() {
		int huisnummer = 0;
		Scanner input = new Scanner(System.in);
		System.out.println("geef huisnummer: ");  
		String tempHuisnummer = input.nextLine();
		try {
			huisnummer = Integer.parseInt(tempHuisnummer);
		} catch (NumberFormatException e) {
			System.out.println("Je hebt geen nummer in getoetst, probeer opnieuw");
			vraagHuisnummer();
		}
		return huisnummer;
	}
        
        public static String vraagWachtwoord() {
		String wachtwoord = null;
                Scanner input = new Scanner(System.in);
		while(!isValidWachtwoord(wachtwoord)){
			System.out.println("Wachtwoord?");
			wachtwoord = input.nextLine();
			if(!isValidWachtwoord(wachtwoord)){
				System.out.println("Dit is geen geldig wachtwoord.\n Een wachtwoord bestaat uit minimaal 4 tekens en bevat minstens 1 cijfer");	
			}
		} 
		return wachtwoord;
	}

	public static boolean isValidBigDecimal(String bigdecimal){
		BigDecimalValidator bigDecimalValidator  = BigDecimalValidator.getInstance();
		return bigDecimalValidator.isValid(bigdecimal);
	}

	public static boolean isValidInt(String integer){
		// de standaard Integer validator gebruikt amerikaanse notatie, "." wordt niet gelezen, maar hier faalt een int wel op
		// vandaar deze zelfgemaakte
		boolean validInt = false;
		try{
			Integer.parseInt(integer);
			validInt = true;
		} catch (Exception ex) {
			validInt = false;
		}
		return validInt;
	}

	public static boolean isValidValue(String integer, int min, int max){
		return (Integer.parseInt(integer)>= min && Integer.parseInt(integer)<= max);
	}


	public static boolean isValidEmail(String email){
		EmailValidator emailValidator  = EmailValidator.getInstance();
		return emailValidator.isValid(email);
	}

	public static boolean isValidTelefoonnummerInternet(String telefoonnummer){
		String regex = "(\\+[0-9]{2}|^\\+[0-9]{2}\\(0\\)|^\\(\\+[0-9]{2}\\)\\(0\\)|^00[0-9]{2}|^0)([0-9]{9}$|[0-9\\-\\s]{10}$)";
		RegexValidator regexValidator = new RegexValidator(regex, true);
		return regexValidator.isValid(telefoonnummer);
	}

	public static boolean isValidTelefoonnummer(String telefoonnummer){
		/*Voorbeelden wat we accepteren
		 * 06-12345678
		 * 0612345678
		 * 06 12345678
		 * 026-1234567
		 * 0261234567
		 * 026 1234567
		 * +31612345678
		 * +316-12345678
		 * +316 12345678
		 * +31 6 12345678
		 * hetzelfde voor land lijn , evt kan regio code ook 4 getallen hebben
		 * ik ga er niet vanuit dat mensen haakjes gaan invoeren ((026) 1234567 is niet toegestaan
		 */
		String regex = "(0|0031|\\+31)( )?(\\d{1}( |-)?\\d{8}|\\d{2}( |-)?\\d{7}|\\d{3}( |-)?\\d{6})";
		RegexValidator regexValidator = new RegexValidator(regex, true);
		return regexValidator.isValid(telefoonnummer);
	}

	public static boolean isValidPostcode(String postcode){
		String regex = "\\d{4}[A-Z]{2}";
		RegexValidator regexValidator = new RegexValidator(regex, true);
		return regexValidator.isValid(postcode);
	}

	public static boolean isValidHuisnummer(String huisnummer){
		String regex = "[1-9]\\d{0,4}";  //grootste huisnummer in NL  heeft 5 getallen
		RegexValidator regexValidator = new RegexValidator(regex, true);
		return regexValidator.isValid(huisnummer);
	}

	public static boolean isValidToevoeging(String toevoeging){
		// een toevoeging mag nu geen getal bevatten wel bijv II
		String regex = "[A-Za-z]{0,5}";
		RegexValidator regexValidator = new RegexValidator(regex, true);
		return regexValidator.isValid(toevoeging);
	}

	public static boolean isValidWachtwoord(String wachtwoord){
		//minimaal 4 karakters lang en een getal bevatten
		String regex = "[^\\-]{4,}";
		String regex2 = "[^\\-]{0,}[0-9]{1}[^\\-]{0,}";

		RegexValidator regexValidator = new RegexValidator(regex, true);
		RegexValidator regexValidator2 = new RegexValidator(regex2, true);
		return (regexValidator.isValid(wachtwoord) && regexValidator2.isValid(wachtwoord));
	}
}
