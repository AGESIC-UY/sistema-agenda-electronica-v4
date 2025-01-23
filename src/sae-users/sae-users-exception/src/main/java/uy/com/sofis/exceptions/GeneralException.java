/*
 * 
 * 
 */
package uy.com.sofis.exceptions;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.ApplicationException;

/**
 *
 * @author Usuario
 */
@ApplicationException(rollback = true)
public class GeneralException extends RuntimeException {

    private List<BusinessError> errores = new ArrayList();

    public GeneralException() {
        super("GeneralException");
    }

    public GeneralException(String msg) {
        super(msg);
    }
    
    public GeneralException(Exception ex){
        super(ex);
    }
    
    public GeneralException(String mensaje, Exception ex){
        super(mensaje, ex);
    }

    public List<BusinessError> getErrores() {
        return errores;
    }

    public void setErrores(List<BusinessError> errores) {
        this.errores = errores;
    }

    
    public void addError(String error) {
        BusinessError e = new BusinessError(error);
        if (this.errores == null) {
            this.errores = new ArrayList();
        }
        this.errores.add(e);
    }


}
