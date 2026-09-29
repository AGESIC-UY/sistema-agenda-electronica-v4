/*
 *  Nombre del cliente
 *  Sistema de Gestión
 *  Desarrollado por Sofis Solutions
 */
package uy.com.sofis.web.mb;

import java.io.Serializable;
import java.security.Principal;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Usuario
 */
@Named
@SessionScoped
public class InicioManagedBean implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(InicioManagedBean.class.getName());
    
    private String nombreUsuario = "";
    private Locale local;
    private Set<String> operaciones = new HashSet<>();

    /**
     * Creates a new instance of InicioManagedBean
     */
    public InicioManagedBean() {
    }

    @PostConstruct
    public void init() {
        try {
            local = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if(principal!=null){
                String codigoUsuario = principal.getName();
                nombreUsuario = codigoUsuario;
            }
            

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Set<String> getOperaciones() {
        return operaciones;
    }

}
