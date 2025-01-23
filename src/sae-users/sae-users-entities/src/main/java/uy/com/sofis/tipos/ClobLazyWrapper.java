/*
 * Sofis Solutions
 */
package uy.com.sofis.tipos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.envers.Audited;
import uy.com.sofis.persistence.annotations.AtributoUltimaModificacion;
import uy.com.sofis.persistence.annotations.AtributoUltimaOrigen;
import uy.com.sofis.persistence.annotations.AtributoUltimoUsuario;
import uy.com.sofis.utils.EntidadListener;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "sd_clob_lazy_wrapper")
@XmlRootElement
@EntityListeners({EntidadListener.class})
@Audited
public class ClobLazyWrapper implements Serializable {
    
    private static final Logger LOGGER = Logger.getLogger(ClobLazyWrapper.class.getName());
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_clob_lazyw_seq")
    @SequenceGenerator(name = "id_clob_lazyw_seq", sequenceName = "clob_lazyw_seq", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "clw_id", nullable = false)
    private Integer clwId;
    
    @Lob
    @Column(name = "clw_valor")
    private String clwValor;
    
    @Column(name = "clw_ult_mod_fecha")
    @AtributoUltimaModificacion
    private LocalDateTime clwUltModFecha;
    @Size(max = 45)
    @Column(name = "clw_ult_mod_usuario", length = 45)
    @AtributoUltimoUsuario
    private String clwUltModUsuario;
    @Size(max = 45)
    @Column(name = "clw_ult_mod_origen", length = 45)
    @AtributoUltimaOrigen
    private String clwUltModOrigen;
    @Column(name = "clw_version")
    @Version
    private Integer clwVersion;

    public Integer getClwId() {
        return clwId;
    }

    public void setClwId(Integer clwId) {
        this.clwId = clwId;
    }

    public String getClwValor() {
        return clwValor;
    }

    public void setClwValor(String clwValor) {
        this.clwValor = clwValor;
    }

    public LocalDateTime getClwUltModFecha() {
        return clwUltModFecha;
    }

    public void setClwUltModFecha(LocalDateTime clwUltModFecha) {
        this.clwUltModFecha = clwUltModFecha;
    }

    public String getClwUltModUsuario() {
        return clwUltModUsuario;
    }

    public void setClwUltModUsuario(String clwUltModUsuario) {
        this.clwUltModUsuario = clwUltModUsuario;
    }

    public String getClwUltModOrigen() {
        return clwUltModOrigen;
    }

    public void setClwUltModOrigen(String clwUltModOrigen) {
        this.clwUltModOrigen = clwUltModOrigen;
    }

    

    public Integer getClwVersion() {
        return clwVersion;
    }

    public void setClwVersion(Integer clwVersion) {
        this.clwVersion = clwVersion;
    }
    
    
    
    
}
