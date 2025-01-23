package uy.gub.imm.sae.login;

import org.jboss.security.SimplePrincipal;

public class SAEPrincipal extends SimplePrincipal {
	
	private static final long serialVersionUID = -568820422663686500L;
	
	String tenant = null;
	
	public SAEPrincipal(String codigoUsuario, String tenant) {
		super(codigoUsuario);
		this.tenant = tenant;
	}
	
	public String getTenant()  {
		return tenant;
	}


}
