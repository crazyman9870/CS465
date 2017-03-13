package test;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Main {

	static String IV = "AAAAAAAAAAAAAAAA";
	static String plaintext = "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"; /*Note null padding*/
	static String encryptionKey = "0123456789abcdef";
	
	public static void main(String [] args) 
	{
		try {
			
//			// Get the KeyGenerator
//			KeyGenerator kgen = KeyGenerator.getInstance("AES");
//			kgen.init(128); // 192 and 256 bits may not be available
//			
//			KeyGenerator kgen2 = KeyGenerator.getInstance("AES");
//			kgen2.init(128); // 192 and 256 bits may not be available
//
//			// Generate the secret key specs.
//			SecretKey skey = kgen.generateKey();
//			byte[] raw = skey.getEncoded();
//			
//			SecretKey skey2 = kgen2.generateKey();
//			byte[] raw2 = skey2.getEncoded();
//			
//			IV = bytesToHex(raw);
//			
//			encryptionKey = bytesToHex(raw2);
//			
//			System.out.println(IV);
//			System.out.println(encryptionKey);
			
			System.out.println("==Java==");
			System.out.println("plain:	 " + plaintext);

			byte[] cipher = encrypt(plaintext, encryptionKey);

			System.out.print(bytesToHex(cipher));
			
			System.out.println("");

			String decrypted = decrypt(cipher, encryptionKey);

			System.out.println("decrypt: " + decrypted);

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}

//	EBC
//	public static byte[] encrypt(String plainText, String encryptionKey) throws Exception 
//	{
//		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");
//		SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
//		cipher.init(Cipher.ENCRYPT_MODE, key);
//		return cipher.doFinal(plainText.getBytes("UTF-8"));
//	}
//
//	public static String decrypt(byte[] cipherText, String encryptionKey) throws Exception
//	{
//		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");
//		SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
//		cipher.init(Cipher.DECRYPT_MODE, key);
//		return new String(cipher.doFinal(cipherText),"UTF-8");
//	}
	
//	CBC
	public static byte[] encrypt(String plainText, String encryptionKey) throws Exception
	{
	Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
	SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
	cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
	return cipher.doFinal(plainText.getBytes("UTF-8"));
	}
	
	public static String decrypt(byte[] cipherText, String encryptionKey) throws Exception
	{
	Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
	SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
	cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
	return new String(cipher.doFinal(cipherText),"UTF-8");
	}
	
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
}
