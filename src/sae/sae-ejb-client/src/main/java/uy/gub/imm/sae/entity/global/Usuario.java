package uy.gub.imm.sae.entity.global;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "ae_usuarios")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 7173791316892020470L;

    private Integer id;
    private String codigo;
    private String nombre;
    private String correoe;
    private List<Empresa> empresas;
    private Date fechaBaja;
    private String password;
    private List<Organismo> organismos;
    private Timestamp ultimoLogin;

    public Usuario() {
    }

    Usuario(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    @SequenceGenerator(name = "seq_usuario", initialValue = 1, sequenceName = "s_ae_usuario", allocationSize = 1)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(nullable = false, length = 25)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = StringUtils.strip(codigo);
    }

    public String getCorreoe() {
        return correoe;
    }

    public void setCorreoe(String correoe) {
        this.correoe = correoe;
    }

    @Column(name = "fecha_baja")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fin) {
        fechaBaja = fin;
    }

    @Column(nullable = false, length = 100)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ae_rel_usuarios_empresas", joinColumns = {
        @JoinColumn(name = "usuario_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "empresa_id", referencedColumnName = "id")})
    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "ultimo_login")
    public Timestamp getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(Timestamp ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }
    @ManyToMany()
    @JoinTable(name = "ae_rel_usuarios_organismos", joinColumns = {
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")}, inverseJoinColumns = {
    @JoinColumn(name = "org_id", referencedColumnName = "id")})
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Organismo> getOrganismos() {
        return organismos;
    }

    public void setOrganismos(List<Organismo> organismos) {
        this.organismos = organismos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
