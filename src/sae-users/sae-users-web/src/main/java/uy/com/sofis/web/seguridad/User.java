/*
 *  Nombre del cliente
 *  Sistema de Gestión
 *  Desarrollado por Sofis Solutions
 */
package uy.com.sofis.web.seguridad;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Usuario
 */
public class User {
    
    private static final String USER = "user";

    private static ResourceBundle bundle = ResourceBundle.getBundle(USER, new Locale("es"));

    public static String getValue(String key) {
        if (bundle.containsKey(key)) {
            return bundle.getString(key);
        } else {
            return "?" + key + "?";
        }
    }

    public static String getValue(String key, Locale loc) {
            if (bundle.containsKey(key)) {
                return bundle.getString(key);
            } else {
                return "?" + key + "?";
            }
    }

    public static boolean containsKey(String key) {
        return bundle.containsKey(key);
    }
}
