/*
 *  SAE-USERS-ADMIN
 *  Desarrollado por Sofis Solutions
 */
package uy.com.sofis.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


/**
 *
 * @author Sofis Solutions
 */
@Entity
@Table(name = "ae_usuarios")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    @SequenceGenerator(name = "seq_usuario", initialValue = 1, sequenceName = "s_ae_usuario", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(nullable = false, length = 25)
    private String codigo;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(nullable = true, length = 100)
    private String correoe;
    
   
    
    @Column(name = "fecha_baja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;
    
    
    private String password;
    
    
    @ManyToMany
    @JoinTable(name = "ae_rel_usuarios_organismos", joinColumns = {
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")}, inverseJoinColumns = {
    @JoinColumn(name = "org_id", referencedColumnName = "id")})
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Organismo> organismos;
    
    
    @Transient
    private Organismo organismoOld;
    
    @Transient
    private Organismo organismo;
    
    @Transient
    private Boolean isAdministrador;
    
    
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

    public String getCorreoe() {
        return correoe;
    }

    public void setCorreoe(String correoe) {
        this.correoe = correoe;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fin) {
        fechaBaja = fin;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Organismo getOrganismo() {
        return organismo;
    }

    public void setOrganismo(Organismo organismo) {
        this.organismo = organismo;
    }

    public Boolean getIsAdministrador() {
        return isAdministrador;
    }

    public void setIsAdministrador(Boolean isAdministrador) {
        this.isAdministrador = isAdministrador;
    }

    public Set<Organismo> getOrganismos() {
        return organismos;
    }

    public void setOrganismos(Set<Organismo> organismos) {
        this.organismos = organismos;
    }

    public Organismo getOrganismoOld() {
        return organismoOld;
    }

    public void setOrganismoOld(Organismo organismoOld) {
        this.organismoOld = organismoOld;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    

   
    
}
