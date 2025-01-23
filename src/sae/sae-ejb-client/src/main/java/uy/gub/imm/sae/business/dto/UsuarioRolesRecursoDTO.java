package uy.gub.imm.sae.business.dto;

public class UsuarioRolesRecursoDTO {

	private String usuario;

	private String nombreRecurso;

	private String roles;

	public UsuarioRolesRecursoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsuarioRolesRecursoDTO(String usuario, String nombreRecurso, String roles) {
		super();
		this.usuario = usuario;
		this.nombreRecurso = nombreRecurso;
		this.roles = roles;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombreRecurso() {
		return nombreRecurso;
	}

	public void setNombreRecurso(String nombreRecurso) {
		this.nombreRecurso = nombreRecurso;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
}
