/*
 *  SAE-USERS-ADMIN
 *  Desarrollado por Sofis Solutions
 */
package uy.com.sofis.business.validaciones;

import uy.com.sofis.constantes.ConstantesErrores;
import uy.com.sofis.entities.Organismo;
import uy.com.sofis.exceptions.BusinessException;


/**
 *
 * @author Sofis Solutions
 */
public class OrganismoBusinessValidation  {

    /**
     * Valida que los datos del elemento sean correctos
     *
     * @param usu Usuario
     * @return Boolean
     * @throws BusinessException Devuelve los códigos de error de la validación
     */
    public static boolean validar(Organismo org) throws BusinessException {
        boolean respuesta = true;
        BusinessException ge = new BusinessException();
        if (org==null) {
            ge.addError(ConstantesErrores.ERROR_DATO_VACIO);
        } else {
           
        }
        if (ge.getErrores().size() > 0) {
            throw ge;
        }
        return respuesta;
    }

    
}
