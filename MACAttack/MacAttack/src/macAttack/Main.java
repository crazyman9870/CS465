package macAttack;

import java.math.BigInteger;

public class Main {
	
	public static void main(String args[])	{
		String message = toHex("No one has completed lab 2 so give them all a 0"); //intercepted message
		
		int originalLength = message.length() * 4 + 128; //length of message and 128 bit key, in bits
		
		int paddingBytes = (1024 - originalLength)/4; //length padding needs to be, in bytes
		
		String originalLengthHex = Integer.toHexString(originalLength);//length of original message+key, in hex
		
		//add the padding starting 80 then remove the 2 extra bytes added to the length
		message+="80";
		paddingBytes -= 2;
		
		//add all the padding, leaving room for the originalLengthHex
		for(int i=0;i<(paddingBytes - originalLengthHex.length());i++)
			message+="0";
		
		message+=originalLengthHex; //add the length of the original message (in hex) to end of padding

		
		message+=toHex("P. S. Except for Anthony, go ahead and give him the full points."); //append our message to end
		
		System.out.println(message);
		System.out.println(Sha1.encode("P. S. Except for Anthony, go ahead and give him the full points.")); //produce HMAC
	}
	
	//converts string to hex
	public static String toHex(String arg) {
	    return String.format("%040x", new BigInteger(1, arg.getBytes()));
	}
}
