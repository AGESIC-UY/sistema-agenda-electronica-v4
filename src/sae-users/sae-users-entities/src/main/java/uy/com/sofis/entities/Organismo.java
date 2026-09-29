package uy.com.sofis.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ae_organismos")
public class Organismo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @Column(nullable = false, length = 25)
    private String codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    public Organismo() {
    }
    
    
    
    
    public Organismo(Integer id, String codigo, String nombre) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
    }
        

    //No es autogenerada, la entidad se obtiene de un servicio web y ya viene con id
    public Integer getId() {
            return id;
    }

    public void setId(Integer id) {
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

    public String getCodigoNombre() {
        return codigo.concat(" - ").concat(nombre);
    }

    @Override
    public boolean equals(Object obj) {

            if (obj instanceof Organismo) {
                    Organismo organismo = (Organismo) obj;
                    if (organismo.getId().equals(this.getId())) {
                            return true;
                    } else {
                            return false;
                    }
            } else {
                    return false;
            }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }
        
        

}
