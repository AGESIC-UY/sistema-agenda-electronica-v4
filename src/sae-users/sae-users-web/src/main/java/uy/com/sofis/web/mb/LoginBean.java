/*
 *  
 *  Sistema de GestiÃ³n
 *  Desarrollado por Sofis Solutions
 */
package uy.com.sofis.web.mb;

import uy.com.sofis.web.presentacion.mensajes.Mensajes;
import uy.com.sofis.web.utils.JSFUtils;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import uy.com.sofis.constantes.ConstantesComponentesId;

/**
 *
 * @author Sofis Solutions
 */
@ManagedBean(name = "loginBean")
public class LoginBean implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(LoginBean.class.getName());

    private String codigoUsuario;
    private String contrasenia;
    private String email;

    /**
     * Creates a new instance of LoginManagedBean
     */
    public LoginBean() {
    }

    @PostConstruct
    public void init() {
    }

    public String ingresar() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
            request.login(codigoUsuario, contrasenia);  
            return "IR_A_INICIO";
           
        } catch (Exception ex) {
            LOGGER.log(Level.INFO, ex.getMessage() + ". " + "Usuario: " + codigoUsuario + ".", "");
            JSFUtils.mostrarMensaje(ConstantesComponentesId.ID_MSG_TEMPLATE, Mensajes.obtenerMensaje(Mensajes.USUARIO_CREDENCIALES_INVALIDAS), FacesMessage.SEVERITY_ERROR);
        }
        return null;
    }

  
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
}
