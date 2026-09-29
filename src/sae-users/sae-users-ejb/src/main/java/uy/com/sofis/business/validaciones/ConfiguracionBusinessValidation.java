/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.sofis.business.validaciones;

import org.apache.commons.lang3.StringUtils;
import uy.com.sofis.entities.Configuracion;
import uy.com.sofis.constantes.ConstantesErrores;
import uy.com.sofis.exceptions.BusinessException;

/**
 *
 * @author USUARIO
 */
public class ConfiguracionBusinessValidation {
    

/**
     * Valida que los datos del elemento sean correctos
     *
     * @param con SgConfiguracion
     * @return Boolean
     * @throws BusinessException Devuelve los códigos de error de la validación
     */
    public static boolean validar(Configuracion con) throws BusinessException {
        boolean respuesta = true;
        BusinessException ge = new BusinessException();
        if (con==null) {
            ge.addError(ConstantesErrores.ERROR_DATO_VACIO);
        } else {
            if (StringUtils.isBlank(con.getCnfCodigo())) {
                ge.addError(ConstantesErrores.ERROR_CODIGO_VACIO);
            } else if (con.getCnfCodigo().length() > 45){
                ge.addError(ConstantesErrores.ERROR_LARGO_CODIGO_45);
            }
            if (StringUtils.isBlank(con.getCnfNombre())) {
                ge.addError(ConstantesErrores.ERROR_NOMBRE_VACIO);
            } else if (con.getCnfNombre().length() > 255){
                ge.addError(ConstantesErrores.ERROR_LARGO_NOMBRE_255);
            }
        }
        if (ge.getErrores().size() > 0) {
            throw ge;
        }
        return respuesta;
    }

    /**
     * Compara dos elementos del tipo.
     * Indica si los elementos contienen la misma información.
     *
     * @param c1 SgConfiguracion
     * @param c2 SgConfiguracion
     * @return Boolean
     */
    public static boolean compararParaGrabar(Configuracion c1, Configuracion c2) {
        boolean respuesta = true;
        if (c1!=null) {
            if (c2!=null) {
                respuesta = StringUtils.equals(c1.getCnfCodigo(), c2.getCnfCodigo());
                respuesta = respuesta && StringUtils.equals(c1.getCnfNombre(), c2.getCnfNombre());
                respuesta = respuesta && StringUtils.equals(c1.getCnfValor(), c2.getCnfValor());
            }
        } else {
            respuesta = c2==null;
        }
        return respuesta;
    }

}