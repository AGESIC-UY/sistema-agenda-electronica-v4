/*
 * Sofis Solutions
 */
package uy.com.sofis.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

    private static final String patternMail = "(^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$)?";

    public static boolean validateEmail(final String mail) {
        Pattern pattern = Pattern.compile(patternMail);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }

    public static boolean validateRUT(String rut) {

        // largo y tipo de dato(enteros) 
        if (rut.length() != 12) {
            return false;
        } else {
            try {
                Long.parseLong(rut);
            } catch (NumberFormatException e) {
                return false;
            }
        }

        // Las dos primeras posiciones han de estar en el rango de 01 a 21 
        int valores = Integer.parseInt(rut.charAt(0) + "" + rut.charAt(1));
        if (valores < 1 || valores > 21) {
            return false;
        }

        // desde la 3ra a la 8va posición debe ser distinto de 000000 
        valores = Integer.parseInt(rut.charAt(2) + "" + rut.charAt(3)
                + "" + rut.charAt(4) + "" + rut.charAt(5) + "" + rut.charAt(6)
                + "" + rut.charAt(7));
        if (valores == 0) {
            return false;
        }

        // Las posiciones 9 y 10 deben ser 00 
        valores = Integer.parseInt(rut.charAt(8) + "" + rut.charAt(9));
        if (valores != 0) {
            return false;
        }

        int digVerificador = Integer.parseInt((rut.charAt(rut.length() - 1)) + "");
        int[] factores = new int[]{4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        int suma = 0;
        for (int i = 0; i < rut.length() - 1; i++) {
            int digito = Integer.parseInt(rut.charAt(i) + "");
            suma += digito * factores[i];
        }

        int resto = suma % 11;
        int checkdigit = 11 - resto;

        if (checkdigit == 11) {
            return (digVerificador == 0);
        } else {
            return (checkdigit == digVerificador);

        }
    }

    public static boolean validateCI(String ci) {

        if (ci.length() != 7 && ci.length() != 8) {
            return false;
        } else {
            try {
                Integer.parseInt(ci);
            } catch (NumberFormatException e) {
                return false;
            }
        }

        int digVerificador = Integer.parseInt((ci.charAt(ci.length() - 1)) + "");
        int[] factores;
        if (ci.length() == 7) { // CI viejas 
            factores = new int[]{9, 8, 7, 6, 3, 4};
        } else {
            factores = new int[]{2, 9, 8, 7, 6, 3, 4};
        }

        int suma = 0;
        for (int i = 0; i < ci.length() - 1; i++) {
            int digito = Integer.parseInt(ci.charAt(i) + "");
            suma += digito * factores[i];
        }

        int resto = suma % 10;
        int checkdigit = 10 - resto;

        if (checkdigit == 10) {
            return (digVerificador == 0);
        } else {
            return (checkdigit == digVerificador);
        }

    }
    
}
