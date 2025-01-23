package uy.com.sofis.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class TechnicalException extends GeneralException{
    
    public TechnicalException() {
        
    }
    
    public TechnicalException(Exception ex) {
        super(ex);
    }
    
    public TechnicalException(Exception ex, String mensaje) {
        super(mensaje, ex);
        super.addError(mensaje);
    }
    
    public TechnicalException(String mensaje) {
        super(mensaje);
        super.addError(mensaje);
    }
}
