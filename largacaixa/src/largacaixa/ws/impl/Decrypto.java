package largacaixa.ws.impl;

import static javax.xml.bind.DatatypeConverter.printHexBinary;
import javax.crypto.Cipher;
import javax.crypto.BadPaddingException;
import java.io.*;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.security.MessageDigest;

public class Decrypto{
	public static boolean descipher(String privateKeyPath, String publicKeyPath, String input1, Object input2) throws Exception{
		
        try{
        // generate an RSA key
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair keys = read(publicKeyPath,privateKeyPath);
      
        // Convert Strings to byte arrays
        final byte[] plainBytes = input1.getBytes();
        byte[] plainBytesDec = (byte[])input2;
        
        // get a message digest object using the MD5 algorithm for input1
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(plainBytes);
        byte[] cmpdigest = messageDigest.digest();
        
        // print the digest of input file
        String digests = printHexBinary(cmpdigest);
       
        // get an RSA cipher object 
        //  decipher the content of output file
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, keys.getPublic());
        byte[] cmpdecript = cipher.doFinal(plainBytesDec);
        String cript = printHexBinary(cmpdecript);
       
        if(digests.equals(cript)){
        	return false;
        }
        else{
        	return true;
        }
        
	}
      catch(BadPaddingException e){
    	  return true;
        }  
   }
	
	 public static KeyPair read(String publicKeyPath, String privateKeyPath) throws Exception {

	        byte[] pubEncoded = readFile(publicKeyPath);

	        X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(pubEncoded);
	        KeyFactory keyFacPub = KeyFactory.getInstance("RSA");
	        PublicKey pub = keyFacPub.generatePublic(pubSpec);
	        
	        byte[] privEncoded = readFile(privateKeyPath);

	        PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(privEncoded);
	        KeyFactory keyFacPriv = KeyFactory.getInstance("RSA");
	        PrivateKey priv = keyFacPriv.generatePrivate(privSpec);

	        KeyPair keys = new KeyPair(pub, priv);
	        return keys;
	    }
	  
	  private static byte[] readFile(String path) throws FileNotFoundException, IOException {
			FileInputStream fis = new FileInputStream(path);
	        byte[] content = new byte[fis.available()];
	        fis.read(content);
	        fis.close();
			return content;
		}
}