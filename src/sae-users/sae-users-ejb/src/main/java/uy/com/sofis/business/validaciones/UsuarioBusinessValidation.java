/*
 *  SAE-USERS-ADMIN
 *  Desarrollado por Sofis Solutions
 */
package uy.com.sofis.business.validaciones;

import org.apache.commons.lang3.StringUtils;
import uy.com.sofis.constantes.ConstantesErrores;
import uy.com.sofis.entities.Usuario;
import uy.com.sofis.exceptions.BusinessException;
import uy.com.sofis.utils.ValidationUtils;


/**
 *
 * @author Sofis Solutions
 */
public class UsuarioBusinessValidation  {
    
    
    /**
     * Valida que los datos del elemento sean correctos
     *
     * @param usu Usuario
     * @return Boolean
     * @throws BusinessException Devuelve los códigos de error de la validación
     */
    public static boolean validar(Usuario usu) throws BusinessException {
        boolean respuesta = true;
        BusinessException ge = new BusinessException();
        if (usu==null) {
            ge.addError(ConstantesErrores.ERROR_DATO_VACIO);
        } else {
           if(StringUtils.isBlank(usu.getCodigo())){
               ge.addError("codigo", ConstantesErrores.ERROR_CODIGO_VACIO);
           }
           else if (usu.getCodigo().length() > 25){
               ge.addError("codigo", ConstantesErrores.ERROR_LARGO_CODIGO_25);
           }
           
           if(StringUtils.isBlank(usu.getNombre())){
               ge.addError("nombre", ConstantesErrores.ERROR_NOM_APELLIDO_VACIO);
           }
           else if (usu.getNombre().length() > 100){
               ge.addError("nombre", ConstantesErrores.ERROR_NOM_APELLIDO_100);
           }
           
           if(StringUtils.isBlank(usu.getCorreoe())){
               ge.addError("correoe", ConstantesErrores.ERROR_EMAIL_VACIO);
           }
           else if(!ValidationUtils.validateEmail(usu.getCorreoe())){
               ge.addError("correoe", ConstantesErrores.ERROR_EMAIL_INVALIDO);
           }
           else if (usu.getCorreoe().length() > 100){
               ge.addError("correoe", ConstantesErrores.ERROR_LARGO_EMAIL_USUARIO);
           }

        }
        
        
        if (ge.getErrores().size() > 0) {
            throw ge;
        }
        return respuesta;
    }

    
}
