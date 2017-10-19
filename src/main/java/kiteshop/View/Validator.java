package kiteshop.test;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;

public class Validator {

	public static void main(String[] args) {
		System.out.println(isValidPnoneNumber("026-6634587"));

	}
	
	public static boolean isValidEmail(String email){
		EmailValidator emailValidator  = EmailValidator.getInstance();
		return emailValidator.isValid(email);
	}
	
	public static boolean isValidPnoneNumber(String phoneNumber){
		
		String regex = "(^\\+[0-9]{2}|^\\+[0-9]{2}\\(0\\)|^\\(\\+[0-9]{2}\\)\\(0\\)|^00[0-9]{2}|^0)([0-9]{9}$|[0-9\\-\\s]{10}$)";
		
		RegexValidator regexValidator = new RegexValidator(regex, true);
		
		return regexValidator.isValid(phoneNumber);
	}

}
