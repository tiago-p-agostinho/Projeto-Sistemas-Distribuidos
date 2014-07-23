package largaCaixa.cli;
import javax.crypto.Cipher;
import java.io.*;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.security.MessageDigest;

public class Crypto{
	public static Object cipher(String privateKeyPath, String publicKeyPath, Object input) throws Exception {

		final byte[] plainBytes = (input.toString()).getBytes();
		
       // generate an RSA key
	   KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
       keyGen.initialize(1024);
       KeyPair keys = read(publicKeyPath,privateKeyPath);
       
       // get a message digest object using the MD5 algorithm
       MessageDigest messageDigest = MessageDigest.getInstance("MD5");
       messageDigest.update(plainBytes);
       byte[] digest = messageDigest.digest();

       // get an RSA cipher object and print the provider
       Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
       cipher.init(Cipher.ENCRYPT_MODE, keys.getPrivate());
       byte[] cipherBytes = cipher.doFinal(digest);
       
       //convert byte array to object
       Object oj = new byte[cipherBytes.length];
       oj = cipherBytes; 
       return oj;
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
	  
	  private static byte[] readFile(String path)
				throws FileNotFoundException, IOException {
			FileInputStream fis = new FileInputStream(path);
	        byte[] content = new byte[fis.available()];
	        fis.read(content);
	        fis.close();
			return content;
		}
	}