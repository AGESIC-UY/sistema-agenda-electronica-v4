/*
 *  Nombre del cliente
 *  Sistema de Gestión
 *  Desarrollado por Sofis Solutions
 */
package uy.com.sofis.entities;

import uy.com.sofis.persistence.annotations.AtributoCodigo;
import uy.com.sofis.persistence.annotations.AtributoNombre;
import uy.com.sofis.persistence.annotations.AtributoNormalizable;
import uy.com.sofis.persistence.annotations.AtributoUltimaModificacion;
import uy.com.sofis.persistence.annotations.AtributoUltimoUsuario;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.envers.Audited;
import uy.com.sofis.constantes.Constantes;
import uy.com.sofis.persistence.annotations.AtributoHabilitado;
import uy.com.sofis.utils.EntidadListener;
import uy.com.sofis.utils.SofisStringUtils;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "lb_ayudas", schema = Constantes.LB_SCHEMA)
@XmlRootElement
@Audited
@EntityListeners({EntidadListener.class})
public class Ayuda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ayu_id", nullable = false)
    private Long ayuId;

    @Column(name = "ayu_codigo", length = 45)
    private String ayuCodigo;

    @Column(name = "ayu_nombre", length = 45)
    private String ayuNombre;

    @Column(name = "ayu_codigo_busqueda", length = 45)
    @AtributoCodigo
    private String ayuCodigoBusqueda;

    @Column(name = "ayu_nombre_busqueda", length = 45)
    @AtributoNombre
    private String ayuNombreBusqueda;

    @Column(name = "ayu_explicacion", length = 4000)
    @AtributoNormalizable
    private String ayuExplicacion;

    @Column(name = "ayu_resaltada")
    private Boolean ayuResaltada;

    @Column(name = "ayu_habilitado")
    @AtributoHabilitado
    private Boolean ayuHabilitado;

    @Column(name = "ayu_ult_mod_fecha")
    @AtributoUltimaModificacion
    private LocalDateTime ayuUltModFecha;

    @Column(name = "ayu_ult_mod_usu", length = 45)
    @AtributoUltimoUsuario
    private String ayuUltModUsuario;

    @Column(name = "ayu_version")
    @Version
    private Integer ayuVersion;

    public Ayuda() {
        this.ayuHabilitado = Boolean.TRUE;
    }

    @PrePersist
    @PreUpdate
    public void preSave() {
        this.ayuNombreBusqueda = SofisStringUtils.normalizarParaBusqueda(this.ayuNombre);
        this.ayuCodigoBusqueda = SofisStringUtils.normalizarParaBusqueda(this.ayuCodigo);
    }

    public Ayuda(Long ayuId) {
        this.ayuId = ayuId;
    }

    public Long getAyuId() {
        return ayuId;
    }

    public void setAyuId(Long ayuId) {
        this.ayuId = ayuId;
    }

    public String getAyuCodigo() {
        return ayuCodigo;
    }

    public void setAyuCodigo(String ayuCodigo) {
        this.ayuCodigo = ayuCodigo;
    }

    public String getAyuExplicacion() {
        return ayuExplicacion;
    }

    public void setAyuExplicacion(String ayuExplicacion) {
        this.ayuExplicacion = ayuExplicacion;
    }

    public String getAyuUltModUsuario() {
        return ayuUltModUsuario;
    }

    public void setAyuUltModUsuario(String ayuUltModUsuario) {
        this.ayuUltModUsuario = ayuUltModUsuario;
    }

    public Boolean getAyuResaltada() {
        return ayuResaltada;
    }

    public void setAyuResaltada(Boolean ayuResaltada) {
        this.ayuResaltada = ayuResaltada;
    }

    public Boolean getAyuHabilitado() {
        return ayuHabilitado;
    }

    public void setAyuHabilitado(Boolean ayuHabilitado) {
        this.ayuHabilitado = ayuHabilitado;
    }

    public LocalDateTime getAyuUltModFecha() {
        return ayuUltModFecha;
    }

    public void setAyuUltModFecha(LocalDateTime ayuUltModFecha) {
        this.ayuUltModFecha = ayuUltModFecha;
    }

    public Integer getAyuVersion() {
        return ayuVersion;
    }

    public void setAyuVersion(Integer ayuVersion) {
        this.ayuVersion = ayuVersion;
    }

    public String getAyuNombre() {
        return ayuNombre;
    }

    public void setAyuNombre(String ayuNombre) {
        this.ayuNombre = ayuNombre;
    }

    public String getAyuCodigoBusqueda() {
        return ayuCodigoBusqueda;
    }

    public void setAyuCodigoBusqueda(String ayuCodigoBusqueda) {
        this.ayuCodigoBusqueda = ayuCodigoBusqueda;
    }

    public String getAyuNombreBusqueda() {
        return ayuNombreBusqueda;
    }

    public void setAyuNombreBusqueda(String ayuNombreBusqueda) {
        this.ayuNombreBusqueda = ayuNombreBusqueda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ayuId != null ? ayuId.hashCode() : 0);
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
        final Ayuda other = (Ayuda) obj;
        if (!Objects.equals(this.ayuId, other.ayuId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.persistence.entities.Ayuda[ ayuId=" + ayuId + " ]";
    }

}
