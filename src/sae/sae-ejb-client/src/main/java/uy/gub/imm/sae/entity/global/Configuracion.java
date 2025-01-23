package uy.gub.imm.sae.entity.global;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ae_configuracion")
public class Configuracion implements Serializable {

	private static final long serialVersionUID = -5219038312184384285L;
        
        private Integer id;
	private String clave;
	private String valor;
        private Integer organismoId;

	public Configuracion() {
	}

	Configuracion(String clave) {
		this.clave = clave;
	}
	@Id
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
        
	@Column(nullable = false, length = 100)
	public String getClave() {
		return clave;
	}


	public void setClave(String clave) {
		this.clave = clave;
	}

	@Column(nullable = false, length = 4096)
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
        
        @Column (name = "org_id")
	public Integer getOrganismoId() {
		return organismoId;
	}
	public void setOrganismoId(Integer organismoId) {
		this.organismoId = organismoId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Configuracion tGlobal = (Configuracion) obj;
		return Objects.equals(clave, tGlobal.getClave());
	}

	@Override
	public int hashCode() {
		return Objects.hash(clave);
	}
}
