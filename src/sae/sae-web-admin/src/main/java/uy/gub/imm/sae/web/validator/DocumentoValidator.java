package uy.gub.imm.sae.web.validator;

import org.apache.commons.lang3.StringUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class DocumentoValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        if(o!=null){
            String valor = o.toString();
            if(StringUtils.containsAny(valor, " ")) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El documento de identidad no debe contener espacios.", null);
                throw new ValidatorException(message);
            }
        }
    }
}
