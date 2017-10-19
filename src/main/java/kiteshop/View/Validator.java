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
	
	public static boolean isValidTelefoonnummer(String telefoonnummer){
		String regex = "(^\\+[0-9]{2}|^\\+[0-9]{2}\\(0\\)|^\\(\\+[0-9]{2}\\)\\(0\\)|^00[0-9]{2}|^0)([0-9]{9}$|[0-9\\-\\s]{10}$)";
		RegexValidator regexValidator = new RegexValidator(regex, true);
		return regexValidator.isValid(telefoonnummer);
	}
	
	public static boolean isValidPostcode(String postcode){
		
		String regex = "\\d{4}[A-Z]{2}";
		RegexValidator regexValidator = new RegexValidator(regex, true);
		return regexValidator.isValid(postcode);
		
		
	}

}
