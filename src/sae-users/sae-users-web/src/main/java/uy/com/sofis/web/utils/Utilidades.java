package uy.com.sofis.web.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.picketbox.commons.cipher.Base64;

public class Utilidades {
    
    private static final Logger LOGGER = Logger.getLogger(Utilidades.class.getName());
    
	//admin = ISMvKXpXpadDiUoOSoAfww==
	public static String encriptarPassword(String pwd) {
		if(pwd == null) {
			return null;
		}
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(pwd.getBytes());
			byte[] md5 = md.digest();
			String b64 = Base64.encodeBytes(md5);
			return b64;
		}catch(Exception ex) {
                    LOGGER.log(Level.SEVERE,ex.getMessage(),ex);
			return null;
		}
	}
	
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		LOGGER.log(Level.INFO,"admin -> ["+encriptarPassword("admin")+"]");
	}

	
	
}
