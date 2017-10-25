package kiteshop.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Hashtest {

	public static String hashPassword(String password) throws NoSuchAlgorithmException{
		/*
		 * Er moet een Salt voor of achter het password worden gezet, en dan moet het geheel worden gehashed
		 * 
		 * standaard geeft de secure random bytes, in een array, aangezien de hash functie sowieso een byte 
		 * array nodig heeft, plak ik de byte arrays direct achter elkaar (en daaran hash ik deze byte array),
		 * onder is de snelste manier daarvoor, gevonden op stackoverflow
		 */

		byte[] a = password.getBytes();
		byte[] b = createSalt(); 
		byte[] c = new byte[a.length + b.length];  //
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		
		System.out.println("Combined array");
		for(byte combined: c){
			System.out.print(combined+" ");
		}
		System.out.println();
		//nu wordt de combineerde array gehashed
		MessageDigest md = MessageDigest.getInstance("md5"); // je kan hier md5 of SHA zetten, blijft werken
		md.update(c); 
		byte[] hashedPasswordInBytes = md.digest();
		
		StringBuilder completehashedPassword = new StringBuilder();
		for(byte bt: hashedPasswordInBytes){
			completehashedPassword.append(Integer.toHexString(bt & 0xff).toString()); // als je die "0xff" niet doet komen overal ffff tussen 1effffffdc2914ffffffeacffffff8afffffffdffffffba3b32ffffffdcffffff9b517c7 ipv 1edc2914eac8afdba3b32dc9b517c7
		}
		


		return completehashedPassword.toString();
	}

	private static byte[] createSalt() {
		SecureRandom random = new SecureRandom();
		byte[] values = new byte[32];
		System.out.println("Salt unrandomized: ");
		for(byte b: values){
			System.out.print(b+" ");
		}
		System.out.println();
		random.nextBytes(values);

		System.out.println("Salt randomized: ");
		for(byte b: values){
			System.out.print(b+" ");
		}
		System.out.println();
		return values;
	}

	public static void main(String[] args){

		String pasword = "Cola456";

		try {
			System.out.println("Hashed pasword 1: "+hashPassword(pasword));
		} catch (NoSuchAlgorithmException e) {
	
			e.printStackTrace();
		}

		String pasword2 = "Cola456";

		try {
			System.out.println("Hashed pasword 2: "+hashPassword(pasword2));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


}
