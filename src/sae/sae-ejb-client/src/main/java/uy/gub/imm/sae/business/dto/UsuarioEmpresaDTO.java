package uy.gub.imm.sae.business.dto;

public class UsuarioEmpresaDTO {

    private Integer id;

    private String codigo;

    private String nombre;

    private String correo;

    private String rolesEmpresa;

    public UsuarioEmpresaDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public UsuarioEmpresaDTO(Integer id, String codigo, String nombre, String correo, String rolesEmpresa) {
        super();
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.correo = correo;
        this.rolesEmpresa = rolesEmpresa;
    }

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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRolesEmpresa() {
        return rolesEmpresa;
    }

    public void setRolesEmpresa(String rolesEmpresa) {
        this.rolesEmpresa = rolesEmpresa;
    }

}
