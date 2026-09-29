package uy.com.sofis.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name = "ae_empresas")
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;
        
        @Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="seq_empresa")
	@SequenceGenerator (name ="seq_empresa", initialValue = 1, sequenceName = "s_ae_empresa",allocationSize=1)
        @Basic(optional = false)
        @Column(name = "id", nullable = false)
	private Long id;
        
        @Column (nullable=false, length=100)
	private String nombre;
        
        @Column (nullable=false, length=25)
	private String datasource;
        
        @Column (name = "fecha_baja")
	@Temporal(TemporalType.TIMESTAMP)
	private Date   fechaBaja;
	
	//Datos del organismo
        @Column (name = "org_id")
	private Integer organismoId; //Si no es null significa que los datos se obtuvieron del servicio web
        
        @Column (name = "org_codigo")
	private String organismoCodigo;
        
        @Column (name = "org_nombre")
	private String organismoNombre;
	
	//Datos de la unidad ejecutora
        @Column (name = "unej_id")
	private Integer unidadEjecutoraId; //Si no es null significa que los datos se obtuvieron del servicio web
        
        @Column (name = "unej_codigo")
	private String unidadEjecutoraCodigo;
        
        @Column (name = "unej_nombre")
	private String unidadEjecutoraNombre;
	
	//OID del Organismo según el PEU
	//Para esto hay que buscar el organismo en http://unaoid.gub.uy/Organizacion.aspx
        @Column (name = "oid")
	private String oid;
	
	//Datos para el formulario de consentimiento
        @Column (name = "cc_finalidad")
	private String ccFinalidad;
        
        @Column (name = "cc_responsable")
	private String ccResponsable;
        
        @Column (name = "cc_direccion")
	private String ccDireccion;
	
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fin) {
		fechaBaja = fin;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDatasource() {
		return datasource;
	}
	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}
	
	
	public Integer getOrganismoId() {
		return organismoId;
	}
	public void setOrganismoId(Integer organismoId) {
		this.organismoId = organismoId;
	}
	
	public String getOrganismoCodigo() {
		return organismoCodigo;
	}
	public void setOrganismoCodigo(String organismoCodigo) {
		this.organismoCodigo = organismoCodigo;
	}
	
	public String getOrganismoNombre() {
		return organismoNombre;
	}
	public void setOrganismoNombre(String organismoNombre) {
		this.organismoNombre = organismoNombre;
	}
	
	
	public Integer getUnidadEjecutoraId() {
		return unidadEjecutoraId;
	}
	public void setUnidadEjecutoraId(Integer unidadEjecutoraId) {
		this.unidadEjecutoraId = unidadEjecutoraId;
	}
	
	public String getUnidadEjecutoraCodigo() {
		return unidadEjecutoraCodigo;
	}
	public void setUnidadEjecutoraCodigo(String unidadEjecutoraCodigo) {
		this.unidadEjecutoraCodigo = unidadEjecutoraCodigo;
	}
	
	public String getUnidadEjecutoraNombre() {
		return unidadEjecutoraNombre;
	}
	public void setUnidadEjecutoraNombre(String unidadEjecutoraNombre) {
		this.unidadEjecutoraNombre = unidadEjecutoraNombre;
	}
	
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	
	public String getCcFinalidad() {
		return ccFinalidad;
	}
	public void setCcFinalidad(String ccFinalidad) {
		this.ccFinalidad = ccFinalidad;
	}
	
	public String getCcResponsable() {
		return ccResponsable;
	}
	public void setCcResponsable(String ccResponsable) {
		this.ccResponsable = ccResponsable;
	}
	
	public String getCcDireccion() {
		return ccDireccion;
	}
	public void setCcDireccion(String ccDireccion) {
		this.ccDireccion = ccDireccion;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Empresa) {
			Empresa empresa = (Empresa) obj;
			if (empresa.getId().equals(this.getId())) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }
	
        
	
}
