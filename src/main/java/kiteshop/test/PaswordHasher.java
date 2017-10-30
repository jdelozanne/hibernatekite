package kiteshop.test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.xml.bind.DatatypeConverter;

public class PaswordHasher {

	public static String createHashedPassword(String saltHex, String password) {
		String paswordHex = toHexadecimal(password);
		String combinedHex = saltHex + paswordHex;

		byte[] combinedArray = hexStringtoByteArray(combinedHex);

		MessageDigest md= null;

		try {
			md = MessageDigest.getInstance("md5"); // je kan hier md5 of SHA zetten, blijft werken
		} catch (Exception e) {
			e.printStackTrace();
		}

		md.update(combinedArray); 
		byte[] hashedPasswordInBytes = md.digest();
		String hashedPasswordHex = biteArrayToHexString(hashedPasswordInBytes);

		return hashedPasswordHex;
	}

	public static String createSaltHex(){
		byte[] saltBytes = createSaltBytes();
		String saltHexString = biteArrayToHexString(saltBytes);
		return saltHexString;
	}


	private static byte[] createSaltBytes() {
		SecureRandom random = new SecureRandom();
		byte[] values = new byte[32];

		random.nextBytes(values);

		return values;
	}






	//Helper 'omreken' functies

	private static String biteArrayToHexString(byte[] array) {
		return DatatypeConverter.printHexBinary(array);
	}

	private static byte[] hexStringtoByteArray(String s) {
		return DatatypeConverter.parseHexBinary(s);
	}

	private static String toHex(byte [] buf) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;
		for (i = 0; i < buf.length; i++) {
			if (((int) buf[i] & 0xff) < 0x10) {
				strbuf.append("0");
			}
			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}
		return strbuf.toString();
	}

	private static String toHexadecimal(String text)
	{
		byte[] myBytes= null;
		try {
			myBytes = text.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return DatatypeConverter.printHexBinary(myBytes);
	}
	public static void main(String[] args) {
		String gebruikersnaam = "Julia";
		String password = "GEfasgas";
		String salthex = createSaltHex();
		
		
		String hashedpasword =createHashedPassword(salthex, password);
		
		String hashedpasword2 = createHashedPassword(salthex, password);
		
		System.out.println(hashedpasword.equals(hashedpasword2));
		
		System.out.println("Hashed Pasword hex "+hashedpasword + "length "+ hashedpasword.length());



	}

}







