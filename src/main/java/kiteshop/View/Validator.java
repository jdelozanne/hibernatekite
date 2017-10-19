package kiteshop.View;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;

public class Validator {

	public static void main(String[] args) {


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
		String regex = "[A-Za-z]{1,5}";
		RegexValidator regexValidator = new RegexValidator(regex, true);
		return regexValidator.isValid(toevoeging);
	}

	public static boolean isValidWachtwoord(String wachtwoord){
		//minimaal 8 karakters lang en een getal bevatten
		String regex = "[^\\-]{8,}";
		String regex2 = "[^\\-]{0,}[0-9]{1}[^\\-]{0,}";
		
		RegexValidator regexValidator = new RegexValidator(regex, true);
		RegexValidator regexValidator2 = new RegexValidator(regex2, true);
		return (regexValidator.isValid(wachtwoord) && regexValidator2.isValid(wachtwoord));
	}



}
