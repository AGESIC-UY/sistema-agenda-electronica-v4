/*
 * 
 * 
 */
package uy.com.sofis.exceptions;

import java.util.ArrayList;
import javax.ejb.ApplicationException;


@ApplicationException(rollback = true)
public class BusinessException extends GeneralException {

    public BusinessException() {
    }

    public void addError(String campo, String error) {
        if (this.getErrores() == null) {
            this.setErrores(new ArrayList());
        }   
        if (campo != null && error != null) {
            this.getErrores().add(new BusinessError(error, campo));
        }
    }

    @Override
    public String getMessage() {
        return "Excepción de negocio";
    }
    
    
}
