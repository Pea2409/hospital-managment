package security;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ecrypt {
	 public String encrypt(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
	        String encryptedText;
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        byte[] srcTextBytes = text.getBytes("UTF-8");
	        byte[] encryptedTextBytes = md.digest(srcTextBytes);
	        BigInteger big = new BigInteger(1, encryptedTextBytes);
	        encryptedText = big.toString(16);
	        return encryptedText;
	    }
}
