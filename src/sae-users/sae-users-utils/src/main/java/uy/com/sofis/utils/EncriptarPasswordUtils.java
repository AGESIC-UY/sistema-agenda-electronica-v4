/*
 * Sofis Solutions
 */
package uy.com.sofis.utils;

import java.security.MessageDigest;
import org.picketbox.commons.cipher.Base64;



public class EncriptarPasswordUtils {

    
    public static String encriptarPassword(String newpassword) {
        try {
            
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(newpassword.getBytes());
            byte[] md5 = md.digest();
            String b64 = Base64.encodeBytes(md5);
            return b64;
        } catch (Exception ex) {
            return null;
        }
    }

  
    
}
