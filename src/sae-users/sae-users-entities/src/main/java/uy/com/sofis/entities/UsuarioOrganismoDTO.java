/*
 *  SAE-USERS-ADMIN
 *  Desarrollado por Sofis Solutions
 */
package uy.com.sofis.entities;

import java.io.Serializable;



/**
 *
 * @author Sofis Solutions
 */
public class UsuarioOrganismoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    private Long id;
    
    private String codigo;
    
    private String nombre;
    
    private String correoe;
    
    private String password;
    
    private Integer orgId;
    
    private String codOrganismo;
        
    private String nombreOrganismo;
    
    private Boolean administrador;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoe() {
        return correoe;
    }

    public void setCorreoe(String correoe) {
        this.correoe = correoe;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getCodOrganismo() {
        return codOrganismo;
    }

    public void setCodOrganismo(String codOrganismo) {
        this.codOrganismo = codOrganismo;
    }

    public String getNombreOrganismo() {
        return nombreOrganismo;
    }

    public void setNombreOrganismo(String nombreOrganismo) {
        this.nombreOrganismo = nombreOrganismo;
    }

    public Boolean getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    
    
}
