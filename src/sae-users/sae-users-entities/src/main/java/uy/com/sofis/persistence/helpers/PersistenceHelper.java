/*
 * Nombre del cliente
 * Sistema de Gestión
 * Desarrollado por Sofis Solutions
 */
package uy.com.sofis.persistence.helpers;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.exception.ConstraintViolationException;


public class PersistenceHelper {

    public static Boolean isConstraintViolation(Exception ex) {

        Throwable t = ex.getCause();
        while ((t != null) && !(t instanceof ConstraintViolationException) && !(t instanceof NonUniqueObjectException)) {
            t = t.getCause();
        }
        if (t instanceof ConstraintViolationException || t instanceof NonUniqueObjectException) {
            return true;
        }

        return false;
    }

}
