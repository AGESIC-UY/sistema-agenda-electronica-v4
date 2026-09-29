/*
 *  SIGES
 *  Desarrollado por Sofis Solutions
 */
package uy.com.sofis.utils;


import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import uy.com.sofis.persistence.annotations.AtributoNormalizable;
import uy.com.sofis.persistence.annotations.AtributoUltimaModificacion;
import uy.com.sofis.persistence.annotations.AtributoUltimoUsuario;

public class EntidadListener {

    private static final Logger LOGGER = Logger.getLogger(EntidadListener.class.getName());
    
    private String callerPrincipal;

    @PrePersist
    @PreUpdate
    public void preSave(Object o) throws Exception {
        
        callerPrincipal =  FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

        for (Field field : ReflectionUtils.getAllFields(o.getClass())) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(AtributoUltimoUsuario.class)) {
                try {
                    field.set(o, callerPrincipal);
                } catch (Exception ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
            }
            if (field.isAnnotationPresent(AtributoUltimaModificacion.class)) {
                try {
                    field.set(o, LocalDateTime.now());
                } catch (Exception ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
            }
            if (field.isAnnotationPresent(AtributoNormalizable.class)) {
                try {
                    String s = (String) field.get(o);
                    if (s != null) {
                        field.set(o, SofisStringUtils.normalizarString(s));
                    }
                } catch (Exception ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
