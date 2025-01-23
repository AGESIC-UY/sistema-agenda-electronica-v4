package uy.com.sofis.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.envers.Audited;
import uy.com.sofis.constantes.Constantes;
import uy.com.sofis.persistence.annotations.AtributoCodigo;
import uy.com.sofis.persistence.annotations.AtributoUltimaModificacion;
import uy.com.sofis.persistence.annotations.AtributoUltimoUsuario;
import uy.com.sofis.utils.EntidadListener;

@Entity
@XmlRootElement
@Audited
@EntityListeners({EntidadListener.class})
@Table(name = "lb_configuraciones", schema = Constantes.LB_SCHEMA)
public class Configuracion implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cnf_id")
    private Long cnfId;

    @Column(name = "cnf_codigo")
    @AtributoCodigo
    private String cnfCodigo;

    @Column(name = "cnf_nombre")
    private String cnfNombre;

    @Column(name = "cnf_valor")
    private String cnfValor;

    @Column(name = "cnf_ult_mod_fecha")
    @AtributoUltimaModificacion
    private LocalDateTime cnfUltModFecha;

    @Size(max = 45)
    @Column(name = "cnf_ult_mod_usuario", length = 45)
    @AtributoUltimoUsuario
    private String cnfUltModUsuario;
    
    @Column(name = "cnf_es_editor")
    private Boolean cnfEsEditor;

    @Column(name = "cnf_version")
    @Version
    private Integer cnfVersion;

    public Configuracion() {
        this.cnfEsEditor = Boolean.FALSE;
    }

    public Long getCnfId() {
        return cnfId;
    }

    public void setCnfId(Long cnfId) {
        this.cnfId = cnfId;
    }

    public Integer getCnfVersion() {
        return cnfVersion;
    }

    public void setCnfVersion(Integer cnfVersion) {
        this.cnfVersion = cnfVersion;
    }

    public String getCnfCodigo() {
        return cnfCodigo;
    }

    public void setCnfCodigo(String cnfCodigo) {
        this.cnfCodigo = cnfCodigo;
    }

    public String getCnfNombre() {
        return cnfNombre;
    }

    public void setCnfNombre(String cnfNombre) {
        this.cnfNombre = cnfNombre;
    }

    public LocalDateTime getCnfUltModFecha() {
        return cnfUltModFecha;
    }

    public void setCnfUltModFecha(LocalDateTime cnfUltModFecha) {
        this.cnfUltModFecha = cnfUltModFecha;
    }

    public String getCnfUltModUsuario() {
        return cnfUltModUsuario;
    }

    public void setCnfUltModUsuario(String cnfUltModUsuario) {
        this.cnfUltModUsuario = cnfUltModUsuario;
    }

    public String getCnfValor() {
        return cnfValor;
    }

    public void setCnfValor(String cnfValor) {
        this.cnfValor = cnfValor;
    }

    public Boolean getCnfEsEditor() {
        return cnfEsEditor;
    }

    public void setCnfEsEditor(Boolean cnfEsEditor) {
        this.cnfEsEditor = cnfEsEditor;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.cnfId);
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
        final Configuracion other = (Configuracion) obj;
        if (!Objects.equals(this.cnfId, other.cnfId)) {
            return false;
        }
        return true;
    }

}
