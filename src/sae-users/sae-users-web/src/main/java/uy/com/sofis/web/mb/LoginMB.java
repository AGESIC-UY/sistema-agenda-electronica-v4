/*
 * Sofis Solutions
 */
package uy.com.sofis.web.mb;

/**
 *
 * @author USUARIO
 */
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import uy.com.sofis.web.utils.JSFUtils;

/**
 * @author Sofis Solutions
 */
@ManagedBean(name = "LoginMB")
@ViewScoped
public class LoginMB implements Serializable {

    public LoginMB() {

    }

    /**
     * Método que se ejecuta antes de generar la respuesta. Es utilizado para
     * mostrar un mensaje de error si la validación de credenciales de JAAS es
     * incorrecta
     *
     * @param event
     */
    public void beforePhase(PhaseEvent event) {
        if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            String e = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("e");
            if (e != null) {
                JSFUtils.agregarError(null, "Credenciales incorrectas", "Las credenciales que has ingresado no son correctas");
            }
        }
    }

}