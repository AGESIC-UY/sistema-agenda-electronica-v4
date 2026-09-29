/*
 * Nombre del cliente
 * Sistema de Gestión
 * Desarrollado por Sofis Solutions
 */
package uy.com.sofis.business.validaciones;

import org.apache.commons.lang3.StringUtils;
import uy.com.sofis.constantes.ConstantesErrores;
import uy.com.sofis.exceptions.BusinessException;
import uy.com.sofis.entities.Ayuda;
 

/**
 *
 * @author Usuario
 */  
public class AyudaBusinessValidation  {
    
    /**
     * Valida que los datos del elemento sean correctos
     * 
     * @param cgo
     * @return
     * @throws BusinessException Devuelve los códigos de error de la validación
     */
    public static boolean validar(Ayuda cgo) throws BusinessException {
        boolean respuesta = true;
        BusinessException ge = new BusinessException();
        if (cgo==null) {
            ge.addError(ConstantesErrores.ERROR_DATO_VACIO);
        } else {
            if (StringUtils.isBlank(cgo.getAyuCodigo())) {
                ge.addError(ConstantesErrores.ERROR_CODIGO_VACIO);
            } else if (cgo.getAyuCodigo().length() > 45){
                ge.addError(ConstantesErrores.ERROR_LARGO_CODIGO_45);
            }
            if (cgo.getAyuExplicacion() == null) {
                ge.addError(ConstantesErrores.ERROR_EXPLICACION_VACIA);
            } else if (cgo.getAyuExplicacion().length() > 65535){
                ge.addError(ConstantesErrores.ERROR_LARGO_EXPLICACION_AYUDA);
            }
        }
        if (ge.getErrores().size()>0) {
            throw ge;
        }
        return respuesta;
    }
    
    /**
     * Compara dos elementos del tipo. 
     * Indica si los elementos contienen la misma información.
     * 
     * @param c1
     * @param c2
     * @return 
     */
    public static boolean compararParaGrabar(Ayuda c1, Ayuda c2) { 
        boolean respuesta = true;
        if (c1!=null) {
            if (c2!=null) {
                respuesta = StringUtils.equals(c1.getAyuCodigo(), c2.getAyuCodigo());
                respuesta = respuesta && StringUtils.equals(c1.getAyuExplicacion(), c2.getAyuExplicacion());
            }  
        } else {
            respuesta = c2==null;
        }
        return respuesta;
    }    
}
